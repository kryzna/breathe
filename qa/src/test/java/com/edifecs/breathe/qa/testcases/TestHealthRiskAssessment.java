package com.edifecs.breathe.qa.testcases;

import com.edifecs.breathe.qa.functions.Andriodsetup;
import com.edifecs.breathe.qa.functions.ExcelReader;
import com.edifecs.breathe.qa.functions.StandardFunctions;
import com.edifecs.breathe.qa.functions.Utility;
import com.edifecs.breathe.qa.pageobjects.HealthRiskAssesment;
import com.edifecs.breathe.qa.pageobjects.LeftMenu;
import com.edifecs.breathe.qa.pageobjects.LoginPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rohikris on 3/31/2016.
 */
public class TestHealthRiskAssessment {
    WebDriverWait wait;
    LoginPage loginPage = new LoginPage();
    LeftMenu leftmenu = new LeftMenu();
    HealthRiskAssesment healthRiskAssesment = new HealthRiskAssesment();
    ExcelReader myxls;
    Andriodsetup andriodsetup = new Andriodsetup();
    StandardFunctions standardFunctions = new StandardFunctions();
    public static List<WebElement> listAnswersOptions = new ArrayList<WebElement>();
    public static WebElement elementQuestionnaireTitle;
    int questionsCount;
    //public static int questionNumber=2;

    @BeforeClass
    public void startApp() throws IOException, InterruptedException {
        Utility.loadPropertyFile("config.properties");

        myxls = new ExcelReader();
      // Need to commented if run through testng TestSuite
        andriodsetup.andySetup();
        loginPage.setLoginButton(andriodsetup.aDriver);
        loginPage.setPatientemailid(andriodsetup.aDriver);
        loginPage.setPatient_Password(andriodsetup.aDriver);
        loginPage.enter_email_id();
        loginPage.enter_password();
        loginPage.loginButtonClick();
        wait = new WebDriverWait(andriodsetup.aDriver, 5);
        leftmenu.getleftnav(andriodsetup.aDriver);
        leftmenu.leftnavclick();

        // Need to open Health Assessment link

        healthRiskAssesment.getleftnav_HealtAssessment(andriodsetup.aDriver);
        healthRiskAssesment.leftnavHealthAssessmentclick();
        Thread.sleep(3000);

        //System.out.println(listAnswersOptions.size());
        questionsCount = myxls.getRowCount("RiskAssessment");
        elementQuestionnaireTitle = healthRiskAssesment.getElementQuestionnaireTitle();
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

    @Test(priority = 2)
    public void verifybuttonstatus(){
        try {
            System.out.println("------------Veirfying Button Status Initially-----------------------");
            healthRiskAssesment.setAssessmentStartButton(Andriodsetup.aDriver);
            System.out.println(healthRiskAssesment.getAssessmentStartButtonText());
            System.out.println("Updated date===="+Utility.getFormattedDate("MM.dd.yyyy"));
            Assert.assertEquals(healthRiskAssesment.getAssessmentStartButtonText(),"Not Completed");

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    /*
    @Test(priority = 2,invocationCount = 1)
    // TC1 - No option selected
    public void verifyAnswerOptionsCount(){
        try {
            assert (listAnswersOptions.size()==5);
        }
        catch(AssertionError e)
        {

    @Test(priority = 1)
    // TC1 - No option selected
    public void verify_ifnothingselected() {
        try {


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 2)
    // TC2 - If selected
    public void verify_ifselected() {
        try {


        } catch (Exception e) {

            e.printStackTrace();
        }
        standardFunctions.click(healthRiskAssesment.getElementNextButton());
    }

    @Factory
    public Object[] create(){
        return new Object[]{
                new TestHealthRiskAssessment(), new TestHealthRiskAssessment()
        };
    }*/

    /*  @AfterClass
    public void teardown() throws IOException {
        andriodsetup.aDriver.quit();
    }
    */
}
