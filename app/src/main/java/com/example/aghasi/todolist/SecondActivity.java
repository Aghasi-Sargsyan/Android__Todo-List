package com.example.aghasi.todolist;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.widget.Toast.makeText;

public class SecondActivity extends AppCompatActivity {

    private Button mSaveSecond;
    private EditText mTitleSecond, mDescriptionSecond;
    private TextView mDateSecond, mPrioritySecond, mTimeSecond;
    private ImageView mUpSecond, mDownSecond;
    private CheckBox mCheckReminderSecond, mCheckRepeatSecond;
    private RadioGroup mRadioGroupSecond;
    private String mTitle;
    private String mDescription;
    private boolean mReminder;
    private boolean mRepeat;
    private int mPriorityCounter;
    private RepeatPeriod repeatPeriod;
    private Date mTimeDate, mDateDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        mPriorityCounter = 0;
        mSaveSecond = findViewById(R.id.save_second);
        mTitleSecond = findViewById(R.id.title_second);
        mDescriptionSecond = findViewById(R.id.description_second);
        mDateSecond = findViewById(R.id.date_second);
        mPrioritySecond = findViewById(R.id.priority_second);
        mCheckReminderSecond = findViewById(R.id.reminder_second);
        mCheckRepeatSecond = findViewById(R.id.repeat_second);
        mRadioGroupSecond = findViewById(R.id.radioGroup_second);
        mUpSecond = findViewById(R.id.up_second);
        mDownSecond = findViewById(R.id.down_second);
        mTimeSecond = findViewById(R.id.time_second);

        mRadioGroupSecond.check(R.id.daily_second);

        mDateSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDate();
            }
        });
        mTimeSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDateDate != null) {
                    setTime();
                } else {
                    Toast toast = makeText(SecondActivity.this, "date is empty", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        mSaveSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTitleSecond.getText() != null && !mTitleSecond.getText().toString().isEmpty()) {
                    collectInputData();
                    Intent intent = new Intent();
                    intent.putExtra(Const.TITLE, mTitle);
                    intent.putExtra(Const.DESCRIPTION, mDescription);
                    intent.putExtra(Const.REPEAT_PERIOD, repeatPeriod);
                    intent.putExtra(Const.REMINDER, mReminder);
                    intent.putExtra(Const.REPEAT, mRepeat);
                    intent.putExtra(Const.PRIORITY, mPriorityCounter);
                    intent.putExtra(Const.DATE, mDateDate);
                    intent.putExtra(Const.TIME, mTimeDate);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Toast toast = makeText(SecondActivity.this, "The title is empty", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        mCheckReminderSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkboxColorSwitcher((CheckBox) view);
            }
        });

        mCheckRepeatSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox checkBox = (CheckBox) view;
                if (checkBox.isChecked()) {
                    mRadioGroupSecond.setVisibility(View.VISIBLE);
                } else {
                    mRadioGroupSecond.setVisibility(View.GONE);
                }
                checkboxColorSwitcher(checkBox);
            }
        });

        View.OnClickListener upDownButtons = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.up_second:
                        mPriorityCounter++;
                        break;
                    case R.id.down_second:
                        if (mPriorityCounter != 0) {
                            mPriorityCounter--;
                        }
                        break;
                }
                mPrioritySecond.setText(Const.PRIORITY_TXT + " " + mPriorityCounter);
            }
        };
        mUpSecond.setOnClickListener(upDownButtons);
        mDownSecond.setOnClickListener(upDownButtons);


    }

    private void collectInputData() {
        mTitle = mTitleSecond.getText().toString();
        mDescription = mDescriptionSecond.getText().toString();
        mReminder = mCheckReminderSecond.isChecked();
        mRepeat = mCheckRepeatSecond.isChecked();

        switch (mRadioGroupSecond.getCheckedRadioButtonId()) {
            case R.id.daily_second:
                repeatPeriod = RepeatPeriod.DAILY;
                break;
            case R.id.weekly_second:
                repeatPeriod = RepeatPeriod.WEEKLY;
                break;
            case R.id.monthly_second:
                repeatPeriod = RepeatPeriod.MONTHLY;
                break;
        }
    }

    private void setTime() {
        final Calendar calendar = Calendar.getInstance();
        int myHour = calendar.get(Calendar.HOUR_OF_DAY);
        int myMinute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                calendar.set(0, 0, 0, hour, minute);
                mTimeDate = calendar.getTime();
                @SuppressLint("SimpleDateFormat")
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("kk:mm");
                mTimeSecond.setText(simpleDateFormat.format(mTimeDate));
            }
        }, myHour, myMinute, true);
        timePickerDialog.show();
    }

    private void setDate() {
        final Calendar calendar = Calendar.getInstance();
        int myYear = calendar.get(Calendar.YEAR);
        int myMonth = calendar.get(Calendar.MONTH);
        int myDay = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, R.style.SpinnerDate, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendar.set(year, month, day);
                mDateDate = calendar.getTime();
                @SuppressLint("SimpleDateFormat")
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyy");
                mDateSecond.setText(simpleDateFormat.format(mDateDate));
            }
        }, myYear, myMonth, myDay);
        datePickerDialog.show();
    }


    public void checkboxColorSwitcher(CheckBox checkBox) {
        if (checkBox.isChecked()) {
            checkBox.setTextColor(getResources().getColor(R.color.green));
        } else {
            checkBox.setTextColor(getResources().getColor(R.color.checkbox_color));
        }
    }
}

