package com.goaway.mapper;

import com.goaway.entity.Gw_group;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GroupMapper {
    /*
    查所有
    return List<Gw_group>
     */
    List<Gw_group> queryGroupList();

    /*
    根据ID查询
    {id} 要查询群号 id
     */
    Gw_group queryGroupById(String id);

    /*
    删除
    {id} 要删除人员的 id
     */
    void deleteById(String id);

    /*
    更新
    {group} 要更新的Gw_Group实例
     */
    void update(Gw_group group);

    /*
    增加
    {group} 要新增的Gw_group实例
     */
    void addGroup(Gw_group group);

    /*
    申请
    {group} 要新增的Gw_group实例
     */
    void apply(Gw_group group);
}
