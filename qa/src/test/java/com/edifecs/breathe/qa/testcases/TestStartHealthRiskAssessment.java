package com.edifecs.breathe.qa.testcases;

import com.edifecs.breathe.qa.functions.StandardFunctions;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

/**
 * Created by amolverm on 4/14/2016.
 */
public class TestStartHealthRiskAssessment {
    StandardFunctions standardFunctions = new StandardFunctions();
    public static WebElement elementQuestionTitle,elementQuestionnaireTitle;
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
