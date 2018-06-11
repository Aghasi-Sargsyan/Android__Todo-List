package com.example.aghasi.todolist;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.aghasi.todolist.util.Const;
import com.example.aghasi.todolist.util.RepeatPeriod;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.widget.Toast.makeText;

public class SecondActivity extends AppCompatActivity {
    private Button mButtonSave;
    private EditText mEditTitle, mEditDescription;
    private TextView mTextDateTime, mTextPriority;
    private CheckBox mCheckBoxReminder, mCheckBoxRepeat;
    private RadioGroup mRadioGroupPeriod;
    private ImageView mImageUp, mImageDown;
    private RepeatPeriod mRepeatPeriod;
    private int mPriorityCounter;
    private Calendar mCalendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mPriorityCounter = 0;
        mRepeatPeriod = null;

        mButtonSave = findViewById(R.id.button_second_save);
        mEditTitle = findViewById(R.id.edit_second_title);
        mEditDescription = findViewById(R.id.edit_second_description);
        mTextDateTime = findViewById(R.id.text_second_date_time);
        mTextPriority = findViewById(R.id.text_second_priority);
        mCheckBoxReminder = findViewById(R.id.checkbox_second_reminder);
        mCheckBoxRepeat = findViewById(R.id.checkbox_second_repeat);
        mRadioGroupPeriod = findViewById(R.id.radioGroup_second_period);
        mImageUp = findViewById(R.id.image_second_up);
        mImageDown = findViewById(R.id.image_second_down);

        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEditTitle.getText() != null && !mEditTitle.getText().toString().isEmpty()) {
                Intent intent = new Intent();
                repeatPeriodChecker();
                intent.putExtra(Const.REPEAT_PERIOD_KEY, mRepeatPeriod);
                intent.putExtra(Const.TITLE_KEY, mEditTitle.getText().toString());
                intent.putExtra(Const.DESCRIPTION_KEY, mEditDescription.getText().toString());
                intent.putExtra(Const.REMINDER_KEY, mCheckBoxReminder.isChecked());
                intent.putExtra(Const.REPEAT_KEY, mCheckBoxRepeat.isChecked());
                intent.putExtra(Const.PRIORITY_KEY, mPriorityCounter);
                intent.putExtra(Const.CALENDAR, mCalendar);
                setResult(RESULT_OK, intent);
                finish();
                } else {
                    Toast toast = makeText(SecondActivity.this, "Title is empty", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        mTextDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseDateAndTime();
            }
        });

        mCheckBoxReminder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    compoundButton.setTextColor(getResources().getColor(R.color.green));
                } else {
                    compoundButton.setTextColor(getResources().getColor(R.color.checkbox_color));
                }
            }
        });
        mCheckBoxRepeat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mRadioGroupPeriod.setVisibility(View.VISIBLE);
                    mRadioGroupPeriod.check(R.id.radioButton_second_daily);
                    mRepeatPeriod = RepeatPeriod.DAILY;
                    compoundButton.setTextColor(getResources().getColor(R.color.green));
                } else {
                    mRadioGroupPeriod.setVisibility(View.GONE);
                    mRepeatPeriod = null;
                    compoundButton.setTextColor(getResources().getColor(R.color.checkbox_color));
                }
            }
        });

        View.OnClickListener upDownButtonsListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.image_second_up:
                        mPriorityCounter++;
                        break;
                    case R.id.image_second_down:
                        if (mPriorityCounter != 0) {
                            mPriorityCounter--;
                        }
                        break;
                }
                mTextPriority.setText(Const.PRIORITY_TXT + " " + mPriorityCounter);
            }
        };

        mImageUp.setOnClickListener(upDownButtonsListener);
        mImageDown.setOnClickListener(upDownButtonsListener);
    }


    private void repeatPeriodChecker() {
        switch (mRadioGroupPeriod.getCheckedRadioButtonId()) {
            case R.id.radioButton_second_daily:
                mRepeatPeriod = RepeatPeriod.DAILY;
                break;
            case R.id.radioButton_second_weekly:
                mRepeatPeriod = RepeatPeriod.WEEKLY;
                break;
            case R.id.radioButton_second_monthly:
                mRepeatPeriod = RepeatPeriod.MONTHLY;
                break;
        }
    }

    private void chooseDateAndTime() {
        final Calendar currentDate = Calendar.getInstance();
        mCalendar = Calendar.getInstance();
        new DatePickerDialog(this, R.style.SpinnerDate, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mCalendar.set(year, monthOfYear, dayOfMonth);
                new TimePickerDialog(SecondActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        mCalendar.set(Calendar.MINUTE, minute);

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Const.DATE_TIME_FORMAT);
                        mTextDateTime.setText(simpleDateFormat.format(mCalendar.getTime()));
                    }
                }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), true).show();
            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
    }
}

