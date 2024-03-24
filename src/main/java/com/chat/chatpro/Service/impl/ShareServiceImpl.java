package com.chat.chatpro.Service.impl;


import com.chat.chatpro.Mapper.PictureMapper;
import com.chat.chatpro.Mapper.ShareMapper;
import com.chat.chatpro.Pojo.DTO.ShareDTO;
import com.chat.chatpro.Pojo.DTO.SharePageQueryDTO;
import com.chat.chatpro.Result.PageResult;
import com.chat.chatpro.Service.ShareService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chat.chatpro.Pojo.Entity.Share;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShareServiceImpl implements ShareService {

	@Autowired
	private ShareMapper shareMapper;

	@Autowired
	private PictureMapper pictureMapper;

	public boolean shareAdd(ShareDTO shareDTO){
		LocalDateTime publishTime = shareDTO.getPublishTime();
	        String publisherId = shareDTO.getPublisherId();
		String content = shareDTO.getContent();
		List<String> picIds = shareDTO.getPicIds();
		
		//检验图片是否都在图库中
		int count_r = pictureMapper.batchQueryExist_share(picIds);
		if(count_r != picIds.size()){
			return false;
		}	
		
		Share newshare = new Share();
		newshare.setPublishTime(publishTime);
		newshare.setPublisherId(publisherId);
		newshare.setContent(content);
		shareMapper.insert(newshare);
	
		//一条朋友圈动态和图片之间是一对多关系，不需要再维护一个中间表
		//所以朋友圈图片数据表中有三个字段，主键ID，路径，对应朋友圈ID
		
		//update_share方法用来更新朋友圈图片对应的朋友圈动态ID
		//由于一条朋友圈动态一般有多张，所以直接创建一个批量插入方法
		pictureMapper.batchUpdateShareId_share(newshare.getId(),picIds);
		
		return true;	

	}

	public boolean shareDel(Share share){
		String shareId = share.getId();
		
		int rel_count = shareMapper.queryExist(shareId);
		if(rel_count == 0){
			return false;
		}

		shareMapper.delete(shareId);
		return true;
	}

	public PageResult getShare(SharePageQueryDTO sharePageQueryDTO){
		
		int page = sharePageQueryDTO.getPage();
		int pageSize = sharePageQueryDTO.getPageSize();


		//它会使用拦截器对下面的查询方法进行拦截，加上limit子句
		//所以就省得开发者在mapper写多余的sql语句
		//page是arraylist的子类
		PageHelper.startPage(page,pageSize);
		List<Share> shareList = shareMapper.pageQuery();
		Page<Share> pageG = (Page<Share>) shareList;

		return PageResult.builder()
		       .total(pageG.getTotal())
			.shareList(shareList)
	 		.build();		

	}


}
