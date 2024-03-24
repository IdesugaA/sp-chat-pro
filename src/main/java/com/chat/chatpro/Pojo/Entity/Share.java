package com.chat.chatpro.Pojo.Entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Share{

	private String id;	
	private LocalDateTime publishTime;
	private String publisherId;
	private String content;

}
