package com.chat.chatpro.Mapper;

import com.chat.chatpro.Pojo.Entity.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.LinkedList;
import java.util.List;

@Mapper
public interface MessageMapper {
    public LinkedList<Message> getList();

    void batchInsert(List<Message> list);
}
