package com.example.aghasi.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    Button mSecondSave;
    EditText mSecondTitle, mSecondDescription;
    TextView mSecondDate, mSecondPriority;
    CheckBox mSecondCheckReminder, mSecondCheckRepeat;
    RadioGroup mSecondRadioGroup;


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

    }
}
