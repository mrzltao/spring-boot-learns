package utils;

import com.alibaba.fastjson.JSONObject;
import http.SSLClient;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @title: HttpsUtils
 * @Description http管理工具
 * @Author Ltter
 * @Date: 2021/12/3 10:00
 * @Version 1.0
 */
public class HttpsUtils {
    private final static String HTTPS="https";

    private static RequestConfig requestConfig;

    private static Logger logger = Logger.getLogger("HttpsUtils.class");

    static {
        RequestConfig.Builder configBuilder = RequestConfig.custom();
        // 设置连接超时
        configBuilder.setConnectTimeout(3000);
        // 设置读取超时
        configBuilder.setSocketTimeout(120000);
        // 设置从连接池获取连接实例的超时
        configBuilder.setConnectionRequestTimeout(3000);

        requestConfig = configBuilder.build();

    }

    /**
     * 发送 GET 请求，不带输入数据
     *
     * @param url 请求地址
     * @param headers 请求头参数
     * @return json
     */
    public static JSONObject doGet(String url, Map<String,String> headers) {
        return doGet(url, new HashMap<>(0),headers);
    }

    /**
     * 发送 GET 请求，K-V形式
     *
     * @param url 请求地址
     * @param params 参数
     * @param headers 请求头参数
     * @return json
     */
    public static JSONObject doGet(String url, Map<String, Object> params,Map<String,String> headers) {
        String apiUrl = url;
        StringBuffer param = new StringBuffer();
        int i = 0;
        for (String key : params.keySet()) {
            if (i == 0) {
                param.append("?");
            } else{
                param.append("&");
            }
            param.append(key).append("=").append(params.get(key));
            i++;
        }
        apiUrl += param;
        String result = null;
        HttpClient httpClient = null;
        try {
            httpClient = new SSLClient();
            HttpGet httpGet = new HttpGet(apiUrl);
            httpGet.setConfig(requestConfig);
            for(Map.Entry<String, String> entry : headers.entrySet()){
                httpGet.addHeader(entry.getKey(),entry.getValue());
            }
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream inputStream = entity.getContent();
                result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "doGet Map error",  e);
        }
        return JSONObject.parseObject(result);
    }

    /**
     * 发送 POST 请求，不带输入数据
     * @param url 请求地址
     * @param headers 请求头参数
     * @return json
     */
    public static JSONObject doPost(String url,Map<String,String> headers) {
        return doPost(url, new HashMap<>(0),headers);
    }

    /**
     * 发送 POST 请求，K-V形式
     * @param url 请求地址
     * @param params 参数
     * @param headers 请求头参数
     * @return json
     * @desc 统一认证_获取鉴权Access Token
     */
    public static JSONObject doPost(String url, Map<String, Object> params,Map<String,String> headers) {
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String httpStr = null;
        HttpResponse response = null;

        try {
            httpClient = new SSLClient();
            httpPost = new HttpPost(url);
            for(Map.Entry<String, String> entry : headers.entrySet()){
                httpPost.addHeader(entry.getKey(),entry.getValue());
            }

            httpPost.setConfig(requestConfig);

            List<NameValuePair> pairList = new ArrayList<>(params.size());
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
                pairList.add(pair);
            }
            httpPost.setEntity(new UrlEncodedFormEntity(pairList, StandardCharsets.UTF_8));

            response = httpClient.execute(httpPost);
            if(response != null){
                HttpEntity httpEntity = response.getEntity();
                if(httpEntity != null){
                    httpStr = EntityUtils.toString(httpEntity,StandardCharsets.UTF_8);
                    //httpStr = EntityUtils.toString(httpEntity, "UTF-8");
                }
            }

        } catch (Exception e) {
            logger.log(Level.WARNING," doPost Map error", e);
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    logger.log(Level.WARNING," doPost Map error", e);
                }
            }
        }
        return JSONObject.parseObject(httpStr);
    }


    /**
     * 发送 POST 请求，K-V形式
     * @param url 请求地址
     * @param jsonParams 请求参数json串
     * @param headers 请求头参数
     * @return json
     * @author mengwg
     * @date 2021-05-17
     */
    public static JSONObject doPostJson(String url, String jsonParams,Map<String,String> headers) {
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String httpStr = null;
        HttpResponse response = null;

        try {
            httpClient = new SSLClient();
            httpPost = new HttpPost(url);
            for(Map.Entry<String, String> entry : headers.entrySet()){
                httpPost.addHeader(entry.getKey(),entry.getValue());
            }

            httpPost.setConfig(requestConfig);

            if(StringUtils.isNotBlank(jsonParams)) {
                //解决中文乱码问题
                StringEntity entity = new StringEntity(jsonParams, "utf-8");
                entity.setContentEncoding("UTF-8");
                //entity.setContentType("application/json");
                entity.setContentType(headers.get("Content-Type"));
                httpPost.setEntity(entity);
            }

            response = httpClient.execute(httpPost);
            if(response != null){
                HttpEntity httpEntity = response.getEntity();
                if(httpEntity != null){
                    //httpStr = EntityUtils.toString(httpEntity,StandardCharsets.UTF_8);
                    httpStr = EntityUtils.toString(httpEntity, "UTF-8");
                }
            }

        } catch (Exception e) {
            logger.log(Level.WARNING," doPost Map error", e);
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    logger.log(Level.WARNING," doPost Map error", e);
                }
            }
        }
        return JSONObject.parseObject(httpStr);
    }


    @SuppressWarnings("resource")
    public static String doPost(String url,String jsonstr,String charset){
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try{
            httpClient = new SSLClient();
            httpPost = new HttpPost(url);

            //httpPost.addHeader("Content-Type", "application/json");

            //TOOD
            httpPost.addHeader("Content-Type", "x-www-form-urlencoded");

            StringEntity se = new StringEntity(jsonstr);
            se.setContentType("text/json");
            se.setContentEncoding(new BasicHeader("Content-Type", "application/json"));
            httpPost.setEntity(se);
            HttpResponse response = httpClient.execute(httpPost);
            if(response != null){
                HttpEntity resEntity = response.getEntity();
                if(resEntity != null){
                    result = EntityUtils.toString(resEntity,charset);
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
}
