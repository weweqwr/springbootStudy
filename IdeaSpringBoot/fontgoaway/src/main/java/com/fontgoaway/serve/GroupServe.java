package com.fontgoaway.serve;

import com.fontgoaway.entity.Gw_group;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GroupServe {
    //查询群的信息
    Map<String,Object> queryGroup(int curPage, String id);
    //屏蔽群
    Map<String,Object> shieldGroup(String id,int state);
}
