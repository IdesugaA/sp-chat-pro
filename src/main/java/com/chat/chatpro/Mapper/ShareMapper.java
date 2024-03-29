package com.chat.chatpro.Mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.chat.chatpro.Pojo.Entity.Share;

import java.util.List;

@Mapper
public interface ShareMapper{

	public void insert(Share share);

	@Select("select count(*) from share_info where id = #{id}")
	public int queryExist(String id);

	@Delete("delete from share_info where id = #{id}")
	public void delete(String id);

	@Select("select * from share_info")
	public List pageQuery();

}
