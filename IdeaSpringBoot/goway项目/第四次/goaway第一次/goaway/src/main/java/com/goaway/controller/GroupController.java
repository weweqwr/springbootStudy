package com.goaway.controller;

import com.goaway.entity.Gw_group;
import com.goaway.entity.Gw_user;
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
        try {
            Gw_group group = groupService.queryGroupById(id);
            Result result;
            if (group==null){
                result =new Result(2222,"群不存在",true);
            }else {
                if (group.getState()==1){
                    result =new Result(ResultCode.SUCCESS);
                    result.setData(group);
                }else {
                    result =new Result(2223,"该群存在违规已被屏蔽",true);
                }
            }

            return result;
        }catch (Exception e){
            return new Result(ResultCode.SERVER_ERROR);
        }
    }

    //解散群聊
    @RequestMapping(value = "/deleteById/{id}",method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable(value = "id")String id){
        try{
            groupService.deleteById(id);
            return new Result(ResultCode.SUCCESS);
        }catch (Exception e){
            return new Result(ResultCode.SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public Result update(@RequestBody Gw_group group){
        try {
            groupService.update(group);
            return new Result(ResultCode.SUCCESS);
        }catch (Exception e){
            return new Result(ResultCode.SERVER_ERROR);
        }
    }

    //创建群
    @RequestMapping(value = "/addGroup/{uid}",method = RequestMethod.POST)
    public Result addGroup(@RequestBody Gw_group group, @PathVariable(value = "uid")int uid){
        try {
            group.setUser(userService.findUserById(uid));
            groupService.addGroup(group);
            return new Result(ResultCode.SUCCESS);
        }catch (Exception e){
            return new Result(ResultCode.SERVER_ERROR);
        }
    }
    //用户拥有的群聊
    @RequestMapping(value="/haveGroup/{uid}" ,method = RequestMethod.GET)
    public Result haveGroup(@PathVariable(value = "uid")int uid){
        try {
            List<Gw_group> groups=groupService.haveGroup(uid);
            Result result = new Result(ResultCode.SUCCESS);
            result.setData(groups);
            return result;
        }catch (Exception e){
            return new Result(ResultCode.SERVER_ERROR);
        }
    }
    //申请进群
    @RequestMapping(value = "/apply/{uid}",method = RequestMethod.POST)
    public Result apply(@RequestBody Gw_group group,@PathVariable(value = "uid")int uid){


        Gw_user_group userGroup=userGroupService.findUser(uid,group.getId());

//        System.out.println(userGroup==null);
        if (userGroup==null){
            Gw_user user = userService.findUserById(uid);
            group.setUser(userService.findUserById(uid));
            groupService.apply(group);
            return new Result(ResultCode.SUCCESS);

        }else {
            return new Result(ResultCode.FAIL);
        }

    }

}
