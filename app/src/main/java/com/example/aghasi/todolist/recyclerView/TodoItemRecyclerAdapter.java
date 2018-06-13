package com.example.aghasi.todolist.recyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.aghasi.todolist.R;
import com.example.aghasi.todolist.SecondActivity;
import com.example.aghasi.todolist.items.TodoItem;
import com.example.aghasi.todolist.util.Const;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TodoItemRecyclerAdapter extends RecyclerView.Adapter<TodoItemViewHolder> {
    private List<TodoItem> todoItemList;

    public TodoItemRecyclerAdapter(List<TodoItem> todoItemList) {
        if (todoItemList == null) {
            this.todoItemList = new ArrayList<>();
        }
        this.todoItemList = todoItemList;
    }

    @NonNull
    @Override
    public TodoItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_todo_item, parent, false);
        return new TodoItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TodoItemViewHolder holder, final int position) {
        final TodoItem todoItem = todoItemList.get(position);
        holder.getTitle().setText(todoItem.getTitle());
        holder.getDescription().setText(todoItem.getDescription());
        holder.getDate().setText(dateEditor(todoItem));
        holder.getItemLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, SecondActivity.class);
                intent.putExtra(Const.TODO_ITEM_KEY, todoItem);
                intent.putExtra(Const.ITEM_POSITION_KEY, position);
                ((Activity) context).startActivityForResult(intent, Const.EDIT_EVENT_CODE);
            }
        });

        holder.getRemoveButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeItem(position);

            }
        });
    }

    @Override
    public int getItemCount() {
        return todoItemList.size();
    }

    private String dateEditor(TodoItem todoItem) {
        if (todoItem.getDate() != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(Const.ITEM_DATE_FORMAT);
            String date = dateFormat.format(todoItem.getDate());
            date = date.substring(0, 12) + "...";
            return date;
        }
        return "";
    }

    void removeItem(int position) {
        todoItemList.remove(position);
        notifyDataSetChanged();
        if (todoItemList.size() < 2) {

        }
    }

}