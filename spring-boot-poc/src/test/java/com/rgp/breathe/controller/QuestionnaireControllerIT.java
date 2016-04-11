package com.rgp.breathe.controller;

import com.rgp.breathe.Application;
import com.rgp.breathe.questionaire.dto.QuestionResponse;
import com.rgp.breathe.questionaire.dto.Questionnaire;
import com.rgp.breathe.questionaire.dto.QuestionnaireResponse;
import com.rgp.breathe.questionaire.dto.RiskScore;
import com.rgp.breathe.questionaire.dto.helper.ChoiceBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.net.URL;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class QuestionnaireControllerIT {

    @Value("${local.server.port}")
    private int port;

    private URL base;
    private RestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/");
        template = new TestRestTemplate();
    }

    @Test
    public void getHello() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
        assertThat(response.getBody(), equalTo("Greetings from Spring Boot, I am perfectly fine!"));
    }

    @Test
    public void testGetGetQuestionnaire() throws Exception {
        ResponseEntity<Questionnaire> response = template.getForEntity(base.toString()+"questionnaire/1", Questionnaire.class);
        assertThat(response.getBody().getTitle(), equalTo("Asthma Questionnaire for Beginners"));
        assertThat(response.getBody().getQuestions().size(), equalTo(3));
    }

    @Test
    public void testPostGetQuestionnaireResponse() throws Exception {
        QuestionnaireResponse questionnaireResponse = new QuestionnaireResponse();
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setId(1);

        QuestionResponse questionResponse = new QuestionResponse();

        questionResponse.setAnswers(asList(ChoiceBuilder.aChoice().withId(1).build()));
        questionnaireResponse.setQuestionnaire(questionnaire);
        questionnaireResponse.setQuestionResponseList(asList(questionResponse));

        RiskScore riskScore = template.postForObject(base.toString() + "questionnaire/response", questionnaireResponse, RiskScore.class, "");
        assertThat(riskScore.getScore(), lessThan(11.0));
        assertThat(riskScore.getScore(), greaterThan(0.0));
    }
}
