package com.edifecs.breathe.qa.testcases;

import com.edifecs.breathe.qa.functions.Andriodsetup;
import com.edifecs.breathe.qa.functions.StandardFunctions;
import com.edifecs.breathe.qa.pageobjects.HealthRiskAssesment;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by amolverm on 4/14/2016.
 */
public class TestStartHealthRiskAssessment {
    StandardFunctions standardFunctions = new StandardFunctions();
    public static WebElement elementQuestionTitle,elementQuestionnaireTitle;
    HealthRiskAssesment healthRiskAssesment = new HealthRiskAssesment();
    public static int questionNumber;
    public static int scenarioNumber=1;
    @BeforeTest
    public void startQuesttionaire(){
        questionNumber=2;
        healthRiskAssesment.setAssessmentStartButton(Andriodsetup.aDriver);
        WebElement assessmentStartButton = healthRiskAssesment.getAssessmentStartButton();
        standardFunctions.click(assessmentStartButton);
    }
    @Test(priority = 1,invocationCount = 1)
    // TC2 - If selected
    public void verifyQuestionTitle(){
        try {
            assert (standardFunctions.getText(elementQuestionnaireTitle).toString().equals("Asthma Questionnaire for Beginners"));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
