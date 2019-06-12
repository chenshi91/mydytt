package com.dytt.module.test.mvc.mapper;

import com.dytt.common.mvc.BaseMapper;
import com.dytt.module.test.mvc.Demo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DemoMapper extends BaseMapper<Demo> {

}