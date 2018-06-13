package com.example.aghasi.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.aghasi.todolist.items.TodoItem;
import com.example.aghasi.todolist.items.TodoItemDateComparator;
import com.example.aghasi.todolist.recyclerView.TodoItemRecyclerAdapter;
import com.example.aghasi.todolist.util.Const;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView mImageAdd;
    Button mButtonSort;
    private List<TodoItem> mTodoItemList;
    private TodoItemRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTodoItemList = new ArrayList<>();

        idInitialization();

        recyclerViewCreator();

        mImageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(intent, Const.ADD_NEW_EVENT_CODE);
            }
        });

        mButtonSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(mTodoItemList,new TodoItemDateComparator());
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mTodoItemList.size() >= 2) {
            mButtonSort.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == Const.ADD_NEW_EVENT_CODE) {
                TodoItem item = (TodoItem) data.getSerializableExtra(Const.TODO_ITEM_KEY);
                mTodoItemList.add(item);
            }
            if (requestCode == Const.EDIT_EVENT_CODE) {
                int position = data.getIntExtra(Const.ITEM_POSITION_KEY, 0);
                TodoItem item = (TodoItem) data.getSerializableExtra(Const.TODO_ITEM_KEY);
                mTodoItemList.set(position,item);
            }
            mAdapter.notifyDataSetChanged();
        }
    }

    private void idInitialization() {
        mImageAdd = findViewById(R.id.image_main_add);
        mButtonSort = findViewById(R.id.button_main_sort);
    }

    private void recyclerViewCreator() {
        RecyclerView mRecyclerView = findViewById(R.id.recycler_main);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        mAdapter = new TodoItemRecyclerAdapter(mTodoItemList);
        mRecyclerView.setAdapter(mAdapter);
    }
}
