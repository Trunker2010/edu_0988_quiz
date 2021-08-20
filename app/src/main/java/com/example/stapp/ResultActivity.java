package com.example.stapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    TextView qAndAnswers;
    String[] resultArr;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        qAndAnswers = findViewById(R.id.q_and_answers);
        resultArr = getIntent().getStringArrayExtra(Constants.Q_AND_ANSWERS_KEY);
        for (int i = 0; i < resultArr.length; i++) {
            qAndAnswers.append(resultArr[i] + "\n");
        }
    }
}