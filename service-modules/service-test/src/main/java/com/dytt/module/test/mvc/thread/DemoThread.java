/*created by chenshi at   2019/4/6*/
package com.dytt.module.test.mvc.thread;

import com.dytt.common.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.PostConstruct;

public class DemoThread implements Runnable {

    @Autowired
    ThreadPoolTaskExecutor asyncThreadPool;


    @PostConstruct//开启线程
    public void start() {
//        new Thread(this).start();
        asyncThreadPool.submit(new Thread(this));//添加进异步线程池执行
    }

    @Override
    public void run() {
        while (true) {
            LogUtil.info("-------------do--stream-thread--");
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
