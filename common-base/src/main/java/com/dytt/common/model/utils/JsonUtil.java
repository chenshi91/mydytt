/* created by chenshi at 2019-02-06 */
package com.dytt.common.model.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class JsonUtil extends JSONObject {

    /**
     * JsonArray排序---按日期
     *
     * @param beforeJsonArray 排序前
     * @param dateField       排序日期字段名
     * @param sortRule        排序规则（正序/倒序）
     * @return
     */
    public static JSONArray sortArrayByDate(JSONArray beforeJsonArray, String dateField, Boolean sortRule) {
        List<JSONObject> list = JSONArray.parseArray(beforeJsonArray.toJSONString(), JSONObject.class);
        Collections.sort(list, new Comparator<JSONObject>() {
            @Override
            public int compare(JSONObject o1, JSONObject o2) {
                String birthday1 = o1.getString(dateField);
                String birthday2 = o2.getString(dateField);
                Date bir1 = DateUtil.parseToDate(birthday1);
                Date bir2 = DateUtil.parseToDate(birthday2);
                if (bir1.getTime() > bir2.getTime()) {
                    return sortRule ? 1 : -1;
                } else if (bir1.getTime() == bir2.getTime()) {
                    return 0;
                } else {
                    return sortRule ? -1 : 1;
                }
            }
        });
        JSONArray afterJsonArray = JSONArray.parseArray(list.toString());
        return afterJsonArray;
    }

    /**
     * JsonArray排序---按大小
     *
     * @param beforeJsonArray 排序前
     * @param integerField    排序日期字段名
     * @param sortRule        排序规则（正序/倒序）
     * @return
     */
    public static JSONArray sortArrayByInteger(JSONArray beforeJsonArray, String integerField, Boolean sortRule) {
        List<JSONObject> list = JSONArray.parseArray(beforeJsonArray.toJSONString(), JSONObject.class);
        Collections.sort(list, new Comparator<JSONObject>() {
            @Override
            public int compare(JSONObject o1, JSONObject o2) {
                Integer integerField1 = o1.getInteger(integerField);
                Integer integerField2 = o2.getInteger(integerField);
                if (integerField1 > integerField2) {
                    return sortRule ? 1 : -1;
                } else if (integerField1 == integerField2) {
                    return 0;
                } else {
                    return sortRule ? -1 : 1;
                }
            }
        });
        JSONArray afterJsonArray = JSONArray.parseArray(list.toString());
        return afterJsonArray;
    }

    /**
     * java对象转换jsonObject
     *
     * @param javaBean
     * @return
     */
    public static JSONObject parseJsonObject(Object javaBean) {
        return JSONObject.parseObject(JSONObject.toJSONString(javaBean));
    }
}
