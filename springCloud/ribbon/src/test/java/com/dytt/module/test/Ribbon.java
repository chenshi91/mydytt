/* created by chenshi at 2019-02-12 */
package com.dytt.module.test;

import com.dytt.common.test.BaseTest;
import org.junit.Test;

public class Ribbon extends BaseTest {

    @Test
    public void hi() {
        super.httpRequestOfGET("/hi");
    }



}
