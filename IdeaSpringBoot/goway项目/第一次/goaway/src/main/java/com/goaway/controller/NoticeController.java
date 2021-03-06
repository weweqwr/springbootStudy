package com.goaway.controller;

import com.goaway.serve.NoticeServe;
import com.goaway.utils.UploadFile;
import com.goaway.utils.UploadImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class NoticeController {
    //用来缓存照片路径
    Map<String, Object> imageMap = new HashMap<>();
    //通知的创建
    @Autowired
    NoticeServe noticeServe;
    @RequestMapping(value = "createNotice",method=RequestMethod.POST )
    public Map<String,Object> createNotice(String title, int noticeType, String content
            , int originator, int groupId){
        Map<String, Object> map = new HashMap<>();
        String imageURL=imageMap.get("imageURL").toString();
        map=noticeServe.createNotice(title,noticeType,content,originator,groupId,imageURL);
        return map;
    }

    //通知图片的上传
    @RequestMapping(value = "upload",method=RequestMethod.POST )
    public Map<String,Object> upload(int userId,MultipartFile file, HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        UploadImage uploadImage = new UploadImage();
        map=uploadImage.upload(userId,file,request);
       // String path=imageMap.get("imageURL").toString()+","+map.get("imageURL");
        if(imageMap.get("imageURL")==null){         //如果没有路径，直接存进map
            imageMap.put("imageURL",map.get("imageURL").toString());
        }else{         //如果有路径则加个逗号分开
            String path=imageMap.get("imageURL").toString()+","+map.get("imageURL");
            imageMap.put("imageURL",path);
        }
        System.out.println(imageMap.get("imageURL"));
        return map;
    }
    //上传附件
    @RequestMapping(value = "uploadFile",method=RequestMethod.POST )
    public Map<String,Object> uploadileFile(int userId,String filename,MultipartFile file, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        UploadFile uploadFile = new UploadFile();
        map=uploadFile.upload(userId,filename,file,request);
        //插入插件信息
        String documentURL=map.get("documentURL").toString();
        noticeServe.insertDocument(filename,documentURL);
        return map;
    }

    // 根据userId或者通知名查询通知
    @RequestMapping(value = "queryNoticeByUserIdAndTitle",method=RequestMethod.POST )
    public Map<String, Object> queryNoticeByUserIdAndTitle(int userId, String title) {
        Map<String, Object> map = new HashMap<>();
        map=noticeServe.queryNoticeByUserIdAndTitle(userId,title);
        return map;
    }
    //根据noticeId软删除通知
    @RequestMapping(value = "updateNoticeStateByNoticeId",method=RequestMethod.POST )
    public Map<String, Object> updateNoticeStateByNoticeId(int id) {
        Map<String, Object> map = new HashMap<>();
        map=noticeServe.updateNoticeStateByNoticeId(id);
        return map;
    }
    //根据noticeId查询userId通知的详细信息
    @RequestMapping(value = "queryNoticeByUserIdAndNoticeId",method=RequestMethod.POST )
    public Map<String, Object> queryNoticeByUserIdAndNoticeId(int userId, int id) {
        Map<String, Object> map = new HashMap<>();
        map=noticeServe.queryNoticeByUserIdAndNoticeId(userId,id);
        return map;
    }
    //签到
    @RequestMapping(value = "setReceived",method=RequestMethod.POST )
    public Map<String, Object> setReceived(int noticeId,int userId) {
        Map<String, Object> map = new HashMap<>();
        map=noticeServe.setReceived(noticeId,userId);
        return map;
    }

}
