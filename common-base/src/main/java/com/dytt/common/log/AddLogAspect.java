/* created by chenshi at 2019-02-02 */
package com.dytt.common.log;

import com.dytt.common.utils.LogUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;

import java.lang.reflect.Method;
import java.util.Date;

@Aspect
public class AddLogAspect implements Ordered {
    private final int order;

    protected Logger logger = LoggerFactory.getLogger(AddLogAspect.class);

    @Around(value = "@annotation(com.dytt.common.log.AddLog)||@annotation(org.springframework.web.bind.annotation.GetMapping)@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        /**1,获取目标对象,方法*/
        Object target = pjp.getTarget();
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();

        RequestLog requestLog = new RequestLog();
        LogUtil.info("---requestLog--start:{}", requestLog);
        Date startDate = new Date();
        Object proceed = null;
        try {
            proceed = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            LogUtil.info("--------request----end--error:{},耗时毫秒数:{}毫秒", throwable, (System.currentTimeMillis() - startDate.getTime()));
            throw throwable;
        }
        LogUtil.info("--------requestLog----end--ok  result:{},耗时毫秒数:{}毫秒", proceed, (System.currentTimeMillis() - startDate.getTime()));
        return proceed;
    }

    @Override
    public int getOrder() {
        return order;
    }

    public AddLogAspect(int order) {
        this.order = order;
    }
}
