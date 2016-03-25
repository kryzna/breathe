package com.edifecs.cdm.breathe.riskassesment;

import com.amazonaws.services.lambda.runtime.Context;

/**
 * Created by tridib.samanta on 3/24/2016.
 */
public class RiskAssessmentService {
    public String[] getQuestions(int questionCount, Context ctx) {
        String[] questions = new String[questionCount];
        for (int i = 0; i < questionCount; i++) {
            questions[i] = "Question " + (i + 1);
        }

        return questions;
    }

    public String hello(String name, Context ctx) {
        return String.format("Hello %s!", name);
    }

    public AssessmentResponse hello2(AssessmentRequest request, Context ctx) {
        AssessmentResponse response = new AssessmentResponse();
        response.setGreeting(String.format("Hello %s!", request.getName()));
        return response;
    }
}
