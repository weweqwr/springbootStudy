package com.goaway.controller;

import com.goaway.serve.RotationServe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RotationController {
    @Autowired
    RotationServe rotationServe;
    @RequestMapping(value = "queryRontation",method= RequestMethod.POST )
    public Map<String, Object> queryRontation() {
        Map<String,Object> map=rotationServe.queryRontation();
        return map;
    }
}
