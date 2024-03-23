package com.chat.chatpro.Pojo.DTO;

@Data
public class FriendAddDTO implements Serializable{

	private LocalDateTime time;
	private String fromUserId;
	private String toUserId;

}
