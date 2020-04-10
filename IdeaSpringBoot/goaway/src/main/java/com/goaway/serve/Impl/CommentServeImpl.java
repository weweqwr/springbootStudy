package com.goaway.serve.Impl;

import com.goaway.entity.Gw_notice_comment;
import com.goaway.mapper.CommentMapper;
import com.goaway.serve.CommentServe;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentServeImpl implements CommentServe {
    @Autowired
    CommentMapper commentMapper;

    //插入留言
    @Override
    public Map<String, Object> createComment(int noticeId, int commentType, String content, int userId, int state, int replyId) {
        Map<String, Object> map = new HashMap<>();
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String commentTime = df.format(new Date());
            commentMapper.createComment(noticeId, commentType, content, userId, commentTime, state, replyId);
            map.put("msg", "留言成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "留言失败");
        }
        return map;
    }
    //根据noticeId查询所有留言的信息
    @Override
    public Map<String, Object> queryCommentByNoticeId(int noticeId) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        try {
            List<Gw_notice_comment> list = commentMapper.queryCommentByNoticeId(noticeId);
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map1 = new HashMap<>();//map得放这里循环才不会使数据重复
                map1.put("id", list.get(i).getId().toString());
                map1.put("commentType", list.get(i).getCommentType());
                map1.put("commentContent", list.get(i).getCommentContent());
                map1.put("userId", list.get(i).getUserId());
                map1.put("nickname", list.get(i).getNickname());
                map1.put("avatarUrl", list.get(i).getAvatarUrl());
                map1.put("commentTime", list.get(i).getCommentTime());
                map1.put("likeNumber", list.get(i).getLikeNumber());
                if (list.get(i).getReplyUserId() != null) {
                    String[] replyId = list.get(i).getReplyId().toString().split(",");
                    String[] replyUserId = list.get(i).getReplyUserId().toString().split(",");
                    String[] replyNickname = list.get(i).getReplyNickname().toString().split(",");
                    String[] replyAvatarUrl = list.get(i).getReplyAvatarUrl().toString().split(",");
                    String[] toUserId = list.get(i).getToUserId().toString().split(",");
                    String[] toNickname = list.get(i).getToNickname().toString().split(",");
                    String[] toAvatarUrl = list.get(i).getToAvatarUrl().toString().split(",");
                    String[] replyContent = list.get(i).getReplyContent().toString().split(",");
                    String[] replyTime = list.get(i).getReplyTime().toString().split(",");
                    map2.put("replyId", replyId);
                    map2.put("replyUserId", replyUserId);
                    map2.put("replyNickname", replyNickname);
                    map2.put("replyAvatarUrl", replyAvatarUrl);
                    map2.put("toUserId", toUserId);
                    map2.put("toNickname", toNickname);
                    map2.put("toAvatarUrl", toAvatarUrl);
                    map2.put("replyContent", replyContent);
                    map2.put("replyContent", replyContent);
                    map2.put("replyTime", replyTime);
                    map1.put("reply", map2);
                }
                map.put(String.valueOf(i), map1);
            }

            System.out.println(map);
            map.put("msg", "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "查询失败");
        }
        return map;
    }

    //删除评论
    @Override
    public Map<String,Object> delteCommentById(int id) {
        Map<String, Object> map = new HashMap<>();
        try{
            commentMapper.delteCommentById(id);
            map.put("msg","删除成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","删除失败");
        }
        return map;
    }


}
