package com.dytt.common.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ObjectUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenshi
 * @date 2019-05-29
 */
@Slf4j
public class MapUtil extends MapUtils {

    /**
     * 转Map
     *
     * @param javaBean
     * @return
     */
    public static Map toMap(Object javaBean) {
        Map<String, Object> hashMap = new HashMap<>(16);
        Class<?> objClass = javaBean.getClass();
        Method[] methods = objClass.getMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            try {

                if (!methodName.startsWith("get")
                        ||methodName.startsWith("getClass")) {
                    continue;
                }
                Object fieldValue = method.invoke(javaBean, null);
                if (ObjectUtils.isEmpty(fieldValue)) {
                    continue;
                }
                String  fieldKey=StringUtil.uncapitalize(StringUtil.replace(methodName,"get",""));
//                try {
//                    Class<?> fieldValueType = objClass.getDeclaredField(fieldKey).getType();
//                    Object cast = fieldValueType.cast(fieldValue);
//                    fieldValue=(Map) fieldValue;
//                } catch (NoSuchFieldException e) {
//                    e.printStackTrace();
//                }
                hashMap.put(fieldKey,fieldValue);
                log.info("反射获取实体类字段:{}={}",fieldKey,fieldValue);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return hashMap;
    }

    /**
     * 转java实体类
     *
     * @param obj
     * @param map
     * @param <T>
     * @return
     */
    public static <T> T toJavaObject(T obj, Map<String, Object> map) {
        try {
            BeanUtils.populate(obj, map);
        } catch (IllegalAccessException e) {
            log.info("类型转换出现异常");
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            log.info("类型转换出现异常");
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 转json
     *
     * @param map
     * @return
     */
    public static JSONObject toJson(Map<String, Object> map) {
        Object obj = JsonUtil.toJSON(map);
        return (JSONObject) obj;
    }

}
