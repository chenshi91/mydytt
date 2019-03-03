/* created by chenshi at 2018-12-17 */
package model;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

public class Mw extends BaseTest {

    @Test
    public void aa() {
        String bilibiliUrl = "https://www.bilibili.com/v/life/funny/?spm_id_from=333.10.b_7375626e6176.2#/all/click/0/15/2017-08-10,2017-10-17";
        JSONObject jsonObject = super.httpRequestOfGet(bilibiliUrl, null);
        JSONObject data = jsonObject.getJSONObject("data");
    }


    @Override
    protected ClassLoader getClassLoader() {
        ClassLoader mwClassLoader = Mw.class.getClassLoader();
        return mwClassLoader;
    }


    @Test
    public void bb() {
        Integer a = 1, b = 2, c = 1;
        JSONObject request = new JSONObject();
        request.put("code", 2);
        System.out.println(a.equals(b));
        System.out.println(a.equals(c));

        System.out.println(request.getInteger("code"));
        System.out.println(b.equals(request.getInteger("code")));
    }
}
