
package com.chat.chatpro.Service;


import com.chat.chatpro.Pojo.DTO.FriendDTO;

public interface FriendService{


    boolean friendAdd(FriendDTO friendDTO);

    boolean friendDel(FriendDTO friendDTO);
}
