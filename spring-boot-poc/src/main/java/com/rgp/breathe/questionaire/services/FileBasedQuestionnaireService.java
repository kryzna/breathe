package com.rgp.breathe.questionaire.services;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rgp.breathe.questionaire.dto.Questionnaire;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by munichan on 11-04-2016.
 */
@Service
public class FileBasedQuestionnaireService {
    private Set<String> availableLanguages = new HashSet<>();

    public FileBasedQuestionnaireService() {
        availableLanguages.add("en");
        availableLanguages.add("en-us");
        availableLanguages.add("hi");
        availableLanguages.add("hi-in");
    }

    public Questionnaire read(String language) throws IOException {
        if (language == null || language.isEmpty() || !availableLanguages.contains(language.toLowerCase())) {
            language = "en";
        }
        ObjectMapper mapper = new ObjectMapper();
        Questionnaire questionnaire = mapper.readValue(new InputStreamReader(FileBasedQuestionnaireService.class.getClassLoader().getResourceAsStream("questionnaire_" + language + ".json"), "UTF-8"), Questionnaire.class);
        return questionnaire;
    }
}
