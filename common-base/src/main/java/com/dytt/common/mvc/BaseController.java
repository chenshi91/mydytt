/* created by chenshi at 2018-11-16 */
package com.dytt.common.mvc;

import com.dytt.common.constance.WebConstance;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.UnknownHostException;

public abstract class BaseController {

    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return requestAttributes == null ? null : ((ServletRequestAttributes) requestAttributes).getRequest();
    }

    public static HttpServletResponse getResponse() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return requestAttributes == null ? null : ((ServletRequestAttributes) requestAttributes).getResponse();
    }

    public static String getRequestId() {
        Object requestId = getRequest().getAttribute(WebConstance.REQUEST_ID);
        if (requestId == null) {
            requestId = System.currentTimeMillis();
            getRequest().setAttribute(WebConstance.REQUEST_ID, requestId);
        }
        return "" + requestId;
    }

    public static String getIp() {
        String ip = null;
        try {
            ip = InetAddress.getLocalHost().getHostAddress().toString();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return ip;
    }

}
