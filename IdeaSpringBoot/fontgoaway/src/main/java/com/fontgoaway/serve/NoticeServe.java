package com.fontgoaway.serve;

import java.util.Map;

public interface NoticeServe {
    //查询回复
    Map<String,Object> queryNotice(int flag, int id, int curPage);
    //屏蔽回复
    Map<String,Object> sheildNotice(int id, int state);
}
