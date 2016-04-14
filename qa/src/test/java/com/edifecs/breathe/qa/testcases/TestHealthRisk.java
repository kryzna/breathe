package com.edifecs.breathe.qa.testcases;

import com.edifecs.breathe.qa.functions.Andriodsetup;
import com.edifecs.breathe.qa.functions.ExcelReader;
import com.edifecs.breathe.qa.functions.ExcelReader;
import com.edifecs.breathe.qa.functions.Utility;
import com.edifecs.breathe.qa.pageobjects.HealthRiskAssesment;
import com.edifecs.breathe.qa.pageobjects.LeftMenu;
import com.edifecs.breathe.qa.pageobjects.LoginPage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by rohikris on 3/31/2016.
 */
public class TestHealthRisk {
    WebDriverWait wait;
    LoginPage loginPage = new LoginPage();
    LeftMenu leftmenu = new LeftMenu();
    HealthRiskAssesment healthassess = new HealthRiskAssesment();
    ExcelReader myxls;
    Andriodsetup andriodsetup = new Andriodsetup();

    @BeforeClass
    public void startApp() throws IOException, InterruptedException {
        Utility.loadPropertyFile("config.properties");
      //  myxls = new ExcelReader(System.getProperty("user.dir") + Utility.getValueOf("testExcel"));
        // Need to commented if run through testng TestSuite
        //andriodsetup.andySetup();
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
        healthassess.getleftnav_HealtAssessment(andriodsetup.aDriver);
        healthassess.leftnavHealthAssessmentclick();
      //  healthassess.gettext_HealtAssessment_completed((andriodsetup.aDriver));
      //  healthassess.leftnavHealthAssessment_completed_btnclick();

    }


    @Test(priority = 1)
    // Complete Survey Submit (Question weight > 0 Answer Weight > 0)
    public void verify_ifqagreaterthan0() {
        try {

            Assert.assertEquals("3",myxls.getCellData("RiskAssessment", "Riskc1", 5));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 2)
    // Complete Survey Submit (Question weight < 0 Answer Weight < 0)
    public void verify_ifqalessthan0() {
        try {

            Assert.assertEquals("3",myxls.getCellData("RiskAssessment", "Riskc1", 5));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 3)
    // Complete Survey Submit (Question weight = 0 Answer Weight = 0)
    public void verify_ifqaequalto0() {
        try {

            Assert.assertEquals("3",myxls.getCellData("RiskAssessment", "Riskc1", 5));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 4)
    // Complete Survey Submit (Question weight = 0 Answer Weight < 0)
    public void verify_ifqequalto0alessthan0() {
        try {

            Assert.assertEquals("3",myxls.getCellData("RiskAssessment", "Riskc1", 5));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 5)
    // Complete Survey Submit (Question weight = 0 Answer Weight > 0)
    public void verify_ifqequalto0agreaterthan0() {
        try {

            Assert.assertEquals("3",myxls.getCellData("RiskAssessment", "Riskc1", 5));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 6)
    // Complete Survey Submit (Question weight > 0 Answer Weight = 0)
    public void verify_ifqgreaterthan0aequalto0() {
        try {

            Assert.assertEquals("3",myxls.getCellData("RiskAssessment", "Riskc1", 5));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 7)
    // Complete Survey Submit (Question weight < 0 Answer Weight = 0)
    public void verify_ifqalessthan0aequalto0() {
        try {

            Assert.assertEquals("3",myxls.getCellData("RiskAssessment", "Riskc1", 5));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /*  @AfterClass
    public void teardown() throws IOException {
        andriodsetup.aDriver.quit();
    }
    */
}
