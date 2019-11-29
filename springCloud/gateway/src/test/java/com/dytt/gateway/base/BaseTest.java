/* created by chenshi at 2018-12-03 */
package com.dytt.gateway.base;

import com.alibaba.fastjson.JSONObject;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.springframework.boot.web.client.RootUriTemplateHandler;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.URLDecoder;
import java.util.*;

@Slf4j
public abstract class BaseTest {

    protected RestTemplate restTemplate;
    protected HttpEntity httpEntity;
    protected HttpHeaders httpHeaders;
    protected Properties properties;
    protected String ipHost;

    protected abstract ClassLoader getClassLoader();

    protected abstract String getAppUserInfoKey();

    protected abstract String getSucResponseCode();

    @Before
    public void before() {
        properties = getProperties("test.properties");
        ipHost = properties.getProperty("ip_host");
        if (StringUtils.isEmpty(ipHost)) {
            log.info("-------没有设置ip:port-------------------");
            throw new RuntimeException("没有设置ip端口号");
        }
        log.info("----------初始化ipHost:" + ipHost);
        restTemplate = new RestTemplate() {{
            setUriTemplateHandler(new RootUriTemplateHandler(ipHost));
            setInterceptors(new ArrayList<ClientHttpRequestInterceptor>(1) {{
                add(new BasicAuthenticationInterceptor("xjl", "123"));
            }});
        }};
        //设置token
        httpHeaders = new HttpHeaders() {{
            add("sign", properties.getProperty("sign"));
            add("app-user-info-key", getAppUserInfoKey());
        }};
        log.info("----------初始化httpHeaders:{}", httpHeaders);

    }

    /**
     * GET
     *
     * @param url          地址
     * @param uriValiables 参数
     * @return 返回值
     */
    protected ResponseEntity<JSONObject> httpRequestOfGET(String url, Object... uriValiables) {
        if (uriValiables == null) {
            return httpRequest(url, null, null, HttpMethod.GET, true, "");
        }
        return httpRequest(url, null, null, HttpMethod.GET, true, uriValiables);
    }

    /**
     * POST
     *
     * @param url            地址
     * @param requestJsonUrl 入参
     * @return 出参
     */
    protected ResponseEntity<JSONObject> httpRequestOfPOST(String url, String requestJsonUrl) {
        return httpRequest(url, requestJsonUrl, null, HttpMethod.POST, true, "");
    }

    protected ResponseEntity<JSONObject> httpRequestOfPOST(String url, HashMap<String, Object> files) {
        return httpRequest(url, null, files, HttpMethod.POST, true, "");
    }

    protected ResponseEntity<JSONObject> httpRequest(String url, String requestJsonUrl, HashMap<String, Object> files, HttpMethod httpMethod, Boolean checkResCode, Object... uriValiables) {
        //获取json入参
        JSONObject requestParams = getJsonByUrl(requestJsonUrl);

        httpEntity = new HttpEntity<>(requestParams, httpHeaders);
        switch (httpMethod) {
            case GET:
                break;
            case POST:
                if (requestParams == null) {
                    MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
                    for (Map.Entry<String, Object> fileEntity : files.entrySet()) {
                        form.add(fileEntity.getKey(), new FileSystemResource((String) fileEntity.getValue()));
                    }
                    httpEntity = new HttpEntity<>(form, httpHeaders);
                }
                break;
            default:
                break;
        }
        log.info("1,发送请求url:{},httpEntity:{}", url, httpEntity);
        ResponseEntity<JSONObject> responseEntity = restTemplate.exchange(ipHost + url, httpMethod, httpEntity, JSONObject.class, uriValiables);
        JSONObject response = responseEntity.getBody();
        //检查出参格式
        if (checkResCode) {
            if (getSucResponseCode().equals(response.getString("code"))) {
                log.info("2,检查出参成功！");
            } else {
                log.info("2,检查出参失败:{}", response);
                throw new RuntimeException("检查出参失败");
            }
        }
        log.info("start------------------------------------------------------------------------");
        log.info("{");
        for (Map.Entry<String, Object> entry : response.entrySet()) {
            switch (entry.getKey()) {
                case "data":
                    log.info("  data:{");
                    printResponse2Space((LinkedHashMap<String, Object>) entry.getValue());
                    log.info("  }");
                    break;
                case "_source":
                    log.info("  _source:{");
                    printResponse2Space((LinkedHashMap<String, Object>) entry.getValue());
                    log.info("  }");
                    break;
                default:
                    log.info("  {}:{}", entry.getKey(), entry.getValue());
                    break;
            }
        }
        log.info("}");
        log.info("--------------------------------------------------------------------------end");
        return responseEntity;
    }

    private void printResponse2Space(LinkedHashMap<String, Object> entry) {
        for (Map.Entry<String, Object> dataEntity : entry.entrySet()) {
            log.info("    {}:{}", dataEntity.getKey(), dataEntity.getValue());
        }
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
            String line = "";
            do {
                line = bufferedReader.readLine();
                if (StringUtil.isNullOrEmpty(line)) {
                    continue;
                }
                requestParamsString.append(line);
            } while (!StringUtil.isNullOrEmpty(line));
            response = JSONObject.parseObject(requestParamsString.toString());
            log.info("-------------获取POST请求入参body成功！body:" + response.toJSONString());
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

    protected Properties getProperties(String fileUrl) {
        Properties properties = null;
        try {
            properties = PropertiesLoaderUtils.loadAllProperties(fileUrl);
        } catch (IOException e) {
            log.info("{}文件未找到!", fileUrl);
            e.printStackTrace();
        }
        return properties;
    }

    public void searchEs(String url, Object... params) {
        reloadIpHost();
        this.httpRequest(url, null, null, HttpMethod.GET, false, params == null ? "" : params);
    }

    protected void reloadIpHost() {
        ipHost = properties.getProperty("ip_host_es");
        restTemplate = new RestTemplate() {{
            setUriTemplateHandler(new RootUriTemplateHandler(ipHost));
            setInterceptors(new ArrayList<ClientHttpRequestInterceptor>(1) {{
                add(new BasicAuthenticationInterceptor("xjl", "123"));
            }});
        }};
        log.info("重置ip_host:{}", ipHost);
    }

    public void updateEs(String url, String requestJsonUrl) {
        url = url.concat("/_update");
        reloadIpHost();
        this.httpRequest(url, requestJsonUrl, null, HttpMethod.POST, false);
    }

}
