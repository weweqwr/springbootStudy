package com.goaway.mapper;

import com.goaway.entity.Gw_notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Mapper
public interface NoticeMapper {
    //创建通知
    void createNotice(@Param("title") String title
            , @Param("noticeType") int noticeType
            , @Param("content") String content
            , @Param("originator") int originator
            , @Param("groupId") int groupId
            , @Param("imageURL") String imageURL
            , @Param("createTime")String createTime
           );
    //插入附件信息
    void insertDocument(@Param("documentName")String documentName,@Param("documentURL")String documentURL);
    //根据userId或者title查询通知
    List<Map> queryNoticeByUserIdAndTitle(@Param("userId") int userId, @Param("title")String title);
    //根据noticeId软删除通知
    void updateNoticeStateByNoticeId(@Param("id") int id);
    //根据noticeId查询userId通知的详细信息
    List<Gw_notice> queryNoticeByUserIdAndNoticeId(@Param("userId") int userId, @Param("id")int id);
    //签到
    void setReceived(@Param("noticeId")int noticeId,@Param("userId") int userId);

}
