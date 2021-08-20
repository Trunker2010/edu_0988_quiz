package com.example.stapp;

import static com.example.stapp.Constants.GOOD_ANSWER_COUNT_KEY;
import static com.example.stapp.Constants.IS_TRUE_ANSWER_KEY;
import static com.example.stapp.Constants.Q_AND_ANSWERS_KEY;
import static com.example.stapp.Constants.Q_INDEX_KEY;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button noBtn, yseBtn, showAnswerBtn;
    TextView questionEdText;
    Toast qResultToast;
    int qIndex = 0;
    int goodAnswerCount = 0;

    Question[] questions = {
            new Question(R.string.question1, true),
            new Question(R.string.question2, true),
            new Question(R.string.question3, false),
            new Question(R.string.question4, true),
            new Question(R.string.question5, false)
    };
    String[] questionsAndAnswers = new String[questions.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noBtn = findViewById(R.id.no_btn);
        yseBtn = findViewById(R.id.yes_btn);
        showAnswerBtn = findViewById(R.id.showAnswerBtn);

        questionEdText = findViewById(R.id.textView);
        if (savedInstanceState != null) {
            qIndex = savedInstanceState.getInt(Q_INDEX_KEY);
            questionsAndAnswers = savedInstanceState.getStringArray(Q_AND_ANSWERS_KEY);
            goodAnswerCount = savedInstanceState.getInt(GOOD_ANSWER_COUNT_KEY);
        }
        questionEdText.setText(questions[qIndex].getQText());
        View.OnClickListener yesNoOCL = view -> {
            switch (view.getId()) {
                case (R.id.no_btn): {
                    checkAnswer(false);
                    break;
                }
                case (R.id.yes_btn): {
                    checkAnswer(true);
                    break;
                }
            }

            questionEdText.setText(questions[qIndex].getQText());
            qIndex++;
            if (qIndex == questions.length) {
                qIndex = 0;
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra(GOOD_ANSWER_COUNT_KEY, goodAnswerCount);
                intent.putExtra(Q_AND_ANSWERS_KEY, questionsAndAnswers);
                startActivity(intent);
            }
            questionEdText.setText(questions[qIndex].getQText());
        };
        noBtn.setOnClickListener(yesNoOCL);
        yseBtn.setOnClickListener(yesNoOCL);
        showAnswerBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ShowAnswerActivity.class);
            intent.putExtra(IS_TRUE_ANSWER_KEY, questions[qIndex].getQuestionIsTrue());
            intent.putExtra(GOOD_ANSWER_COUNT_KEY, goodAnswerCount);
            startActivity(intent);
        });
    }

    private void checkAnswer(Boolean answer) {
        if (qResultToast != null) {
            qResultToast.cancel();
        }
        if (questions[qIndex].getQuestionIsTrue() == answer) {
            qResultToast = Toast.makeText(MainActivity.this, "Ответ верный", Toast.LENGTH_LONG);
            goodAnswerCount++;
        } else {
            qResultToast = Toast.makeText(MainActivity.this, "Ответ не верный", Toast.LENGTH_LONG);
        }
        questionsAndAnswers[qIndex] = attachAnswerQuestion(getString(questions[qIndex].getQText()), answer);
        qResultToast.show();
    }

    private String attachAnswerQuestion(String question, boolean answer) {
        return question + " - " + "ваш ответ: " + (answer ? getString(R.string.Yes) : getString(R.string.No));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(Q_INDEX_KEY, qIndex);
        outState.putStringArray(Q_AND_ANSWERS_KEY, questionsAndAnswers);
        outState.putInt(GOOD_ANSWER_COUNT_KEY, goodAnswerCount);
        super.onSaveInstanceState(outState);
    }
}