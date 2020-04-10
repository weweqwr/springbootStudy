package com.goaway.controller;

import com.goaway.serve.ReplyServe;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ReplyController {
    @Autowired
    ReplyServe replyServe;

    @RequestMapping(value = "createReply",method = RequestMethod.POST)
    Map<String,Object> createReply(int commentId,int userId,String nickname,String avatarUrl
            ,int toUserId,String toNickname,String toAvatarUrl,String content,int state){
        Map<String,Object> map=new HashMap();
        map=replyServe.createReply(commentId,userId,nickname,avatarUrl,toUserId,toNickname,toAvatarUrl,content,state);
        return map;
    }

    @RequestMapping(value = "deleteReplyById",method = RequestMethod.POST)
    Map<String,Object> deleteReplyById(int id){
        Map<String,Object> map=new HashMap();
        map=replyServe.deleteReplyById(id);
        return map;
    }
}
