package com.fontgoaway.controller;

import com.fontgoaway.entity.Gw_admin;
import com.fontgoaway.entity.Gw_rotation;
import com.fontgoaway.serve.RotatioServe;
import com.fontgoaway.utils.MultipartFileToFile;
import com.fontgoaway.utils.UploadImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RotationController {
    //上传图片轮播图片
    @Autowired
    RotatioServe rotatioServe;

    @RequestMapping(value = "uploadImage",method = RequestMethod.POST)
    public Map<String,Object> uploadImage(@RequestParam(value = "upfile",required = false) MultipartFile upfile,
                                          @RequestParam(value = "filename",required = false) String filename,
                                          @RequestParam(value = "originator",required = false) String originator,
                                          HttpServletRequest request) {
        System.out.println(originator);
        System.out.println(filename);
        Map<String,Object> map=rotatioServe.uploadRotation(filename,upfile,originator);
        return map;
    }
    //查询轮播照片
    @RequestMapping(value = "queryRotation",method = RequestMethod.POST)
    public Map<String,Object> queryRotation(@RequestBody Gw_rotation rotation) {
        Map<String,Object> map=rotatioServe.queryRotation(rotation.getFlag(),rotation.getId(),rotation.getCurPage());
        return map;
    }
    //禁用和启动
    @RequestMapping(value = "sheildNotice",method = RequestMethod.POST)
    public Map<String,Object> sheildNotice(@RequestBody Gw_rotation rotation) {
        Map<String,Object> map=rotatioServe.sheildNotice(rotation.getId(),rotation.getState());
        return map;
    }
    //按小程序展示查询
    @RequestMapping(value = "queryRotationMini",method = RequestMethod.POST)
    public Map<String,Object> queryRotationMini(@RequestBody Gw_rotation rotation) {
        Map<String,Object> map=rotatioServe.queryRotationMini(rotation.getCurPage());
        return map;
    }
   //轮播排序
   @RequestMapping(value = "orderRotationById",method = RequestMethod.POST)
   public Map<String,Object> orderRotationById(@RequestParam(value ="id" ,required = false) int[] id,
                                               @RequestParam(value ="orderId",required = false) int[] orderId) {
       Map<String,Object> maps=new HashMap<>();
        for(int i=0;i<id.length;i++){
            maps.put(String.valueOf(id[i]),orderId[i]);
        }
        Map<String,Object> map=rotatioServe.orderRotationById(maps);
       return map;
   }
}
