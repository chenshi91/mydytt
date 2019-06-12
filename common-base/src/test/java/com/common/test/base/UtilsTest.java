/*created by chenshi at   2019/5/12*/
package com.common.test.base;

import com.alibaba.fastjson.JSONObject;
import com.dytt.common.mvc.BaseEntity;
import com.dytt.common.test.BaseTest;
import com.dytt.common.utils.ExcelUtil;
import com.dytt.common.utils.JsonUtil;
import com.dytt.common.utils.MapUtil;
import com.dytt.common.utils.StringUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenshi
 * @date 2019-05-12
 */
@Slf4j
public class UtilsTest extends BaseTest {

    @Test
    public void readExcel(){
        File file = new File("C:\\Users\\39248\\Desktop\\didian7.xlsx");
        List<String[]> strings = ExcelUtil.readExcel(file);
        log.info(strings.toString());
    }

    @Test
    public void writeExcel(){
        File file = new File("E:\\test.xls");
        ArrayList<String[]> list = Lists.newArrayList();
        list.add(new String[]{"zhang3","22","hubei"});
        list.add(new String[]{"li4","21","henan"});
        list.add(new String[]{"wang5","12","jiangxi"});
        list.add(new String[]{"ma6","32","beijing"});
        ExcelUtil.writeExcel(list,file);
    }

    @Test
    public void stringUtil(){
        String  number="sdghjg3.0";
        String remove = StringUtil.remove(number, 1);
        log.info(remove);
        System.out.println(StringUtil.isNull(null));
        System.out.println(StringUtil.isNull(""));
        System.out.println(StringUtil.isNull("   "));
        System.out.println(StringUtil.isNull("xujiali"));
        ArrayList<Integer> list = Lists.newArrayList(3);
        ArrayList<Object> objects = new ArrayList<>(11);
        log.info("----------");
    }

    @Test
    public void jsonUtil() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("createUserId","zhang3");
        map.put("lastUpdateUserId","11");
        JSONObject jsonObject = JsonUtil.parseJsonObject(map);
        Map map1 = JsonUtil.toJavaObject(jsonObject, Map.class);
        log.info("json/map 互转");
    }

    @Test
    public void mapUtil(){
        Long    num=122322332221178866l;
        String string = num.toString();
        log.info(string);
        ArrayList<String> list = new ArrayList<String>(2) {{
            add("qqq");
            add("www");
        }};
        log.info(ArrayUtils.toString(list));
        BaseEntity entity = new BaseEntity(){{
            setLastUpdateUserId("aaa");
            setCreateUserId("shen");
        }};
        entity.setId(11L);
        Map map = MapUtil.toMap(entity);
        BaseEntity baseEntity = MapUtil.toJavaObject(new BaseEntity(){{}}, map);
        JSONObject jsonObject = MapUtil.toJson(map);
    }


    @Override
    protected ClassLoader getClassLoader() {
        return UtilsTest.class.getClassLoader();
    }

    @Override
    protected String getIpHost() {
        return "";
    }
}
