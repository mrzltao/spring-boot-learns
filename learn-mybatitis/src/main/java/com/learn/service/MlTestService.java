package com.learn.service;

import com.learn.entity.MlTest;

import java.util.List;

/**
 * @Title MlTestService
 * @Description MlTestService
 * @Author Ltter
 * @Date 2022/8/18 15:01
 * @Version 1.0
 */
public interface MlTestService {
    List<MlTest> find();

    Boolean save(MlTest mlTest);
}
