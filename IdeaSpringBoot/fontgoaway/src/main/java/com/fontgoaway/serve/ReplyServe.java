package com.fontgoaway.serve;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface ReplyServe {
    //查询回复
    Map<String,Object> queryReply(int flag, int id, int curPage);
    //屏蔽回复
    Map<String,Object> sheildReply(int id,int state);
}
