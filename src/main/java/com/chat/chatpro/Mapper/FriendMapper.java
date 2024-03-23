package com.chat.chatpro.Mapper;

@Mapper
public interface FriendMapper{

	public void insert(Friend fri);

	@Select("select count(*) from friend_table where (user1Id = #{user1Id} and user2Id = #{user2Id}"+
	") or (#{user1Id = #{user2Id} and user2Id = #{user1Id})")
	public int queryExist(String user1Id , String user2Id);

	@Delete("delete from friend_table where (user1Id = #{user1Id} and user2Id = #{user2Id}"+
	") or (#{user1Id = #{user2Id} and user2Id = #{user1Id})")
	public void delete(String user1Id , String user2Id);

}
