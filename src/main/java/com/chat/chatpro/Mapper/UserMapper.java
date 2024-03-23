package com.chat.chatpro.Mapper;

@Mapper
public interface UserMapper{

	@Select("select password from user_info where userid = #{userid}")
	public String pwQuery(String userid);	

	@Select("select count(*) from user_info where userid = #{userid}")
	public int queryExist(String userid);

	public void insert(User user);

}
