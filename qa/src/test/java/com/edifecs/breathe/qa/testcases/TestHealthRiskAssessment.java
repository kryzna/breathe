package com.edifecs.breathe.qa.testcases;

import com.edifecs.breathe.qa.functions.Andriodsetup;
import com.edifecs.breathe.qa.functions.Excelreader;
import com.edifecs.breathe.qa.functions.Utility;
import com.edifecs.breathe.qa.pageobjects.HealthRiskAssesment;
import com.edifecs.breathe.qa.pageobjects.LeftMenu;
import com.edifecs.breathe.qa.pageobjects.LoginPage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by rohikris on 3/31/2016.
 */
public class TestHealthRiskAssessment {
    WebDriverWait wait;
    LoginPage loginPage = new LoginPage();
    LeftMenu leftmenu = new LeftMenu();
    HealthRiskAssesment healthassess = new HealthRiskAssesment();
    Excelreader myxls;
    Andriodsetup andriodsetup = new Andriodsetup();

    @BeforeClass
    public void startApp() throws IOException, InterruptedException {
        Utility.loadPropertyFile("config.properties");
        myxls = new Excelreader(System.getProperty("user.dir")+Utility.getValueOf("testExcel"));
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
        healthassess.getleftnav_HealtAssessment(andriodsetup.aDriver);
        healthassess.leftnavHealthAssessmentclick();

    }


    @Test(priority = 1)
    // TC1 - No option selected
    public void verify_ifnothingselected(){
        try {


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test(priority = 2)
    // TC2 - If selected
    public void verify_ifselected(){
        try {


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }



  /*  @AfterClass
    public void teardown() throws IOException {
        andriodsetup.aDriver.quit();
    }
*/
}
