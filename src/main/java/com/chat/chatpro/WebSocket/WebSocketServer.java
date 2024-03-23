package com.chat.chatpro.WebSocket;


//每个连接加入都会为该连接新建一个服务器对象绑定
@Component
@ServerEndpoint("/sendMsg") //作用是将类定义成一个websocket服务器端，注解值用于
//监听用户连接的终端访问URL地址，客户端通过该url连接到websocket服务器端
//实际上每有一个客户端连接过来就会创建一个实例
@Slf4j
public class WebSocketServer{
	
	//当前在线
	private static AtomicInteger onlineCount = 0;
	
	//存储用户ID对应的通话
	private ConcurrentHashMap<Integer,Session> sessionMap = new ConcurrentHashMap();

	//当前websocket
	private Session session;

	//当前监听的客户端的id
	private String userId;

	@Autowired
	private MessageCtner msgCtner;


	@OnOpen
	public void onOpen(Session session , @PathParam("userId")Integer userId){
		
		this.session = session;
		this.userId = userId;

		if(sessionMap.containsKey(userId)){
			sessionMap.remove(userId);
		}
		sessionMap.put(userId,session);
		onlineCount.getAndIncrement();
		log.info("用户"+userId+"连接");
	}
	
	@OnClose
	public void onClose(){
		if(sessionMap.containsKey(userId)){
			sessionMap.remove(userId);
			onlineCount.getAndDecrement();
		}
		log.info("用户"+userId+"退出")


	}

//	@OnMessage
//	public void onMessage(String message , Session session){
//		
//		if(message == null){
//			throw new BlankMessageException();
//		}	
//
//		JSONObject jsonObject = JSON.parseObject(message);
//		String toUserid = jsonObject.getString("toUserid");
//		
//		Session toUserSession = sessionMap.get(toUserid);
//			
//	}

	@OnError
	public void onError(Session session , Throwable error){
		log.error("用户："+this.userId+"出现错误："+error.getMessage());
	}

	public static void sendMessage(Message msg){
		String toUserid = msg.getToUserId();
		Session toUsersession = sessionMap.get(toUserid);
		if(toUsersession == null || toUsersession){
			msgCtner.push(msg);
			return;
		}
		JSONObject jsonObject = JSONObject.fromObject(msg);
		String msg_jsonstr = jsonObject.toString();
		toUsersession.getBasicRemote().sendText(msg_jsonstr);	
	}



}
