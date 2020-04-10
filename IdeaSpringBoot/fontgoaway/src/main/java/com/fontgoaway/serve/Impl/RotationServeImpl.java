package com.fontgoaway.serve.Impl;

import com.fontgoaway.entity.Gw_rotation;
import com.fontgoaway.mapper.RotationMapper;
import com.fontgoaway.serve.RotatioServe;
import com.fontgoaway.utils.MultipartFileToFile;
import com.fontgoaway.utils.UploadImage;
import com.obs.services.ObsClient;
import com.obs.services.model.PutObjectResult;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.javassist.bytecode.stackmap.BasicBlock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RotationServeImpl implements RotatioServe {
    @Autowired
    RotationMapper rotationMapper;

    @Override
    public Map<String, Object> uploadRotation(String filename, MultipartFile upfile, String originator) {
        Map<String,Object> map=new HashMap<>();
        try {
            String endPoint="obs.cn-south-1.myhuaweicloud.com";
            String ak = "BLT3FQKF02NW5BA0PYWE";
            String sk = "kCFwHRccrbAmouJFERKYRv3LsON9zqDJTqmss2SF";
            ObsClient obsClient=new ObsClient(ak, sk, endPoint);
            SimpleDateFormat fo = new SimpleDateFormat("yyyyMMddHHmmSSss");//生成时间戳
            String flag=fo.format(new Date());
            String FileRoth="goaway/rotation/"+originator+"/"+flag+"/";
            MultipartFileToFile mf=new MultipartFileToFile();
            File file=mf.multipartFileToFile(upfile);
            PutObjectResult result = obsClient.putObject("longlongago",FileRoth+filename,file);
            String imageUrl=result.getObjectUrl();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            String createTime=format.format(new Date());
            rotationMapper.uploadRotation(imageUrl,createTime,originator);
            obsClient.close();
            map.put("msg","上传成功");
            map.put("flag",1);
        }catch (Exception e){
            map.put("msg","上传失败");
            map.put("flag",0);
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public Map<String, Object> queryRotation(int flag,int id,int curPage) {
        Map<String, Object> map=new HashMap<>();
        try {
            int count=rotationMapper.queryRotationCount(id).getCount();
            int pageNumber=(int)Math.ceil((double)count/15);//向上取整
            int page=(curPage-1)*15;
            List<Gw_rotation> rotationList=rotationMapper.queryRotation(flag,id,page);
            map.put("rotationList",rotationList);
            map.put("pageNumber",pageNumber);
            map.put("count",count);
            map.put("msg","查询成功");
            map.put("flah",1);
        }catch (Exception e){
            map.put("msg","查询失败");
            map.put("flah",0);
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public Map<String, Object> sheildRotation(int id,int state) {
        Map<String, Object> map=new HashMap<>();
        try {
            rotationMapper.sheildRotation(id, state);
            map.put("msg","屏蔽成功");
            map.put("flag",1);
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","屏蔽失败");
            map.put("flag",0);
        }
        return map;
    }

    @Override
    public Map<String, Object> queryRotationMini(int curPage) {
        Map<String, Object> map=new HashMap<>();
        try {
            int count=rotationMapper.queryRotationCountMini().getCount();
            int pageNumber=(int)Math.ceil((double)count/15);//向上取整
            int page=(curPage-1)*15;
            List<Gw_rotation> rotationList=rotationMapper.queryRotationMini(page);
            map.put("infoList",rotationList);
            map.put("pageNumber",pageNumber);
            map.put("count",count);
            map.put("msg","查询成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","查询失败");
        }
        return map;
    }

    @Override
    public Map<String, Object> orderRotationById(Map<String,Object> maps) {
        Map<String,Object> map=new HashMap<>();
        try {
            rotationMapper.orderRotationById(maps);
            map.put("msg","排序成功");
            map.put("flag",1);
        }catch (Exception e) {
            e.printStackTrace();
            map.put("msg","排序失败");
            map.put("flag",0);
        }
        return map;
    }


}
