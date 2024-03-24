package com.chat.chatpro.Pojo.DTO;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class FriendDTO implements Serializable {

	private LocalDateTime time;
	private String fromUserId;
	private String toUserId;

}
