package com.fontgoaway.serve;

import com.fontgoaway.entity.Gw_group;

import java.util.List;

public interface GroupServe {
    //所有
    public List<Gw_group> findAllGroup();
    //groupId
    public Gw_group findGroupById(String id);

    //模糊groupName
    public List<Gw_group> findGroupByNameLike(String groupName);
    //state
    public List<Gw_group> findGroupByState(int state);

    //屏蔽
    public void modifyGroupState(Gw_group group);
}
