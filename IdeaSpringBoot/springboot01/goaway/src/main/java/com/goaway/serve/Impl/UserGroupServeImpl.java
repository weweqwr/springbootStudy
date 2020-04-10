package com.goaway.serve.Impl;

import com.goaway.entity.Gw_user_group;
import com.goaway.mapper.UserGroupMapper;
import com.goaway.serve.UserGroupServe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：com.gjt
 * @description：TODO
 * @date ：Created in 2020/3/22 10:54
 */
@Service
public class UserGroupServeImpl implements UserGroupServe {
    @Autowired
    UserGroupMapper userGroupMapper;

    @Override
    public List<Gw_user_group> findAcceptList(Gw_user_group userGroup) {
        userGroup.setGroupIdentification(4);
        return userGroupMapper.findAcceptList(userGroup);
    }

    @Override
    public Gw_user_group findUser(Integer userId, String groupId) {
        return userGroupMapper.findUser(userId,groupId);
    }

    @Override
    public List<Gw_user_group> findGroupByGroupId(String gid) {
        return userGroupMapper.findGroupByGroupId(gid);
    }

    @Override
    public void deleteUserGroup(Gw_user_group group) {
        userGroupMapper.deleteUserGroup(group);
    }

    @Override
    public void updateUserGroup(Gw_user_group group) {
        Gw_user_group temp = userGroupMapper.findUserGroupById(group.getUserId(),group.getTempGroupId());
        System.out.println(temp);
        temp.setGroupId(group.getTempGroupId());
        temp.setGroupIdentification(2);
        temp.setTempGroupId("");
        System.out.println(temp);
        userGroupMapper.updateUserGroup(temp);
    }
}
