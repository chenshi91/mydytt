package com.dytt.web;

import com.alibaba.fastjson.JSONObject;
import com.dytt.common.model.test.BaseTest;
import com.dytt.common.model.utils.JsonUtil;
import org.junit.Test;

public class Test1 extends BaseTest {
    @Override
    protected ClassLoader getClassLoader() {
        return null;
    }

    @Override
    protected String getIpHost() {
        return null;
    }

    @Test
    public void aaa() {
        String content = "{}";
        MovieController movieController = JsonUtil.toJavaObject(new JSONObject(), MovieController.class);

    }
}
