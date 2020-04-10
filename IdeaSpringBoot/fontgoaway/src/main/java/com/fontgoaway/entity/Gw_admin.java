package com.fontgoaway.entity;

import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

public class Gw_admin {
    private Integer id;
    private String account;
    private String password;
    private String username;
    private String avatarUrl;
    private Integer state;
    private Integer curPage;
    private Integer verify;
    private Integer page;
    private String roleId;
    private String rolename;
    private String[] roleIds;
    private String[] rolenames;
    private Integer countUser;
    private Integer countNotice;
    private Integer countGroup;
    private Integer countUserBan;
    private Integer countNoticeBan;
    private Integer countGroupBan;
    private Integer adminBand;

    private String province;
    private String city;
    private String area;
    private Integer gender;
    private String phone;
    private String detailAdd;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getCurPage() {
        return curPage;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    public Integer getVerify() {
        return verify;
    }

    public void setVerify(Integer verify) {
        this.verify = verify;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String[] roleIds) {
        this.roleIds = roleIds;
    }

    public String[] getRolenames() {
        return rolenames;
    }

    public void setRolenames(String[] rolenames) {
        this.rolenames = rolenames;
    }

    public Integer getCountUser() {
        return countUser;
    }

    public void setCountUser(Integer countUser) {
        this.countUser = countUser;
    }

    public Integer getCountNotice() {
        return countNotice;
    }

    public void setCountNotice(Integer countNotice) {
        this.countNotice = countNotice;
    }

    public Integer getCountGroup() {
        return countGroup;
    }

    public void setCountGroup(Integer countGroup) {
        this.countGroup = countGroup;
    }

    public Integer getCountUserBan() {
        return countUserBan;
    }

    public void setCountUserBan(Integer countUserBan) {
        this.countUserBan = countUserBan;
    }

    public Integer getCountNoticeBan() {
        return countNoticeBan;
    }

    public void setCountNoticeBan(Integer countNoticeBan) {
        this.countNoticeBan = countNoticeBan;
    }

    public Integer getCountGroupBan() {
        return countGroupBan;
    }

    public void setCountGroupBan(Integer countGroupBan) {
        this.countGroupBan = countGroupBan;
    }

    public Integer getAdminBand() {
        return adminBand;
    }

    public void setAdminBand(Integer adminBand) {
        this.adminBand = adminBand;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDetailAdd() {
        return detailAdd;
    }

    public void setDetailAdd(String detailAdd) {
        this.detailAdd = detailAdd;
    }
}
