package com.longlongago.mapper;

import com.longlongago.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleMapper {
     //添加文章
     void addArticle(@Param("article")String article,@Param("title")String title,@Param("account")String account,@Param("createTime")String createTime,@Param("state")Integer state);
     //根据文章状态和账号查找文章
     List<Article> queryArticleByAccountAndState(@Param("state")Integer state, @Param("account")String account,@Param("arIntroduct")String arIntroduct,@Param("title")String title);
     //根据文章id查询文章详细信息
     List<Article> queryArticleById(@Param("id")Integer id);
}
