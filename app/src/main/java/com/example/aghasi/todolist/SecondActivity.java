package com.example.aghasi.todolist;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
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
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.widget.Toast.makeText;

public class SecondActivity extends AppCompatActivity {

    private Button mSaveSecond;
    private EditText mTitleSecond, mDescriptionSecond;
    private TextView mDateSecond, mPrioritySecond;
    private ImageView mUpSecond, mDownSecond;
    private CheckBox mCheckReminderSecond, mCheckRepeatSecond;
    private RadioGroup mRadioGroupSecond;
    private String mTitle;
    private String mDescription;
    private boolean mReminder;
    private boolean mRepeat;
    private int mPriorityCounter;
    private RepeatPeriod repeatPeriod;
    private String mDateTxt;


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




        mDateSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseDate();
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
                    intent.putExtra(Const.DATE, mDateTxt);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Toast toast = makeText(SecondActivity.this, "The title is empty", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        mCheckRepeatSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCheckRepeatSecond.isChecked()) {
                    mRadioGroupSecond.setVisibility(View.VISIBLE);
                } else {
                    mRadioGroupSecond.setVisibility(View.GONE);
                }
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
    private void chooseDate() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final DatePickerDialog datePicker = new DatePickerDialog(this, R.style.SpinnerDate, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(final DatePicker view, final int year, final int month,
                                          final int dayOfMonth) {
                        @SuppressLint("SimpleDateFormat")
                        SimpleDateFormat monthWordFormat = new SimpleDateFormat("dd/MMM/yyyy");
                        @SuppressLint("SimpleDateFormat")
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        calendar.set(year, month, dayOfMonth);
                        mDateTxt = monthWordFormat.format(calendar.getTime());

                        mDateSecond.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                }, year, month, day);
        datePicker.show();

        datePicker.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(final DialogInterface dialog) {
                dialog.dismiss();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == Const.EDIT_EVENT_CODE) {
                mSaveSecond.setText("Edit");
            }
        }
    }
}
