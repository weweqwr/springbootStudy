package com.longlongago.controller;

import com.longlongago.entity.Article;
import com.longlongago.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @RequestMapping(value = "addArticle",method = RequestMethod.POST)
    public Map<String,Object> addArticle(@RequestBody Article articles){
        Map<String,Object> map=new HashMap<>();
        String article=articles.getArticle();
        String title=articles.getTitle();
        String account=articles.getAccount();
        int state=articles.getState();
        map=articleService.addArticle(article,title,account,state);
        return map;
    }

    //显示首页信息
    //根据title或arItroduce模糊查询
    @RequestMapping(value = "queryArticleByAccountAndState",method = RequestMethod.POST)
    public Map<String,Object> queryArticleByAccountAndState(@RequestBody Article article){
        Map<String,Object> map=new HashMap<>();
        int state= article.getState();
        String account=article.getAccount();
        String arIntroduct=article.getArIntroduct();
        String title=article.getTitle();
        map=articleService.queryArticleByAccountAndState(state,account,arIntroduct,title);
        return map;
    }

    @RequestMapping(value = "queryArticleById",method = RequestMethod.POST)
    public Map<String,Object> queryArticleById(@RequestBody Map map1){
        Map<String,Object> map=new HashMap<>();
        int id= Integer.parseInt(map1.get("id").toString());
        map=articleService.queryArticleById(id);
        return map;
    }


}
