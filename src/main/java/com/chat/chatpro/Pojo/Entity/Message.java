package com.chat.chatpro.Entity;

@Data
public class Message implements Serializble{

	private int id;
	private String msg;
	private String fromUserId;
	private String toUserId;
	private String picUrl;
	private String expIds;
}
