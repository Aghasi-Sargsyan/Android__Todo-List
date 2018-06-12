package com.example.aghasi.todolist.recyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.aghasi.todolist.R;

public class TodoItemViewHolder extends RecyclerView.ViewHolder{

    private TextView title;
    private TextView description;
    private TextView date;

    public TodoItemViewHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.text_item_title);
        description = itemView.findViewById(R.id.text_item_description);
        date = itemView.findViewById(R.id.text_item_date);
    }

    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    public TextView getDescription() {
        return description;
    }

    public void setDescription(TextView description) {
        this.description = description;
    }

    public TextView getDate() {
        return date;
    }

    public void setDate(TextView date) {
        this.date = date;
    }
}
