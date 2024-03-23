package com.chat.chatpro.Controller.user;

@RestController
@RequestMapping("/fri")
public class FriendController{

	@Autowired
	private FriendService friendService;

	@PostMapping("/add")
	public Result friendAdd(@RequestBody FriendDTO friendDTO){
		boolean success = friendService.friendAdd(friendDTO);
		if(success == true){
			return Result.success();
		}else{
			return Result.error(); //返回错误,要么是因为用户不存在，要么已经添加过
		}
	}

	@PostMapping("/delete")
	public Result friendDel(@RequestBody FriendDTO friendDTO){
		boolean success = friendService.friendDel(friendDTO);
		if(success == true){
			return Result.success();
		}else{
			return Result.error();
		}
	}	


}
