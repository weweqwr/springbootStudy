package com.fontgoaway.mapper;

import com.fontgoaway.entity.Gw_notice_comment;
import com.fontgoaway.entity.Gw_notice_reply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {
    //查询回复
    List<Gw_notice_comment> queryComment(@Param("flag") int flag, @Param("id") int id, @Param("page") int page);
    int CommentCount(@Param("id") int id);
    //屏蔽评论
    void sheildComment(@Param("id") int id, @Param("state") int state);
}
