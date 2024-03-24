package com.chat.chatpro.Service;

import com.chat.chatpro.Pojo.DTO.ShareDTO;
import com.chat.chatpro.Pojo.DTO.SharePageQueryDTO;
import com.chat.chatpro.Result.PageResult;
import com.chat.chatpro.Pojo.Entity.Share;
public interface ShareService {

    boolean shareAdd(ShareDTO shareDTO);

    boolean shareDel(Share share);

    PageResult getShare(SharePageQueryDTO pageQueryDTO);
}
