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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rohikris on 3/31/2016.
 */
public class TestHealthRiskAssessmentSubmit {
    HealthRiskAssesment healthRiskAssesment = new HealthRiskAssesment();
    double myscore = TestHealthAssessmentQuestions.riskscorefinal;

    @BeforeClass
    public void startscore() throws IOException, InterruptedException {
        healthRiskAssesment.setQuestionairescore(Andriodsetup.aDriver);
        healthRiskAssesment.getQuestionairescore();
    }


    @Test(priority = 1,invocationCount = 1)
    public void verifyscore(){
        try {
            System.out.println("verifying score");
            System.out.println(healthRiskAssesment.getQuestionairescore());
            System.out.println(myscore);
            Assert.assertEquals(healthRiskAssesment.getQuestionairescore(),myscore);
            System.out.println("Score Matched");
        }
        catch(Exception e)
        {
            System.out.println("Score not Matched");
            e.printStackTrace();
        }
    }
}
