package com.example.stapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ShowAnswerActivity extends AppCompatActivity {
    TextView answerTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_answer);
        answerTV = findViewById(R.id.trueAnswerIsTV);
        boolean isYesAnswer = getIntent().getBooleanExtra(Constants.IS_TRUE_ANSWER_KEY, false);
        answerTV.setText(isYesAnswer ? R.string.Yes : R.string.No);
    }
}