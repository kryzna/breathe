package com.rgp.breathe.questionaire.controller;

import com.rgp.breathe.questionaire.dto.Questionnaire;
import com.rgp.breathe.questionaire.services.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/questionnaire/")
public class QuestionnaireController {

    @Autowired
    private QuestionnaireService questionnaireService;
    
    @RequestMapping("/{id}")
    public Questionnaire index(@PathVariable("id") long id) {
        return questionnaireService.getQuestionnaire(id);
    }
    
}