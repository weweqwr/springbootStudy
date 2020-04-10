package com.goaway.serve.Impl;


import com.goaway.entity.Gw_notice;
import com.goaway.entity.Gw_user;
import com.goaway.mapper.NoticeMapper;
import com.goaway.serve.NoticeServe;
import com.goaway.utils.UploadImage;
import com.sun.org.apache.bcel.internal.generic.DMUL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
    public Map<String, Object> createNotice(String title, int noticeType, String content, int originator, int groupId, String imageURL) {
        Map<String, Object> map = new HashMap<>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String createTime=df.format(new Date());
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
    public Map<String, Object> insertDocument(String documentName, String documentURL) {
        Map<String, Object> map = new HashMap<>();
        try {
            noticeMapper.insertDocument(documentName, documentURL);
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
            List<Gw_notice> list = noticeMapper.queryNoticeByUserIdAndNoticeId(userId, id);
            for (int i = 0; i < list.size(); i++) {
                String[] documentName = list.get(i).getDocumentName().split(",");
                String[] documentURL = list.get(i).getDocumentURL().split(",");
                String[] imageURL = list.get(i).getImageURL().split(",");
                map.put("id", list.get(i).getId());
                map.put("title", list.get(i).getTitle());
                map.put("noticeType", list.get(i).getNoticetype());
                map.put("originator", list.get(i).getOriginator());
                map.put("content", list.get(i).getContent());
                map.put("flag", list.get(i).getFlag());
                map.put("createTime", list.get(i).getCreateTime());
                map.put("documentName", documentName);
                map.put("documentURL", documentURL);
                map.put("state", list.get(i).getState());
                map.put("imageURL", imageURL);
            }
            map.put("msg", "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "查询失败");
        }
        return map;
    }

    @Override
    public Map<String, Object> setReceived(int noticeId,int userId) {
        Map<String, Object> map = new HashMap<>();
        try {
            noticeMapper.setReceived(noticeId,userId);
            map.put("msg", "签到成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "签到失败");
        }
        return map;
    }


}
