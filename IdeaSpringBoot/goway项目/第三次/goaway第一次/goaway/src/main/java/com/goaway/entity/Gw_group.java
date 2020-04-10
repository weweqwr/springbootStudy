package com.goaway.entity;

public class Gw_group {
    private String id;

    private String groupName;

    private Gw_user_group userGroup;
    private Gw_user user;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    private int state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public Gw_user_group getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(Gw_user_group userGroup) {
        this.userGroup = userGroup;
    }

    public Gw_user getUser() {
        return user;
    }

    public void setUser(Gw_user user) {
        this.user = user;
    }
}