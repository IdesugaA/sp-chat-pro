package com.chat.chatpro.ExtraWorker;

@Component
public class MessageCtner<Message>{

	@Autowired
	private MsgMapper msgMapper;

	private static LinkedList<Message> msgStack = null;
	//spring在创建bean实例时默认会调用其无参构造函数

	public boolean isEmpty(){
		if(msgStack != null){
			synchronized(msgStack){
				return msgStack.isEmpty();
			}
		}
		return false;
	}

	public boolean MessageCtner(){
		if(msgStack != null){
			throw new MsgCtnerAlreadyExistException()
		}
		msgStack = msgMapper.getList();
		if(msgStack == null){
			throw new MsgCtnerCreatedFailException();
		}
		return true;
	}

	public boolean push(Message msg){
		if(msgStack == null){
			return false;
		}
		synchronized(msgStack){
			msgStack.push(msg);
		}
		return true;
	}	

	public Message pop(){
		if(msgStack == null || isEmpty()){
			return null;
		}
		synchronized(msgStack){
			return msgStack.pop();
		}
	}

	public void destroy(){
		if(msgStack == null){
			throw new MsgCtnerNotExistException();
		}
		msgMapper.batchInsert(msgStack);
	}


}
