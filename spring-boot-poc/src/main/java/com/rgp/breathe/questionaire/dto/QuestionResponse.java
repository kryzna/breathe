package com.rgp.breathe.questionaire.dto;

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

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Choice> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Choice> answers) {
        this.answers = answers;
    }
}
