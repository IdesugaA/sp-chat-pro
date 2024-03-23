package com.chat.chatpro.Properties;

//jwt令牌信息，所有用户的jwt令牌基本属性都是这样的

@Component
@ConfigurationProperties(prefix="")
@Data
public class JwtProperties{

	//管理端
	private String adminSecretKey;
	private long adminTtl;
	private String adminTokenName;

	//用户端
	private String userSecretKey;
	private long userTtl;
	private String userTokenName;

}
