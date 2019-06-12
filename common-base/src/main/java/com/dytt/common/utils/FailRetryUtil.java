package com.dytt.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.function.IntConsumer;

/**
 * @author chenshi
 * @date 2019-05-31
 */
@Slf4j
public class FailRetryUtil {

    /**
     * 重试(重试10次,每次睡眠:200毫秒)
     *
     * @param service    业务方法
     * @param sucResult  成功返回处理
     * @param failResult 失败返回处理
     */
    public static void operate(Runnable service, IntConsumer sucResult, IntConsumer failResult) {
        operate(10, 200L, service, sucResult, failResult);
    }

    /**
     * 重试
     *
     * @param retryCount        重试次数
     * @param retryIntervalTime 重试间隔时间
     * @param service           业务方法
     * @param sucResult         成功返回处理
     * @param failResult        失败返回处理
     */
    public static void operate(Integer retryCount, Long retryIntervalTime, Runnable service, IntConsumer sucResult, IntConsumer failResult) {
        for (Integer i = 0; i < retryCount; i++) {
            try {
                service.run();
                if (i>0) {
                    log.info("第{}次重试成功，执行成功逻辑",i);
                }
                sucResult.accept(i + 1);
                break;
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    Thread.sleep(retryIntervalTime);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                log.info("第{}次重试失败，睡眠:{}毫秒,执行失败逻辑",i,retryIntervalTime);
                failResult.accept(i + 1);
                continue;
            }
        }
    }

}
