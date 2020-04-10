package com.goaway.entity;

public class Gw_user_group {
    private Integer id;

    private Integer userid;

    private Integer groupid;

    private byte[] groupnickname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public byte[] getGroupnickname() {
        return groupnickname;
    }

    public void setGroupnickname(byte[] groupnickname) {
        this.groupnickname = groupnickname;
    }
}