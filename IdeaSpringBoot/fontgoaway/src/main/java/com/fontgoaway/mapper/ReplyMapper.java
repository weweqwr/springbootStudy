package com.fontgoaway.mapper;

import com.fontgoaway.entity.Gw_notice_reply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyMapper {
    //查询回复
    List<Gw_notice_reply> queryReply(@Param("flag")int flag, @Param("id")int id, @Param("page")int page);
    int ReplyCount(@Param("id")int id);
    //屏蔽评论
    void sheildReply(@Param("id")int id,@Param("state")int state);
}
