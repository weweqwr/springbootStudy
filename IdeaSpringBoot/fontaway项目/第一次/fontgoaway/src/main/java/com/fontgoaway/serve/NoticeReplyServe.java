package com.fontgoaway.serve;

import com.fontgoaway.entity.Gw_notice_reply;

import java.util.List;

public interface NoticeReplyServe {
    //    所有
    public List<Gw_notice_reply> findAllReply();
    //    commentId,userId,toUserId
    public List<Gw_notice_reply> findReplyByCIdUIdTUId(int commentId,int userId,int toUserId);
    //    state
    public List<Gw_notice_reply> findReplyByState(int state);
    //    屏蔽
    public void modifyState(int id,int state);

}
