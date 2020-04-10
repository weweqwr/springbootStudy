package com.goaway.controller;

import com.goaway.serve.LogoServe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LogoController {
    @Autowired
    LogoServe logoServe;
    @RequestMapping(value="getLogoUrl",method= RequestMethod.POST)
    public Map<String,Object> getLogoUrl(){
        Map<String,Object> map=new HashMap<>();
        map=logoServe.getLogoUrl();
        return  map;
    }

}
