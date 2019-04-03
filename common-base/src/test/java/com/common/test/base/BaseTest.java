/* created by chenshi at 2018-12-03 *//*

package com.common.test.base;

import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RootUriTemplateHandler;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public abstract class BaseTest {
    protected Logger logger = LoggerFactory.getLogger(BaseTest.class);

    protected RestTemplate restTemplate = new RestTemplate();
    protected HttpEntity httpEntity;
    protected HttpHeaders httpHeaders = new HttpHeaders();

    protected abstract ClassLoader getClassLoader();

    protected abstract String getIpHost();

    @Before
    public void before() {
        //初始化url
//        Properties properties = getProperties("baseTest.properties");
        String ipHost = getIpHost();
        RootUriTemplateHandler rootUriTemplateHandler = new RootUriTemplateHandler(ipHost);
        restTemplate.setUriTemplateHandler(rootUriTemplateHandler);
        logger.info("----------初始化ipHost:" + ipHost);
        //初始化interceptors
        String username = "xujiali";
        String password = "xjl";
        ClientHttpRequestInterceptor auth1 = new BasicAuthorizationInterceptor(username, password);
        List<ClientHttpRequestInterceptor> authorizationInterceptorList = new ArrayList<>(1);
        authorizationInterceptorList.add(auth1);
        restTemplate.setInterceptors(authorizationInterceptorList);
        logger.info("----------初始化interceptors:username=" + username + ",password=" + password);
        //设置token
        String token = "123456";
        httpHeaders.add("token", token);
        logger.info("----------初始化token:" + token);

    }

    */
/**
 * GET
 *
 * @param url
 * @param uriValiables
 * @return POST
 * @param url
 * @param requestJsonUrl
 * @return POST
 * @param url
 * @param requestJsonUrl
 * @return
 *//*

    protected JSONObject httpRequestOfGet(String url, Object... uriValiables) {
        if (uriValiables == null) {
            return httpRequest(url, null, HttpMethod.GET, "");
        }
        return httpRequest(url, null, HttpMethod.GET, uriValiables);
    }

    */
/**
 * POST
 *
 * @param url
 * @param requestJsonUrl
 * @return
 *//*

    protected JSONObject httpRequestOfPost(String url, String requestJsonUrl) {
        return httpRequest(url, requestJsonUrl, HttpMethod.POST, null);
    }

    protected JSONObject httpRequest(String url, String requestJsonUrl, HttpMethod httpMethod, Object... uriValiables) {
        //获取json入参
        JSONObject requestParams = getJsonByUrl(requestJsonUrl);
        //发送http请求
        logger.info("1,发送请求url:" + url + ",入参:" + requestParams);
        httpEntity = new HttpEntity<>(requestParams, httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, httpMethod, httpEntity, String.class, uriValiables);
        String responseResult = responseEntity.getBody();
        logger.info("2,发送请求url:" + url + ",出参:" + responseResult);
        JSONObject response = JSONObject.parseObject(responseResult);
        //检查出参格式
        checkResponseResult(response);
        return response;
    }

    private JSONObject getJsonByUrl(String requestJsonUrl) {
        if (StringUtils.isEmpty(requestJsonUrl)) {
            return null;
        }
        String path = getClassLoader().getResource(requestJsonUrl).getPath();
        JSONObject response = null;
        try {
            //处理中文空格占位符问题
            path = URLDecoder.decode(path, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            StringBuffer requestParamsString = new StringBuffer();
            do {
                String line = bufferedReader.readLine();
                requestParamsString.append(line);
            } while (!requestParamsString.toString().endsWith("}"));
            response = JSONObject.parseObject(requestParamsString.toString());
            logger.info("-------------获取POST请求入参body成功！body:" + response.toJSONString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private void checkResponseResult(JSONObject responseResult) {
        logger.info("3,检查出参成功！");
    }

    private Properties getProperties(String fileUrl) {
        Properties properties = null;
        try {
            properties = PropertiesLoaderUtils.loadAllProperties(fileUrl);
        } catch (IOException e) {
            System.out.println(fileUrl + "文件未找到!");
            e.printStackTrace();
        }
        return properties;
    }
}
*/
