package com.chat.chatpro.Mapper;


//同是管理服务器上的朋友圈动态图库和表情图库的数据库信息
@Mapper
public interface PictureMapper{

	public int batchQueryExist_share(List<Integer> picIds);

	public void batchUpdateShareId_share(List<Integer> picIds);

}
