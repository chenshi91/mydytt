package com.dytt.common.utils;

import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author chenshi
 * @date 2019-07-25
 */
public class UrlUtil {

    /**
     * 迅雷下载链接转换成普通http地址
     *
     * @param thunderUrl
     * @return
     */
    public static String thunderurlToHttpurl(String thunderUrl) {
        byte[] bytes = null;
        String httpdownurl = null;
        try {
            bytes = new BASE64Decoder().decodeBuffer(thunderUrl.replace("thunder://", ""));
            httpdownurl = new String(bytes, "gbk");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (httpdownurl.startsWith("AA")) {
            httpdownurl = httpdownurl.substring(2, httpdownurl.length());
        }
        if (httpdownurl.endsWith("ZZ")) {
            httpdownurl = httpdownurl.substring(0, httpdownurl.length() - 2);
        }
        return httpdownurl;
    }
}
