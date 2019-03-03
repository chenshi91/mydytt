/*created by chenshi    at 2018-05-08*/
package com.cmss.dytt.common.web.utils;


import com.cmss.dytt.common.web.exception.ServiceException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtil {

    private static final Map<String, Properties> map = new HashMap();

    public static String getPropertiesByName(String name) throws IOException {
        Properties properties = new Properties();

        InputStream in = PropertiesUtil.class.getResourceAsStream("/config/jdbc.properties");
        properties.load(in);
        String value = properties.getProperty(name);
        in.close();
        return value;
    }

    public static String getPropertiesValue(String propertiesName, String propertitesKey) throws IOException {
        Properties properties = map.get(propertiesName);
        if (properties == null) {
            InputStream in = PropertiesUtil.class.getResourceAsStream("/config/" + propertiesName + ".properties");
            properties = new Properties();
            properties.load(new InputStreamReader(in, "utf-8"));
            map.put(propertiesName, properties);
            String value = properties.getProperty(propertitesKey);
            in.close();
            return propertiesValueIsNull(value);
        }
        return propertiesValueIsNull(properties.getProperty(propertitesKey));
    }

    private static String propertiesValueIsNull(String value) {
        if (org.springframework.util.StringUtils.isEmpty(value)) {
            throw new ServiceException("properties文件未命名字段！");
        }
        return value;
    }
}
