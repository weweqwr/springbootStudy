package com.longlongago.service;

import com.longlongago.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    //添加文章
    Map<String, Object> addArticle(String article, String title, String account,Integer state);

    //根据文章状态和账号查找文章
    Map<String, Object> queryArticleByAccountAndState(Integer state, String account,String arIntroduct,String title);

    //根据文章id查询文章详细信息
    Map<String, Object> queryArticleById(Integer id);
}
