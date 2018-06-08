package com.example.aghasi.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView mMainTitle, mMainDescription, mMainDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainTitle = findViewById(R.id.main_title);
        mMainDescription = findViewById(R.id.main_description);
        mMainDate = findViewById(R.id.main_date);
    }
}
