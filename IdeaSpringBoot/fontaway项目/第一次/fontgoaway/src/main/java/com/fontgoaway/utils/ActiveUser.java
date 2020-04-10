package com.fontgoaway.utils;

import com.fontgoaway.entity.Gw_admin;

import java.util.List;

public class ActiveUser {
    private static final long serialVersionUID = 1L;

    private Gw_admin admin;
    private List<String> roles;
    private  List<String> permissions;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Gw_admin getAdmin() {
        return admin;
    }

    public void setAdmin(Gw_admin admin) {
        this.admin = admin;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public ActiveUser(){

    }

    public ActiveUser(Gw_admin admin, List<String> roles, List<String> permissions) {
        this.admin = admin;
        this.roles = roles;
        this.permissions = permissions;
    }
}
