package com.fontgoaway.serve;

import com.fontgoaway.entity.Gw_user;

import java.util.List;

public interface UserServe {
    //    所有
    public List<Gw_user> findAllUser();
    //    id
    public Gw_user findUserById(Integer id);
    //    nickname
    public List<Gw_user> findUserByNickname(String nickname);
    //    模糊nickname
    public List<Gw_user> findUserByNicknameLick(String nickname);
    public void modifyUserState(Gw_user user);
}
