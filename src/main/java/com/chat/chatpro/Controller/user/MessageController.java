package com.chat.chatpro.Controller.user;


@RestController
@RequestMapping("/msg")
public class MessageController{

	@Autowired MessageService messageService;

	@PostMapping("/sendsgmsg")
	public void sendsgmsg(@RequestBody Message msg){
		messageService.sendsgmsg(msg);
	}


}

