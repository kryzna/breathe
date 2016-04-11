package com.rgp.breathe.questionaire.controller;

import com.rgp.breathe.questionaire.dto.Questionnaire;
import com.rgp.breathe.questionaire.dto.QuestionnaireResponse;
import com.rgp.breathe.questionaire.dto.RiskScore;
import com.rgp.breathe.questionaire.services.FileBasedQuestionnaireService;
import com.rgp.breathe.questionaire.services.QuestionnaireService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/questionnaire/")
public class QuestionnaireController {
    private static Logger logger = LoggerFactory.getLogger(QuestionnaireController.class);

    @Autowired
    private FileBasedQuestionnaireService fileBasedQuestionnaireService;

    @RequestMapping("/{id}")
    public ResponseEntity<Questionnaire> getQuestionnaire(@PathVariable("id") long id, @RequestParam(name = "prefLang", defaultValue = "en", required = false) String preferredLanguage, HttpServletRequest request) throws IOException {
        logger.info("Received request {} from IP {}", request.getRequestURI(), request.getRemoteHost());
        return new ResponseEntity<>(fileBasedQuestionnaireService.read(preferredLanguage), HttpStatus.OK);
    }

    @RequestMapping(value = "/response", method = RequestMethod.POST)
    public ResponseEntity<RiskScore> receiveResponse(@RequestBody QuestionnaireResponse questionnaireResponse) {
        logger.info("Received response from questionnaireResponse = " + questionnaireResponse);
        RiskScore dummyRiskScore = new RiskScore();
        dummyRiskScore.setScore(ThreadLocalRandom.current().nextDouble(0.5, 10));

        return new ResponseEntity<>(dummyRiskScore, HttpStatus.CREATED);
    }

}