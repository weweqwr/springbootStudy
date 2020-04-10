package com.fontgoaway.mapper;

import com.fontgoaway.entity.Gw_notice_comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeCommentMapper {
    //    所有
    public List<Gw_notice_comment> findAllComment();
    //    根据 noticeId和userId
    public List<Gw_notice_comment> findByNoticeIdUserId(int noticeId,int userId);
    //    根据 noticeId和userId、commentType
    public List<Gw_notice_comment> findByNoticeIdUserIdCommentType(int noticeId,int userId,int commentType);
    //    content
    public List<Gw_notice_comment> findByContent(String content);
    //    state
    public List<Gw_notice_comment> findByState(int state);
    //    屏蔽
    public void modifyState(int id,int state);

}
