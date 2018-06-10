package com.example.aghasi.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTitleMain, mDescriptionMain, mDateMain;
    private ImageView mAddMain;
    private ViewGroup mItemMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitleMain = findViewById(R.id.title_main);
        mDescriptionMain = findViewById(R.id.description_main);
        mDateMain = findViewById(R.id.date_main);
        mAddMain = findViewById(R.id.add_main);
        mItemMain = findViewById(R.id.item_main);

        mAddMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(intent, Const.ADD_NEW_EVENT_CODE);
            }
        });

        mItemMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(intent, Const.EDIT_EVENT_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == Const.ADD_NEW_EVENT_CODE) {
                mTitleMain.setText(data.getStringExtra(Const.TITLE));
                mDescriptionMain.setText(data.getStringExtra(Const.DESCRIPTION));
                mDateMain.setText(data.getStringExtra(Const.DATE));
                RepeatPeriod repeatPeriod = (RepeatPeriod) data.getSerializableExtra(Const.REPEAT_PERIOD);
                boolean reminder = data.getBooleanExtra(Const.REMINDER, false);
                boolean repeat = data.getBooleanExtra(Const.REPEAT, false);
                int priorityCounter = data.getIntExtra(Const.PRIORITY, 0);

            }
        }
    }
}
