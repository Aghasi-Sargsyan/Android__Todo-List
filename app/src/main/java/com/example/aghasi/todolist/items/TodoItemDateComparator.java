package com.example.aghasi.todolist.items;

import java.util.Comparator;

public class TodoItemDateComparator implements Comparator<TodoItem> {
    @Override
    public int compare(TodoItem item1, TodoItem item2) {

        Long item1Date = item1.getDate().getTime();
        Long item2Date = item2.getDate().getTime();
        return item1Date.compareTo(item2Date);
    }
}
