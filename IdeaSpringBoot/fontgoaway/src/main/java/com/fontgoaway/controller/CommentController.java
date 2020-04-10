package com.fontgoaway.controller;

import com.fontgoaway.entity.Gw_notice_comment;
import com.fontgoaway.entity.Gw_notice_reply;
import com.fontgoaway.serve.CommentServe;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CommentController {
    @Autowired
    CommentServe commentServe;
    //查询评论
    @RequiresPermissions("comment")
    @RequestMapping(value = "queryComment",method = RequestMethod.POST)
    public Map<String,Object> queryReply(@RequestBody Gw_notice_comment comment) {
        Map<String,Object> map=commentServe.queryComment(comment.getFlag(),comment.getId(),comment.getCurPage());
        return map;
    }
    //屏蔽回复
    @RequiresPermissions("comment")
    @RequestMapping(value = "sheildComment",method = RequestMethod.POST)
    public Map<String,Object> sheildReply(@RequestBody Gw_notice_comment comment) {
        Map<String,Object> map=commentServe.sheildComment(comment.getId(),comment.getState());
        return map;
    }
}
