package com.rgp.breathe.model;

import java.util.List;

/**
 * Created by mdansari on 3/28/2016.
 */
public class Questionnaire {

    private String title;
    private String status;
    private List<Question> questionList;

    public Questionnaire(String title, String status, List<Question> questionList) {
        this.title = title;
        this.status = status;
        this.questionList = questionList;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
