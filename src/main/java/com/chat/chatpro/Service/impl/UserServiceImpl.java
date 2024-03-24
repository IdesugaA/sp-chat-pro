package com.chat.chatpro.Service.impl;


import com.chat.chatpro.Exception.AccountAlreadyExistException;
import com.chat.chatpro.Exception.PasswordErrorException;
import com.chat.chatpro.Mapper.UserMapper;
import com.chat.chatpro.Pojo.DTO.UserLoginDTO;
import com.chat.chatpro.Pojo.DTO.UserRegisterDTO;
import com.chat.chatpro.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import com.chat.chatpro.Pojo.Entity.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	public void login(UserLoginDTO userLoginDTO){
		String userid = userLoginDTO.getUserid();
		String password = userLoginDTO.getPassword();
		String password_r = userMapper.pwQuery(userid);
		if(!password.equals(password_r)){
			throw new PasswordErrorException();
		}
	}

	public String register(UserRegisterDTO userRegisterDTO){
		String username = userRegisterDTO.getUsername();
		String password = userRegisterDTO.getPassword();
		String userid = userMapper.queryByName(username);
		if(userid != null){
			throw new AccountAlreadyExistException();
		}
		User newuser = new User();
		newuser.setUsername(username);
		newuser.setPassword(password);
		newuser.setCreateTime(LocalDateTime.now());
		userMapper.insert(newuser);
		return newuser.getUserid();
	}


}
