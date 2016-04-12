package com.rgp.breathe.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by munichan on 05-04-2016.
 */
public class Questionnaire {
    public static final String NOT_COMPLETED = "Not Completed";
    public static final String COMPLETED = "Completed";
    private int id;
    private String title;

    private String status = NOT_COMPLETED;
    private Double weight;

    List<Question> questions = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Questionnaire withId(int id) {
        this.id = id;
        return this;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

}
