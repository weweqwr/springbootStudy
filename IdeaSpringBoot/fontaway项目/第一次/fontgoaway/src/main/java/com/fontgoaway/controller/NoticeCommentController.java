package com.fontgoaway.controller;

import com.fontgoaway.entity.Gw_notice_comment;
import com.fontgoaway.serve.NoticeCommentServe;
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
 * @date ：Created in 2020/4/1 18:03
 */
@RestController
@RequestMapping(value = "/noticeComment")
public class NoticeCommentController {
    @Autowired
    NoticeCommentServe noticeCommentServe;
    //    所有
    @RequestMapping(value = "/findAllComment",method = RequestMethod.GET)
    public Result findAllComment(@RequestParam() int page, @RequestParam int size){
        Result result;
        try {
            PageHelper.startPage(page,size);
            List<Gw_notice_comment> list = noticeCommentServe.findAllComment();
            if (list.size()>0){
                PageInfo<Gw_notice_comment>pageInfo = new PageInfo<>(list);
                result = new Result(ResultCode.SUCCESS);
                result.setData(pageInfo);
            }else {
                result=new Result(7777,"没有评论",true);
            }

        }catch (Exception e){
            result = new Result(ResultCode.SERVER_ERROR);
            result.setData(e.getLocalizedMessage());
        }
        return result;
    }
    @RequestMapping(value = "/findByNoticeIdUserId",method = RequestMethod.GET)
    public Result findByNoticeIdUserId(@RequestParam int page, @RequestParam int size,
                                       @RequestParam int noticeId,@RequestParam int userId){
        Result result;
        try {
            PageHelper.startPage(page,size);
            List<Gw_notice_comment> list = noticeCommentServe.findByNoticeIdUserId(noticeId,userId);
            if (list.size()>0){
                PageInfo<Gw_notice_comment>pageInfo = new PageInfo<>(list);
                result = new Result(ResultCode.SUCCESS);
                result.setData(pageInfo);
            }else {
                result = new Result(7778,"没有改评论，或者被屏蔽了",true);
            }

        }catch (Exception e){
            result = new Result(ResultCode.SERVER_ERROR);
            result.setData(e.getLocalizedMessage());
        }

        return result;
    }
    @RequestMapping(value = "/findByNoticeIdUserIdCommentType",method = RequestMethod.GET)
    public Result findByNoticeIdUserIdCommentType(@RequestParam int page, @RequestParam int size,
                                                  @RequestParam int noticeId,@RequestParam int userId,
                                                  @RequestParam int commentType){
        Result result;
        try {
            PageHelper.startPage(page, size);
            List<Gw_notice_comment> list = noticeCommentServe.findByNoticeIdUserIdCommentType(noticeId, userId, commentType);
            if(list.size()>0){
                PageInfo<Gw_notice_comment> pageInfo = new PageInfo<>(list);
                result = new Result(ResultCode.SUCCESS);
                result.setData(pageInfo);
            }else{
                result = new Result(7779,"没有改类型的评论",true);
            }

        }catch (Exception e){
            result = new Result(ResultCode.SERVER_ERROR);
            result.setData(e.getLocalizedMessage());
        }
        return result;
    }
    @RequestMapping(value = "/findByContent",method = RequestMethod.GET)
    public Result findByContent(@RequestParam int page, @RequestParam int size,
                                @RequestParam String content){
        Result result;
        try {
            PageHelper.startPage(page,size);
            List<Gw_notice_comment> list = noticeCommentServe.findByContent(content);
            if (list.size()>0){
                PageInfo<Gw_notice_comment>pageInfo = new PageInfo<>(list);
                result = new Result(ResultCode.SUCCESS);
                result.setData(pageInfo);
            }else {
                result = new Result(7780,"没有此内容的评论",true);
            }

        }catch (Exception e){
            result = new Result(ResultCode.SERVER_ERROR);
            result.setData(e.getLocalizedMessage());
        }
        return result;
    }
    @RequestMapping(value = "/findByState",method = RequestMethod.GET)
    public Result findByState(@RequestParam int page, @RequestParam int size,
                              @RequestParam int state){
        Result result;
        try {
            PageHelper.startPage(page,size);
            List<Gw_notice_comment> list = noticeCommentServe.findByState(state);
            if(list.size()>0){
                PageInfo<Gw_notice_comment>pageInfo = new PageInfo<>(list);
                result = new Result(ResultCode.SUCCESS);
                result.setData(pageInfo);
            }else {
                result = new Result(7781,"状态码错误或没有改状态的评论",true);
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
            noticeCommentServe.modifyState(id,state);
            return new Result(ResultCode.SUCCESS);
        }catch (Exception e){
            return new Result(ResultCode.FAIL);
        }

    }
}
