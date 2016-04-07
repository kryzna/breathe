package com.rgp.breathe.questionaire.dto.helper;

import com.rgp.breathe.questionaire.dto.Choice;

/**
 * Created by munichan on 05-04-2016.
 */
public class ChoiceBuilder {
    private int id;
    private String text;

    private ChoiceBuilder() {
    }

    public static ChoiceBuilder aChoice() {
        return new ChoiceBuilder();
    }

    public ChoiceBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public ChoiceBuilder withText(String text) {
        this.text = text;
        return this;
    }

    public ChoiceBuilder but() {
        return aChoice().withId(id).withText(text);
    }

    public Choice build() {
        Choice choice = new Choice();
        choice.setId(id);
        choice.setText(text);
        return choice;
    }
}
