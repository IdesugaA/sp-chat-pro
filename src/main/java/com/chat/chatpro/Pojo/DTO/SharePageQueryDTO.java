package com.chat.chatpro.Pojo.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class SharePageQueryDTO implements Serializable {

	private int page;
	private int pageSize;

}
