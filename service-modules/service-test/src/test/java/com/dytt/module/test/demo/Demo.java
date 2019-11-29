/* created by chenshi at 2019-02-03 */
package com.dytt.module.test.demo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dytt.common.test.BaseTest;
import com.dytt.common.utils.DateUtil;
import com.dytt.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class Demo extends BaseTest {


    @Test
    public void insert() {
        log.info("-----");
        super.httpRequestOfPost("/add", "add.json");
    }

    @Test
    public void detail() {
        super.httpRequestOfGet("/selectById/4");
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

    @Test
    public void aaa() throws UnknownHostException {
        Integer m1;
        log.info("--------sdjshfdjfhdf-----------");
        String dataString = DateUtil.YYYY_MM_DD_HH_MM_SS;
        Integer integer1 = new Integer(128);
        Integer integer2 = integer1;

        System.out.println(integer1 == integer2);
        String address = InetAddress.getLocalHost().getHostAddress().toString();
        ArrayList<Object> list = new ArrayList<>(8);
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        for (Object o : list) {
            if ("bbb".equals(o)) {
                list.remove(o);
            }
        }

        LinkedList<Object> linkedList = new LinkedList<>();
        linkedList.add("aaa");
        linkedList.add("bbb");
        linkedList.add("ccc");
        for (Object o : linkedList) {
            if ("bbb".equals(o)) {
                linkedList.remove(o);
            }
        }
        HashMap<Object, Object> map = new HashMap<>(14, 1);
        ConcurrentHashMap<Object, Object> concurrentHashMap =
                new ConcurrentHashMap<>(2, 11);

//        ArrayList<com.dytt.test.mvc.Demo> arrayList = Lists.newArrayList();
//        Map<Long, List<com.dytt.test.mvc.Demo>> listMap = arrayList.stream().collect(Collectors.groupingBy(com.dytt.test.mvc.Demo::getId));


    }

    @Override

    protected ClassLoader getClassLoader() {
        return Demo.class.getClassLoader();
    }

    @Override
    protected String getIpHost() {
        return "http://localhost:8087";
    }
}
