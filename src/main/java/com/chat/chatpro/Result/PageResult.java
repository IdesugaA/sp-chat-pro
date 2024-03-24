package com.chat.chatpro.Result;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class PageResult implements Serializable {
	private long total;
	private List shareList;
}
