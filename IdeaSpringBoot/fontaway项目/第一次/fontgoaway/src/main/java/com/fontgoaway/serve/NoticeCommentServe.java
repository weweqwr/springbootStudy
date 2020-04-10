package com.fontgoaway.serve;

import com.fontgoaway.entity.Gw_notice_comment;

import java.util.List;

/**
 * @author ：com.gjt
 * @description：TODO
 * @date ：Created in 2020/3/31 19:05
 */
public interface NoticeCommentServe {
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
