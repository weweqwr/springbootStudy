package com.fontgoaway.entity;

public class Gw_permission {
    private Integer perId;
    private String perIdstr;
    private String pername;
    private String percode;
    private String account;
    private Integer roleId;
    private String[] pernames;
    private String navModular;
    private String[] percodes;
    private String[] perIds;

    public Integer getPerId() {
        return perId;
    }

    public void setPerId(Integer perId) {
        this.perId = perId;
    }

    public String getPername() {
        return pername;
    }

    public void setPername(String pername) {
        this.pername = pername;
    }

    public String getPercode() {
        return percode;
    }

    public void setPercode(String percode) {
        this.percode = percode;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String[] getPernames() {
        return pernames;
    }

    public void setPernames(String[] pernames) {
        this.pernames = pernames;
    }

    public String getNavModular() {
        return navModular;
    }

    public void setNavModular(String navModular) {
        this.navModular = navModular;
    }

    public String[] getPercodes() {
        return percodes;
    }

    public void setPercodes(String[] percodes) {
        this.percodes = percodes;
    }

    public String[] getPerIds() {
        return perIds;
    }

    public void setPerIds(String[] perIds) {
        this.perIds = perIds;
    }

    public String getPerIdstr() {
        return perIdstr;
    }

    public void setPerIdstr(String perIdstr) {
        this.perIdstr = perIdstr;
    }
}
