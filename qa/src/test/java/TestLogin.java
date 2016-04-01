import Functions.AndroidSetup;
import Functions.Utility;
import Functions.Xls_Reader;
import PageObjects.LeftMenu;
import PageObjects.LoginPage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Properties;

/**
 * Created by amolverm on 3/29/2016.
 */
public class TestLogin {
    WebDriverWait wait;
    LoginPage loginPage = new LoginPage();
    LeftMenu leftmenu = new LeftMenu();
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
/*
    // This test case is to check if blank username and password were submitted
    @Test(priority = 1)
    public void testBlankLogin() throws InterruptedException {
        assert(loginPage.Patient_loginButton.getText().equals("Login"));
        loginPage.enter_blankemail_id();
        loginPage.enter_blankpassword();
        loginPage.loginButtonClick();
        wait = new WebDriverWait(androidSetup.aDriver, 5);
        // assert for message if user pass blank credentails
    }

    // This test case is to check if blank username was submitted
    @Test(priority = 1)
    public void testBlankUsernameLogin() throws InterruptedException {
        assert(loginPage.Patient_loginButton.getText().equals("Login"));
        loginPage.enter_blankemail_id();
        loginPage.enter_password();
        loginPage.loginButtonClick();
        wait = new WebDriverWait(androidSetup.aDriver, 5);
        // assert for message if user pass blank username
    }

    // This test case is to check if blank password was submitted
    @Test(priority = 1)
    public void testBlankPasswordLogin() throws InterruptedException {
        assert(loginPage.Patient_loginButton.getText().equals("Login"));
        loginPage.enter_email_id();
        loginPage.enter_blankpassword();
        loginPage.loginButtonClick();
        wait = new WebDriverWait(androidSetup.aDriver, 5);
        // assert for message if user pass blank password
    }
*/
    // This test case is to check if valid credentails were submitted
    @Test(priority = 1)
    public void testValidLogin() throws InterruptedException {
        assert(loginPage.Patient_loginButton.getText().equals("Login"));
        loginPage.enter_email_id();
        loginPage.enter_password();
        loginPage.loginButtonClick();
        wait = new WebDriverWait(androidSetup.aDriver, 5);
       // androidSetup.aDriver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='Open navigation drawer']")).click();
        leftmenu.getleftnav(androidSetup.aDriver);
        leftmenu.leftnavclick();
    }

    // This test case is to check if correct username got displayed or not
    @Test(priority = 2)
    public void verifyUserName(){
        leftmenu.setUserName(androidSetup.aDriver);
        assert(leftmenu.getUserName().getText().toString().equals(myxls.getCellData("UserData","UserName",2)));
        wait = new WebDriverWait(androidSetup.aDriver, 5);
    }

    // This test case is to check if correct emailid got displayed or not
    @Test(priority = 3)
    public void verifyUserEmail(){
        leftmenu.setUserEmail(androidSetup.aDriver);
        assert(leftmenu.getUserEmail().getText().toString().equals(myxls.getCellData("UserData","PatientEmailid",2)));
    }

    // -------------Need to add test cases for autologin functionality-----------------------------

   /*  @AfterClass
    public void teardown() throws IOException {
        androidSetup.aDriver.quit();
    }
*/

}
