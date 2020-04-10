package com.fontgoaway.mapper;

import com.fontgoaway.entity.Gw_group;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GroupMapper {
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
