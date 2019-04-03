/*created by chenshi at   2019/4/2*/
package com.dytt.common.model.log;

import com.dytt.common.model.constance.WebConstance;
import com.dytt.common.model.utils.WebUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.deploy.util.ArrayUtil;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Data
public class RequestLog {

    private String requestId;
    private String url;
    private String method;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, Object> paramsMap;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String requestBody;
    @JsonIgnore
    private final HttpServletRequest request = WebUtils.getRequest();

    public RequestLog() {
        this.requestId = getRequestId();
        this.url = request.getRequestURI();
        this.method = request.getMethod();
        this.paramsMap = getParamsMap(request);
        this.requestBody = getRequestBody(request);
    }

    private String getRequestBody(HttpServletRequest request) {
        Object requestBody = request.getAttribute(WebConstance.REQUEST_BODY);


        return (String) requestBody;
    }

    private Map<String, Object> getParamsMap(HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<>();
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
        for (Map.Entry<String, String[]> entry : entries) {
            String key = entry.getKey();
            String[] value = entry.getValue();
            map.put(key, ArrayUtil.arrayToString(value));
        }
        return map;
    }

    private String getRequestId() {
        Object requestId = request.getAttribute(WebConstance.REQUEST_ID);
        if (requestId == null) {
            requestId = System.currentTimeMillis();
            request.setAttribute(WebConstance.REQUEST_ID, requestId);
        }
        return "" + requestId;
    }
}
