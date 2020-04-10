package com.goaway.controller;

import com.goaway.entity.Gw_group;
import com.goaway.entity.Gw_user_group;
import com.goaway.serve.GroupServe;
import com.goaway.serve.UserGroupServe;
import com.goaway.serve.UserServe;
import com.goaway.utils.Result;
import com.goaway.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ：com.gjt
 * @description：TODO
 * @date ：Created in 2020/3/22 11:00
 */
@RestController
@RequestMapping(value = "/group")
public class GroupController {
    @Autowired
    GroupServe groupService;
    @Autowired
    UserGroupServe userGroupService;
    @Autowired
    UserServe userService;

    @RequestMapping(value = "/queryGroupList",method = RequestMethod.GET)
    public Result queryGroupList(){
        List<Gw_group> lists=groupService.queryGroupList();
        Result result =new Result(ResultCode.SUCCESS);
        result.setData(lists);
        return result;
    }

    @RequestMapping(value = "/queryGroupById/{id}",method = RequestMethod.GET)
    public Result queryGroupById(@PathVariable(value = "id")String id){
        Gw_group group = groupService.queryGroupById(id);
        Result result =new Result(ResultCode.SUCCESS);
        result.setData(group);
        return result;
    }

    //解散群聊
    @RequestMapping(value = "/deleteById/{id}",method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable(value = "id")String id){
        groupService.deleteById(id);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public Result update(@RequestBody Gw_group group){
        groupService.update(group);
        return new Result(ResultCode.SUCCESS);
    }

    //创建群
    @RequestMapping(value = "/addGroup/{uid}",method = RequestMethod.POST)
    public Result addGroup(@RequestBody Gw_group group, @PathVariable(value = "uid")int uid){
        group.setUser(userService.findUserById(uid));
        groupService.addGroup(group);
        return new Result(ResultCode.SUCCESS);
    }
    //申请进群
    @RequestMapping(value = "/apply/{uid}",method = RequestMethod.POST)
    public Result apply(@RequestBody Gw_group group,@PathVariable(value = "uid")int uid){


        Gw_user_group userGroup=userGroupService.findUser(uid,group.getId());
        if (userGroup==null){
            group.setUser(userService.findUserById(uid));
            groupService.apply(group);
            return new Result(ResultCode.SUCCESS);

        }else {
            return new Result(ResultCode.FAIL);
        }


    }

}
