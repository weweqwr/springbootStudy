package com.fontgoaway.serve.Impl;

import com.fontgoaway.entity.Gw_group;
import com.fontgoaway.mapper.GroupMapper;
import com.fontgoaway.serve.GroupServe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：com.gjt
 * @description：TODO
 * @date ：Created in 2020/3/31 16:13
 */
@Service
public class GroupServeImpl implements GroupServe {
    @Autowired
    GroupMapper groupMapper;
    @Override
    public List<Gw_group> findAllGroup() {
        return groupMapper.findAllGroup();
    }

    @Override
    public Gw_group findGroupById(String id) {
        return groupMapper.findGroupById(id);
    }

    @Override
    public List<Gw_group> findGroupByNameLike(String groupName) {
        return groupMapper.findGroupByNameLike("%"+groupName+"%");
    }

    @Override
    public List<Gw_group> findGroupByState(int state) {
        return groupMapper.findGroupByState(state);
    }

    @Override
    public void modifyGroupState(Gw_group group) {
        groupMapper.modifyGroupState(group);
    }
}
