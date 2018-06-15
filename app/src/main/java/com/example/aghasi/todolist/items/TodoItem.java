package com.example.aghasi.todolist.items;

import android.support.v7.widget.RecyclerView;

import java.io.Serializable;
import java.util.Date;

public class TodoItem implements Serializable{

    private String mTitle;
    private String mDescription;
    private Date mDate;

    public TodoItem(String title, String description, Date date) {
        this.mTitle = title;
        this.mDescription = description;
        this.mDate = date;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public Date getDate() {
        return mDate;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public void setDate(Date date) {
        this.mDate = date;
    }
}
