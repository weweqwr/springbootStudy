package com.goaway.controller;

import com.goaway.serve.CommentServe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CommentController {
    @Autowired
    CommentServe commentServe;

    //留言
    @RequestMapping(value = "createComment", method = RequestMethod.POST)
    public Map<String, Object> createComment(int noticeId, int commentType, String content, int userId,int state,int replyId) {
        Map<String, Object> map = new HashMap<>();
        map = commentServe.createComment(noticeId,commentType,content,userId,state,replyId);
        return map;
    }
    //根据noticeId查询所有留言的信息
    @RequestMapping(value = "queryCommentByNoticeId", method = RequestMethod.POST)
    public Map<String, Object> queryCommentByNoticeId(int noticeId) {
        Map<String, Object> map = new HashMap<>();
        map = commentServe.queryCommentByNoticeId(noticeId);
        return map;
    }
    //根据commentId删除留言
    @RequestMapping(value = "delteCommentById", method = RequestMethod.POST)
    public Map<String, Object> delteCommentById(int id) {
        Map<String, Object> map = new HashMap<>();
        map = commentServe.delteCommentById(id);
        return map;
    }


}
