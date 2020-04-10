package com.goaway.serve;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.Map;

public interface CommentServe {
    Map<String,Object> createComment(int noticeId, int commentType
            ,String content,int userId,int state,int replyId);
    //更具noticeId查询评论
    Map<String,Object> queryCommentByNoticeId(@Param("noticeId")int noticeId);
    //根据commentId删除留言
    Map<String,Object>delteCommentById(@Param("id")int id);
}
