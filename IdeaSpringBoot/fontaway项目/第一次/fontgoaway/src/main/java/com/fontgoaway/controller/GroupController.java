package com.fontgoaway.controller;

import com.fontgoaway.entity.Gw_group;
import com.fontgoaway.serve.GroupServe;
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
 * @date ：Created in 2020/4/1 18:01
 */
@RestController
@RequestMapping(value = "/group")
public class GroupController {
    @Autowired
    GroupServe groupServe;
    //    所有
    @RequestMapping(value = "/findAllGroup",method = RequestMethod.POST)
    public Result findAllGroup(@RequestParam int page, @RequestParam int size){
        System.out.println(page);
        Result result;
        try{
            PageHelper.startPage(page,size);
            List<Gw_group> list = groupServe.findAllGroup();
            if(list.size()>0){
                PageInfo<Gw_group> pageInfo = new PageInfo<>(list);
                result = new Result(ResultCode.SUCCESS);
                result.setData(pageInfo);
            }else {
                result = new Result(8887,"一个群都没有",true);
            }
        }catch (Exception e){
            result = new Result(ResultCode.SERVER_ERROR);
            result.setData(e.getLocalizedMessage());
        }
        return result;
    }
    @RequestMapping(value = "/findGroupById",method = RequestMethod.GET)
    public Result findGroupById(@RequestParam String id){
        Result result;
        try {
            Gw_group group = groupServe.findGroupById(id);
            if (group.getId()!=null||group.getId()!=""){
//                System.out.println(equals(group));
                result = new Result(ResultCode.SUCCESS);
                result.setData(group);
            }else {
                result = new Result(8888,"群不存在",true);
            }

        }catch (Exception e){
            result = new Result(ResultCode.SERVER_ERROR);
            result.setData(e.getMessage());
        }
        return result;
    }
    @RequestMapping(value = "/findGroupByNameLike",method = RequestMethod.GET)
    public Result findGroupByNameLike(@RequestParam int page, @RequestParam int size, @RequestParam String groupName){
        Result result;
        try {
            PageHelper.startPage(page, size);
            List<Gw_group> list = groupServe.findGroupByNameLike(groupName);
            if(list.size()>0){
                PageInfo<Gw_group> pageInfo = new PageInfo<>(list);
                result = new Result(ResultCode.SUCCESS);
                result.setData(pageInfo);
            }else {
                result = new Result(8888,"群不存在",true);
            }

        }catch (Exception e){
            result = new Result(ResultCode.SERVER_ERROR);
            result.setData(e.getLocalizedMessage());
        }
        return result;
    }
    @RequestMapping(value = "/findGroupByState",method = RequestMethod.GET)
    public Result findGroupByState(@RequestParam int page, @RequestParam int size, @RequestParam int state){
        Result result;
        try {
            PageHelper.startPage(page, size);
            List<Gw_group> list = groupServe.findGroupByState(state);
            if (list.size()>0){
                PageInfo<Gw_group> pageInfo = new PageInfo<>(list);
                result = new Result(ResultCode.SUCCESS);
                result.setData(pageInfo);
            }else {
                result = new Result(8889,"输入状态错误或不存在该状态群",true);
            }

        }catch (Exception e){
            result = new Result(ResultCode.SERVER_ERROR);
            result.setData(e.getLocalizedMessage());
        }
        return result;
    }
    @RequestMapping(value = "/modifyGroupState",method = RequestMethod.PUT)
    public Result modifyGroupState(@RequestBody Gw_group group){
        try {
            groupServe.modifyGroupState(group);
            return new Result(ResultCode.SUCCESS);
        }catch (Exception e){
            return new Result(ResultCode.FAIL);
        }

    }
}

