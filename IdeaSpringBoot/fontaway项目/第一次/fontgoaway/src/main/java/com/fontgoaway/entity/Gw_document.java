package com.fontgoaway.entity;

public class Gw_document {
    private Integer id;

    private Integer noticeid;

    private String documentname;

    private String documenturl;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNoticeid() {
        return noticeid;
    }

    public void setNoticeid(Integer noticeid) {
        this.noticeid = noticeid;
    }

    public String getDocumentname() {
        return documentname;
    }

    public void setDocumentname(String documentname) {
        this.documentname = documentname == null ? null : documentname.trim();
    }

    public String getDocumenturl() {
        return documenturl;
    }

    public void setDocumenturl(String documenturl) {
        this.documenturl = documenturl == null ? null : documenturl.trim();
    }


}