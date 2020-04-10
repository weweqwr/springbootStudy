package com.fontgoaway.serve.Impl;

import com.fontgoaway.entity.Gw_group;
import com.fontgoaway.entity.Gw_role;
import com.fontgoaway.mapper.GroupMapper;
import com.fontgoaway.serve.GroupServe;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GroupServeImpl implements GroupServe {
    @Autowired
    GroupMapper groupMapper;
    @Override
    public Map<String, Object> queryGroup(int curPage, String id) {
        Map<String,Object> map=new HashMap<>();
        try {
            int count=groupMapper.groupCount(id);
            int pageNumber=(int)Math.ceil((double)count/15);//向上取整
            int page=(curPage-1)*15; //一条数据一页
            List<Gw_group> groups=groupMapper.queryGroup(page,id);

            map.put("groups",groups);
            map.put("pageNumber",pageNumber);
            map.put("count",count);
            map.put("msg","查询成功");

            map.put("flag",1);
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","查询失败");
            map.put("flag",0);
        }
        return map;
    }

    @Override
    public Map<String, Object> shieldGroup(String id, int state) {
        Map<String, Object> map=new HashMap<>();
        try {
            groupMapper.shieldGroup(id, state);
            map.put("msg","屏蔽成功");
            map.put("flag",1);
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","屏蔽失败");
            map.put("flag",0);
        }
        return map;
    }


}
