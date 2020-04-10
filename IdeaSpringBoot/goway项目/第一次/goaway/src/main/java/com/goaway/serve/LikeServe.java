package com.goaway.serve;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface LikeServe {
    //点赞功能
    Map<String,Object> addLike(@Param("commentId")int commentId, @Param("userId")int userId);


}
