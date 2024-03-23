package com.chat.chatpro.ExtraWorker;


@Component
public class MessageWorker{

	@Autowired
	private MessageCtner msgCtner;


	private volatile boolean shutdown = false;

	public MessageWorker(){
		checkMsg();	
	}

	public void setShutdown(){
		shudown = true;
	}

	@Async("getMsgPool")
	public void checkMsg(){
		while(1){
			if(shutdown == true){
				break;
			}
			if(msgCtner.isEmpty()){
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
