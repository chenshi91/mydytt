/* created by chenshi at 2019-01-30 */
package com.dytt.common.model.utils;

import com.dytt.common.model.constance.WebConstance;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtils {

    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return requestAttributes == null ? null : ((ServletRequestAttributes) requestAttributes).getRequest();
    }

    public static HttpServletResponse getResponse() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return requestAttributes == null ? null : ((ServletRequestAttributes) requestAttributes).getResponse();
    }

    public String getRequestId() {
        Object requestId = getRequest().getAttribute(WebConstance.REQUEST_ID);
        if (requestId == null) {
            requestId = System.currentTimeMillis();
            getRequest().setAttribute(WebConstance.REQUEST_ID, requestId);
        }
        return requestId.toString();
    }

}
