package com.chat.chatpro.Controller.user.ShareController;

@RestController
@RequestMapping("/share")
public class ShareController{

	@Autowired
	private ShareService shareService;


	@PostMapping("/add")
	public Result shareAdd(ShareDTO shareDTO){
		boolean success = shareService.shareAdd(shareDTO);
		if(success == true){
			return Result.success();
		}else{
			return Result.error();
		}
	}

	@PostMapping("/del")
	public Result shareDel(Share share){
		boolean success = shareService.shareDel(share);
		if(success == true){
			return Result.success();
		}else{
			return Result.error();
		}
	}

	@PostMappinp("/getshare")
	public PageResult getShare(PageQueryDTO pageQueryDTO){
		return shareService.getShare(pageQueryDTO);
	}	

}
