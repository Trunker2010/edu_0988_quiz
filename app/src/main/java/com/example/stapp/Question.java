package com.example.stapp;

class Question {
    int qText;
    Boolean questionIsTrue;

    public int getQText() {
        return qText;
    }

    public Boolean getQuestionIsTrue() {
        return questionIsTrue;
    }

    public Question(int qText, Boolean questionIsTrue) {
        this.qText = qText;
        this.questionIsTrue = questionIsTrue;
    }
}
