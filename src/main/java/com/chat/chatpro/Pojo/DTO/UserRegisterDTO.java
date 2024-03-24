package com.chat.chatpro.Pojo.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRegisterDTO implements Serializable {

	private String username;
	private String password;	

}
