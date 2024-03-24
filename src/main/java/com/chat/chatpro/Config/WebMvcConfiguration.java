package com.chat.chatpro.Config;

import com.chat.chatpro.ExtraWorker.MessageCtner;
import com.chat.chatpro.ExtraWorker.MessageWorker;
import com.chat.chatpro.Interceptor.JwtInterceptor;
import jakarta.annotation.Nonnull;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;
import java.time.LocalTime;


@Configuration
@Slf4j
@EnableSwagger2WebMvc
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

	@Autowired
	private JwtInterceptor jwtInterceptor;

	@Autowired
	private ThreadPoolTaskExecutor getMsgPool;

	@Autowired
	private ThreadPoolTaskExecutor sndMsgPool;

	@Autowired
	private MessageWorker msgWorker;

	@Autowired
	private MessageCtner msgCtner;

	//在别的类里得到其他的，只要这样注入就行，因为都是在一个IOC容器中

	@Override
	public void addInterceptors(InterceptorRegistry registry){
		log.info("拦截器开始注册");
		registry.addInterceptor(jwtInterceptor)
			.addPathPatterns("/user/**")
			.excludePathPatterns("/user/login")
				.excludePathPatterns("/user/test");


	}

	@Bean(value = "dockerBean")
	public Docket dockerBean() {
		//指定使用Swagger2规范
		Docket docket=new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(new ApiInfoBuilder()
						//描述字段支持Markdown语法
						.description("# Knife4j RESTful APIs")
						.termsOfServiceUrl("https://doc.xiaominfo.com/")
						.contact("xiaoymin@foxmail.com")
						.version("1.0")
						.build())
				//分组名称
				.groupName("用户服务")
				.select()
				//这里指定Controller扫描包路径
				.apis(RequestHandlerSelectors.basePackage("com.chat.chatpro.Controller"))
				.paths(PathSelectors.any())
				.build();
		return docket;
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

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}


}
