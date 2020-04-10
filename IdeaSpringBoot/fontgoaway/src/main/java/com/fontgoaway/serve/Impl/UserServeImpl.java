package com.fontgoaway.serve.Impl;

import com.fontgoaway.entity.Gw_group;
import com.fontgoaway.entity.Gw_role;
import com.fontgoaway.entity.Gw_user;
import com.fontgoaway.mapper.GroupMapper;
import com.fontgoaway.mapper.UserMapper;
import com.fontgoaway.serve.GroupServe;
import com.fontgoaway.serve.UserServe;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServeImpl implements UserServe {
    @Autowired
    UserMapper userMapper;
    @Override
    public Map<String, Object> queryUser(int curPage,int id) {
        Map<String,Object> map=new HashMap<>();
        try {
            int count=userMapper.UserCount(id);
            int pageNumber=(int)Math.ceil((double)count/15);//向上取整
            int page=(curPage-1)*15; //一条数据一页
            List<Gw_user> userList=userMapper.queryUser(id,page);

            map.put("userList",userList);
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
    public Map<String, Object> sheildUser(int id, int state) {
        Map<String, Object> map=new HashMap<>();
        try {
            userMapper.sheildUser(id, state);
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
