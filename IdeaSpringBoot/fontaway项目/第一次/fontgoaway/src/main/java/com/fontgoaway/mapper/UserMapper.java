package com.fontgoaway.mapper;

import com.fontgoaway.entity.Gw_user;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    //    所有
    public List<Gw_user> findAllUser();
    //    id
    public Gw_user findUserById(Integer id);
    //    nickname
    public List<Gw_user> findUserByNickname(String nickname);
    //    模糊nickname
    public List<Gw_user> findUserByNicknameLick(String nickname);
    //    屏蔽
    public void modifyUserState(Gw_user user);
}
