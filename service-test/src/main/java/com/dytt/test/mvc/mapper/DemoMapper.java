package com.dytt.test.mvc.mapper;

import com.dytt.common.model.mvc.BaseMapper;
import com.dytt.test.mvc.Demo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DemoMapper extends BaseMapper<Demo> {

}