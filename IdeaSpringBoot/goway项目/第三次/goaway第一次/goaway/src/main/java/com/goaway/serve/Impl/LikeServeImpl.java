package com.goaway.serve.Impl;

import com.goaway.entity.Gw_comment_like;
import com.goaway.mapper.LikeMapper;
import com.goaway.serve.LikeServe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LikeServeImpl implements LikeServe {
    @Autowired
    LikeMapper likeMapper;

    @Override
    public Map<String, Object> addLike(int commentId, int userId) {
        Map<String, Object> map = new HashMap<>();
        try {

            List<Gw_comment_like> list = likeMapper.queryLikeByCommentIdAndUserId(commentId, userId);
            if (list.size() > 0) {
                likeMapper.deleteLike(commentId, userId);
                map.put("msg", "点赞以取消");
            } else {
                likeMapper.addLike(commentId, userId);
                map.put("msg", "点赞成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "点赞失败");
        }
        return map;
    }

}
