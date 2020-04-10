package com.fontgoaway.mapper;

import com.fontgoaway.entity.Gw_notice;
import com.fontgoaway.entity.Gw_user;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    //查询回复
    List<Gw_user> queryUser( @Param("id") int id, @Param("page") int page);
    int UserCount(@Param("id") int id);
    //屏蔽评论
    void sheildUser(@Param("id") int id, @Param("state") int state);
}
