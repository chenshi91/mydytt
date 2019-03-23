/* created by chenshi at 2019-02-03 */
package com.cmss.test.demo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dytt.common.model.test.BaseTest;
import com.dytt.common.model.utils.JsonUtil;
import org.junit.Test;

public class Demo extends BaseTest {


    @Test
    public void insert() {
        super.httpRequestOfPost("/add", "add.json");
    }

    @Test
    public void detail() {
        super.httpRequestOfGet("/detail/1");
    }

    @Test
    public void detail2() {
        super.httpRequestOfGet("/detail2/1");
    }

    @Test
    public void exceptionTest() {
        super.httpRequestOfGet("/exceptionTest");
    }

    @Test
    public void sort() {
        JSONObject o1 = JSONObject.parseObject("{\"id\":2,\"birthday\":\"2011-01-03 04:23:20\"}");
        JSONObject o2 = JSONObject.parseObject("{\"id\":1,\"birthday\":\"2013-02-01 01:23:20\"}");
        JSONObject o3 = JSONObject.parseObject("{\"id\":5,\"birthday\":\"2011-01-02 08:23:20\"}");
        JSONObject o4 = JSONObject.parseObject("{\"id\":4,\"birthday\":\"2011-02-03 06:23:20\"}");
        JSONArray a = new JSONArray();
        a.add(o1);
        a.add(o2);
        a.add(o3);
        a.add(o4);
        System.out.println("排序前：" + a);
        JSONArray afterJsonArray = JsonUtil.sortArrayByDate(a, "birthday", true);
//        JSONArray afterJsonArray = JsonUtil.sortArrayByInteger(a, "id", false);
        System.out.println("排序后：" + afterJsonArray);
    }

    /*private JSONArray sortArrayByDate(JSONArray beforeJsonArray, String dateField, Boolean sortRule) {
        List<JSONObject> list = JSONArray.parseArray(beforeJsonArray.toJSONString(), JSONObject.class);
        Collections.sort(list, new Comparator<JSONObject>() {
            @Override
            public int compare(JSONObject o1, JSONObject o2) {
                String birthday1 = o1.getString(dateField);
                String birthday2 = o2.getString(dateField);
                Date    bir1 = parseToDate(birthday1);
                Date    bir2 = parseToDate(birthday2);
                if (bir1.getTime() > bir2.getTime()) {
                    return 1;
                } else if (bir1.getTime() == bir2.getTime()) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        JSONArray afterJsonArray = JSONArray.parseArray(list.toString());
        return afterJsonArray;
    }

    public static Date parseToDate(String dateString) {
        *//*if (StringUtils.isEmpty(dateString)) {
            throw new ParseException("");
        }*//*
        Date date = null;
        if (!dateString.contains(":")) {
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return date;
        }

        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;

    }*/

    @Override

    protected ClassLoader getClassLoader() {
        return Demo.class.getClassLoader();
    }

    @Override
    protected String getIpHost() {
        return "http://localhost:3000";
    }
}
