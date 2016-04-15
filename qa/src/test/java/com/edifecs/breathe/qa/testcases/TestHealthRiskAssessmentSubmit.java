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
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by rohikris on 3/31/2016.
 */
public class TestHealthRiskAssessmentSubmit {
    Date dt = new Date();
    HealthRiskAssesment healthRiskAssesment = new HealthRiskAssesment();
    ExcelReader myxls;
    @BeforeClass
    public void startscore() throws IOException, InterruptedException {
        Utility.loadPropertyFile("config.properties");
        myxls = new ExcelReader();
    }

    @Test(priority = 1)
    public void verifyscore(){
        try {
            System.out.println("------------Veirfying score-----------------------");

            System.out.println("Expected: ---"+TestStartHealthRiskAssessment.riskscorefinal);
            healthRiskAssesment.setQuestionairescore(Andriodsetup.aDriver);
            healthRiskAssesment.getQuestionairescore();
            System.out.println("Actual ---"+healthRiskAssesment.getQuestionairescore());
            Assert.assertEquals(healthRiskAssesment.getQuestionairescore(),TestStartHealthRiskAssessment.riskscorefinal);
        }
        catch(Exception e)
        {

            e.printStackTrace();
        }
    }
    @Test(priority = 2)
    public void verifybuttonstatus(){
        try {
            System.out.println("------------Veirfying Button Status-----------------------");
            healthRiskAssesment.setAssessmentStartButton(Andriodsetup.aDriver);
            System.out.println(healthRiskAssesment.getAssessmentStartButtonText());
            System.out.println("Updated date===="+Utility.getFormattedDate("MM.dd.yyyy"));
            Assert.assertEquals("Completed "+Utility.getFormattedDate("MM.dd.yyyy"),healthRiskAssesment.getAssessmentStartButtonText());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    @Test(priority = 3)
    public void verifyQuestionairetitle() {
        try {
            System.out.println("------------Veirfying Questionaire Title-----------------------");
            healthRiskAssesment.setElementQuestionnaireTitle(Andriodsetup.aDriver);
            StandardFunctions.getText(healthRiskAssesment.getElementQuestionnaireTitle());
            Assert.assertEquals(StandardFunctions.getText(healthRiskAssesment.getElementQuestionnaireTitle()),"Asthma Questionnaire for Beginners");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @AfterClass
    public void nextScenario(){
        TestStartHealthRiskAssessment.scenarioNumber=TestStartHealthRiskAssessment.scenarioNumber+1;
    }
}
