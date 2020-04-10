package com.goaway.serve.Impl;


import com.goaway.entity.Gw_notice;
import com.goaway.entity.Gw_user;
import com.goaway.entity.ObsClientEntity;
import com.goaway.mapper.NoticeMapper;
import com.goaway.serve.NoticeServe;
import com.goaway.utils.MultipartFileToFile;
import com.goaway.utils.UploadImage;
import com.obs.services.ObsClient;
import com.obs.services.model.PutObjectResult;
import com.sun.org.apache.bcel.internal.generic.DMUL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class NoticeServeImpl implements NoticeServe {
    @Autowired
    NoticeMapper noticeMapper;

    //通知的创建
    @Override
    public Map<String, Object> createNotice(String title, int noticeType, String content, int originator, String groupId, String imageURL) {
        Map<String, Object> map = new HashMap<>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String createTime = df.format(new Date());
        System.out.println(createTime);
        try {
            Map<String, Object> imageMap = new HashMap<>();
            noticeMapper.createNotice(title, noticeType, content, originator, groupId, imageURL, createTime);
            map.put("msg", "创建成功");
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "创建失败");
        }
        return map;
    }

    @Override
    public Map<String, Object> upload(int userId, MultipartFile file, String filename) {
        Map<String, Object> map = new HashMap<>();
        try {
            ObsClientEntity obs = new ObsClientEntity();
            String endPoint = obs.getEndPoint();
            String ak = obs.getAk();
            String sk = obs.getSk();
            ObsClient obsClient = new ObsClient(ak, sk, endPoint);
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmSSss");
            String time = format.format(new Date());
            String path = "goaway/notice/Image/" + userId + "/" + time + "/" + filename;
            //将图片转为File类型
            MultipartFileToFile toFile = new MultipartFileToFile();
            File files = toFile.multipartFileToFile(file);
            PutObjectResult result = obsClient.putObject("longlongago", path, files);
            String imageURL = result.getObjectUrl();
            map.put("msg", "上传成功");
            map.put("imageURL", imageURL);
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg", "上传失败");
        }
        return map;
    }

    //根据userId或者通知名查询通知
    @Override
    public Map<String, Object> queryNoticeByUserIdAndTitle(int userId, String title) {
        Map<String, Object> map = new HashMap<>();
        try {
            List<Map> list = noticeMapper.queryNoticeByUserIdAndTitle(userId, title);
            map.put("msg", "查询成功");
            map.put("noticeInfo", list);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "查询失败");
        }
        return map;
    }

    @Override
    public Map<String, Object> insertDocument(int userId,String filename,MultipartFile file) {
        Map<String, Object> map = new HashMap<>();
        try {
            ObsClientEntity obs = new ObsClientEntity();
            String endPoint = obs.getEndPoint();
            String ak = obs.getAk();
            String sk = obs.getSk();
            ObsClient obsClient = new ObsClient(ak, sk, endPoint);
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmSSss");
            String time = format.format(new Date());
            String path = "goaway/notice/document/" + userId + "/" + time + "/" + filename;
            //将图片转为File类型
            MultipartFileToFile toFile = new MultipartFileToFile();
            File files = toFile.multipartFileToFile(file);
            PutObjectResult result = obsClient.putObject("longlongago", path, files);
            String documentURL = result.getObjectUrl();
            noticeMapper.insertDocument(filename, documentURL);
            map.put("msg", "上传成功");
            map.put("msg", "插入附件成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "插入附件失败");
        }

        return map;
    }

    //根据noticeId删除通知
    @Override
    public Map<String, Object> updateNoticeStateByNoticeId(int id) {
        Map<String, Object> map = new HashMap<>();
        try {
            noticeMapper.updateNoticeStateByNoticeId(id);
            map.put("msg", "删除成功");
        } catch (Exception e) {
            map.put("msg", "删除失败");
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public Map<String, Object> queryNoticeByUserIdAndNoticeId(int userId, int id) {
        Map<String, Object> map = new HashMap<>();
        try {
            Gw_notice list = noticeMapper.queryNoticeByUserIdAndNoticeId(userId, id);
            String[] documentName=null;
            if(list.getDocumentName()!=null) {
              documentName = list.getDocumentName().split(",");
            }
            String[] documentURL=null;
            if(list.getDocumentURL()!=null) {
               documentURL = list.getDocumentURL().split(",");
           }
            String[] imageURL=null;
            if(list.getDocumentURL()!=null) {
                imageURL = list.getImageURL().split(",");
            }
                map.put("id", list.getId());
                map.put("title", list.getTitle());
                map.put("noticeType", list.getNoticeType());
                map.put("originator", list.getOriginator());
                map.put("content", list.getContent());
                map.put("flag", list.getFlag());
                map.put("createTime", list.getCreateTime());
                map.put("documentName", documentName);
                map.put("documentURL", documentURL);
                map.put("state", list.getState());
                map.put("imageURL", imageURL);
            map.put("msg", "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "查询失败");
        }
        return map;
    }

    @Override
    public Map<String, Object> setReceived(int noticeId, int userId) {
        Map<String, Object> map = new HashMap<>();
        try {
            noticeMapper.setReceived(noticeId, userId);
            map.put("msg", "签到成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "签到失败");
        }
        return map;
    }


}
