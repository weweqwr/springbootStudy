package com.goaway.mapper;

import com.goaway.entity.Gw_notice_comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface CommentMapper {
    //留言
    void createComment(@Param("noticeId")int noticeId,@Param("commentType")int commentType
            ,@Param("content") String content,@Param("userId")int userId,@Param("commentTime") String commentTime
            ,@Param("state")int state,@Param("replyId")int replyId);
    //根据noticeId查询所有留言的信息
    List<Gw_notice_comment> queryCommentByNoticeId(@Param("noticeId")int noticeId);
    //根据commentId删除留言
    void delteCommentById(@Param("id")int id);
    //评论点赞
    void addLikeNumber();

}
