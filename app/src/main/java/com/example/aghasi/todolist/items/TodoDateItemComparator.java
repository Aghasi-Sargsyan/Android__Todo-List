package com.example.aghasi.todolist.items;

import java.util.Comparator;

public class TodoDateItemComparator implements Comparator<TodoDateItem> {

    @Override
    public int compare(TodoDateItem item1, TodoDateItem item2) {

        Long item1Date = item1.getDate().getTime();
        Long item2Date = item2.getDate().getTime();
        return item1Date.compareTo(item2Date);
    }
}
