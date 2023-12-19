package com.example.Librarymgmt.model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class BorrowBook {
    @Id
    private Long id;
    public String names;
    public String tel;
    public String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date date;
    public String file;

    public BorrowBook() {
    }

    public BorrowBook(Long id, String names, String tel, String email, Date date, String file) {
        this.id = id;
        this.names = names;
        this.tel = tel;
        this.email = email;
        this.date = date;
        this.file = file;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
