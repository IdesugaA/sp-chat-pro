package com.chat.chatpro.Pojo.Entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Message implements Serializable {

	private String id;
	private String msg;
	private String fromUserId;
	private String toUserId;
	private String picUrl;
	private String expIds;
}
