package com.fontgoaway.controller;

import com.fontgoaway.entity.Gw_notice;
import com.fontgoaway.entity.Gw_notice_comment;
import com.fontgoaway.serve.CommentServe;
import com.fontgoaway.serve.NoticeServe;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class NoticeController {
    @Autowired
    NoticeServe noticeServe;
    //查询通知
    @RequiresPermissions("notice")
    @RequestMapping(value = "queryNotices",method = RequestMethod.POST)
    public Map<String,Object> queryNotices(@RequestBody Gw_notice notice) {
        Map<String,Object> map=noticeServe.queryNotice(notice.getFlag(),notice.getId(),notice.getCurPage());
        return map;
    }
    //屏蔽通知
    @RequiresPermissions("notice")
    @RequestMapping(value = "sheildNotices",method = RequestMethod.POST)
    public Map<String,Object> sheildNotices(@RequestBody Gw_notice notice) {
        Map<String,Object> map=noticeServe.sheildNotice(notice.getId(),notice.getState());
        return map;
    }
}
