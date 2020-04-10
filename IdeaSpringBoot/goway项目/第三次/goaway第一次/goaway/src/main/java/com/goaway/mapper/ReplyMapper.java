package com.goaway.mapper;

import com.goaway.entity.Gw_notice_comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyMapper {
    //添加回复
    void createReply(@Param("commentId") int commentId
            ,@Param("userId") int userId
            ,@Param("nickname")String nickname
            ,@Param("avatarUrl")String avatarUrl
            ,@Param("toUserId") int toUserId
            ,@Param("toNickname") String toNickname
            ,@Param("toAvatarUrl") String toAvatarUrl
            ,@Param("content")String content
            ,@Param("replyTime")String replyTime
            ,@Param("state")int state
    );
    //根据回复id删除回复
    void deleteReplyById(@Param("id")int id);

}
