package com.example.aghasi.todolist.items;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TodoDateItem extends Item {

    private Date mDate;

    private List<TodoItem> mTodoItemList = new ArrayList<>();

    public TodoDateItem(Date date) {
        mDate = date;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public void addToList(TodoItem item) {
        mTodoItemList.add(item);
    }

    public List<TodoItem> getTodoItemList() {
        return mTodoItemList;
    }
}
