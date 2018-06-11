package com.example.aghasi.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.aghasi.todolist.util.Const;
import com.example.aghasi.todolist.util.RepeatPeriod;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    ImageView mButtonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                String title = data.getStringExtra(Const.TITLE_KEY);
                String description = data.getStringExtra(Const.DESCRIPTION_KEY);
                Boolean reminder = data.getBooleanExtra(Const.REMINDER_KEY, false);
                Boolean repeat = data.getBooleanExtra(Const.REPEAT_KEY, false);
                RepeatPeriod repeatPeriod = (RepeatPeriod) data.getSerializableExtra(Const.REPEAT_PERIOD_KEY);
                int priority = data.getIntExtra(Const.PRIORITY_KEY, 0);
                Calendar calendar = (Calendar) data.getSerializableExtra(Const.CALENDAR);
            }
        }
    }
}
