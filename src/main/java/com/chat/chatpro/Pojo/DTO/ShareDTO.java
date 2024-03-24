package com.chat.chatpro.Pojo.DTO;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ShareDTO implements Serializable {
	
	private LocalDateTime publishTime;
	private String publisherId;
	private String content;


	//服务器图库有两个，一个用来存放表情，一个用来存放朋友圈图片
	//对应的关系数据库的结构是，ID主键+图片本地路径
	private List<String> picIds;

}
