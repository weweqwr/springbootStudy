package com.longlongago.controller;

import com.alibaba.fastjson.JSONException;
import com.baidu.ueditor.ActionEnter;

import com.longlongago.entity.Ueditor;
import com.longlongago.utils.PublicMsg;

import com.longlongago.utils.UploadImage;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


@RestController
public class UeditorController {
    @RequestMapping("/")
    private String showPage(){
        return "index";
    }

    @RequestMapping(value="/ueditor",produces = "text/script;charset=UTF-8")
    public String ueditor( HttpServletRequest request,HttpServletResponse response, String callback) {
        String s=PublicMsg.UEDITOR_CONFIG;

        return callback + "(" + s + ")";
    }

    @RequestMapping(value="/imgUpload")
    public Ueditor imgUpload(MultipartFile upfile,HttpServletRequest request) {
        Ueditor ueditor = new Ueditor();
        UploadImage uploadImage=new UploadImage();
        Map<String,Object> map=new HashMap<>();
        map=uploadImage.upload(upfile,request);
        String imageURL=map.get("imageURL").toString();
        ueditor.setState("SUCCESS");
        ueditor.setUrl(imageURL);
        ueditor.setTitle(upfile.getOriginalFilename());
        ueditor.setOriginal(upfile.getOriginalFilename());
        return ueditor;
    }

}