package com.chat.chatpro.Service.impl;


public class UserServiceImpl implements UserService{

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

	public Integer register(UserRegisterDTO userRegisterDTO){
		String username = userRegisterDTO.getUserid();
		String password = userRegisterDTO.getPassword();
		userMapper.insert(userid,password);
		String userid = userMapper.queryByName(username);
		if(userid != null) throw AccountAlreadyExistException();
		User newuser = new User();
		newuser.setUsername(username);
		newuser.setPassword(password);
		newuser.setCreateTime(LocalDateTime.now());
		userMapper.insert(newuser);
		return newuser.getUserid();
	}


}
