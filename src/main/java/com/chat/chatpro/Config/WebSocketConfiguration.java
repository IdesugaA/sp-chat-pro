package com.chat.chatpro.config;

//配置类，作用是扫描@ServerEndpoint
@Configuration 
public class WebSocketConfiguration{

	@Bean
	public ServerEndpointExporter serverEndpointExporter(){
		return new ServerEndpointExporter();
	}


}
