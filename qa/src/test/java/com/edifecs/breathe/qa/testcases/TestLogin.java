package com.edifecs.breathe.qa.testcases;

import com.edifecs.breathe.qa.functions.Andriodsetup;
import com.edifecs.breathe.qa.functions.Appiumserver;
import com.edifecs.breathe.qa.functions.Utility;
import com.edifecs.breathe.qa.functions.Excelreader;
import com.edifecs.breathe.qa.pageobjects.LeftMenu;
import com.edifecs.breathe.qa.pageobjects.LoginPage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by amolverm on 3/29/2016.
 */
public class TestLogin {
    WebDriverWait wait;
    LoginPage loginPage = new LoginPage();
    LeftMenu leftmenu = new LeftMenu();
    Excelreader myxls;
    Andriodsetup andriodsetup = new Andriodsetup();

    @BeforeClass
    public void startApp() throws IOException, InterruptedException {
        System.out.println(System.getProperty("user.dir"));
        Utility.loadPropertyFile("config.properties");
        myxls = new Excelreader(System.getProperty("user.dir") + Utility.getValueOf("testExcel"));
        andriodsetup.andySetup();
        wait = new WebDriverWait(andriodsetup.aDriver, 5);
        loginPage.setLoginButton(andriodsetup.aDriver);
        loginPage.setPatientemailid(andriodsetup.aDriver);
        loginPage.setPatient_Password(andriodsetup.aDriver);
    }

    /*
    // This test case is to check if blank username and password were submitted
    @Test(priority = 1)
    public void testBlankLogin() throws InterruptedException {
        assert(loginPage.Patient_loginButton.getText().equals("Login"));
        loginPage.enter_blankemail_id();
        loginPage.enter_blankpassword();
        loginPage.loginButtonClick();
        wait = new WebDriverWait(andriodsetup.aDriver, 5);
        // assert for message if user pass blank credentails
    }
    
    // This test case is to check if blank username was submitted
    @Test(priority = 1)
    public void testBlankUsernameLogin() throws InterruptedException {
        assert(loginPage.Patient_loginButton.getText().equals("Login"));
        loginPage.enter_blankemail_id();
        loginPage.enter_password();
        loginPage.loginButtonClick();
        wait = new WebDriverWait(andriodsetup.aDriver, 5);
        // assert for message if user pass blank username
    }
    
    // This test case is to check if blank password was submitted
    @Test(priority = 1)
    public void testBlankPasswordLogin() throws InterruptedException {
        assert(loginPage.Patient_loginButton.getText().equals("Login"));
        loginPage.enter_email_id();
        loginPage.enter_blankpassword();
        loginPage.loginButtonClick();
        wait = new WebDriverWait(andriodsetup.aDriver, 5);
        // assert for message if user pass blank password
    }
    */
    // This test case is to check if valid credentails were submitted
    @Test(priority = 1)
    public void testValidLogin() throws InterruptedException {
        try {
            assert (loginPage.Patient_loginButton.getText().equals("Login"));
            loginPage.enter_email_id();
            loginPage.enter_password();
            loginPage.loginButtonClick();
            wait = new WebDriverWait(andriodsetup.aDriver, 5);
            // andriodsetup.aDriver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='Open navigation drawer']")).click();
            leftmenu.getleftnav(andriodsetup.aDriver);
            leftmenu.leftnavclick();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // This test case is to check if correct username got displayed or not
    @Test(priority = 2)
    public void verifyUserName() {
        try {
            leftmenu.setUserName(andriodsetup.aDriver);
            assert (leftmenu.getUserName().getText().toString()
                    .equals(myxls.getCellData("UserData", "UserName", 2)));
            wait = new WebDriverWait(andriodsetup.aDriver, 5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // This test case is to check if correct emailid got displayed or not
    @Test(priority = 3)
    public void verifyUserEmail() {
        try {
            leftmenu.setUserEmail(andriodsetup.aDriver);
            assert (leftmenu.getUserEmail().getText().toString()
                    .equals(myxls.getCellData("UserData", "PatientEmailid", 2)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // -------------Need to add test cases for autologin functionality-----------------------------

    @AfterClass
    public void teardown() throws IOException {
        andriodsetup.aDriver.quit();
        Appiumserver.stopAppiumServer();


    }


}
