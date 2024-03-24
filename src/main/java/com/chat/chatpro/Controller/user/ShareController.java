package com.chat.chatpro.Controller.user;

import com.chat.chatpro.Pojo.DTO.ShareDTO;
import com.chat.chatpro.Pojo.DTO.SharePageQueryDTO;
import com.chat.chatpro.Result.PageResult;
import com.chat.chatpro.Result.Result;
import com.chat.chatpro.Service.ShareService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.chat.chatpro.Pojo.Entity.Share;
@RestController
@RequestMapping("/share")
@Api(tags = "动态操作接口")
public class ShareController{

	@Autowired
	private ShareService shareService;


	@PostMapping("/add")
	@ApiOperation("增加动态")
	public Result shareAdd(ShareDTO shareDTO){
		boolean success = shareService.shareAdd(shareDTO);
		if(success == true){
			return Result.success();
		}else{
			return Result.error();
		}
	}

	@PostMapping("/del")
	@ApiOperation("删除动态")
	public Result shareDel(Share share){
		boolean success = shareService.shareDel(share);
		if(success == true){
			return Result.success();
		}else{
			return Result.error();
		}
	}

	@PostMapping("/getshare")
	@ApiOperation("得到动态")
	public PageResult getShare(SharePageQueryDTO pageQueryDTO){
		return shareService.getShare(pageQueryDTO);
	}	

}
