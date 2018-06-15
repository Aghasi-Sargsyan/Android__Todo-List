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

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ImageView mImageAdd;
    private TodoItemRecyclerAdapter mAdapter;

    private TodoItemRecyclerAdapter.OnItemSelectedListener mOnItemSelectedListener = new TodoItemRecyclerAdapter.OnItemSelectedListener() {
        @Override
        public void onItemClicked(TodoItem item, int position) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra(Const.TODO_ITEM_KEY, item);
            intent.putExtra(Const.ITEM_POSITION_KEY, position);
            startActivityForResult(intent, Const.EDIT_EVENT_CODE);
        }

        @Override
        public void onItemRemoved(int position) {
            mAdapter.getTodoItemList().remove(position);
            mAdapter.notifyDataSetChanged();
        }
    };

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        idInitialization();

        recyclerViewCreator();

        mImageAdd.setOnClickListener(new View.OnClickListener() {
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
            TodoItem item = new TodoItem("asda","asd",new Date());
            if (requestCode == Const.ADD_NEW_EVENT_CODE) {
                item = (TodoItem) data.getSerializableExtra(Const.TODO_ITEM_KEY);
                mAdapter.getTodoItemList().add(item);
            }
            if (requestCode == Const.EDIT_EVENT_CODE) {
                int position = data.getIntExtra(Const.ITEM_POSITION_KEY, 0);
                item = (TodoItem) data.getSerializableExtra(Const.TODO_ITEM_KEY);
                mAdapter.getTodoItemList().set(position, item);
            }
            mAdapter.notifyDataSetChanged();

            if (item.getDate() != null) {
                if (mAdapter.getItemDate() != item.getDate())
                mAdapter.setItemDate(item.getDate());
            }
        }

    }

    private void idInitialization() {
        mImageAdd = findViewById(R.id.image_main_add);
    }

    private void recyclerViewCreator() {
        RecyclerView mRecyclerView = findViewById(R.id.recycler_main);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        mAdapter = new TodoItemRecyclerAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemSelectedListener(mOnItemSelectedListener);
    }
}
