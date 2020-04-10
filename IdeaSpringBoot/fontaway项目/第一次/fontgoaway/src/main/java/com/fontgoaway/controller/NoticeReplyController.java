package com.fontgoaway.controller;

import com.fontgoaway.entity.Gw_notice_reply;
import com.fontgoaway.serve.NoticeReplyServe;
import com.fontgoaway.utils.Result;
import com.fontgoaway.utils.ResultCode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ：com.gjt
 * @description：TODO
 * @date ：Created in 2020/4/1 18:04
 */
@RestController
@RequestMapping(value = "/noticeReply")
public class NoticeReplyController {
    @Autowired
    NoticeReplyServe noticeReplyServe;
    //    所有
    @RequestMapping(value = "/findAllReply",method = RequestMethod.GET)
    public Result findAllReply(@RequestParam int page, @RequestParam int size){
        Result result;
        try {
            PageHelper.startPage(page, size);
            List<Gw_notice_reply> list = noticeReplyServe.findAllReply();
            if(list.size()>0){
                PageInfo<Gw_notice_reply> pageInfo = new PageInfo<>(list);
                result = new Result(ResultCode.SUCCESS);
                result.setData(pageInfo);
            }else {
                result = new Result(5555,"没有相关回复",true);
            }

        }catch (Exception e){
            result = new Result(ResultCode.SERVER_ERROR);
            result.setData(e.getLocalizedMessage());
        }
        return result;
    }
    @RequestMapping(value = "/findReplyByCIdUIdTUId",method = RequestMethod.GET)
    public Result findReplyByCIdUIdTUId(@RequestParam int page, @RequestParam int size,@RequestParam int commentId,@RequestParam int userId,@RequestParam int toUserId){
        Result result;
        try {
            PageHelper.startPage(page, size);
            List<Gw_notice_reply> list = noticeReplyServe.findReplyByCIdUIdTUId(commentId, userId, toUserId);
            if(list.size()>0){
                PageInfo<Gw_notice_reply> pageInfo = new PageInfo<>(list);
                result = new Result(ResultCode.SUCCESS);
                result.setData(pageInfo);
            }else {
                result = new Result(5556,"没有相关的用户回复",true);
            }

        }catch (Exception e){
            result = new Result(ResultCode.SERVER_ERROR);
            result.setData(e.getLocalizedMessage());
        }
        return result;
    }
    @RequestMapping(value = "/findReplyByState",method = RequestMethod.GET)
    public Result findReplyByState(@RequestParam int page, @RequestParam int size,
                                   @RequestParam int state){
        Result result;
        try {
            PageHelper.startPage(page, size);
            List<Gw_notice_reply> list = noticeReplyServe.findReplyByState(state);
            if (list.size()>0){
                PageInfo<Gw_notice_reply> pageInfo = new PageInfo<>(list);
                result = new Result(ResultCode.SUCCESS);
                result.setData(pageInfo);
            }else {
                result = new Result(5557,"没有该状态的通知",true);
            }

        }catch (Exception e){
            result = new Result(ResultCode.SERVER_ERROR);
            result.setData(e.getLocalizedMessage());
        }
        return result;
    }
    @RequestMapping(value = "/modifyState",method = RequestMethod.PUT)
    public Result modifyState(@RequestParam int id, @RequestParam int state){
        try {
            noticeReplyServe.modifyState(id,state);
            return new Result(ResultCode.SUCCESS);
        }catch (Exception e){

            return new Result(ResultCode.FAIL);
        }

    }
}
