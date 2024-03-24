package com.chat.chatpro.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.chat.chatpro.Pojo.Entity.User;

@Mapper
public interface UserMapper{

	@Select("select password from user_info where userid = #{userid}")
	public String pwQuery(String userid);	

	@Select("select count(*) from user_info where userid = #{userid}")
	public int queryExist(String userid);

	public void insert(User user);

	String queryByName(String username);
}
