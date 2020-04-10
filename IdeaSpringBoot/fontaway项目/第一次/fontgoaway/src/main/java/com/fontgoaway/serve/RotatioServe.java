package com.fontgoaway.serve;

import com.fontgoaway.entity.Gw_rotation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

public interface RotatioServe {
    //上传轮播照片
    Map<String,Object> uploadRotation(String filename, MultipartFile file, String originator);
    //查询轮播
    Map<String,Object> queryRotation(int flag,int id,int curPage);
    //屏蔽\启动通知
    Map<String,Object> sheildNotice(int id,int state);
    //按小程序展示
    Map<String,Object> queryRotationMini(int curPage);
    //轮播排序
    Map<String,Object> orderRotationById(Map<String,Object> maps);

}
