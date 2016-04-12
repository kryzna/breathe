package com.edifecs.breathe.qa.testcases;

import com.edifecs.breathe.qa.functions.ExcelReader;
import com.edifecs.breathe.qa.functions.StandardFunctions;
import com.edifecs.breathe.qa.pageobjects.HealthRiskAssesment;
import com.edifecs.breathe.qa.functions.Andriodsetup;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/**
 * Created by amolverm on 4/11/2016.
 */
public class TestHealthAssessmentQuestions {
    HealthRiskAssesment healthRiskAssesment = new HealthRiskAssesment();
    ExcelReader excelReader = new ExcelReader();
    private String questionTitle;

    @Test(priority = 1,invocationCount = 1)
    // TC2 - If selected
    public void verifyQuestionTitle(){
        try {
            questionTitle=excelReader.getCellData("RiskAssessment","Title",TestHealthRiskAssessment.questionNumber);
            System.out.println(questionTitle);
            System.out.println(StandardFunctions.getText(TestHealthRiskAssessment.elementQuestionTitle));
            assert (StandardFunctions.getText(TestHealthRiskAssessment.elementQuestionTitle).toString().equals(questionTitle));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test(priority = 2,invocationCount = 1)
    // TC1 - No option selected
    public void verifyAnswerOptionsCount(){
        try {
            int answersCount = excelReader.getColumnCount("RiskAssessment", TestHealthRiskAssessment.questionNumber);
            System.out.println(answersCount);
            assert (TestHealthRiskAssessment.listAnswersOptions.size()==5);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        WebElement elementNextButton =   HealthRiskAssesment.getElementNextButton();
        StandardFunctions.click(elementNextButton);
        TestHealthRiskAssessment.questionNumber=TestHealthRiskAssessment.questionNumber+1;
    }

    @Factory
    public Object[] create(){
        return new Object[]{
                new TestHealthAssessmentQuestions(), new TestHealthAssessmentQuestions(), new TestHealthAssessmentQuestions()
        };
    }

}
