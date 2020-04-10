package com.goaway.serve.Impl;

import com.goaway.mapper.LogoMapper;
import com.goaway.serve.LogoServe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LogoServeImpl implements LogoServe {
    @Autowired
    LogoMapper logoMapper;
    @Override
    public Map<String, Object> getLogoUrl() {
        Map<String,Object> map=new HashMap<>();
        try {
            map.put("logoUrl",logoMapper.getLogoUrl());
            map.put("msg","获取成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","获取失败");
        }
        return map;
    }
}
