package com.rgp.breathe.dao;

import java.util.List;

public class Question {
    private int id;
    private int sequence;
    private QuestionType type;
    private String content;
    private List<Choice> options;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Choice> getOptions() {
        return options;
    }

    public void setOptions(List<Choice> options) {
        this.options = options;
    }
}
