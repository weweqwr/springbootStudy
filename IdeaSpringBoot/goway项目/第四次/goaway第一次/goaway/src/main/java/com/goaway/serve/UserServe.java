package com.goaway.serve;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.goaway.entity.Gw_user;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserServe {
    //插入用户信息
    Map<String,Object> insertUser(String avatarUrl, String openid, String country
            , String province, String city, Short gender
            , String nickname);
    //根据openid查询用户信息
    Map<String,Object> queryUserByOpenId(String openid);
    //查询未签到人的信息
    Map<String,Object> queryNoReceived(int noticeId,int state);

    public Gw_user findUserById(int id);
    //获取openId的值
    Map<String,Object> obtainUserOpenId(String code) throws JsonProcessingException;
}
