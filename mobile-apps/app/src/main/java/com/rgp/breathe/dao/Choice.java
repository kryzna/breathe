package com.rgp.breathe.dao;


/**
 * Created by munichan on 05-04-2016.
 */
public class Choice {
    private int id;
    private int sequence;
    private String text;
    private boolean isChosen;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Choice withId(int id) {
        this.id = id;
        return this;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setChoice(boolean choice) {
        this.isChosen = choice;
    }

    public boolean isChosen() {
        return this.isChosen;
    }


}
