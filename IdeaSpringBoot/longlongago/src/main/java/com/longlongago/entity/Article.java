package com.longlongago.entity;

public class Article {
    private Integer id;
    private String article;
    private String title;
    private String account;
    private String createTime;
    private String coverImg;
    private Integer state;
    private String name;
    private String arIntroduct;
    private Integer readNumber;
    private String year;
    private String month;
    private String day;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getArIntroduct() {
        return arIntroduct;
    }

    public void setArIntroduct(String arIntroduct) {
        this.arIntroduct = arIntroduct;
    }

    public Integer getReadNumber() {
        return readNumber;
    }

    public void setReadNumber(Integer readNumber) {
        this.readNumber = readNumber;
    }
}
