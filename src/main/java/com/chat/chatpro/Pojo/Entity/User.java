package com.chat.chatpro.Entity;


@Data
public class User{
	//项目约定实体类id统一用String类型，但在数据库里是整型
	private String userid;
	private String username;
	private String password;
	private LocalDateTime createTime;
	private List<Integer> friendIdList;

}
