package com.chat.chatpro.Pojo.DTO;

@Data
@Builder
@NoArgsConstructor
public class SharePageQueryDTO implements Serializble{

	private int page;
	private int pageSize;

}
