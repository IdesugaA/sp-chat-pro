package com.chat.chatpro.Service.impl;

public class MessageService implements MessageService{

	@Autowired
	private FriendMapper friendMapper;

	public void sendsgmsg(Message msg){
		String fromUserId = msg.getFromUserId();
	        String toUserId = msg.getToUserId();
	
		int rel_count = friendMapper.queryExist(fromUserId,toUserId);
		if(rel_count == 0){
			throw new NotFriendException();
		}

		WebSocketserver.sendMessage(msg,toUsersession);
	}


}
