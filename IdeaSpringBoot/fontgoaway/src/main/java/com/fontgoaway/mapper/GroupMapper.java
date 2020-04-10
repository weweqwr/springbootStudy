package com.fontgoaway.mapper;

import com.fontgoaway.entity.Gw_group;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GroupMapper {
    //查询群的信息
    List<Gw_group> queryGroup(@Param("page")int page,@Param("id")String id);
    //查询群的数量
    int groupCount(@Param("id")String id);
    //屏蔽群
    void shieldGroup(@Param("id")String id,@Param("state")int state);
}
