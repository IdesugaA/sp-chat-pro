package com.chat.chatpro.Config;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@ConfigurationProperties(prefix = "spring.threadpool")
@EnableAsync
@Slf4j
@Data
public class TaskExecutorConfig{

	private int getMsgPoolcorePoolSize;
	
	private int getMsgPoolmaxPoolSize;

	private int getMsgPoolqueueCapacity;

	private int sndMsgPoolcorePoolSize;
	
	private int sndMsgPoolmaxPoolSize;

	private int sndMsgPoolqueueCapacity;


	@Bean(name = "getMsgPool")
	public ThreadPoolTaskExecutor getmsgPool(){
		ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
		log.info("getMsgPool开始注入");
		pool.setCorePoolSize(getMsgPoolcorePoolSize);
		pool.setMaxPoolSize(getMsgPoolmaxPoolSize);
		pool.setQueueCapacity(getMsgPoolqueueCapacity);
		pool.initialize();
		return pool;
	}

	@Bean(name = "sndMsgPool")
	public ThreadPoolTaskExecutor sndmsgPool(){
		ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
		pool.setCorePoolSize(sndMsgPoolcorePoolSize);
		pool.setMaxPoolSize(sndMsgPoolmaxPoolSize);
		pool.setQueueCapacity(sndMsgPoolqueueCapacity);
		pool.initialize();
		return pool;
	}


}
