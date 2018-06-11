package com.example.aghasi.todolist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private TextView mTitleMain, mDescriptionMain, mDateMain, mTimeMain;

    private ImageView mAddButtonMain;
    private String mTitleTxt, mDescriptionTxt, mDateTxt, mTimeTxt;
    private Date mDate, mTime;
    private ViewGroup mSecondConstraint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitleMain = findViewById(R.id.title_main);
        mDescriptionMain = findViewById(R.id.description_main);
        mDateMain = findViewById(R.id.date_main);
        mAddButtonMain = findViewById(R.id.add_main);
        mTimeMain = findViewById(R.id.time_main);
        mSecondConstraint = findViewById(R.id.second_constraint);

        mAddButtonMain.setOnClickListener(new View.OnClickListener() {
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
                mTitleTxt = data.getStringExtra(Const.TITLE);
                mDescriptionTxt = data.getStringExtra(Const.DESCRIPTION);
                mDate = (Date) data.getSerializableExtra(Const.DATE);
                mTime = (Date) data.getSerializableExtra(Const.TIME);
                dateAndTimeMerger(mDate, mTime);
                fieldsSetter();


                /*RepeatPeriod repeatPeriod = (RepeatPeriod) data.getSerializableExtra(Const.REPEAT_PERIOD);
                boolean reminder = data.getBooleanExtra(Const.REMINDER, false);
                boolean repeat = data.getBooleanExtra(Const.REPEAT, false);
                int priorityCounter = data.getIntExtra(Const.PRIORITY, 0);*/
            }
        }
    }

    private void dateAndTimeMerger(Date date, Date time) {

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat timeFormat = new SimpleDateFormat("kk:mm");
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyy");

        if (date != null) {
            mDateTxt = dateFormat.format(date);
        }
        if (time != null) {
            mTimeTxt = timeFormat.format(time);
        }
    }

    private void fieldsSetter() {
        mTitleMain.setText(mTitleTxt);
        if (mDescriptionTxt.length() > 10) {
            mDescriptionMain.setText(mDescriptionTxt.substring(0, 10) + "...");
        }
        if (mDate != null && mTitleTxt != null) {
            mDateMain.setText(mDateTxt + "...");
        } else {
            mDateMain.setText(mDateTxt);
            mTimeMain.setText(mTimeTxt);
        }
        mSecondConstraint.setVisibility(View.VISIBLE);
    }
}
