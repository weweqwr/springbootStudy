package com.goaway.serve;

import com.goaway.entity.Gw_group;

import java.util.List;

public interface GroupServe {
    public List<Gw_group> queryGroupList();

    public Gw_group queryGroupById(String id);

    public List<Gw_group> haveGroup(int uid);

    public void deleteById(String id);

    public void update(Gw_group group);

    public void addGroup(Gw_group group);

    public void apply(Gw_group group);
}
