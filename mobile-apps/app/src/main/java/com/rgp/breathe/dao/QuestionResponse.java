package com.rgp.breathe.dao;

import java.util.List;

/**
 * Created by munichan on 05-04-2016.
 */
public class QuestionResponse {
    private Question question;
    private List<Choice> answers;

    public Question getQuestion() {
        return question;
    }

    public QuestionResponse withQuestion(Question question) {
        this.question = question;
        return this;
    }

    public List<Choice> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Choice> answers) {
        this.answers = answers;
    }
}
