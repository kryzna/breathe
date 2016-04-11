package com.rgp.breathe.questionaire.services;

import com.rgp.breathe.questionaire.dto.Questionnaire;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by munichan on 11-04-2016.
 */
public class FileBasedQuestionnaireServiceTest {

    @Test
    public void testReadEnglish() throws Exception {
        FileBasedQuestionnaireService service = new FileBasedQuestionnaireService();
        final Questionnaire en = service.read("en");
        System.out.println("en = " + en);
    }

    @Test
    public void testReadHindi() throws Exception {
        FileBasedQuestionnaireService service = new FileBasedQuestionnaireService();
        final Questionnaire hindiQuestionnaire = service.read("hi");
        System.out.println("hindiQuestionnaire = " + hindiQuestionnaire);
    }
}