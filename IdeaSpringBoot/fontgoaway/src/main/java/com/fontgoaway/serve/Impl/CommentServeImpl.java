package com.fontgoaway.serve.Impl;

import com.fontgoaway.entity.Gw_notice_comment;
import com.fontgoaway.entity.Gw_notice_reply;
import com.fontgoaway.mapper.CommentMapper;
import com.fontgoaway.mapper.ReplyMapper;
import com.fontgoaway.serve.CommentServe;
import com.fontgoaway.serve.ReplyServe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentServeImpl implements CommentServe {
    @Autowired
    CommentMapper commentMapper;

    @Override
    public Map<String, Object> queryComment(int flag, int id, int curPage) {
        Map<String, Object> map=new HashMap<>();
        try {
            int count=commentMapper.CommentCount(id);
            int pageNumber=(int)Math.ceil((double)count/15);//向上取整
            int page=(curPage-1)*15;
            List<Gw_notice_comment> commentList=commentMapper.queryComment(flag,id,page);
            map.put("commentList",commentList);
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
    public Map<String, Object> sheildComment(int id, int state) {
        Map<String, Object> map=new HashMap<>();
        try {
            commentMapper.sheildComment(id, state);
            map.put("msg","屏蔽成功");
            map.put("flag",1);
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","屏蔽失败");
            map.put("flag",0);
        }
        return map;
    }
}
