package com.goaway.serve;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface ReplyServe {
    Map<String,Object> createReply(int commentId
            , int userId
            , String nickname
            , String avatarUrl
            , int toUserId
            , String toNickname
            , String toAvatarUrl
            , String content
            , int state);
    //根据回复id删除回复
    Map<String,Object> deleteReplyById(@Param("id")int id);
}
