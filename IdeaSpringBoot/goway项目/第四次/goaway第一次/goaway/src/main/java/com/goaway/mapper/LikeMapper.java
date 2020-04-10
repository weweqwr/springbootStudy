package com.goaway.mapper;

import com.goaway.entity.Gw_notice_comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.security.PermitAll;
import java.util.List;

@Mapper
public interface LikeMapper {
    //点赞
    void addLike(@Param("commentId")int commentId, @Param("userId")int userId);
    //取消点赞
    void deleteLike(@Param("commentId")int commentId, @Param("userId")int userId);
    //判断是否已有该用户的点赞信息
    List queryLikeByCommentIdAndUserId(@Param("commentId")int commentId, @Param("userId")int userId);
}
