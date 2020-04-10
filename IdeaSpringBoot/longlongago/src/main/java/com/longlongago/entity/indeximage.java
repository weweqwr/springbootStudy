package com.longlongago.entity;

public class indeximage {
    private Integer id;
    private String url;
    private String account;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "indeximage{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", account='" + account + '\'' +
                '}';
    }
}
