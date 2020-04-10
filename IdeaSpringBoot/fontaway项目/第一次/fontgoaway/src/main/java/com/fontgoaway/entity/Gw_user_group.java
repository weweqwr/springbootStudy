package com.fontgoaway.entity;

public class Gw_user_group {
    private Integer id;

    private Integer userId;

    private String groupId;

    private String groupNickname;

    private Integer groupIdentification;

    private String tempGroupId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupNickname() {
        return groupNickname;
    }

    public void setGroupNickname(String groupNickname) {
        this.groupNickname = groupNickname;
    }

    public Integer getGroupIdentification() {
        return groupIdentification;
    }

    public void setGroupIdentification(Integer groupIdentification) {
        this.groupIdentification = groupIdentification;
    }

    public String getTempGroupId() {
        return tempGroupId;
    }

    public void setTempGroupId(String tempGroupId) {
        this.tempGroupId = tempGroupId;
    }
}