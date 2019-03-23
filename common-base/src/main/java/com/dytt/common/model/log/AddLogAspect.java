/* created by chenshi at 2019-02-02 */
package com.dytt.common.model.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;

import java.lang.reflect.Method;
import java.util.Date;

@Aspect
public class AddLogAspect implements Ordered {
    private final int order;

//    protected Logger logger;

    @Around(value = "@annotation(com.dytt.common.model.log.AddLog)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        /**1,获取目标对象,方法*/
        Object target = pjp.getTarget();
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
//        logger.info("--------service----start:" + target.getClass() + "." + method.getName());
        Date startDate = new Date();
        Object proceed = null;
        try {
            proceed = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
//        logger.info("--------service----end:" + target.getClass() + "." + method.getName() + ",耗时毫秒数:" + (new Date().getTime() - startDate.getTime()) + "毫秒");
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
