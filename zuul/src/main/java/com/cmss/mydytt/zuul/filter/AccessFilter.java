/* created by chenshi at 2018-11-21 */
package com.cmss.mydytt.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AccessFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";//定义filter的类型，有pre、route、post、error四种
    }

    @Override
    public int filterOrder() {
        return 0;//定义filter的顺序，数字越小表示顺序越高，越先执行
    }

    @Override
    public boolean shouldFilter() {
        return true;//表示是否需要执行该filter，true表示执行，false表示不执行
    }

    @Override
    public Object run() throws ZuulException {
//        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        HttpServletResponse response = requestContext.getResponse();
        response.setHeader("Access-Control-Allow-Origin", "*");//允许跨域

        String token = request.getHeader("token");
        /*if (StringUtils.isEmpty(token)) {
            //过滤该请求，不往下级服务去转发请求，到此结束
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(401);
            requestContext.setResponseBody("{\"result\":\"accessToken为空!\"}");
            requestContext.getResponse().setContentType("text/html;charset=UTF-8");
            return null;
        }*/
        return null;
    }
}
