package com.chatpro.chat.Config;

@Configuration
@Slf4j
public class WebMvcConfiguration extends WebMvcConfigurationSupport{

	@Autowired
	private JwtInterceptor jwtInterceptor;

	@Autowired
	private Executor getMsgPool;

	@Autowired
	private Executor sndMsgPool;

	@Autowired
	private MessageWorker msgWorker;

	@Autowired
	private MessageCtner msgCtner;

	//在别的类里得到其他的，只要这样注入就行，因为都是在一个IOC容器中

	protected void addInterceptors(InterceptorRegistry registry){
		log.info("开始注册拦截器...");
		registry.addInterceptor(jwtInterceptor);
			.addPathPatterns("/user/**")
			.excludePathPatterns("/user/login");


	}


	//不能有任何参数，不能是static方法，不能抛出未经检查的异常
	@PreDestroy
	public void preDestroy(){
		log.info("Now destroying springboot server...");
		msgWorker.setShutdown();
		LocalTime lt_start = LocalTime.now();
		LocalTime lt_end = LocalTime.now();
		boolean poolshutdownSuccess = true;
		while(getMsgPool.getActiveCount() != 0 || sndMsgPool.getActiveCount() != 0){
			lt_end = LocalTime.now();
			if(lt_start.plusMinutes(1).isBefore(lt_end)){
				poolshutdownSuccess = false;
				break;
			}
		}
		if(poolshutdownSuccess == true){
			msgCtner.destroy();
		}
		
	}


}
