package com.example.aghasi.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.aghasi.todolist.items.TodoItem;
import com.example.aghasi.todolist.recyclerView.TodoItemRecyclerAdapter;
import com.example.aghasi.todolist.util.Const;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView mButtonAdd;
    private List<TodoItem> mTodoItemList;
    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTodoItemList = new ArrayList<>();
        mRecyclerView = findViewById(R.id.recycler_main);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));

        mButtonAdd = findViewById(R.id.image_main_add);

        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(intent, Const.ADD_NEW_EVENT_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == Const.ADD_NEW_EVENT_CODE) {
                TodoItem item = (TodoItem) data.getSerializableExtra(Const.TODO_ITEM_KEY);
                mTodoItemList.add(item);

                mRecyclerView.setAdapter(new TodoItemRecyclerAdapter(mTodoItemList));
            }
            if (requestCode == Const.EDIT_EVENT_CODE) {
                int position = data.getIntExtra(Const.ITEM_POSITION_KEY, 0);
                TodoItem item = (TodoItem) data.getSerializableExtra(Const.TODO_ITEM_KEY);
                mTodoItemList.set(position,item);

                mRecyclerView.setAdapter(new TodoItemRecyclerAdapter(mTodoItemList));
            }
        }
    }
}
