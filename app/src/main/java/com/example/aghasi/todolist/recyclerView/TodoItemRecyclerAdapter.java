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

public class TodoItemRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<TodoItem> todoItemList = new ArrayList<>();

    private OnItemSelectedListener mOnItemSelectedListener;


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case Const.TODO_ITEM_TYPE: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_todo_item, parent, false);
                return new TodoItemViewHolder(view);
            }
            case Const.TODO_DATE_TYPE: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_todo_date, parent, false);
                return new TodoItemViewHolder(view);
            }
        }
        throw new IllegalStateException("Unknown view type");
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

        switch (getItemViewType(position)) {
            case Const.TODO_ITEM_TYPE:
                TodoItemViewHolder itemViewHolder = (TodoItemViewHolder) holder;
                final TodoItem todoItem = todoItemList.get(position);
                itemViewHolder.getTextTitle().setText(todoItem.getTitle());
                itemViewHolder.getTextDescription().setText(todoItem.getDescription());
                itemViewHolder.getTextDate().setText(dateEditor(todoItem));
                itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                if (mOnItemSelectedListener != null) {
                        mOnItemSelectedListener.onItemClicked(todoItem, holder.getAdapterPosition());
//                }
                    }
                });
                itemViewHolder.getImageRemove().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mOnItemSelectedListener.onItemRemoved(holder.getAdapterPosition());
                    }
                });
                break;
            case Const.TODO_DATE_TYPE:
                break;
        }


    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return todoItemList.size();
    }

    public List<TodoItem> getTodoItemList() {
        return todoItemList;
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

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        mOnItemSelectedListener = onItemSelectedListener;
    }

    public interface OnItemSelectedListener {

        void onItemClicked(TodoItem item, int position);

        void onItemRemoved(int position);

    }

}
