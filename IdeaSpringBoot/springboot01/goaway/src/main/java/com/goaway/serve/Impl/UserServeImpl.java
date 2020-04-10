package com.goaway.serve.Impl;

import com.goaway.entity.Gw_user;
import com.goaway.mapper.UserMapper;
import com.goaway.serve.UserServe;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServeImpl implements UserServe {
    @Autowired
    UserMapper userMapper;

    //插入用户信息
    @Override
    public Map<String, Object> insertUser(String avatarUrl, String openid, String country, String province, String city, Short gender, String nickname) {
        HashMap<String, Object> map = new HashMap<>();
        List<Gw_user> list = userMapper.queryUserByOpenId(openid);//查询数据库用户表是否已经有该用户信息
        try {
            if (list.size() > 0) {
                map.put("msg", "用户表以有该用户信息");
            } else {
                userMapper.insertUser(avatarUrl, openid, country, province, city, gender, nickname);
                map.put("msg", "插入用户信息成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "插入失败");
        }
        return map;
    }

    //根据用户openid查询用户信息
    @Override
    public Map<String, Object> queryUserByOpenId(String openid) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            List<Gw_user> list = userMapper.queryUserByOpenId(openid);
            if(openid!=null){
                userMapper.queryUserByOpenId(openid);
                map.put("msg", "查询用户信息成功");
                for (int i = 0; i < list.size(); i++) {
                    map.put("avatarUrl", list.get(i).getAvatarurl());
                    map.put("openid", list.get(i).getOpenid());
                    map.put("country", list.get(i).getCountry());
                    map.put("province", list.get(i).getProvince());
                    map.put("city", list.get(i).getCity());
                    map.put("gender", list.get(i).getGender());
                    map.put("nickname", list.get(i).getNickname());
                }
            }else{
                map.put("msg", "查询信息成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "查询用户信息失败");
        }

        return map;
    }

    @Override
    public Map<String, Object> queryNoReceived(int noticeId) {
        HashMap<String, Object> map = new HashMap<>();
        try{
            List<Gw_user> list = userMapper.queryNoReceived(noticeId);
            map.put("userInfo",list);
            map.put("msg","查询成功");
        }catch (Exception e){
            map.put("msg","查询失败");
        }
        return map;
    }

    @Override
    public Gw_user findUserById(int id) {
        return userMapper.findUserById(id);
    }

}
