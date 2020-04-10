package com.longlongago.service.impl;

import com.longlongago.entity.Article;
import com.longlongago.mapper.ArticleMapper;
import com.longlongago.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleMapper articleMapper;
    @Override
    public Map<String,Object> addArticle(String article, String title, String account,Integer state) {
        Map<String,Object> map=new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String createTime = sdf.format(new Date());
        try {
            articleMapper.addArticle(article,title,account,createTime,state);
            map.put("msg","添加文章成功");
            map.put("flag",1);
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","添加文章失败");
            map.put("flag",0);
        }
        return map;
    }

    @Override
    public Map<String, Object> queryArticleByAccountAndState(Integer state, String account,String arIntroduct,String title) {
        List<Article> list=articleMapper.queryArticleByAccountAndState(state,account,arIntroduct,title);
        Map<String, Object> map1=new HashMap<>();
        Map<String, Object> map2=new HashMap<>();
        for(int i=0;i<list.size();i++){
            String ymds=list.get(i).getCreateTime();
            String[] ymd=ymds.split(" ");
            String ym=ymd[0];
            String[] y=ym.split("-");
         list.get(i).setYear(y[0]);
         list.get(i).setMonth(y[1]);
         list.get(i).setDay(y[2]);
        }
        Map<String, Object> map=new HashMap<>();
        map.put("articleInfo",list);
        return map;
    }

    @Override
    public Map<String, Object> queryArticleById(Integer id) {
        List<Article> list=articleMapper.queryArticleById(id);
        Map<String, Object> map1=new HashMap<>();
        Map<String, Object> map2=new HashMap<>();
        for(int i=0;i<list.size();i++){
            String ymds=list.get(i).getCreateTime();
            String[] ymd=ymds.split(" ");
            String ym=ymd[0];
            String[] y=ym.split("-");
            System.out.println(ymd[0]);
            list.get(i).setYear(y[0]);
            list.get(i).setMonth(y[1]);
            list.get(i).setDay(y[2]);
        }
        System.out.println(list);
        Map<String, Object> map=new HashMap<>();
        map.put("articleInfo",list);
        return map;
    }
}
