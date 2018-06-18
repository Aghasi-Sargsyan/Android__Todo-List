package com.example.aghasi.todolist.recyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aghasi.todolist.R;
import com.example.aghasi.todolist.items.Item;
import com.example.aghasi.todolist.items.TodoDateItem;
import com.example.aghasi.todolist.items.TodoItem;
import com.example.aghasi.todolist.recyclerView.holders.TodoDateViewHolder;
import com.example.aghasi.todolist.recyclerView.holders.TodoItemViewHolder;
import com.example.aghasi.todolist.util.Const;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TodoItemRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<TodoDateItem> todoDateItemList = new ArrayList<>();

    private OnItemSelectedListener mOnItemSelectedListener;

    private int dateI = -1;
    private int itemI = -1;


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
                return new TodoDateViewHolder(view);
            }
        }
        throw new IllegalStateException("Unknown view type in onCreateViewHolder()");
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        Item item = getItem();

        switch (getItemViewType(position)) {
            case Const.TODO_ITEM_TYPE:
                TodoItemViewHolder itemViewHolder = (TodoItemViewHolder) holder;
                final TodoItem todoItem = (TodoItem) item;
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
                TodoDateItem todoDateItem = (TodoDateItem) item;
                TodoDateViewHolder dateViewHolder = (TodoDateViewHolder) holder;
                dateViewHolder.setDateText(todoDateItem.getDate());
                break;
        }
    }

    private Item getItem() {

        if (dateI < todoDateItemList.size() - 1) {
            dateI++;
            return todoDateItemList.get(dateI);
        }
        if (itemI < todoDateItemList.get(dateI).getTodoItemList().size() - 1) {
            itemI++;
            return todoDateItemList.get(dateI).getTodoItemList().get(itemI);
        } else {
            if (todoDateItemList.size() - 1 > dateI) {
                dateI++;
                itemI = -1;
                return todoDateItemList.get(dateI);
            }
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        Item item = getItem();
        if (item instanceof TodoItem) {
            return Const.TODO_ITEM_TYPE;
        }
        if (item instanceof TodoDateItem) {
            return Const.TODO_DATE_TYPE;
        }
        throw new IllegalStateException("Unknown view type in getItemViewType");
    }

    public ArrayList<TodoDateItem> getTodoDateItemList() {
        return todoDateItemList;
    }

    @Override
    public int getItemCount() {
        int count = 0;
        for (TodoDateItem dateItem : todoDateItemList) {
            count++;
            for (TodoItem item : dateItem.getTodoItemList()) {
                count++;
            }
        }
        return count;
    }

    public void addToList(TodoDateItem todoDateItem) {
        todoDateItemList.add(todoDateItem);
    }

    public void removeFromList(int index) {
        /*
        TODO..............
        todoDateItemList.remove(index);*/
    }

    private String dateEditor(TodoItem todoItem) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyy");
        String date = dateFormat.format(todoItem.getDate());
        date = date + "...";
        return date;
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        mOnItemSelectedListener = onItemSelectedListener;
    }

    public interface OnItemSelectedListener {

        void onItemClicked(TodoItem item, int position);

        void onItemRemoved(int position);

    }

}
