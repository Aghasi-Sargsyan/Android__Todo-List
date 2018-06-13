package com.example.aghasi.todolist.recyclerView;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.aghasi.todolist.R;

public class TodoItemViewHolder extends RecyclerView.ViewHolder{

    private TextView mTitle;
    private TextView mDescription;
    private TextView mDate;
    private ConstraintLayout mItemLayout;

    public TodoItemViewHolder(View itemView) {
        super(itemView);
        mTitle = itemView.findViewById(R.id.text_item_title);
        mDescription = itemView.findViewById(R.id.text_item_description);
        mDate = itemView.findViewById(R.id.text_item_date);
        mItemLayout = itemView.findViewById(R.id.constraint_item);
    }

    public TextView getTitle() {
        return mTitle;
    }

    public void setTitle(TextView mTitle) {
        this.mTitle = mTitle;
    }

    public TextView getDescription() {
        return mDescription;
    }

    public void setDescription(TextView mDescription) {
        this.mDescription = mDescription;
    }

    public TextView getDate() {
        return mDate;
    }

    public void setDate(TextView mDate) {
        this.mDate = mDate;
    }

    public ConstraintLayout getItemLayout() {
        return mItemLayout;
    }

    public void setItemLayout(ConstraintLayout mItemLayout) {
        this.mItemLayout = mItemLayout;
    }
}
