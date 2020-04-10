package com.goaway.serve.Impl;

import com.goaway.mapper.ReplyMapper;
import com.goaway.serve.ReplyServe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReplyServeImpl implements ReplyServe {
    @Autowired
    ReplyMapper replyMapper;
    @Override
    public Map<String,Object> createReply(int commentId, int userId, String nickname, String avatarUrl, int toUserId, String toNickname, String toAvatarUrl, String content,int state) {
        Map<String, Object> map = new HashMap<>();
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String replyTime = format.format(new Date());
            replyMapper.createReply(commentId, userId, nickname, avatarUrl, toUserId, toNickname, toAvatarUrl, content, replyTime,state);
            map.put("msg","回复成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","回复失败");
        }
        return map;
    }

    @Override
    public Map<String, Object> deleteReplyById(int id) {
        Map<String, Object> map = new HashMap<>();
        try{
            replyMapper.deleteReplyById(id);
            map.put("msg","删除成功");
        }catch (Exception e){
            map.put("msg","删除失败");
        }
        return map;
    }


}
