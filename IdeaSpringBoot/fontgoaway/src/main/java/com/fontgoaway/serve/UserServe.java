package com.fontgoaway.serve;

import java.util.Map;

public interface UserServe {
    //查询回复
    Map<String,Object> queryUser(int id, int curPage);
    //屏蔽回复
    Map<String,Object> sheildUser(int id, int state);
}
