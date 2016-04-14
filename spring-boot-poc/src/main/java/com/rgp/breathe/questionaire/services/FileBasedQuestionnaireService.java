package com.rgp.breathe.questionaire.services;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rgp.breathe.questionaire.dto.Questionnaire;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by munichan on 11-04-2016.
 */
@Service
public class FileBasedQuestionnaireService {
    private Map<String, String> availableLanguages = new HashMap<>();

    public FileBasedQuestionnaireService() {
        availableLanguages.put("en", "en");
        availableLanguages.put("en-us", "en");
        availableLanguages.put("en_us", "en");
        availableLanguages.put("hi", "hi");
        availableLanguages.put("hi-in", "hi");
        availableLanguages.put("hi_in", "hi");
    }

    public Questionnaire read(String language) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Questionnaire questionnaire = mapper.readValue(new InputStreamReader(FileBasedQuestionnaireService.class.getClassLoader().getResourceAsStream("questionnaire_" + availableLanguages.getOrDefault(language.toLowerCase(), "en") + ".json"), "UTF-8"), Questionnaire.class);
        return questionnaire;
    }
}
