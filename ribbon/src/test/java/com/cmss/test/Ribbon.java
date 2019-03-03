/* created by chenshi at 2019-02-12 */
package com.cmss.test;

import com.cmss.dytt.common.web.test.BaseTest;
import org.junit.Test;

public class Ribbon extends BaseTest {

    @Test
    public void hi() {
        super.httpRequestOfGet("/hi");
    }


    @Override
    protected ClassLoader getClassLoader() {
        return Ribbon.class.getClassLoader();
    }

    @Override
    protected String getIpHost() {
        return "http://localhost:7000";
    }
}
