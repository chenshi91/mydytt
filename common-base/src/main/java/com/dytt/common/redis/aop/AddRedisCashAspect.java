/*created by chenshi at   2019/4/2*/
package com.dytt.common.redis.aop;

import com.alibaba.fastjson.JSONObject;
import com.dytt.common.log.AddLogAspect;
import com.dytt.common.utils.JsonUtil;
import com.dytt.common.utils.LogUtil;
import com.sun.deploy.util.ArrayUtil;
import org.apache.commons.lang.StringEscapeUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Aspect
public class AddRedisCashAspect implements Ordered {

    @Autowired
    RedisTemplate redisTemplate;

    private final int order;

    protected Logger logger = LoggerFactory.getLogger(AddLogAspect.class);

    @Around(value = "@annotation(com.dytt.common.redis.aop.AddRedisCash)")
    public Object around(ProceedingJoinPoint pjp) {
        /**1,获取目标对象,方法*/
        Object target = pjp.getTarget();
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();

        String key = getKey(pjp.getArgs(), method);
        LogUtil.info("---requestLog--start:{}");
        Date startDate = new Date();

        Object proceed = null;
        try {
            proceed = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            LogUtil.info("--------request----end--error:{},耗时毫秒数:{}毫秒", throwable, (System.currentTimeMillis() - startDate.getTime()));

        }
        String value2 = (String) redisTemplate.boundValueOps(key).get();
        String s = StringEscapeUtils.unescapeJava(value2);//去除转义字符
        //去除首尾多余“”
        StringBuffer sb = new StringBuffer(s);
        sb.deleteCharAt(0);
        sb.deleteCharAt(sb.length()-1);
        JSONObject jsonObject = JsonUtil.parseJsonObject(sb.toString());
//        redisTemplate.set

        String value = JsonUtil.toJSONString(proceed);
        redisTemplate.getExpire("");
        redisTemplate.opsForValue().set(key,value,60*30L,TimeUnit.SECONDS);
//        redisTemplate.opsForValue().set
        LogUtil.info("--------addRedisCash-end-ok  result:{},耗时毫秒数:{}毫秒", proceed, (System.currentTimeMillis() - startDate.getTime()));
        return proceed;
    }

    private String getKey(Object[] args, Method method) {
        StringBuffer key = new StringBuffer(method.getDeclaringClass().getName())
                .append(":").append(method.getName());

        for (Object arg : args) {
            if (arg instanceof String[]) {
                arg=ArrayUtil.arrayToString((String[]) arg);
            }
            key.append(":").append(arg);
        }
        return key.toString();
    }


    public AddRedisCashAspect(int order) {
        this.order = order;
    }

    @Override
    public int getOrder() {
        return this.order;
    }
}
