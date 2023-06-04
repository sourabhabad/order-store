package com.planet.order.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Sourabh.k
 * @see org.springframework.scheduling.annotation.AsyncConfigurer
 * <p>
 * Async Configuration
 */
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    private final Integer corePoolSize;
    private final Integer maxPoolSize;
    private final Integer queueCapacity;
    private final String threadNamePrefix;

    public AsyncConfig(@Value("${async.threadpool.corePoolSize:10}") Integer corePoolSize,
                       @Value("${async.threadpool.maxPoolSize:50}") Integer maxPoolSize,
                       @Value("${async.threadpool.queueCapacity:5000}") Integer queueCapacity,
                       @Value("${async.threadpool.threadNamePrefix:taskExecutor-}") String threadNamePrefix) {
        this.corePoolSize = corePoolSize;
        this.maxPoolSize = maxPoolSize;
        this.queueCapacity = queueCapacity;
        this.threadNamePrefix = threadNamePrefix;
    }

    /**
     * Create Executor beans
     */
    @Override
    @Bean(name = "threadPoolTaskExecutor")
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix(threadNamePrefix);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}