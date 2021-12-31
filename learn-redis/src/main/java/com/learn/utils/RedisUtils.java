package com.learn.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.*;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @title: RedisUtils
 * @Description TODO
 * @Author ZLT
 * @Date: 2021/12/31 9:46
 * @Version 1.0
 */
@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    public RedisTemplate<Object,Object> getInstance(){
        return redisTemplate;
    }

    /**
     * 删除指定key
     * @param key
     * @return
     */
    public boolean delete(String key){
        return redisTemplate.delete(key);
    }

    /**
     * 指定key是否存在
     * @param key
     * @return
     */
    public boolean exists(String key){
        return redisTemplate.hasKey(key);
    }

    /** ================================String================================ **/
    /**
     * 获取字符串类型key的值
     * @param key
     * @return
     */
    public String get(String key){
        return (String) redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置字符串类型key-value
     * @param key
     * @param value
     */
    public void set(String key, String value){
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 设置并获取字符串类型key的value
     * 若key存在，则覆盖，并返回old-value；若不存在，则添加，并返回null.
     * @param key
     * @param value
     * @return
     */
    public String getAndSet(String key, String value){
        return (String) redisTemplate.opsForValue().getAndSet(key, value);
    }

    /**
     * 批量获取字符串类型key-value
     * @param keys
     * @return
     */
    public List<Object> multiGet(List<Object> keys){
        return (List<Object>) redisTemplate.opsForValue().multiGet(keys);
    }

    /**
     * 批量设置字符串类型key-value
     * @param map
     */
    public void multiSet(Map<String, String> map){
        redisTemplate.opsForValue().multiSet(map);
    }

    /**
     * 批量设置字符串类型key-value
     * 只有key不存在时，才添加；Map中只要有一个key存在，则全部取消添加
     * @param map
     */
    public void multiSetAbsent(Map<String, String> map){
        redisTemplate.opsForValue().multiSetIfAbsent(map);
    }

    /**
     * 获取指定key的过期时间
     * @param key
     * @return
     */
    public long getExpirses(String key){
        return redisTemplate.boundValueOps(key).getExpire();
    }

    /**
     * 为指定的key值增加过期时间
     * @param key
     * @param timeout
     * @param timeUnit
     * @return
     */
    public boolean setExpires(String key, long timeout, TimeUnit timeUnit){
        return redisTemplate.boundValueOps(key).expire(timeout, timeUnit);
    }

    /**
     * 设置字符串类型的k-v，并添加有效时间(毫秒)
     * @param key
     * @param value
     * @param milliSeconds
     */
    public void setExpiresMilliSeconds(String key, String value, long milliSeconds){
        redisTemplate.opsForValue().set(key, value, milliSeconds, TimeUnit.MILLISECONDS);
    }

    /**
     * 设置字符串类型的k-v，并添加有效时间(秒)
     * @param key
     * @param value
     * @param seconds
     */
    public void setExpiresSeconds(String key, String value, long seconds){
        redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
    }

    /**
     * 设置字符串类型的k-v，并添加有效时间(分钟)
     * @param key
     * @param value
     * @param minutes
     */
    public void setExpiresMinutes(String key, String value, long minutes){
        redisTemplate.opsForValue().set(key, value, minutes, TimeUnit.MINUTES);
    }

    /**
     * 设置字符串类型的k-v，并添加有效时间(小时)
     * @param key
     * @param value
     * @param hours
     */
    public void setExpiresHours(String key, String value, long hours){
        redisTemplate.opsForValue().set(key, value, hours, TimeUnit.HOURS);
    }

    /**
     * 设置字符串类型的k-v，并添加有效时间(天)
     * @param key
     * @param value
     * @param days
     */
    public void setExpiresDays(String key, String value, long days){
        redisTemplate.opsForValue().set(key, value, days, TimeUnit.DAYS);
    }

    /**
     * 移除指定key的过期时间
     * @param key
     */
    public void persist(String key){
        redisTemplate.boundValueOps(key).persist();
    }

    /**
     * 将指定key中的数值加1
     * @param key
     */
    public long increment(String key){
        return redisTemplate.opsForValue().increment(key);
    }

    /**
     * 为指定key中的值进行加操作
     * 若不存在则新增key并赋值number
     * 若存在且key中的值不为长整型，将报错
     * @param key
     * @param number
     * @return
     */
    public Long increment(String key, long number){
        return redisTemplate.opsForValue().increment(key, number);
    }

    /**
     * 为指定key中的值进行加操作
     * 若不存在则新增key并赋值number
     * 若存在且key中的值不为数字，将报错
     * @param key
     * @param number
     * @return
     */
    public Double increment(String key, double number){
        return redisTemplate.opsForValue().increment(key, number);
    }

    /**
     * 将指定key中的数值减1
     * @param key
     * @return
     */
    public Long decrement(String key){
        return redisTemplate.opsForValue().decrement(key);
    }

    /**
     * 为指定key中的值进行减操作
     * 若不存在则新增key并初始化为0，再执行减操作
     * 若存在且key中的值不为长整型，将报错
     * @param key
     * @param number
     * @return
     */
    public Long decrement(String key, long number){
        return redisTemplate.opsForValue().decrement(key, number);
    }

    /**
     * 修改key
     * @param oldKey
     * @param newKey
     */
    public void rename(String oldKey, String newKey){
        redisTemplate.boundValueOps(oldKey).rename(newKey);
    }

    /**
     * 获取key值的字符串长度
     * @param key
     * @return
     */
    public Long size(String key){
        return redisTemplate.opsForValue().size(key);
    }

    /** ================================Hash================================ **/
    /**
     * 添加 Hash 键值对
     * @param key
     * @param hashKey
     * @param value
     */
    public void put(String key, String hashKey, String value){
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    /**
     * 批量添加 hash 的 键值对
     * 有则覆盖,没有则添加
     * @param key
     * @param map
     */
    public void putAll(String key,Map<String,String> map){
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 添加 hash 键值对. 不存在的时候才添加
     * @param key
     * @param hashKey
     * @param value
     * @return
     */
    public boolean putIfAbsent(String key, String hashKey, String value){
        return redisTemplate.opsForHash().putIfAbsent(key, hashKey, value);
    }


    /**
     * 删除指定 hash 的 HashKey
     * @param key
     * @param hashKeys
     * @return 删除成功的 数量
     */
    public Long delete(String key, String... hashKeys){
        return redisTemplate.opsForHash().delete(key, hashKeys);
    }


    /**
     * 给指定 hash 的 hashkey 做增减操作
     * @param key
     * @param hashKey
     * @param number
     * @return
     */
    public Long increment(String key, String hashKey,long number){
        return redisTemplate.opsForHash().increment(key, hashKey, number);
    }

    /**
     * 给指定 hash 的 hashkey 做增减操作
     * @param key
     * @param hashKey
     * @param number
     * @return
     */
    public Double increment(String key, String hashKey,Double number){
        return redisTemplate.opsForHash().increment(key, hashKey, number);
    }

    /**
     * 获取指定 key 下的 hashkey
     * @param key
     * @param hashKey
     * @return
     */
    public Object getHashKey(String key,String hashKey){
        return redisTemplate.opsForHash().get(key, hashKey);
    }


    /**
     * 获取 key 下的 所有  hashkey 和 value
     * @param key
     * @return
     */
    public Map<Object,Object> getHashEntries(String key){
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 获取 key 下的 所有 hashkey 字段名
     * @param key
     * @return
     */
    public Set<Object> hashKeys(String key){
        return redisTemplate.opsForHash().keys(key);
    }

    /**
     * 获取key下所有字段(hashKey)的值
     * @param key
     * @return
     */
    public List<Object> hashValues(String key){
        return redisTemplate.opsForHash().values(key);
    }

    /**
     * 获取指定 hash 下面的 键值对 数量
     * @param key
     * @return
     */
    public Long hashSize(String key){
        return redisTemplate.opsForHash().size(key);
    }

    /**
     * 验证指定 key 下 有没有指定的 hashkey
     * @param key
     * @param hashKey
     * @return
     */
    public boolean hashKey(String key,String hashKey){
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    /** ================================List================================ **/

    /**
     * 为指定list的key从左添加value
     * @param key
     * @return 当前队列的长度
     */
    public Long leftPush(String key, Object value){
        return redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 为指定list的key从左移除并获取第一个元素
     * 如果列表没有元素,会堵塞到列表一直有元素或者超时为止
     * @param key
     * @return 移除的值
     */
    public Object leftPop(String key){
        return redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 为指定list的key从左移除并获取第一个元素在等待的时间里
     * 如果超过等待的时间仍没有元素则退出。
     * @param key
     * @param timeout
     * @param timeUnit
     * @return
     */
    public Object leftPop(String key, long timeout, TimeUnit timeUnit){
        return redisTemplate.opsForList().leftPop(key, timeout, timeUnit);
    }

    /**
     * 从左边依次添加values
     * 导入顺序按照 Collection 顺序
     * 如: a b c => c b a
     * @param key
     * @param values
     * @return
     */
    public Long leftPushAll(String key, Collection<Object> values){
        return redisTemplate.opsForList().leftPushAll(key, values);
    }

    /**
     * 指定list从右添加value
     * @param key
     * @return 当前队列的长度
     */
    public Long rightPush(String key, Object value){
        return redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 为指定list的key从右移除并获取第一个元素
     * 如果列表没有元素,会堵塞到列表一直有元素或者超时为止
     * @param key
     * @return 移除的值
     */
    public Object rightPop(String key){
        return redisTemplate.opsForList().rightPop(key);
    }

    /**
     * 为指定list的key从右移除并获取第一个元素在等待的时间里
     * 如果超过等待的时间仍没有元素则退出。
     * @param key
     * @param timeout
     * @param timeUnit
     * @return
     */
    public Object rightPop(String key, long timeout, TimeUnit timeUnit){
        return redisTemplate.opsForList().rightPop(key, timeout, timeUnit);
    }

    /**
     * 从右边依次添加values
     * 导入顺序按照 Collection 顺序
     * 如: a b c => a b c
     * @param key
     * @param values
     * @return
     */
    public Long rightPushAll(String key, Collection<Object> values){
        return redisTemplate.opsForList().rightPushAll(key, values);
    }

    /**
     * 根据下标获取值
     * @param key
     * @param index
     * @return
     */
    public Object popIndex(String key, long index){
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * 获取列表指定长度
     * @param key
     * @param index
     * @return
     */
    public Long listSize(String key, long index){
        return redisTemplate.opsForList().size(key);
    }

    /**
     * 获取列表 指定范围内的所有值
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<Object> listRange(String key, long start, long end){
        return redisTemplate.opsForList().range(key, start, end);
    }


    /**
     * 删除 key 中 值为 value 的 count 个数.
     * @param key
     * @param count
     * @param value
     * @return 成功删除的个数
     */
    public Long listRemove(String key, long count, Object value){
        return redisTemplate.opsForList().remove(key, count, value);
    }

    /**
     * 删除 列表 [start,end] 以外的所有元素
     * @param key
     * @param start
     * @param end
     */
    public void listTrim(String key, long start, long end){
        redisTemplate.opsForList().trim(key, start, end);
    }

    /**
     * 将keyPop从右出栈并获取,并从左添加到keyPush
     * @param keyPop 从右移除并获取的列表
     * @param keyPush 从左添加的列表
     * @return 操作的值
     */
    public Object rightPopAndLeftPush(String keyPop, String keyPush){
        return redisTemplate.opsForList().rightPopAndLeftPush(keyPop, keyPush);
    }

    /** ================================Set================================ **/
    /**
     * 添加 set 元素
     * @param key
     * @param values
     * @return
     */
    public Long add(String key ,String... values){
        return redisTemplate.opsForSet().add(key, values);
    }

    /**
     * 获取两个集合的差集
     * @param key
     * @param otherkey
     * @return
     */
    public Set<Object> difference(String key, String otherkey){
        return redisTemplate.opsForSet().difference(key, otherkey);
    }


    /**
     * 获取 key 和 集合  collections 中的 key 集合的差集
     * @param key
     * @param otherKeys
     * @return
     */
    public Set<Object> difference(String key, Collection<Object> otherKeys){
        return redisTemplate.opsForSet().difference(key, otherKeys);
    }

    /**
     * 将  key 与 otherkey 的差集 ,添加到新的 newKey 集合中
     * @param key
     * @param otherkey
     * @param newKey
     * @return 返回差集的数量
     */
    public Long differenceAndStore(String key, String otherkey, String newKey){
        return redisTemplate.opsForSet().differenceAndStore(key, otherkey, newKey);
    }

    /**
     * 将 key 和 集合  collections 中的 key 集合的差集 添加到  newkey 集合中
     * @param key
     * @param otherKeys
     * @param newKey
     * @return 返回差集的数量
     */
    public Long differenceAndStore(String key, Collection<Object> otherKeys, String newKey){
        return redisTemplate.opsForSet().differenceAndStore(newKey, otherKeys, newKey);
    }

    /**
     * 删除一个或多个集合中的指定值
     * @param key
     * @param values
     * @return 成功删除数量
     */
    public Long remove(String key,Object... values){
        return redisTemplate.opsForSet().remove(key, values);
    }

    /**
     * 随机移除一个元素,并返回出来
     * @param key
     * @return
     */
    public Object randomSetPop(String key){
        return redisTemplate.opsForSet().pop(key);
    }

    /**
     * 随机获取一个元素
     * @param key
     * @return
     */
    public Object randomSet(String key){
        return redisTemplate.opsForSet().randomMember(key);
    }

    /**
     * 随机获取指定数量的元素,同一个元素可能会选中两次
     * @param key
     * @param count
     * @return
     */
    public List<Object> randomSet(String key, long count){
        return redisTemplate.opsForSet().randomMembers(key, count);
    }

    /**
     * 随机获取指定数量的元素,去重(同一个元素只能选择两一次)
     * @param key
     * @param count
     * @return
     */
    public Set<Object> randomSetDistinct(String key, long count){
        return redisTemplate.opsForSet().distinctRandomMembers(key, count);
    }

    /**
     * 将 key 中的 value 转入到 destKey 中
     * @param key
     * @param value
     * @param destKey
     * @return 返回成功与否
     */
    public boolean moveSet(String key, Object value, String destKey){
        return redisTemplate.opsForSet().move(key, value, destKey);
    }

    /**
     * 无序集合的大小
     * @param key
     * @return
     */
    public Long setSize(String key){
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * 判断 set 集合中 是否有 value
     * @param key
     * @param value
     * @return
     */
    public boolean isMember(String key, Object value){
        return redisTemplate.opsForSet().isMember(key, value);
    }

    /**
     * 返回 key 和 othere 的并集
     * @param key
     * @param otherKey
     * @return
     */
    public Set<Object> unionSet(String key, String otherKey){
        return redisTemplate.opsForSet().union(key, otherKey);
    }

    /**
     * 返回 key 和 otherKeys 的并集
     * @param key
     * @param otherKeys key 的集合
     * @return
     */
    public Set<Object> unionSet(String key, Collection<Object> otherKeys){
        return redisTemplate.opsForSet().union(key, otherKeys);
    }

    /**
     * 将 key 与 otherKey 的并集,保存到 destKey 中
     * @param key
     * @param otherKey
     * @param destKey
     * @return destKey 数量
     */
    public Long unionAndStoreSet(String key, String otherKey, String destKey){
        return redisTemplate.opsForSet().unionAndStore(key, otherKey, destKey);
    }

    /**
     * 将 key 与 otherKey 的并集,保存到 destKey 中
     * @param key
     * @param otherKeys
     * @param destKey
     * @return destKey 数量
     */
    public Long unionAndStoreSet(String key, Collection<Object> otherKeys, String destKey){
        return redisTemplate.opsForSet().unionAndStore(key, otherKeys, destKey);
    }

    /**
     * 返回集合中所有元素
     * @param key
     * @return
     */
    public Set<Object> members(String key){
        return redisTemplate.opsForSet().members(key);
    }

    /** ================================ZSet================================ **/
    /**
     * 添加 ZSet 元素
     * @param key
     * @param value
     * @param score
     */
    public boolean add(String key, Object value, double score){
        return redisTemplate.opsForZSet().add(key, value, score);
    }

    /**
     * 批量添加 ZSet
     * Set<TypedTuple<Object>> tuples = new HashSet<>();
     * TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple<Object>("zset-5",9.6);
     * tuples.add(objectTypedTuple1);
     * @param key
     * @param tuples
     * @return
     */
    public Long batchAddZset(String key, Set<TypedTuple<Object>> tuples){
        return redisTemplate.opsForZSet().add(key, tuples);
    }

    /**
     * Zset 删除一个或多个元素
     * @param key
     * @param values
     * @return
     */
    public Long removeZset(String key, String... values){
        return redisTemplate.opsForZSet().remove(key, values);
    }

    /**
     * 对指定的 zset 的 value 值 , socre 属性做增减操作
     * @param key
     * @param value
     * @param score
     * @return
     */
    public Double incrementScore(String key, Object value, double score){
        return redisTemplate.opsForZSet().incrementScore(key, value, score);
    }

    /**
     * 获取 key 中指定 value 的排名(从0开始,从小到大排序)
     * @param key
     * @param value
     * @return
     */
    public Long rank(String key, Object value){
        return redisTemplate.opsForZSet().rank(key, value);
    }

    /**
     * 获取 key 中指定 value 的排名(从0开始,从大到小排序)
     * @param key
     * @param value
     * @return
     */
    public Long reverseRank(String key, Object value){
        return redisTemplate.opsForZSet().reverseRank(key, value);
    }

    /**
     * 获取索引区间内的排序结果集合(从0开始,从小到大,带上分数)
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<TypedTuple<Object>> rangeWithScores(String key, long start, long end){
        return redisTemplate.opsForZSet().rangeWithScores(key, start, end);
    }

    /**
     * 获取索引区间内的排序结果集合(从0开始,从小到大,只有列名)
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<Object> range(String key, long start, long end){
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    /**
     * 获取分数范围内的 [min,max] 的排序结果集合 (从小到大,只有列名)
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Set<Object> rangeByScore(String key, double min, double max){
        return redisTemplate.opsForZSet().rangeByScore(key, min, max);
    }

    /**
     * 获取分数范围内的 [min,max] 的排序结果集合 (从小到大,集合带分数)
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Set<TypedTuple<Object>> rangeByScoreWithScores(String key, double min, double max){
        return redisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max);
    }

    /**
     * 返回 分数范围内 指定 count 数量的元素集合, 并且从 offset 下标开始(从小到大,不带分数的集合)
     * @param key
     * @param min
     * @param max
     * @param offset 从指定下标开始
     * @param count 输出指定元素数量
     * @return
     */
    public Set<Object> rangeByScore(String key, double min, double max,long offset,long count){
        return redisTemplate.opsForZSet().rangeByScore(key, min, max, offset, count);
    }

    /**
     * 返回 分数范围内 指定 count 数量的元素集合, 并且从 offset 下标开始(从小到大,带分数的集合)
     * @param key
     * @param min
     * @param max
     * @param offset 从指定下标开始
     * @param count 输出指定元素数量
     * @return
     */
    public Set<TypedTuple<Object>> rangeByScoreWithScores(String key, double min, double max,long offset,long count){
        return redisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max, offset, count);
    }

    /**
     * 获取索引区间内的排序结果集合(从0开始,从大到小,只有列名)
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<Object> reverseRange(String key, long start, long end){
        return redisTemplate.opsForZSet().reverseRange(key, start, end);
    }

    /**
     * 获取索引区间内的排序结果集合(从0开始,从大到小,带上分数)
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<TypedTuple<Object>> reverseRangeWithScores(String key, long start, long end){
        return redisTemplate.opsForZSet().reverseRangeWithScores(key, start, end);
    }

    /**
     * 获取分数范围内的 [min,max] 的排序结果集合 (从大到小,集合不带分数)
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Set<Object> reverseRangeByScore(String key, double min, double max){
        return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max);
    }

    /**
     * 获取分数范围内的 [min,max] 的排序结果集合 (从大到小,集合带分数)
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Set<TypedTuple<Object>> reverseRangeByScoreWithScores(String key, double min, double max){
        return redisTemplate.opsForZSet().reverseRangeByScoreWithScores(key, min, max);
    }

    /**
     * 返回 分数范围内 指定 count 数量的元素集合, 并且从 offset 下标开始(从大到小,不带分数的集合)
     * @param key
     * @param min
     * @param max
     * @param offset 从指定下标开始
     * @param count 输出指定元素数量
     * @return
     */
    public Set<Object> reverseRangeByScore(String key, double min, double max, long offset, long count){
        return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max, offset, count);
    }

    /**
     * 返回 分数范围内 指定 count 数量的元素集合, 并且从 offset 下标开始(从大到小,带分数的集合)
     * @param key
     * @param min
     * @param max
     * @param offset 从指定下标开始
     * @param count 输出指定元素数量
     * @return
     */
    public Set<TypedTuple<Object>> reverseRangeByScoreWithScores(String key, double min, double max, long offset, long count){
        return redisTemplate.opsForZSet().reverseRangeByScoreWithScores(key, min, max, offset, count);
    }

    /**
     * 返回指定分数区间 [min,max] 的元素个数
     * @param key
     * @param min
     * @param max
     * @return
     */
    public long countZSet(String key, double min, double max){
        return redisTemplate.opsForZSet().count(key, min, max);
    }

    /**
     * 返回 zset 集合数量
     * @param key
     * @return
     */
    public long sizeZset(String key){
        return redisTemplate.opsForZSet().size(key);
    }

    /**
     * 获取指定成员的 score 值
     * @param key
     * @param value
     * @return
     */
    public Double score(String key, Object value){
        return redisTemplate.opsForZSet().score(key, value);
    }

    /**
     * 删除指定索引位置的成员,其中成员分数按( 从小到大 )
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Long removeRange(String key, long start, long end){
        return redisTemplate.opsForZSet().removeRange(key, start, end);
    }

    /**
     * 删除指定 分数范围 内的成员 [main,max],其中成员分数按( 从小到大 )
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Long removeRangeByScore(String key, double min , double max){
        return redisTemplate.opsForZSet().removeRangeByScore(key, min, max);
    }

    /**
     *  key 和 other 两个集合的并集,保存在 destKey 集合中, 列名相同的 score 相加
     * @param key
     * @param otherKey
     * @param destKey
     * @return
     */
    public Long unionAndStoreZset(String key, String otherKey, String destKey){
        return redisTemplate.opsForZSet().unionAndStore(key, otherKey, destKey);
    }

    /**
     *  key 和 otherKeys 多个集合的并集,保存在 destKey 集合中, 列名相同的 score 相加
     * @param key
     * @param otherKeys
     * @param destKey
     * @return
     */
    public Long unionAndStoreZset(String key, Collection<String> otherKeys, String destKey){
        return redisTemplate.opsForZSet().unionAndStore(key, otherKeys, destKey);
    }

    /**
     *  key 和 otherKey 两个集合的交集,保存在 destKey 集合中
     * @param key
     * @param otherKey
     * @param destKey
     * @return
     */
    public Long intersectAndStore(String key, String otherKey, String destKey){
        return redisTemplate.opsForZSet().intersectAndStore(key, otherKey, destKey);
    }

    /**
     *  key 和 otherKeys 多个集合的交集,保存在 destKey 集合中
     * @param key
     * @param otherKeys
     * @param destKey
     * @return
     */
    public Long intersectAndStore(String key, Collection<String> otherKeys, String destKey){
        return redisTemplate.opsForZSet().intersectAndStore(key, otherKeys, destKey);
    }
}
