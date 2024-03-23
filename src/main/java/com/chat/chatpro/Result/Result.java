package com.chat.chatpro.Result;


@Data
public class Result<T> implements Serializable {

	private Integer code;
	private String msg;
	private Tdata;

	public static <T> Result<T> success(){
		Result<T> result = new Result<T>();
		result.code = 1;
		return result;
	}

	public static <T> result<T> success(T object){
		Result<T> result = new Result<T>();
		result.data = object;
		result.code = 1;
		return result;
	}

	public static <T> Result<T> error(String msg){
		Result result = new Result();
		result.msg = msg;
		return result;
	}


}
