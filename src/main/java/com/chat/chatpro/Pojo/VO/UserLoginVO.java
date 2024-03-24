package com.chat.chatpro.Pojo.VO;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginVO implements Serializable {

	private String userid;
	private String token;

}
