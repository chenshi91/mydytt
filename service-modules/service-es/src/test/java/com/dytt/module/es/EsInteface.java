package com.dytt.module.es;

import com.dytt.common.test.BaseTest;
import org.junit.Test;

/**
 * @author chenshi
 * @date 2019-05-14
 */
public class EsInteface extends BaseTest {

    @Test
    public void createIndex() {
        super.httpRequestOfGet("/createIndex?index=index_log", null);
    }

    @Test
    public void insertOrUpdate() {
        super.httpRequestOfPost("/insertOrUpdate", "add.json");
    }

    @Test
    public void delete() {
        super.httpRequestOfGet("/delete/index_topic/topic/5", null);
    }

    @Test
    public void search() {
        super.httpRequestOfGet("/search/index_topic?key=name&value=家", null);
    }

    @Test
    public void hi(){
        super.httpRequestOfGet("/hi");
    }

    @Override
    protected ClassLoader getClassLoader() {
        return EsInteface.class.getClassLoader();
    }

    @Override
    protected String getIpHost() {
        return "8092";
    }
}
