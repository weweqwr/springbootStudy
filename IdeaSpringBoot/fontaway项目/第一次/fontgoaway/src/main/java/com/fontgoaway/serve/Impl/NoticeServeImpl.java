package com.fontgoaway.serve.Impl;

import com.fontgoaway.entity.Gw_notice;
import com.fontgoaway.mapper.NoticeMapper;
import com.fontgoaway.serve.NoticeServe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：com.gjt
 * @description：TODO
 * @date ：Created in 2020/4/1 17:59
 */
@Service
public class NoticeServeImpl implements NoticeServe {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public List<Gw_notice> findAllNotice() {


        return noticeMapper.findAllNotice();
    }

    @Override
    public Gw_notice findNoticeById(Integer id) {
        return noticeMapper.findNoticeById(id);
    }

    @Override
    public List<Gw_notice> findNoticeByTitle(String title) {
        return noticeMapper.findNoticeByTitle(title);
    }

    @Override
    public List<Gw_notice> findNoticeByTitleLick(String title) {
        return noticeMapper.findNoticeByTitleLick("%"+title+"%");
    }

    @Override
    public List<Gw_notice> finsNoticeByType(Integer type) {
        return noticeMapper.finsNoticeByType(type);
    }

    @Override
    public List<Gw_notice> findNoticeByOriginator(Integer originator) {
        return noticeMapper.findNoticeByOriginator(originator);
    }

    @Override
    public List<Gw_notice> findNoticeByGroupId(String groupId) {
        return noticeMapper.findNoticeByGroupId(groupId);
    }

    @Override
    public List<Gw_notice> finDNoticeByState(Integer state) {
        return noticeMapper.finDNoticeByState(state);
    }

    @Override
    public void modifyStateById(Gw_notice notice) {
        noticeMapper.modifyStateById(notice);
    }
}

