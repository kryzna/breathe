package com.rgp.breathe.questionaire.services;

import com.rgp.breathe.questionaire.dto.QuestionType;
import com.rgp.breathe.questionaire.dto.Questionnaire;
import com.rgp.breathe.questionaire.dto.helper.ChoiceBuilder;
import com.rgp.breathe.questionaire.dto.helper.QuestionBuilder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Created by munichan on 05-04-2016.
 */
@Service
public class QuestionnaireService {

    private final Questionnaire questionnaire;

    public QuestionnaireService() {
        questionnaire = new Questionnaire();
        questionnaire.setId(1);
        questionnaire.setTitle("Asthma Questionnaire for Beginners");
        questionnaire.getQuestions().add(QuestionBuilder.aQuestion()
                .withId(1)
                .withType(QuestionType.RADIO)
                .withContent("In the past month, how frequently did your asthma keep you from getting as much done at work, school or at home? Potential answers (user can select one and only one)")
                .withOptions(Arrays.asList(ChoiceBuilder.aChoice().withId(1).withText("All of the time").build(),
                                ChoiceBuilder.aChoice().withId(2).withText("Most of the time").build(),
                                ChoiceBuilder.aChoice().withId(3).withText("Some of the time").build(),
                                ChoiceBuilder.aChoice().withId(4).withText("A little of the time").build(),
                                ChoiceBuilder.aChoice().withId(5).withText("None").build())
                ).build());

        questionnaire.getQuestions().add(QuestionBuilder.aQuestion()
                .withId(2)
                .withType(QuestionType.RADIO)
                .withContent("In the past month, how many instances of shortness of breath (SOB) have you had? Potential answers (user can select one and only one):")
                .withOptions(Arrays.asList(ChoiceBuilder.aChoice().withId(1).withText("> 1 per day").build(),
                                ChoiceBuilder.aChoice().withId(2).withText("1 per day").build(),
                                ChoiceBuilder.aChoice().withId(3).withText("3-6 per week").build(),
                                ChoiceBuilder.aChoice().withId(4).withText("1-2 per week").build(),
                                ChoiceBuilder.aChoice().withId(5).withText("None").build())
                ).build());

        questionnaire.getQuestions().add(QuestionBuilder.aQuestion()
                .withId(3)
                .withType(QuestionType.CHECKBOX)
                .withContent("Are your Asthma attacks triggered by any of the following? Potential answers (user must select at least one)")
                .withOptions(Arrays.asList(ChoiceBuilder.aChoice().withId(1).withText("Cockroaches").build(),
                                ChoiceBuilder.aChoice().withId(2).withText("Exercise").build(),
                                ChoiceBuilder.aChoice().withId(3).withText("Medicines").build(),
                                ChoiceBuilder.aChoice().withId(4).withText("Pollution").build(),
                                ChoiceBuilder.aChoice().withId(5).withText("Smoke").build())
                ).build());
    }

    public Questionnaire getQuestionnaire(long id) {
        return questionnaire;
    }
}
