package com.goaway.serve.Impl;

import com.goaway.entity.Gw_rotation;
import com.goaway.mapper.RotationMapper;
import com.goaway.serve.RotationServe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RotationServeImpl  implements RotationServe {
    @Autowired
    RotationMapper rotationMapper;

    @Override
    public Map<String, Object> queryRontation() {
        Map<String,Object> map=new HashMap<>();
        try {
            List<Gw_rotation> rotationList=rotationMapper.queryRontation();
            map.put("RotationList",rotationList);
            map.put("msg","查询成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","查询失败");
        }
        return map;
    }
}
