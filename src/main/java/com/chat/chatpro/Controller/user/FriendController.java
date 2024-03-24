package com.chat.chatpro.Controller.user;

import com.chat.chatpro.Pojo.DTO.FriendDTO;
import com.chat.chatpro.Result.Result;
import com.chat.chatpro.Service.FriendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fri")
@Api(tags="好友操作接口")
public class FriendController{

	@Autowired
	private FriendService friendService;

	@PostMapping("/add")
	@ApiOperation("添加好友")
	public Result friendAdd(@RequestBody FriendDTO friendDTO){
		boolean success = friendService.friendAdd(friendDTO);
		if(success == true){
			return Result.success();
		}else{
			return Result.error(); //返回错误,要么是因为用户不存在，要么已经添加过
		}
	}

	@PostMapping("/delete")
	@ApiOperation("删除好友")
	public Result friendDel(@RequestBody FriendDTO friendDTO){
		boolean success = friendService.friendDel(friendDTO);
		if(success == true){
			return Result.success();
		}else{
			return Result.error();
		}
	}	


}
