package com.example.aghasi.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.aghasi.todolist.items.TodoDateItem;
import com.example.aghasi.todolist.items.TodoItem;
import com.example.aghasi.todolist.items.TodoDateItemComparator;
import com.example.aghasi.todolist.recyclerView.TodoItemRecyclerAdapter;
import com.example.aghasi.todolist.util.Const;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageAdd;
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
           /* TODO...........
            mAdapter.removeFromList(position);
            mAdapter.notifyDataSetChanged();*/
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
            TodoItem todoItem;
            if (requestCode == Const.ADD_NEW_EVENT_CODE) {
                todoItem = (TodoItem) data.getSerializableExtra(Const.TODO_ITEM_KEY);
                addItemToDateList(todoItem);
            }
            if (requestCode == Const.EDIT_EVENT_CODE) {
               /* TODO..............
                int position = data.getIntExtra(Const.ITEM_POSITION_KEY, 0);
                mTodoItem = (TodoItem) data.getSerializableExtra(Const.TODO_ITEM_KEY);
                mAdapter.getTodoDateItemList().set(position, mTodoItem);*/

            }
            Collections.sort(mAdapter.getTodoDateItemList(),new TodoDateItemComparator());
            mAdapter.notifyDataSetChanged();
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

    private void addItemToDateList(TodoItem item) {
        ArrayList<TodoDateItem> todoDateItems = mAdapter.getTodoDateItemList();
        Calendar itemCalendar = Calendar.getInstance();
        itemCalendar.setTime(item.getDate());
        int itemMonth = itemCalendar.get(Calendar.MONTH);
        Calendar dateItemCalendar = Calendar.getInstance();

        if (todoDateItems.isEmpty()) {
            TodoDateItem todoDateItem = new TodoDateItem(item.getDate());
            todoDateItem.addToList(item);
            mAdapter.addToList(todoDateItem);
        } else {
            for (int i = 0; i < todoDateItems.size(); i++) {
                dateItemCalendar.setTime(todoDateItems.get(i).getDate());
                int dateItemMonth = dateItemCalendar.get(Calendar.MONTH);
                if (itemMonth == dateItemMonth) {
                    todoDateItems.get(i).addToList(item);
                } else {
                    TodoDateItem todoDateItem = new TodoDateItem(item.getDate());
                    todoDateItem.addToList(item);
                    mAdapter.addToList(todoDateItem);
                }
            }
        }
    }
}
