package com.dytt.common.bean;

import com.dytt.common.threadPool.MyThreadPoolTaskExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author chenshi
 * @date 2019-05-28
 */
@Configuration
public class ThreadPoolConfig {

    @Bean
    public Object asyncThreadPool() {
        ThreadPoolTaskExecutor poolTaskExecutor = new MyThreadPoolTaskExecutor();
        //配置核心线程数
        poolTaskExecutor.setCorePoolSize(5);
        //配置最大线程数
        poolTaskExecutor.setMaxPoolSize(5);
        //配置队列大小
        poolTaskExecutor.setQueueCapacity(99999);
        //配置线程池中的线程的名称前缀
        poolTaskExecutor.setThreadNamePrefix("async-threadPool-");

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        poolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        //执行初始化
        poolTaskExecutor.initialize();
//        poolTaskExecutor.submit(new Thread());
        return poolTaskExecutor;
    }
}
