package com.rgp.breathe.model;

/**
 * Created by mdansari on 3/28/2016.
 */
public class Questionary {

    private String title;
    private String status;

    public Questionary(String title, String status) {
        this.title = title;
        this.status = status;
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
