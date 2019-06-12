/*created by chenshi at   2019/5/9*/
package com.dytt.common.utils;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * @author chenshi
 * @date 2019-05-09
 */
public class DownloadUtil {

    /**
     * 文件下载
     *
     * @param downUrl      下载链接url地址
     * @param downLocation 下载到电脑的路径
     *                     String  downUrl="https://download.jetbrains.8686c.com/idea/ideaIU-2019.1.exe";
     *                     String  downLocation="D:\\idea.exe";
     */
    public static void download(String downUrl, String downLocation) {
        //1,先判断文件是否存在,若存在则先删除原文件
        File file = new File(downLocation);
        if (file.exists()) {
            file.delete();
        }
        //定义请求头的接收类型
        RequestCallback requestCallback = request -> request.getHeaders()
                .setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM, MediaType.ALL));
        // getForObject会将所有返回直接放到内存中,使用流来替代这个操作
        ResponseExtractor<Void> responseExtractor = response -> {
            // Here I write the response to a file but do what you like
            Files.copy(response.getBody(), Paths.get(downLocation));
            return null;
        };
        RestTemplate template = new RestTemplate();
        template.execute(downUrl, HttpMethod.GET, requestCallback, responseExtractor);
    }
}
