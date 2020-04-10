package com.fontgoaway.mapper;

import com.fontgoaway.entity.Gw_rotation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

@Mapper
public interface RotationMapper {
    //上传轮播图
    void uploadRotation(@Param("imageUrl")String imageUrl,@Param("createTime")String createTime,@Param("originator")String originator);
    //查询轮播图
    List<Gw_rotation> queryRotation(@Param("flag")int flag,@Param("id")int id,@Param("page")int page);
    //屏蔽通知
    void sheildNotice(@Param("id")int id,@Param("state")int state);
    //查询一共有几条数据
    Gw_rotation queryRotationCount(@Param("id")int id);
    //查询微信小程序展示一共有几条数据
    Gw_rotation queryRotationCountMini();
    //按小程序展示
    List<Gw_rotation> queryRotationMini(@Param("page")int page);
    //轮播排序
    void orderRotationById(@Param("maps") Map<String,Object> maps);

}
