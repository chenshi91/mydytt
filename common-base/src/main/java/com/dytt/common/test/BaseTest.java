/* created by chenshi at 2018-12-03 */
package com.dytt.common.test;

import com.alibaba.fastjson.JSONObject;
import com.dytt.common.constance.CommonResponse;
import com.dytt.common.utils.LogUtil;
import com.dytt.common.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
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

@Slf4j
public abstract class BaseTest {
    public static final String NOT_IP = ":";
    public static final String HTTP_LOCALHOST = "http://localhost:";
    //    protected Logger logger = LoggerFactory.getLogger(BaseTest.class);

    protected RestTemplate restTemplate = new RestTemplate();
    protected HttpEntity httpEntity;
    protected HttpHeaders httpHeaders = new HttpHeaders();

    protected abstract ClassLoader getClassLoader();

    protected abstract String getIpHost();

    @Before
    public void before() {
        //初始化url
        String ipHost = getIpHost();
        if (!ipHost.contains(NOT_IP)) {
            log.info("-------没有设置ip----初始化ip:{}",HTTP_LOCALHOST);
            ipHost= HTTP_LOCALHOST.concat(ipHost);
        }
        RootUriTemplateHandler rootUriTemplateHandler = new RootUriTemplateHandler(ipHost);
        restTemplate.setUriTemplateHandler(rootUriTemplateHandler);
        log.info("----------初始化ipHost:"+ipHost);
        //初始化interceptors
        String username = "xujiali";
        String password = "xjl";
        ClientHttpRequestInterceptor auth1 = new BasicAuthorizationInterceptor(username, password);
        List<ClientHttpRequestInterceptor> authorizationInterceptorList = new ArrayList<>(1);
        authorizationInterceptorList.add(auth1);
        restTemplate.setInterceptors(authorizationInterceptorList);
        log.info("----------初始化interceptors:username="+username+",password="+password);
        //设置token
        String token = "123456";
        httpHeaders.add("token", token);
        log.info("----------初始化token:"+token);

    }

    /**
     * GET
     *
     * @param url 地址
     * @param uriValiables  参数
     * @return  返回值
     */
    protected JSONObject httpRequestOfGet(String url, Object... uriValiables) {
        if (uriValiables == null) {
            return httpRequest(url, null, HttpMethod.GET, "");
        }
        return httpRequest(url, null, HttpMethod.GET, uriValiables);
    }

    /**
     * POST
     *
     * @param url   地址
     * @param requestJsonUrl    入参
     * @return  出参
     */
    protected JSONObject httpRequestOfPost(String url, String requestJsonUrl) {
        return httpRequest(url, requestJsonUrl, HttpMethod.POST, "");
    }

    protected JSONObject httpRequest(String url, String requestJsonUrl, HttpMethod httpMethod, Object... uriValiables) {
        //获取json入参
        JSONObject requestParams = getJsonByUrl(requestJsonUrl);
        //发送http请求
        LogUtil.info("1,发送请求url:"+url+",入参:"+requestParams);
        httpEntity = new HttpEntity<>(requestParams, httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, httpMethod, httpEntity, String.class, uriValiables);
        String responseResult = responseEntity.getBody();
        LogUtil.info("2,发送请求url:"+url+",出参:"+responseResult);
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
            String line="";
            do {
                line = bufferedReader.readLine();
                if (StringUtil.isNull(line)) {
                    continue;
                }
                requestParamsString.append(line);
            } while (!StringUtil.isNull(line));
            response = JSONObject.parseObject(requestParamsString.toString());
            LogUtil.info("-------------获取POST请求入参body成功！body:"+response.toJSONString());
            bufferedReader.close();
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
        if (CommonResponse.SUCCESS_CODE.equals(responseResult.getString("code"))) {
            LogUtil.info("3,检查出参成功！,{}",responseResult);
        }else {
            throw   new  RuntimeException("检查出参失败");
        }
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
