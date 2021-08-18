package com.example.stapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    final String Q_INDEX_KEY = "qIndex";
    Question[] questions = {
            new Question(R.string.question1, true),
            new Question(R.string.question2, true),
            new Question(R.string.question3, false),
            new Question(R.string.question4, true),
            new Question(R.string.question5, false)
    };

    Button yseBtn;
    Button noBtn;
    TextView questionEdText;
    int qIndex = 0;
    Toast qResultToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noBtn = findViewById(R.id.no_btn);
        yseBtn = findViewById(R.id.yes_btn);
        questionEdText = findViewById(R.id.textView);
        if (savedInstanceState != null) {
            qIndex = savedInstanceState.getInt(Q_INDEX_KEY);
        }
        View.OnClickListener yesNoOCL = view -> {
            switch (view.getId()) {
                case (R.id.no_btn): {
                    checkAnswer(false);
                }
                case (R.id.yes_btn): {
                    checkAnswer(true);
                }
                default: {
                    qIndex = (qIndex + 1) % questions.length;
                    questionEdText.setText(questions[qIndex].getQText());
                }
            }
        };
        noBtn.setOnClickListener(yesNoOCL);
        yseBtn.setOnClickListener(yesNoOCL);
    }

    private void checkAnswer(Boolean answer) {
        if (qResultToast != null) {
            qResultToast.cancel();
        }
        if (questions[qIndex].getQuestionIsTrue() == answer) {
            qResultToast = Toast.makeText(MainActivity.this, "Ответ верный", Toast.LENGTH_LONG);

        } else {
            qResultToast = Toast.makeText(MainActivity.this, "Ответ не верный", Toast.LENGTH_LONG);
        }
        qResultToast.show();
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Q_INDEX_KEY, qIndex);
    }

}