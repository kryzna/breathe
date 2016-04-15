package com.edifecs.breathe.qa.testcases;

import com.edifecs.breathe.qa.functions.ExcelReader;
import com.edifecs.breathe.qa.functions.StandardFunctions;
import com.edifecs.breathe.qa.pageobjects.HealthRiskAssesment;
import com.edifecs.breathe.qa.functions.Andriodsetup;
import org.apache.tools.ant.taskdefs.condition.And;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Created by amolverm on 4/11/2016.
 */
public class TestHealthAssessmentQuestions {
    HealthRiskAssesment healthRiskAssesment = new HealthRiskAssesment();
    public static WebElement elementQuestionTitle;
    public static ExcelReader excelReader = new ExcelReader();
    private static String questionTitle;
    private int expectedAnswerCount,actualAnswerCount;
    private String expectedAnswerText,actualAnswerText;

    public static  double Qweight = 0;
    public static double Aweight = 0;
    private int weightcount = 0;
    public static int riskscore = 0;

    @BeforeClass
    public void setBeforeTest(){
        healthRiskAssesment.setElementQuestionTitle(Andriodsetup.aDriver);
        elementQuestionTitle = healthRiskAssesment.getElementQuestionTitle();
        if(excelReader.getCellData("RiskAssessment","Type",TestStartHealthRiskAssessment.questionNumber).toString().equals("r")) {
            HealthRiskAssesment.setListAnswerOptions(Andriodsetup.aDriver);
            TestHealthRiskAssessment.listAnswersOptions = HealthRiskAssesment.getListAnswerOptions();
        } else if(excelReader.getCellData("RiskAssessment","Type",TestStartHealthRiskAssessment.questionNumber).toString().equals("c")) {
            HealthRiskAssesment.setListCheckboxAnswerOptions(Andriodsetup.aDriver);
            TestHealthRiskAssessment.listAnswersOptions = HealthRiskAssesment.getListCheckboxAnswerOptions();
        }
        healthRiskAssesment.setElementNextButton(Andriodsetup.aDriver);
        healthRiskAssesment.setElementQuestionnaireTitle(Andriodsetup.aDriver);
    }

    //checking the title of the question
    @Test(priority = 1)
    public static void verifyQuestionTitle() throws InterruptedException {
        try {
            Qweight = Double.parseDouble(excelReader.getCellData("RiskAssessment", "Qweight", TestStartHealthRiskAssessment.questionNumber));
            questionTitle=excelReader.getCellData("RiskAssessment","Title",TestStartHealthRiskAssessment.questionNumber);
            System.out.println(questionTitle);
            System.out.println(StandardFunctions.getText(elementQuestionTitle).toString());
            Assert.assertEquals(StandardFunctions.getText(elementQuestionTitle).toString(),questionTitle);
            //assert (StandardFunctions.getText(TestHealthRiskAssessment.elementQuestionTitle).toString().equals(questionTitle));
        }
        catch(Exception e)
        {
            e.printStackTrace();

        }
    }

    //Checking the number of answer options for a question
    @Test(priority = 2)
    public void verifyAnswerOptionsCount() throws InterruptedException {
        try {
            expectedAnswerCount = excelReader.getColumnCount("RiskAssessment", TestStartHealthRiskAssessment.questionNumber-1)/2;
            System.out.println(expectedAnswerCount);
            actualAnswerCount = TestHealthRiskAssessment.listAnswersOptions.size();
            Assert.assertEquals(actualAnswerCount,expectedAnswerCount);

        }
        catch(Exception e)
        {
            System.out.println("Expected: "+expectedAnswerCount+" and Actual: "+actualAnswerCount);
            e.printStackTrace();

        }
    }

    //checking for all the answer options text for the question
    @Test(priority = 3)
    public void verifyAnswersText() throws InterruptedException {
        int i;
        try{
            for(i=1;i<=expectedAnswerCount;i++) {
                expectedAnswerText=excelReader.getCellData("RiskAssessment", "choice"+i, TestStartHealthRiskAssessment.questionNumber);
                actualAnswerText=TestHealthRiskAssessment.listAnswersOptions.get(i-1).getText().toString();
                Assert.assertEquals(TestHealthRiskAssessment.listAnswersOptions.get(i-1).getText().toString(),excelReader.getCellData("RiskAssessment", "choice"+i, TestStartHealthRiskAssessment.questionNumber));

            }
        }   catch (Exception e){
            System.out.println("Expected: "+expectedAnswerText+" and Actual: "+actualAnswerText);
            e.printStackTrace();

        }
    }

    //checking weather the answer is getting selected or not
    @Test(priority = 4)
    public void verifyAnswerSelection(){
        String questionType = excelReader.getCellData("RiskAssessment", "Type", TestStartHealthRiskAssessment.questionNumber).toString();
        System.out.println(questionType);
        String checkboxSelectionNumbers;
        if(questionType.equals("c")){
            System.out.println("It's a checkbox based question:");
            for(int i=0;i<expectedAnswerCount;i++) {
                if(TestHealthRiskAssessment.listAnswersOptions.get(i).getAttribute("checked").toString().equals("true")){
                    TestHealthRiskAssessment.listAnswersOptions.get(i).click();
                }
            }
            checkboxSelectionNumbers = excelReader.getCellData("RiskScenarios", "scenario" + TestStartHealthRiskAssessment.scenarioNumber, TestStartHealthRiskAssessment.questionNumber + 1);
            System.out.println(checkboxSelectionNumbers);
            String[] checkboxSelectionNumber = checkboxSelectionNumbers.split(",");
            System.out.println(checkboxSelectionNumber.length);
            if(checkboxSelectionNumber.length<2){
                if(Double.valueOf(checkboxSelectionNumber[0]).intValue()>0) {
                    TestHealthRiskAssessment.listAnswersOptions.get(Double.valueOf(checkboxSelectionNumber[0]).intValue() - 1).click();
                    Aweight = Double.parseDouble(excelReader.getCellData("RiskAssessment", "cweight" + weightcount, TestStartHealthRiskAssessment.questionNumber));
                    //Assert.assertEquals(TestHealthRiskAssessment.listAnswersOptions.get(Double.valueOf(checkboxSelectionNumber[0]).intValue() - 1).getAttribute("checked").toString(), "true");
                }
            } else if(checkboxSelectionNumber.length>1) {
                for (int i = 0; i < checkboxSelectionNumber.length; i++) {
                    TestHealthRiskAssessment.listAnswersOptions.get(Integer.parseInt(checkboxSelectionNumber[i]) - 1).click();

                    Aweight = Aweight + Double.parseDouble(excelReader.getCellData("RiskAssessment", "cweight" + weightcount, TestStartHealthRiskAssessment.questionNumber));
                }
            }
        } else if(questionType.equals("r")) {
            int radioSelectionNumber = 0;
            try {
                radioSelectionNumber = Double.valueOf(excelReader.getCellData("RiskScenarios", "scenario" + TestStartHealthRiskAssessment.scenarioNumber, TestStartHealthRiskAssessment.questionNumber + 1)).intValue();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Selection: " + radioSelectionNumber);
            if (radioSelectionNumber > 0) {
                weightcount = radioSelectionNumber;
                TestHealthRiskAssessment.listAnswersOptions.get(radioSelectionNumber - 1).click();
                Aweight = Double.parseDouble(excelReader.getCellData("RiskAssessment", "cweight" + weightcount, TestStartHealthRiskAssessment.questionNumber));
                Assert.assertEquals(TestHealthRiskAssessment.listAnswersOptions.get(radioSelectionNumber - 1).getAttribute("checked").toString(), "true");
            }
        }
    }

    @Test(priority = 5)
    public void verifySubmitButton(){
        int currentQuestionNumber = TestStartHealthRiskAssessment.questionNumber-1;
        int totalQuestions = excelReader.getRowCount("RiskAssessment")-1;
        if(currentQuestionNumber==totalQuestions){
            WebElement elementSubmitButton =   HealthRiskAssesment.getElementNextButton();
            Assert.assertEquals(elementSubmitButton.getText().toString(),"SUBMIT","Submit button not present");
        }
    }

    @Test(priority = 6)
    public void verifyPreviousButton() throws InterruptedException {
        int currentQuestionNumber = TestStartHealthRiskAssessment.questionNumber-1;
        int totalQuestions = excelReader.getRowCount("RiskAssessment")-1;
        if(currentQuestionNumber>1 && currentQuestionNumber<=totalQuestions){
            healthRiskAssesment.setElementPreviousButton(Andriodsetup.aDriver);
            WebElement elementPreviousButton =   HealthRiskAssesment.getElementPreviousButton();
            Assert.assertEquals(elementPreviousButton.getText().toString(),"PREVIOUS","Previous button not present");
        }
    }

    @AfterClass
    public void moveToNextQuestion(){
        WebElement elementNextButton =   HealthRiskAssesment.getElementNextButton();
        StandardFunctions.click(elementNextButton);
        TestStartHealthRiskAssessment.questionNumber=TestStartHealthRiskAssessment.questionNumber+1;
       // weightcount++;
        riskscore = (int) (Qweight*Aweight);
        System.out.println(riskscore);
        TestStartHealthRiskAssessment.riskscorefinal = TestStartHealthRiskAssessment.riskscorefinal+riskscore;
        System.out.println(TestStartHealthRiskAssessment.riskscorefinal);
    }

   /* @Factory
    public Object[] create(){
        return new Object[]{
                new TestHealthAssessmentQuestions(), new TestHealthAssessmentQuestions(), new TestHealthAssessmentQuestions()
        };
    }*/

}

