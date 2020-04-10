package com.fontgoaway.serve.Impl;

import com.fontgoaway.entity.Gw_notice_reply;
import com.fontgoaway.mapper.NoticeReplyMapper;
import com.fontgoaway.serve.NoticeReplyServe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：com.gjt
 * @description：TODO
 * @date ：Created in 2020/4/1 17:59
 */
@Service
public class NoticeReplyServeImpl implements NoticeReplyServe {
    @Autowired
    NoticeReplyMapper noticeReplyMapper;
    @Override
    public List<Gw_notice_reply> findAllReply() {
        return noticeReplyMapper.findAllReply();
    }

    @Override
    public List<Gw_notice_reply> findReplyByCIdUIdTUId(int commentId, int userId, int toUserId) {
        return noticeReplyMapper.findReplyByCIdUIdTUId(commentId,userId,toUserId);
    }

    @Override
    public List<Gw_notice_reply> findReplyByState(int state) {
        return noticeReplyMapper.findReplyByState(state);
    }

    @Override
    public void modifyState(int id, int state) {
        noticeReplyMapper.modifyState(id,state);
    }
}
