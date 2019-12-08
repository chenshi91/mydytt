/*created by chenshi at   2019/5/9*/
package com.dytt.test;

import com.dytt.common.test.BaseTest;
import org.junit.Test;

/**
 * @author chenshi
 * @date 2019-05-09
 */
public class UserApiTest extends BaseTest {

    @Test
    public void add() {
        super.httpRequestOfPOST("/insert", "user/addUser.json");
    }

    @Test
    public void page() {
        super.httpRequestOfGET("/selectWithPage?pageNo=1&pageSize=10");
    }

    @Test
    public void getById(){
        super.httpRequestOfGET("/getById?id=1202241378664067073");
    }

}
