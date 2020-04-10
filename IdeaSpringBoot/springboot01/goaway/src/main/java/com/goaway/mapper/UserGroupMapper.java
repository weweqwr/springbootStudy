package com.goaway.mapper;

import com.goaway.entity.Gw_user_group;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserGroupMapper {

    /*
查所有
return List<Gw_group>
*/
    List<Gw_user_group> findAllUserGroup();

    List<Gw_user_group> findAcceptList(Gw_user_group userGroup);

    /*
    根据ID查询
    {id} 要查询群号 id
     */
    Gw_user_group findUserGroupById(Integer userId,String tempGroupId);

    Gw_user_group findUser(Integer userId,String groupId);


    /*
    删除
    {id} 要删除人员的 id
     */
    void deleteUserGroup(Gw_user_group userGroup);

    /*
    解散群
     */
    void deleteGroupById(String groupId);

    /**
     * 查询群成员
     * @param groupId
     * @return
     */
    public List<Gw_user_group> findGroupByGroupId(String groupId);

    /*
    更新
    {group} 要更新的Gw_Group实例
     */
    void updateUserGroup(Gw_user_group userGroup);

    /*
    增加
    {group} 要新增的Gw_group实例
     */
    void addUserGroup(Gw_user_group userGroup);
}
