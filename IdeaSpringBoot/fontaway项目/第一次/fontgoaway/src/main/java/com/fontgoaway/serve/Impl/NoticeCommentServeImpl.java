package com.fontgoaway.serve.Impl;

import com.fontgoaway.entity.Gw_notice_comment;
import com.fontgoaway.mapper.NoticeCommentMapper;
import com.fontgoaway.serve.NoticeCommentServe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：com.gjt
 * @description：TODO
 * @date ：Created in 2020/4/1 17:58
 */
@Service
public class NoticeCommentServeImpl implements NoticeCommentServe {
    @Autowired
    NoticeCommentMapper noticeCommentMapper;
    @Override
    public List<Gw_notice_comment> findAllComment() {
        return noticeCommentMapper.findAllComment();
    }

    @Override
    public List<Gw_notice_comment> findByNoticeIdUserId(int noticeId, int userId) {
        return noticeCommentMapper.findByNoticeIdUserId(noticeId,userId);
    }

    @Override
    public List<Gw_notice_comment> findByNoticeIdUserIdCommentType(int noticeId, int userId, int commentType) {
        return noticeCommentMapper.findByNoticeIdUserIdCommentType(noticeId,userId,commentType);
    }

    @Override
    public List<Gw_notice_comment> findByContent(String content) {
        return noticeCommentMapper.findByContent("%"+content+"%");
    }

    @Override
    public List<Gw_notice_comment> findByState(int state) {
        return noticeCommentMapper.findByState(state);
    }

    @Override
    public void modifyState(int id, int state) {
        noticeCommentMapper.modifyState(id,state);
    }
}
