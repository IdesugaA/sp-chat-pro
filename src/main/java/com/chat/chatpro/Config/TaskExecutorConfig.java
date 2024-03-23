package com.chat.chatpro.Config;


@Configuration
@ConfigurationProperties(prefix="threadpool")
@EnableAsync
public class ThreadPoolConfig{

	private int getMsgPoolcorePoolSize;
	
	private int getMsgPoolmaxPoolSize;

	private int getMsgPoolqueueCapacity;

	private int sndMsgPoolcorePoolSize;
	
	private int sndMsgPoolmaxPoolSize;

	private int sndMsgPoolqueueCapacity;


	@Bean("getMsgPool")
	public Executor getmsgPool(){
		ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
		pool.setCorePoolSize(getMsgPoolcorePoolSize);
		pool.setMaxPoolSize(getMsgPoolmaxPoolSize);
		pool.setQueueCapacity(getMsgPoolqueueCapacity);
		pool.initialize();
		return pool;
	}

	@Bean("sndMsgPool")
	public Executor sndmsgPool(){
		ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
		pool.setCorePoolSize(getMsgPoolcorePoolSize);
		pool.setMaxPoolSize(getMsgPoolmaxPoolSize);
		pool.setQueueCapacity(getMsgPoolqueueCapacity);
		pool.initialize();
		return pool;
	}


}
