package com.example.aghasi.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView mMainTitle, mMainDescription, mMainDate;
    ImageView mMainAdd;
    public static final int ADD_NEW_EVENT_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainTitle = findViewById(R.id.main_title);
        mMainDescription = findViewById(R.id.main_description);
        mMainDate = findViewById(R.id.main_date);
        mMainAdd = findViewById(R.id.main_add);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(intent,ADD_NEW_EVENT_CODE);
            }
        };

        mMainAdd.setOnClickListener(clickListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == ADD_NEW_EVENT_CODE) {
                String a = data.getStringExtra("title");
                RepeatPeriod repeatPeriod = (RepeatPeriod) data.getSerializableExtra("repeatPeriod");
                String b = data.getStringExtra("description");
            }
        }
    }
}
