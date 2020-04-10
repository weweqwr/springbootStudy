package com.fontgoaway.entity;

public class Gw_role {
    private Integer roleId;
    private String rolename;
    private String account;
    private Integer curPage;
    private Integer page;
    private Integer count;
    private Integer state;
    private String perId;
    private String pername;
    private String[] perIds;
    private String[] perNames;
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getCurPage() {
        return curPage;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getPerId() {
        return perId;
    }

    public void setPerId(String perId) {
        this.perId = perId;
    }


    public String getPername() {
        return pername;
    }

    public void setPername(String pername) {
        this.pername = pername;
    }

    public String[] getPerIds() {
        return perIds;
    }

    public void setPerIds(String[] perIds) {
        this.perIds = perIds;
    }

    public String[] getPerNames() {
        return perNames;
    }

    public void setPerNames(String[] perNames) {
        this.perNames = perNames;
    }
}
