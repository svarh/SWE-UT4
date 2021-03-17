package com.example.care;

import android.widget.CheckBox;

public class Questions {

    private int questionNumber;
    private String questionMain;
    private String questionDetail;

    public Questions(int questionNumber, String questionMain, String questionDetail) {
        this.questionNumber = questionNumber;
        this.questionMain = questionMain;
        this.questionDetail = questionDetail;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getQuestionMain() {
        return questionMain;
    }

    public void setQuestionMain(String questionMain) {
        this.questionMain = questionMain;
    }

    public String getQuestionDetail() {
        return questionDetail;
    }

    public void setQuestionDetail(String questionDetail) {
        this.questionDetail = questionDetail;
    }

}
