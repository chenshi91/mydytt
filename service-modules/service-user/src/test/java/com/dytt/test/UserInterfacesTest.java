/*created by chenshi at   2019/5/9*/
package com.dytt.test;

import com.dytt.common.test.BaseTest;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * @author chenshi
 * @date 2019-05-09
 */
public class UserInterfacesTest extends BaseTest {

    @Test
    public void add() {
        super.httpRequestOfPost("/insert", "addUser.json");
    }

    @Test
    public void page() {
        super.httpRequestOfGet("/page/1/10?nickName=xujiali", "");
    }

    @Test
    public void file() throws IOException {
        String downUrl = "https://download.jetbrains.8686c.com/idea/ideaIU-2019.1.exe";
        String downLocation = "D:\\idea.exe";
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
        long a = 12;
    }


    @Override
    protected ClassLoader getClassLoader() {
        return UserInterfacesTest.class.getClassLoader();
    }

    @Override
    protected String getIpHost() {
        return "8010";
    }
}
