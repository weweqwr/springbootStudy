package com.fontgoaway.mapper;

import com.fontgoaway.entity.Gw_notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    //    所有
    public List<Gw_notice> findAllNotice();
    //    id
    public Gw_notice findNoticeById(Integer id);
    //    title
    public List<Gw_notice> findNoticeByTitle(String title);
    //    模糊title
    public List<Gw_notice> findNoticeByTitleLick(String title);
    //    noticeType
    public List<Gw_notice> finsNoticeByType(Integer noticeType);
    //    originator发起人
    public List<Gw_notice>findNoticeByOriginator(Integer originator);
    //    groupId
    public List<Gw_notice>findNoticeByGroupId(String groupId);
    //    state
    public List<Gw_notice>finDNoticeByState(Integer state);

    //    屏蔽modify
    void modifyStateById(Gw_notice notice);


}
