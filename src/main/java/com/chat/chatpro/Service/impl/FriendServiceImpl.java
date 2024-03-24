package com.chat.chatpro.Service.impl;

import com.chat.chatpro.Mapper.FriendMapper;
import com.chat.chatpro.Mapper.UserMapper;
import com.chat.chatpro.Pojo.DTO.FriendDTO;
import com.chat.chatpro.Pojo.Entity.Friend;
import com.chat.chatpro.Service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FriendServiceImpl implements FriendService {

	@Autowired
	private FriendMapper friendMapper;

	@Autowired
	private UserMapper userMapper;

	public boolean friendAdd(FriendDTO friendDTO){
		LocalDateTime addTime = friendDTO.getTime();
		String user1Id = friendDTO.getFromUserId();
		String user2Id = friendDTO.getToUserId();

		//检验关系是否已经存在
		//以及两个用户是否存在
		int rel_count = friendMapper.queryExist(user1Id,user2Id);
		int ur1_count = userMapper.queryExist(user1Id);
		int ur2_count = userMapper.queryExist(user2Id);

		if(rel_count != 0 || (ur1_count == 0 || ur2_count == 0)){
			return false;
		}

		Friend newfriend = new Friend();
		newfriend.setUser1Id(user1Id);
		newfriend.setUser2Id(user2Id);
		newfriend.setAddTime(addTime);
		friendMapper.insert(newfriend);

		return true;
		
	}

	public boolean friendDel(FriendDTO friendDTO){
		String user1Id = friendDTO.getFromUserId();
		String user2Id = friendDTO.getToUserId();

		int rel_count = friendMapper.queryExist(user1Id,user2Id);
		if(rel_count == 0){
			return false;
		}
		
		friendMapper.delete(user1Id,user2Id);
		return true;

	}


}
