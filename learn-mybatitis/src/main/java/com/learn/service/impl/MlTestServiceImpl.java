package com.learn.service.impl;

import com.learn.entity.MlTest;
import com.learn.mapper.MlTestMapper;
import com.learn.service.MlTestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * @Title MlTestServiceImpl
 * @Description MlTestServiceImpl
 * @Author Ltter
 * @Date 2022/8/18 15:00
 * @Version 1.0
 */
@Service
public class MlTestServiceImpl implements MlTestService {

    @Resource
    private MlTestMapper mlTestMapper;

    @Override
    public List<MlTest> find() {
        return mlTestMapper.find();
    }

    @Override
    public Boolean save(MlTest mlTest) {
        mlTest.setId(UUID.randomUUID().toString().replace("-",""));
        return mlTestMapper.insertOne(mlTest);
    }
}
