package com.chat.chatpro.Pojo.Entity;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Friend{

	private String id;
	private String user1Id;
	private String user2Id;
	private LocalDateTime addTime;
}
