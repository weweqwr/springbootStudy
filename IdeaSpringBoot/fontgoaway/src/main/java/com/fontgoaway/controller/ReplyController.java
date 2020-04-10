package com.fontgoaway.controller;

import com.fontgoaway.entity.Gw_notice_reply;
import com.fontgoaway.entity.Gw_rotation;
import com.fontgoaway.serve.ReplyServe;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ReplyController {
    @Autowired
    ReplyServe replyServe;
    //查询回复
    @RequiresPermissions("reply")
    @RequestMapping(value = "queryReply",method = RequestMethod.POST)
    public Map<String,Object> queryReply(@RequestBody Gw_notice_reply reply) {
        Map<String,Object> map=replyServe.queryReply(reply.getFlag(),reply.getId(),reply.getCurPage());
        return map;
    }
    //屏蔽回复
    @RequiresPermissions("reply")
    @RequestMapping(value = "sheildReply",method = RequestMethod.POST)
    public Map<String,Object> sheildReply(@RequestBody Gw_notice_reply reply) {
        Map<String,Object> map=replyServe.sheildReply(reply.getId(),reply.getState());
        return map;
    }
}
