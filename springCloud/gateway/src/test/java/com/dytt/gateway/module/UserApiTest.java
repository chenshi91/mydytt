package com.dytt.gateway.module;

import com.dytt.gateway.base.BaseTest;
import org.junit.Test;

public class UserApiTest    extends BaseTest {

    @Test
    public void getById(){
        super.httpRequestOfGET("/user/getById?id=1202241378664067073");
    }
}
