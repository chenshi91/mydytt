package com.cmss.test.mvc.mapper;

import com.cmss.dytt.common.web.mvc.BaseMapper;
import com.cmss.test.mvc.Demo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DemoMapper extends BaseMapper<Demo> {

}