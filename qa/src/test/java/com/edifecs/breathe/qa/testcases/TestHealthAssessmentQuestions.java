package com.edifecs.breathe.qa.testcases;

import com.edifecs.breathe.qa.functions.ExcelReader;
import com.edifecs.breathe.qa.functions.StandardFunctions;
import com.edifecs.breathe.qa.pageobjects.HealthRiskAssesment;
import com.edifecs.breathe.qa.functions.Andriodsetup;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

/**
 * Created by amolverm on 4/11/2016.
 */
public class TestHealthAssessmentQuestions {
    //HealthRiskAssesment healthRiskAssesment = new HealthRiskAssesment();
    public static ExcelReader excelReader = new ExcelReader();
    private static String questionTitle;
    private int expectedAnswerCount,actualAnswerCount;
    private String expectedAnswerText,actualAnswerText;
    public static  int Qweight = 0;
    public static int Aweight = 0;
    private int weightcount = 1;
    public static int riskscore = 0;

    @BeforeClass
    public void setBeforeTest(){
        if(excelReader.getCellData("RiskAssessment","Type",TestHealthRiskAssessment.questionNumber).toString().equals("r")) {
            HealthRiskAssesment.setListAnswerOptions(Andriodsetup.aDriver);
            TestHealthRiskAssessment.listAnswersOptions = HealthRiskAssesment.getListAnswerOptions();
        } else if(excelReader.getCellData("RiskAssessment","Type",TestHealthRiskAssessment.questionNumber).toString().equals("c")) {
            HealthRiskAssesment.setListCheckboxAnswerOptions(Andriodsetup.aDriver);
            TestHealthRiskAssessment.listAnswersOptions = HealthRiskAssesment.getListCheckboxAnswerOptions();
        }
    }

    @Test(priority = 1)
    // TC2 - If selected
    public static void verifyQuestionTitle(){
        try {
            Qweight = Integer.parseInt(excelReader.getCellData("RiskAssessment", "Qweight", TestHealthRiskAssessment.questionNumber));
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

    @Test(priority = 2)
    // TC1 - No option selected
    public void verifyAnswerOptionsCount(){
        try {
            expectedAnswerCount = excelReader.getColumnCount("RiskAssessment", TestHealthRiskAssessment.questionNumber)/2;
            System.out.println(expectedAnswerCount);
            actualAnswerCount = TestHealthRiskAssessment.listAnswersOptions.size();
            assert (actualAnswerCount==expectedAnswerCount);
        }
        catch(Exception e)
        {
            System.out.println("Expected: "+expectedAnswerCount+" and Actual: "+actualAnswerCount);
            e.printStackTrace();
        }
    }

    @Test(priority = 3)
    public void verifyAnswersText(){
        int i;
        try{
            for(i=1;i<=5;i++) {
                expectedAnswerText=excelReader.getCellData("RiskAssessment", "choice"+i, TestHealthRiskAssessment.questionNumber);
                actualAnswerText=TestHealthRiskAssessment.listAnswersOptions.get(i-1).getText().toString();
                assert (TestHealthRiskAssessment.listAnswersOptions.get(i-1).getText().toString().equals(excelReader.getCellData("RiskAssessment", "choice"+i, TestHealthRiskAssessment.questionNumber)));
            }
        }   catch (Exception e){
            System.out.println("Expected: "+expectedAnswerText+" and Actual: "+actualAnswerText);
            e.printStackTrace();
        }
    }

    @Test(priority = 4)
    public void verifyRadioAnswerSelection(){
        int radioSelectionNumber = 2;
        TestHealthRiskAssessment.listAnswersOptions.get(radioSelectionNumber-1).click();
        Aweight = Integer.parseInt(excelReader.getCellData("RiskAssessment", "cweight"+weightcount, TestHealthRiskAssessment.questionNumber));
        assert (TestHealthRiskAssessment.listAnswersOptions.get(radioSelectionNumber-1).getAttribute("checked").toString().equals("true"));
    }
    public static int riskcalculation()
    {
        riskscore = Qweight*Aweight;
        return riskscore;
    }
    @AfterClass
    public void moveToNextQuestion(){
      WebElement elementNextButton =   HealthRiskAssesment.getElementNextButton();
        StandardFunctions.click(elementNextButton);
        TestHealthRiskAssessment.questionNumber=TestHealthRiskAssessment.questionNumber+1;
        weightcount++;
    }

   /* @Factory
    public Object[] create(){
        return new Object[]{
                new TestHealthAssessmentQuestions(), new TestHealthAssessmentQuestions(), new TestHealthAssessmentQuestions()
        };
    }*/

}

