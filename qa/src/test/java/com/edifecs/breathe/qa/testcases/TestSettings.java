package com.edifecs.breathe.qa.testcases;

import com.edifecs.breathe.qa.functions.Andriodsetup;
import com.edifecs.breathe.qa.functions.ExcelReader;
import com.edifecs.breathe.qa.functions.Utility;
import com.edifecs.breathe.qa.pageobjects.LoginPage;
import com.edifecs.breathe.qa.pageobjects.RightMenu;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by rohikris on 4/1/2016.
 */
public class TestSettings {
    WebDriverWait wait;
    LoginPage loginPage = new LoginPage();
    RightMenu settings = new RightMenu();
    ExcelReader myxls;
    Andriodsetup andriodsetup = new Andriodsetup();

    @BeforeClass
    public void startApp() throws IOException, InterruptedException {
        Utility.loadPropertyFile("config.properties");

        myxls = new ExcelReader();

        andriodsetup.andySetup();
        wait = new WebDriverWait(andriodsetup.aDriver, 5);
        loginPage.setLoginButton(andriodsetup.aDriver);
        loginPage.setPatientemailid(andriodsetup.aDriver);
        loginPage.setPatient_Password(andriodsetup.aDriver);
        loginPage.enter_email_id();
        loginPage.enter_password();
        loginPage.loginButtonClick();
        settings.setRightmenuLink(andriodsetup.aDriver);
        settings.RightmenuLinkClick();
        settings.setSettingsLink(andriodsetup.aDriver);
        settings.SettingsLinkClick();
    }

    @Test(priority = 1)
    public void testmodifyUserName() throws InterruptedException {
        wait = new WebDriverWait(andriodsetup.aDriver, 15);


    }
}
