package com.goaway.controller;

import com.goaway.entity.Gw_user_group;
import com.goaway.serve.UserGroupServe;
import com.goaway.utils.Result;
import com.goaway.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ：com.gjt
 * @description：TODO
 * @date ：Created in 2020/3/22 11:03
 */
@RestController
@RequestMapping(value = "/userGroup")
public class UserGroupController {
    @Autowired
    UserGroupServe userGroupService;

    //待审核列表
    @RequestMapping(value = "/acceptList",method = RequestMethod.POST)
    public Result acceptList(@RequestBody Gw_user_group userGroup){
        List<Gw_user_group> lists=userGroupService.findAcceptList(userGroup);
        Result result =new Result(ResultCode.SUCCESS);
        result.setData(lists);
        return result;
    }

    //审核通过
    @RequestMapping(value = "/accept",method = RequestMethod.PUT)
    public Result accept(@RequestBody Gw_user_group userGroup){
        userGroupService.updateUserGroup(userGroup);
        return new Result(ResultCode.SUCCESS);
    }
    //移出群聊
    @RequestMapping(value = "/remove",method = RequestMethod.DELETE)
    public Result remove(@RequestBody Gw_user_group userGroup){

        userGroupService.deleteUserGroup(userGroup);
        return new Result(ResultCode.SUCCESS);
    }
    //同一个群聊
    @RequestMapping(value = "/findGroupByGroupId/{gid}",method = RequestMethod.GET)
    public Result findGroupByGroupId(@PathVariable(value = "gid")String gid){

        List<Gw_user_group> lists=userGroupService.findGroupByGroupId(gid);
        Result result =new Result(ResultCode.SUCCESS);
        result.setData(lists);
        return result;
    }
    //群身份查询
    @RequestMapping(value = "/findIdentity",method = RequestMethod.GET)
    public Result findIdentity(@RequestBody Gw_user_group userGroup){
        int identity = userGroupService.findIdentity(userGroup);
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(identity);
        return result;
    }
}
