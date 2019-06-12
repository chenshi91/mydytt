/*created by chenshi at   2019/4/29*/
package com.dytt.module.test.jdk1_8;

import com.alibaba.fastjson.JSONObject;
import com.dytt.module.test.demo.Demo;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class OptionalDemo extends Demo {

    @Test
    public void optional() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "xujiali");
        jsonObject.put("home", "hubei");
        JSONObject country = new JSONObject();
        country.put("history", "罗马");
        jsonObject.put("country", country);
        String history = Optional.ofNullable(jsonObject)
                .map(j -> j.getJSONObject("country"))
                .map(c -> c.getString("history"))
                .orElse("default-string");
        log.info(history);

    }

    @Test
    public void stream() {
        ArrayList<String> list = Lists.newArrayList();
        list.add("zhang3");
        list.add("li4");
        list.add("wang5");
        Map<Integer, List<String>> map = list.stream()
                .filter(s -> s.length() > 2)
                .collect(Collectors.groupingBy(s -> s.length()));

//        list.removeIf(s -> "li4".equals(s));
//        list.stream().forEach(s -> log.info(s));
        String s = list.stream().findAny().orElse("default");
        Map<Integer, List<String>> map1 = list.stream().collect(Collectors.groupingBy(s1 -> s1.length()));
        log.info(s);
    }

    @Test
    public void split() throws IOException {
        String s = String.valueOf(System.currentTimeMillis());
        log.info(s);
        for (String s1 : "".split("#")) {
            log.info(s1);
            System.out.println("111");
        }
    }

}
