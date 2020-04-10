package com.goaway.controller;

import com.goaway.serve.LikeServe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LikeController {
    @Autowired
    LikeServe likeServe;

    @RequestMapping(value="addLike",method= RequestMethod.POST)
    public Map<String,Object> addLike(int commentId,int userId){
        Map<String,Object> map=new HashMap<>();
        map=likeServe.addLike(commentId,userId);
        return  map;
    }

}
