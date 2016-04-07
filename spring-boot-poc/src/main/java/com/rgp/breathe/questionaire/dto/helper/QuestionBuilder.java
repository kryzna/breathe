package com.rgp.breathe.questionaire.dto.helper;

import com.rgp.breathe.questionaire.dto.Choice;
import com.rgp.breathe.questionaire.dto.Question;
import com.rgp.breathe.questionaire.dto.QuestionType;

import java.util.List;

/**
 * Created by munichan on 05-04-2016.
 */
public class QuestionBuilder {
    private int id;
    private QuestionType type;
    private String content;
    private List<Choice> options;

    private QuestionBuilder() {
    }

    public static QuestionBuilder aQuestion() {
        return new QuestionBuilder();
    }

    public QuestionBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public QuestionBuilder withType(QuestionType type) {
        this.type = type;
        return this;
    }

    public QuestionBuilder withContent(String content) {
        this.content = content;
        return this;
    }

    public QuestionBuilder withOptions(List<Choice> options) {
        this.options = options;
        return this;
    }

    public QuestionBuilder but() {
        return aQuestion().withId(id).withType(type).withContent(content).withOptions(options);
    }

    public Question build() {
        Question question = new Question();
        question.setId(id);
        question.setType(type);
        question.setContent(content);
        question.setOptions(options);
        return question;
    }
}
