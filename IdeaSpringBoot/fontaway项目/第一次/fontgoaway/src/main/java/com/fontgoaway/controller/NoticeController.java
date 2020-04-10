package com.fontgoaway.controller;

import com.fontgoaway.entity.Gw_notice;
import com.fontgoaway.serve.NoticeServe;
import com.fontgoaway.utils.Result;
import com.fontgoaway.utils.ResultCode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ：com.gjt
 * @description：TODO
 * @date ：Created in 2020/4/1 18:04
 */
@RestController
@RequestMapping(value = "/notice")
public class NoticeController {

    @Autowired
    NoticeServe noticeServe;
    //    所有
    @RequestMapping(value = "/findAllNotice",method = RequestMethod.GET)
    public Result findAllNotice(@RequestParam int page, @RequestParam int size){
        Result result;
        try {
            PageHelper.startPage(page, size);
            List<Gw_notice> list = noticeServe.findAllNotice();
            if(list.size()>0){
                PageInfo<Gw_notice> pageInfo = new PageInfo<>(list);
                result = new Result(ResultCode.SUCCESS);
                result.setData(pageInfo);
            }else {
                result = new Result(6666,"没有通知",true);
            }

        }catch (Exception e){
            result = new Result(ResultCode.SERVER_ERROR);
            result.setData(e.getLocalizedMessage());
        }
        return result;
    }
    //    id
    @RequestMapping(value = "/findNoticeById",method = RequestMethod.GET)
    public Result findNoticeById(@RequestParam Integer id){
        Result result;
        try {
            Gw_notice notice = noticeServe.findNoticeById(id);
            if (!"".equals(notice)){
                result = new Result(ResultCode.SUCCESS);
                result.setData(notice);
            }else {
                result = new Result(6667,"没有改通知或通知id有误",true);
            }

        }catch (Exception e){
            result = new Result(ResultCode.SERVER_ERROR);
            result.setData(e.getLocalizedMessage());
        }
        return result;
    }
    //    title
    @RequestMapping(value = "/findNoticeByTitle",method = RequestMethod.GET)
    public Result findNoticeByTitle(@RequestParam int page, @RequestParam int size,@RequestParam String title){
        Result result;
        try {
            PageHelper.startPage(page, size);
            List<Gw_notice> notices = noticeServe.findNoticeByTitle(title);
            if (notices.size()>0){
                PageInfo<Gw_notice> pageInfo = new PageInfo<>(notices);
                result = new Result(ResultCode.SUCCESS);
                result.setData(pageInfo);
            }else {
                result = new Result(6668,"没有该通知相关的标题",true);
            }

        }catch (Exception e){
            result = new Result(ResultCode.SERVER_ERROR);
            result.setData(e.getLocalizedMessage());
        }
        return result;
    }
    //    模糊title
    @RequestMapping(value = "/findNoticeByTitleLick",method = RequestMethod.GET)
    public Result findNoticeByTitleLick(@RequestParam int page, @RequestParam int size,@RequestParam String title){
        Result result;
        try {
            PageHelper.startPage(page,size);
            List<Gw_notice> notices = noticeServe.findNoticeByTitleLick(title);
            if (notices.size()>0){
                PageInfo<Gw_notice>pageInfo = new PageInfo<>(notices);
                result = new Result(ResultCode.SUCCESS);
                result.setData(pageInfo);
            }else {
                result = new Result(6668,"没有该通知相关的标题.",true);
            }

        }catch (Exception e){
            result = new Result(ResultCode.SERVER_ERROR);
            result.setData(e.getLocalizedMessage());
        }
        return result;
    }
    //    noticeType
    @RequestMapping(value = "/finsNoticeByType",method = RequestMethod.GET)
    public Result finsNoticeByType(@RequestParam int page, @RequestParam int size,@RequestParam int noticeType){
        Result result;
        try {
            PageHelper.startPage(page, size);
            List<Gw_notice> notices = noticeServe.finsNoticeByType(noticeType);
            if (notices.size()>0){
                PageInfo<Gw_notice> pageInfo = new PageInfo<>(notices);
                result = new Result(ResultCode.SUCCESS);
                result.setData(pageInfo);
            }else {
                result = new Result(6669,"没有该类型的通知",true);
            }

        }catch (Exception e){
            result = new Result(ResultCode.SERVER_ERROR);
            result.setData(e.getLocalizedMessage());
        }
        return result;
    }
    //    originator发起人
    @RequestMapping(value = "/findNoticeByOriginator",method = RequestMethod.GET)
    public Result findNoticeByOriginator(@RequestParam int page, @RequestParam int size,@RequestParam Integer originator){
        Result result;
        try {
            PageHelper.startPage(page, size);
            List<Gw_notice> notices = noticeServe.findNoticeByOriginator(originator);
            if (notices.size()>0){
                PageInfo<Gw_notice> pageInfo = new PageInfo<>(notices);
                result = new Result(ResultCode.SUCCESS);
                result.setData(pageInfo);
            }else {
                result = new Result(6670,"没有该发起人相关的通知",true);
            }

        }catch (Exception e){
            result = new Result(ResultCode.SERVER_ERROR);
            result.setData(e.getLocalizedMessage());
        }
        return result;
    }
    //    groupId
    @RequestMapping(value = "/findNoticeByGroupId",method = RequestMethod.GET)
    public Result findNoticeByGroupId(@RequestParam int page, @RequestParam int size,@RequestParam String groupId){
        Result result;
        try {
            PageHelper.startPage(page, size);
            List<Gw_notice> notices = noticeServe.findNoticeByGroupId(groupId);
            if (notices.size()>0){
                PageInfo<Gw_notice> pageInfo = new PageInfo<>(notices);
                result = new Result(ResultCode.SUCCESS);
                result.setData(pageInfo);
            }else {
                result = new Result(6671,"没有该群相关的通知或没有该群",true);
            }

        }catch (Exception e){
            result = new Result(ResultCode.SERVER_ERROR);
            result.setData(e.getLocalizedMessage());
        }
        return result;
    }
    //    state
    @RequestMapping(value = "/finDNoticeByState",method = RequestMethod.GET)
    public Result finDNoticeByState(@RequestParam int page, @RequestParam int size,@RequestParam Integer state){
        Result result;
        try {
            PageHelper.startPage(page, size);
            List<Gw_notice> notices = noticeServe.finDNoticeByState(state);
            if (notices.size()>0){
                PageInfo<Gw_notice> pageInfo = new PageInfo<>(notices);
                result = new Result(ResultCode.SUCCESS);
                result.setData(pageInfo);
            }else {
                result = new Result(6672,"没有该状态的通知或没有该状态存在",true);
            }

        }catch (Exception e){
            result = new Result(ResultCode.SERVER_ERROR);
            result.setData(e.getLocalizedMessage());
        }
        return result;
    }

    //    屏蔽modify
    @RequestMapping(value = "/modifyStateById",method = RequestMethod.PUT)
    public Result modifyStateById(@RequestBody Gw_notice notice){
        try {
            noticeServe.modifyStateById(notice);
            return new Result(ResultCode.SUCCESS);
        }catch (Exception e){
            return new Result(ResultCode.FAIL);
        }

    }

}

