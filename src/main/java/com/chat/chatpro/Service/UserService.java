package com.chat.chatpro.Service;


import com.chat.chatpro.Pojo.DTO.UserLoginDTO;
import com.chat.chatpro.Pojo.DTO.UserRegisterDTO;

public interface UserService{

	public void login(UserLoginDTO userLoginDTO);

	public String register(UserRegisterDTO userRegisterDTO);

}
