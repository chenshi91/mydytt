/* created by chenshi at 2019-02-14 */
package com.cmss.dytt.common.web.bean;

import com.cmss.dytt.common.web.filter.RestRequestFilter;
import com.cmss.dytt.common.web.log.AddLogAspect;
import com.cmss.dytt.common.web.threadPool.MyThreadPoolTaskExecutor;
import com.cmss.dytt.common.web.utils.SendMailService;
import com.cmss.dytt.common.web.utils.SendMailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class CommonConfiguration {

    @Bean
    public RestRequestFilter restRequestFilter() {
        return new RestRequestFilter();
    }

    @Bean
    public AddLogAspect addLogAspect() {
        return new AddLogAspect(122);
    }

    @Bean
    public SendMailService sendMailService() {
        return new SendMailServiceImpl();
    }

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
        return poolTaskExecutor;
    }
}
