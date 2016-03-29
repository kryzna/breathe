package com.rgp.breathe.model;

import java.util.List;

/**
 * Created by mdansari on 3/29/2016.
 */
public class Question {

    private String questionContent;
    private String questionType;
    private List<String> possibleAnswers;
    private List<Object> answers;

    public Question(String questionContent, String questionType, List<String> possibleAnswers, List<Object> answers) {
        this.questionContent = questionContent;
        this.questionType = questionType;
        this.possibleAnswers = possibleAnswers;
        this.answers = answers;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public List<String> getPossibleAnswers() {
        return possibleAnswers;
    }

    public void setPossibleAnswers(List<String> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }

    public List<Object> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Object> answers) {
        this.answers = answers;
    }

}
