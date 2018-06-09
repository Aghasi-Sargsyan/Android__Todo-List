package com.example.aghasi.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Date;

import static com.example.aghasi.todolist.MainActivity.ADD_NEW_EVENT_CODE;

public class SecondActivity extends AppCompatActivity {

    private Button mSecondSave;
    private EditText mSecondTitle, mSecondDescription;
    private TextView mSecondDate, mSecondPriority;
    private CheckBox mSecondCheckReminder, mSecondCheckRepeat;
    private RadioGroup mSecondRadioGroup;
    private String mTitle;
    private String mDescription;
    private RepeatPeriod repeatPeriod;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mSecondSave = findViewById(R.id.second_save);
        mSecondTitle = findViewById(R.id.second_title);
        mSecondDescription = findViewById(R.id.second_description);
        mSecondDate = findViewById(R.id.second_date);
        mSecondPriority = findViewById(R.id.second_priority);
        mSecondCheckReminder = findViewById(R.id.second_reminder);
        mSecondCheckRepeat = findViewById(R.id.second_repeat);
        mSecondRadioGroup = findViewById(R.id.second_radioGroup);

        mSecondRadioGroup.check(R.id.second_daily);

        mSecondSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collectInputData();
                Intent intent = new Intent();
                intent.putExtra("title", mTitle);
                intent.putExtra("description", mDescription);
                intent.putExtra("repeatPeriod", repeatPeriod);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void collectInputData() {
        mTitle = mSecondTitle.getText().toString();
        mDescription = mSecondDescription.getText().toString();
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
