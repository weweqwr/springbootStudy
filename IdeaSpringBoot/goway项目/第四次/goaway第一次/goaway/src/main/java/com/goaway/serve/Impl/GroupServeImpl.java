package com.goaway.serve.Impl;

import com.goaway.entity.Gw_group;
import com.goaway.entity.Gw_user_group;
import com.goaway.mapper.GroupMapper;
import com.goaway.mapper.UserGroupMapper;
import com.goaway.serve.GroupServe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ：com.gjt
 * @description：TODO
 * @date ：Created in 2020/3/22 10:43
 */
@Service
public class GroupServeImpl implements GroupServe {

    @Autowired
    GroupMapper groupMapper;

    @Autowired
    UserGroupMapper userGroupMapper;

    @Override
    public List<Gw_group> queryGroupList() {
        return groupMapper.queryGroupList();
    }

    @Override
    public Gw_group queryGroupById(String id) {
        return groupMapper.queryGroupById(id);
    }

    @Override
    public List<Gw_group> haveGroup(int uid) {
        List gIds=userGroupMapper.haveGroupIdByUid(uid);

        return groupMapper.haveGroup(gIds);
    }

    @Override
    public void deleteById(String id) {
        groupMapper.deleteById(id);
        userGroupMapper.deleteGroupById(id);
    }

    @Override
    public void update(Gw_group group) {
        groupMapper.update(group);
    }

    @Override
    public void addGroup(Gw_group group) {
        Date date = new Date();
        long ts = date.getTime();
        String res = String.valueOf(ts);
        Gw_user_group userGroup =new Gw_user_group();
        userGroup.setUserId(group.getUser().getId());
        userGroup.setGroupId(res);
        userGroup.setGroupNickname(group.getUser().getNickname());
        userGroup.setGroupIdentification(1);//群主
        group.setId(res);

        groupMapper.addGroup(group);
        userGroupMapper.addUserGroup(userGroup);
    }

    @Override
    public void apply(Gw_group group) {
        Gw_user_group userGroup =new Gw_user_group();
        userGroup.setUserId(group.getUser().getId());
        userGroup.setGroupNickname(group.getUser().getNickname());
        userGroup.setGroupIdentification(4);//待审核
        userGroup.setTempGroupId(group.getId());
        userGroupMapper.addUserGroup(userGroup);
    }
}
