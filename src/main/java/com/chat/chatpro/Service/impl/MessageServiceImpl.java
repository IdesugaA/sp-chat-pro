package com.chat.chatpro.Service.impl;

import com.chat.chatpro.Exception.NotFriendException;
import com.chat.chatpro.Mapper.FriendMapper;
import com.chat.chatpro.Service.MessageService;
import com.chat.chatpro.WebSocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import com.chat.chatpro.Pojo.Entity.Message;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private FriendMapper friendMapper;

	public void sendsgmsg(Message msg){
		String fromUserId = msg.getFromUserId();
	        String toUserId = msg.getToUserId();
	
		int rel_count = friendMapper.queryExist(fromUserId,toUserId);
		if(rel_count == 0){
			throw new NotFriendException();
		}

		WebSocketServer.sendMessage(msg);
	}


}
