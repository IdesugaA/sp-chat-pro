package com.chat.chatpro.Controller.user;


import com.chat.chatpro.Pojo.Entity.Message;
import com.chat.chatpro.Service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/msg")
@Api(tags = "消息操作接口")
public class MessageController{

	@Autowired
	private MessageService messageService;

	@PostMapping("/sendsgmsg")
	@ApiOperation("发送消息")
	public void sendsgmsg(@RequestBody Message msg){
		messageService.sendsgmsg(msg);
	}


}

