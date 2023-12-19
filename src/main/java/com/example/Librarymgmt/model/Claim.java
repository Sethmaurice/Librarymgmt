package com.example.Librarymgmt.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Claim {
    public String namess;
    @Id
    public String emaill;
    public String comment;

    public Claim() {
    }

    public Claim(String namess, String emaill, String comment) {
        this.namess = namess;
        this.emaill = emaill;
        this.comment = comment;
    }

    public String getNamess() {
        return namess;
    }

    public void setNamess(String namess) {
        this.namess = namess;
    }

    public String getEmaill() {
        return emaill;
    }

    public void setEmaill(String emaill) {
        this.emaill = emaill;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
