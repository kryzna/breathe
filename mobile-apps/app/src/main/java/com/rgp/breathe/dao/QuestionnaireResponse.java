package com.rgp.breathe.dao;

import java.util.List;

/**
 * Created by munichan on 05-04-2016.
 */
public class QuestionnaireResponse {
    private Questionnaire questionnaire;

    private List<QuestionResponse> questionResponseList;

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    public List<QuestionResponse> getQuestionResponseList() {
        return questionResponseList;
    }

    public void setQuestionResponseList(List<QuestionResponse> questionResponseList) {
        this.questionResponseList = questionResponseList;
    }
}
