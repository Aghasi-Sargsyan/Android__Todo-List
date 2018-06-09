package com.example.aghasi.todolist;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private Button mSecondSave;
    private EditText mSecondTitle, mSecondDescription;
    private TextView mSecondDate, mSecondPriority;
    private ImageView mSecondUp, mSecondDown;
    private CheckBox mSecondCheckReminder, mSecondCheckRepeat;
    private RadioGroup mSecondRadioGroup;
    private String mTitle;
    private String mDescription;
    private boolean mReminder;
    private boolean mRepeat;
    private int mPriorityCounter;
    private RepeatPeriod repeatPeriod;
    private Dialog mDialog;
    private DatePicker mDatePicker;
    private Button mCalendarSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mPriorityCounter = 0;
        mSecondSave = findViewById(R.id.second_save);
        mSecondTitle = findViewById(R.id.second_title);
        mSecondDescription = findViewById(R.id.second_description);
        mSecondDate = findViewById(R.id.second_date);
        mSecondPriority = findViewById(R.id.second_priority);
        mSecondCheckReminder = findViewById(R.id.second_reminder);
        mSecondCheckRepeat = findViewById(R.id.second_repeat);
        mSecondRadioGroup = findViewById(R.id.second_radioGroup);
        mSecondUp = findViewById(R.id.second_up);
        mSecondDown = findViewById(R.id.second_down);
        mDatePicker = findViewById(R.id.calendar);
        mSecondRadioGroup.check(R.id.second_daily);
        mCalendarSave = findViewById(R.id.calendarSave);

        mDialog = new Dialog(this);
        mDialog.setContentView(R.layout.popup_calendar);
        mSecondDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.show();
            }
        });


        mCalendarSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mSecondSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collectInputData();
                Intent intent = new Intent();
                intent.putExtra(Const.TITLE, mTitle);
                intent.putExtra(Const.DESCRIPTION, mDescription);
                intent.putExtra(Const.REPEAT_PERIOD, repeatPeriod);
                intent.putExtra(Const.REMINDER, mReminder);
                intent.putExtra(Const.REPEAT, mRepeat);
                intent.putExtra(Const.PRIORITY, mPriorityCounter);
                intent.putExtra(Const.PRIORITY, mPriorityCounter);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        mSecondCheckRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSecondCheckRepeat.isChecked()) {
                    mSecondRadioGroup.setVisibility(View.VISIBLE);
                } else {
                    mSecondRadioGroup.setVisibility(View.GONE);
                }
            }
        });

        View.OnClickListener upDownButtons = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.second_up:
                        mPriorityCounter ++;
                        break;
                    case R.id.second_down:
                        if (mPriorityCounter != 0) {
                            mPriorityCounter --;
                        }
                        break;
                }
                    mSecondPriority.setText(Const.PRIORITY_TXT + " " + mPriorityCounter);
            }
        };
        mSecondUp.setOnClickListener(upDownButtons);
        mSecondDown.setOnClickListener(upDownButtons);
    }



    private void collectInputData() {
        mTitle = mSecondTitle.getText().toString();
        mDescription = mSecondDescription.getText().toString();
        mReminder = mSecondCheckReminder.isChecked();
        mRepeat = mSecondCheckRepeat.isChecked();

        switch (mSecondRadioGroup.getCheckedRadioButtonId()) {
            case R.id.second_daily:
                repeatPeriod = RepeatPeriod.DAILY;
                break;
            case R.id.second_weekly:
                repeatPeriod = RepeatPeriod.WEEKLY;
                break;
            case R.id.second_monthly:
                repeatPeriod = RepeatPeriod.MONTHLY;
                break;
        }
    }
}
