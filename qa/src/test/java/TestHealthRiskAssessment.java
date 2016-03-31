import Functions.AndroidSetup;
import Functions.Utility;
import Functions.Xls_Reader;
import PageObjects.HealthRiskAssesment;
import PageObjects.LeftMenu;
import PageObjects.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
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
    Xls_Reader myxls;
    AndroidSetup androidSetup = new AndroidSetup();

    @BeforeClass
    public void startApp() throws IOException {
        Utility.loadPropertyFile("config.properties");
        myxls = new Xls_Reader(Utility.getValueOf("testExcel"));
        androidSetup.andySetup();
        wait = new WebDriverWait(androidSetup.aDriver, 5);
        loginPage.setLoginButton(androidSetup.aDriver);
        loginPage.setPatientemailid(androidSetup.aDriver);
        loginPage.setPatient_Password(androidSetup.aDriver);
    }

    @Test(priority = 1)
      public void verifyHealthRiskAssessment_yes(){
        loginPage.enter_email_id();
        loginPage.enter_password();
        loginPage.loginButtonClick();
        wait = new WebDriverWait(androidSetup.aDriver, 5);
        leftmenu.getleftnav(androidSetup.aDriver);
        leftmenu.leftnavclick();
        healthassess.getleftnav_HealtAssessment(androidSetup.aDriver);
        healthassess.leftnavHealthAssessmentclick();
        healthassess.getleftnav_HealtAssessment_completed(androidSetup.aDriver);
        healthassess.leftnavHealthAssessment_completed_btnclick();
        healthassess.getleftnav_HealtAssessment_questionnairechoice_yes(androidSetup.aDriver);
        healthassess.leftnav_Healthassessment_choice_yesclick();
        healthassess.getHealtAssessment_questionnairechoice_yes_radio1(androidSetup.aDriver);
        healthassess.getHealtAssessment_questionnairechoice_next(androidSetup.aDriver);
        healthassess.getHealtAssessment_questionnairechoice_yes_radio1(androidSetup.aDriver);
        healthassess.getHealtAssessment_questionnairechoice_next(androidSetup.aDriver);
        healthassess.getHealtAssessment_questionnairechoice_checkbox1(androidSetup.aDriver);
        healthassess.getHealtAssessment_questionnairechoice_next(androidSetup.aDriver);
       // assert(leftmenu.getUserEmail().getText().toString().equals(myxls.getCellData("Sheet1","PatientEmailid",2)));
        }

    @AfterClass
    public void teardown() throws IOException {
        androidSetup.aDriver.quit();
    }

}
