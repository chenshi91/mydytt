/* created by chenshi at 2018-11-21 */
package com.dytt.module.user.zuul.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AccessFilter extends ZuulFilter {

    // //每秒产生1000个令牌
    public static final RateLimiter RATE_LIMITER = RateLimiter.create(1000);

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
        return true;//表示是否需要执行该filter，true表示执行，false表示不执行,这里可以对指定url拦截也可以对全部请求拦截
    }

    @Override
    public Object run() throws ZuulException {
//        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        HttpServletResponse response = requestContext.getResponse();
        response.setHeader("Access-Control-Allow-Origin", "*");//允许跨域

        String token = request.getHeader("token");
        String requestURI = request.getRequestURI();

        /*if (StringUtils.isEmpty(token)) {
            //过滤该请求，不往下级服务去转发请求，到此结束
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(401);
            requestContext.setResponseBody("{\"result\":\"accessToken为空!\"}");
            requestContext.getResponse().setContentType("text/html;charset=UTF-8");
            return null;
        }*/

        //就相当于每调用一次tryAcquire()方法，令牌数量减1，当1000个用完后，那么后面进来的用户无法访问上面接口
        //当然这里只写类上面一个接口，可以这么写，实际可以在这里要加一层接口判断。
        if (!RATE_LIMITER.tryAcquire()) {
            requestContext.setSendZuulResponse(false);
            //HttpStatus.TOO_MANY_REQUESTS.value()里面有静态代码常量
            requestContext.setResponseStatusCode(HttpStatus.TOO_MANY_REQUESTS.value());
        }
        return null;
    }
}
