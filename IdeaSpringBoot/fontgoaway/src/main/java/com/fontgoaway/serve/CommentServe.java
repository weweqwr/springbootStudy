package com.fontgoaway.serve;

import java.util.Map;

public interface CommentServe {
    //查询回复
    Map<String,Object> queryComment(int flag, int id, int curPage);
    //屏蔽回复
    Map<String,Object> sheildComment(int id, int state);
}
