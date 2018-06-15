package com.example.aghasi.todolist.recyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aghasi.todolist.R;

public class TodoItemViewHolder extends RecyclerView.ViewHolder {

    private TextView mTextTitle;
    private TextView mTextDescription;
    private TextView mTextDate;
    private ImageView mImageRemove;

    public TodoItemViewHolder(View itemView) {
        super(itemView);
        mTextTitle = itemView.findViewById(R.id.text_item_title);
        mTextDescription = itemView.findViewById(R.id.text_item_description);
        mTextDate = itemView.findViewById(R.id.text_item_date);
        mImageRemove = itemView.findViewById(R.id.image_remove_item);
    }

    public TextView getTextTitle() {
        return mTextTitle;
    }

    public void setTextTitle(TextView mTitle) {
        this.mTextTitle = mTitle;
    }

    public TextView getTextDescription() {
        return mTextDescription;
    }

    public void setTextDescription(TextView mDescription) {
        this.mTextDescription = mDescription;
    }

    public TextView getTextDate() {
        return mTextDate;
    }

    public void setTextDate(TextView mDate) {
        this.mTextDate = mDate;
    }

    public ImageView getImageRemove() {
        return mImageRemove;
    }
}
