package com.rgp.breathe.dao;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private int id;
    private int sequence;
    private QuestionType type;
    private String content;
    private boolean isAttempted;
    private List<Choice> options;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Question withId(int id) {
        this.id = id;
        return this;
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

    public List<Choice> getSelectedChoices() {
        List<Choice> chosenList = null;

        for (Choice choice : options) {
            if (choice.isChosen()) {
                if (chosenList == null) {
                    chosenList = new ArrayList<Choice>(1);
                }
                chosenList.add(choice);
            }
        }
        return chosenList;
    }

    public void switchChoice(int id) {
        List<Choice> choiceList = this.getSelectedChoices();
        if (choiceList != null && choiceList.size() == 1) {
            choiceList.get(0).setChoice(false);
        }
        for (Choice choice : getOptions()) {
            if (id == choice.getId()) {
                choice.setChoice(true);
            }
        }
    }

    public void markAttempted() {
        this.isAttempted = true;
    }

    public boolean isAttempted() {
        return this.isAttempted;
    }
}
