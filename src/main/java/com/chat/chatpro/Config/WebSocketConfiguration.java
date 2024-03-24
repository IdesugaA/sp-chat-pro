package com.chat.chatpro.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

//配置类，作用是扫描@ServerEndpoint
@Configuration
public class WebSocketConfiguration{

	@Bean
	public ServerEndpointExporter serverEndpointExporter(){
		return new ServerEndpointExporter();
	}


}
