package com.rgp.breathe.questionaire.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by munichan on 05-04-2016.
 */
public class Questionnaire {
    private int id;
    private String title;

    List<Question> questions = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
