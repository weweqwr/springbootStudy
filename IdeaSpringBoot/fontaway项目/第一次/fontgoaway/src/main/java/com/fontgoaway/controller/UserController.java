package com.fontgoaway.controller;

import com.fontgoaway.entity.Gw_user;
import com.fontgoaway.serve.UserServe;
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
 * @date ：Created in 2020/4/1 18:05
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserServe userServe;
    //    所有
    @RequestMapping(value = "/findAllUser",method = RequestMethod.GET)
    public Result findAllUser(@RequestParam int page, @RequestParam int size){
        Result result;
        try {
            PageHelper.startPage(page, size);
            List<Gw_user> list = userServe.findAllUser();
            if (list.size()>0){
                PageInfo<Gw_user> pageInfo = new PageInfo<>(list);
                result = new Result(ResultCode.SUCCESS);
                result.setData(pageInfo);
            }else {
                result = new Result(4444,"没有用户",true);
            }

        }catch (Exception e){
            result = new Result(ResultCode.SERVER_ERROR);
            result.setData(e.getLocalizedMessage());
        }
        return result;
    }
    //id
    @RequestMapping(value = "/findUserById",method = RequestMethod.GET)
    public Result findUserById(@RequestParam int id){
        Result result;
        try {
            Gw_user user = userServe.findUserById(id);
            if (!"".equals(user)){
                System.out.println(equals(user));
                result = new Result(ResultCode.SUCCESS);
                result.setData(user);
            }else {
                result = new Result(4445,"没有该用户",true);
            }

        }catch (Exception e){
            result = new Result(ResultCode.SERVER_ERROR);
            result.setData(e.getLocalizedMessage());
        }
        return result;
    }
    //    模糊nickname
    @RequestMapping(value = "/findUserByNicknameLick",method = RequestMethod.GET)
    public Result findUserByNicknameLick(@RequestParam int page, @RequestParam int size,@RequestParam String nickname){
        Result result;
        try {
            PageHelper.startPage(page, size);
            List<Gw_user> list = userServe.findUserByNicknameLick(nickname);
            if (list.size()>0){
                PageInfo<Gw_user> pageInfo = new PageInfo<>(list);
                result = new Result(ResultCode.SUCCESS);
                result.setData(pageInfo);
            }else {
                result = new Result(4446,"没有该昵称的用户",true);
            }

        }catch (Exception e){
            result = new Result(ResultCode.SERVER_ERROR);
            result.setData(e.getLocalizedMessage());
        }
        return result;
    }
    @RequestMapping(value = "/modifyUserState")
    public Result modifyUserState(@RequestBody Gw_user user){
        try {
            userServe.modifyUserState(user);
            return new Result(ResultCode.SUCCESS);
        }catch (Exception e){
            return new Result(ResultCode.SUCCESS);
        }

    }

}

