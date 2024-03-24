package com.chat.chatpro.Pojo.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginDTO implements Serializable {
	
	private String userid;
	private String password;

}
