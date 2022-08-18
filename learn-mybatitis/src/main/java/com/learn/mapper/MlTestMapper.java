package com.learn.mapper;

import com.learn.entity.MlTest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Title MlTestMapper
 * @Description MlTestMapper
 * @Author Ltter
 * @Date 2022/8/18 15:01
 * @Version 1.0
 */
@Mapper
public interface MlTestMapper {
    List<MlTest> find();

    Boolean insertOne(@Param("tt") MlTest mlTest);
}
