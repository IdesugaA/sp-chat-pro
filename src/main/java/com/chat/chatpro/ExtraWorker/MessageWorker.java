package com.chat.chatpro.ExtraWorker;


import com.chat.chatpro.WebSocket.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import com.chat.chatpro.Pojo.Entity.Message;
@Component
@Slf4j
public class MessageWorker{

	@Autowired
	private MessageCtner msgCtner;


	private volatile boolean shutdown = false;

	public MessageWorker(){
		log.info("MsgWorker开始注入");
		checkMsg();
	}

	public void setShutdown(){
		shutdown = true;
	}

	@Async("getMsgPool")
	public void checkMsg(){
		while(true){
			if(shutdown == true){
				break;
			}
			if(msgCtner == null || msgCtner.isEmpty()){
				continue;
			}
			sendMsg(msgCtner.pop());
		}
	}


	@Async("sndMsgPool")
	public void sendMsg(Message msg){
		WebSocketServer.sendMessage(msg);
	}


}
