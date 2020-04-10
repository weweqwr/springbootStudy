package com.fontgoaway.serve.Impl;

import com.fontgoaway.entity.Gw_user;
import com.fontgoaway.mapper.UserMapper;
import com.fontgoaway.serve.UserServe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：com.gjt
 * @description：TODO
 * @date ：Created in 2020/4/1 18:00
 */
@Service
public class UserServeImpl implements UserServe {
    @Autowired
    UserMapper userMapper;
    @Override
    public List<Gw_user> findAllUser() {
        return userMapper.findAllUser();
    }

    @Override
    public Gw_user findUserById(Integer id) {
        return userMapper.findUserById(id);
    }

    @Override
    public List<Gw_user> findUserByNickname(String nickname) {
        return userMapper.findUserByNickname(nickname);
    }

    @Override
    public List<Gw_user> findUserByNicknameLick(String nickname) {
        return userMapper.findUserByNicknameLick("%"+nickname+"%");
    }

    @Override
    public void modifyUserState(Gw_user user) {
        userMapper.modifyUserState(user);
    }
}
