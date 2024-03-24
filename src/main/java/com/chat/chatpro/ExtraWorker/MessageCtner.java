package com.chat.chatpro.ExtraWorker;

import com.chat.chatpro.Exception.MsgCtnerAlreadyExistException;
import com.chat.chatpro.Exception.MsgCtnerCreatedFailException;
import com.chat.chatpro.Exception.MsgCtnerNotExistException;
import com.chat.chatpro.Mapper.MessageMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.chat.chatpro.Pojo.Entity.Message;
import java.util.LinkedList;

@Component
@Slf4j
public class MessageCtner{

	@Autowired
	private MessageMapper msgMapper;

	private static LinkedList<Message> msgStack = null;
	//spring在创建bean实例时默认会调用其无参构造函数

	public boolean isEmpty(){
		if(msgStack != null){
			synchronized(msgStack){
				return msgStack.isEmpty();
			}
		}
		return false;
	}

	public MessageCtner(){
		log.info("MsgCtner开始注入");
		if(msgStack != null){
			throw new MsgCtnerAlreadyExistException();
		}
		msgStack = msgMapper.getList();
		if(msgStack == null){
			msgStack = new LinkedList<>();
		}
	}

	public boolean push(Message msg){
		if(msgStack == null){
			return false;
		}
		synchronized(msgStack){
			msgStack.push(msg);
		}
		return true;
	}	

	public Message pop(){
		if(msgStack == null || isEmpty()){
			return null;
		}
		synchronized(msgStack){
			return msgStack.pop();
		}
	}

	public void destroy(){
		if(msgStack == null){
			throw new MsgCtnerNotExistException();
		}
		msgMapper.batchInsert(msgStack);
	}


}
