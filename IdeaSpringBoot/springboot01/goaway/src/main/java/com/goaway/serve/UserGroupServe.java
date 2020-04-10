package com.goaway.serve;

import com.goaway.entity.Gw_user_group;

import java.util.List;

public interface UserGroupServe {
    public List<Gw_user_group> findAcceptList(Gw_user_group userGroup);
    public Gw_user_group findUser(Integer userId, String groupId);
    public List<Gw_user_group> findGroupByGroupId(String gid);

    public void deleteUserGroup(Gw_user_group group);

    public void updateUserGroup(Gw_user_group group);
}
