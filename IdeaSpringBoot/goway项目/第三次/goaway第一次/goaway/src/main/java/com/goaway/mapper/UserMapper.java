package com.goaway.mapper;

import com.goaway.entity.Gw_user;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.annotation.security.PermitAll;
import java.util.Date;
import java.util.List;


@Mapper
public interface UserMapper {
    //插入用户信息
    void insertUser(@Param("avatarUrl") String avatarUrl, @Param("openid") String openid, @Param("country") String country
            , @Param("province") String province, @Param("city") String city, @Param("gender") Short gender
            , @Param("nickname") String nickname);

    //根据id查询用户信息
    List<Gw_user> queryUserByOpenId(@Param("openid")String openid);
    //查询未签到人的信息
    List<Gw_user> queryNoReceived(@Param("noticeId")int noticeId);

    /*
    根据ID查询
    {id} 要查询 id
     */
    Gw_user findUserById(int id);
}
