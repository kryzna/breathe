package com.edifecs.breathe.qa.testcases;

import com.edifecs.breathe.qa.functions.ExcelReader;
import com.edifecs.breathe.qa.functions.StandardFunctions;
import com.edifecs.breathe.qa.pageobjects.HealthRiskAssesment;
import com.edifecs.breathe.qa.functions.Andriodsetup;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
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
    public static  double Qweight = 0;
    public static double Aweight = 0;
    private int weightcount = 1;
    public static double riskscore = 0.0;
    public static double riskscorefinal = 0.0;

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

    //checking the title of the question
    @Test(priority = 1)
    public static void verifyQuestionTitle(){
        try {
            Qweight = Double.parseDouble(excelReader.getCellData("RiskAssessment", "Qweight", TestHealthRiskAssessment.questionNumber));
            questionTitle=excelReader.getCellData("RiskAssessment","Title",TestHealthRiskAssessment.questionNumber);
            System.out.println(questionTitle);
            System.out.println(StandardFunctions.getText(TestHealthRiskAssessment.elementQuestionTitle));
            Assert.assertEquals(StandardFunctions.getText(TestHealthRiskAssessment.elementQuestionTitle).toString(),questionTitle);
            //assert (StandardFunctions.getText(TestHealthRiskAssessment.elementQuestionTitle).toString().equals(questionTitle));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    //Checking the number of answer options for a question
    @Test(priority = 2)
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

    //checking for all the answer options text for the question
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

    //checking weather the answer is getting selected or not
    @Test(priority = 4)
    public void verifyAnswerSelection(){
        int selectionNumber = 2;
        TestHealthRiskAssessment.listAnswersOptions.get(selectionNumber-1).click();
        Aweight = Double.parseDouble(excelReader.getCellData("RiskAssessment", "cweight"+weightcount, TestHealthRiskAssessment.questionNumber));
        assert (TestHealthRiskAssessment.listAnswersOptions.get(selectionNumber-1).getAttribute("checked").toString().equals("true"));
    }

    @AfterClass
    public void moveToNextQuestion(){
      WebElement elementNextButton =   HealthRiskAssesment.getElementNextButton();
        StandardFunctions.click(elementNextButton);
        TestHealthRiskAssessment.questionNumber=TestHealthRiskAssessment.questionNumber+1;
        weightcount++;
        riskscore = Qweight*Aweight;
        riskscorefinal = riskscorefinal+riskscore;
    }

   /* @Factory
    public Object[] create(){
        return new Object[]{
                new TestHealthAssessmentQuestions(), new TestHealthAssessmentQuestions(), new TestHealthAssessmentQuestions()
        };
    }*/

}

