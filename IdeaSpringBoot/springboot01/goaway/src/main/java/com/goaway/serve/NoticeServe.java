package com.goaway.serve;

import com.goaway.entity.Gw_notice;
import com.goaway.entity.Gw_user;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface NoticeServe {

    //通知的创建
    Map<String, Object> createNotice(String title, int noticeType, String content
            , int originator, int groupId, String imageURL);

    //根据userId或者通知名查询通知
    Map<String, Object> queryNoticeByUserIdAndTitle( int userId,String title);

    //插入附件信息
    Map<String, Object> insertDocument(@Param("documentName")String documentName,@Param("documentURL")String documentURL);

    //根据noticeId软删除通知
    Map<String, Object>  updateNoticeStateByNoticeId(@Param("id") int id);

    //根据noticeId和UserId查询通知的详细信息
    Map<String, Object>queryNoticeByUserIdAndNoticeId(@Param("userId") int userId, @Param("id")int id);

    //签到
    Map<String, Object> setReceived(int noticeId,int userId);
}
