package com.example.aghasi.todolist.recyclerView.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.aghasi.todolist.R;

public class TodoDateViewHolder extends RecyclerView.ViewHolder {

    private TextView mTextDate;

    public TodoDateViewHolder(View itemView) {
        super(itemView);
        mTextDate = itemView.findViewById(R.id.text_res_date);
    }

    public TextView getTextDate() {
        return mTextDate;
    }

}
