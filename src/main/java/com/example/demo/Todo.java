package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Todo {

    @Id
    public int id;
    public String title;
    public String description;
    public String cdate;

    public boolean isCstatus() {
        return cstatus;
    }

    public void setCstatus(boolean cstatus) {
        this.cstatus = cstatus;
    }

    public boolean cstatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }
}
