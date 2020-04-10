package com.fontgoaway.mapper;

import com.fontgoaway.entity.Gw_notice;
import com.fontgoaway.entity.Gw_notice_comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NoticeMapper {
    //查询回复
    List<Gw_notice> queryNotice(@Param("flag") int flag, @Param("id") int id, @Param("page") int page);
    int NoticeCount(@Param("id") int id);
    //屏蔽评论
    void sheildNotice(@Param("id") int id, @Param("state") int state);
}
