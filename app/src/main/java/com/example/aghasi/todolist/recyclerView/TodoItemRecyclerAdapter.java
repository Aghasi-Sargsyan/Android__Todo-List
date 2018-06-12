package com.example.aghasi.todolist.recyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aghasi.todolist.R;
import com.example.aghasi.todolist.items.TodoItem;
import com.example.aghasi.todolist.util.Const;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TodoItemRecyclerAdapter extends RecyclerView.Adapter<TodoItemViewHolder> {
    private List<TodoItem> todoItemList = new ArrayList<>();

    public TodoItemRecyclerAdapter(List<TodoItem> todoItemList) {
        this.todoItemList.addAll(todoItemList);
    }

    @NonNull
    @Override
    public TodoItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_todo_item, parent, false);
        return new TodoItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoItemViewHolder holder, int position) {
        TodoItem todoItem = todoItemList.get(position);
        holder.getTitle().setText(todoItem.getTitle());
        holder.getDescription().setText(todoItem.getDescription());
        holder.getDate().setText(dateEditor(todoItem));
    }

    @Override
    public int getItemCount() {
        return todoItemList.size();
    }

    private String dateEditor(TodoItem todoItem) {
        if (todoItem.getDate() != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(Const.ITEM_DATE_FORMAT);
            String date = dateFormat.format(todoItem.getDate());
            if (date.length() > 10) {
                date = date.substring(0, 10);
            }
            return date;
        }
        return "";
    }
}