package com.chat.chatpro.Mapper;

@Mapper
public interface ShareMapper{

	public void insert(Share share);

	@Select("select count(*) from share_info where id = #{id}")
	public int queryExist(String id);

	@Delete("delete from share_info where id = #{id}")
	public void delete(String id);

	@Select("select * from share_info")
	public void pageQuery();

}
