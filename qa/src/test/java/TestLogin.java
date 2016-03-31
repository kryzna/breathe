import Functions.AndroidSetup;
import Functions.Utility;
import Functions.Xls_Reader;
import PageObjects.LeftMenu;
import PageObjects.LoginPage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
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

    @Test(priority = 1)
    public void testLogin() throws InterruptedException {
        assert(loginPage.Patient_loginButton.getText().equals("Login"));
        loginPage.enter_email_id();
        loginPage.enter_password();
        loginPage.loginButtonClick();
        wait = new WebDriverWait(androidSetup.aDriver, 5);
       // androidSetup.aDriver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='Open navigation drawer']")).click();
        leftmenu.getleftnav(androidSetup.aDriver);
        leftmenu.leftnavclick();
    }

    @Test(priority = 2)
    public void verifyUserName(){
        leftmenu.setUserName(androidSetup.aDriver);
        assert(leftmenu.getUserName().getText().toString().equals(myxls.getCellData("Sheet1","UserName",2)));
        wait = new WebDriverWait(androidSetup.aDriver, 5);
    }

    @Test(priority = 3)
    public void verifyUserEmail(){
        leftmenu.setUserEmail(androidSetup.aDriver);
        assert(leftmenu.getUserEmail().getText().toString().equals(myxls.getCellData("Sheet1","PatientEmailid",2)));
    }
    @Test(priority = 4)
    public void verifyHealthRiskAssessment(){
        androidSetup.aDriver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id='com.rgp.breathe:id/nav_view']/android.support.v7.widget.RecyclerView//android.widget.CheckedTextView[contains(@text,'Health Risk Assesment')]")).click();
        String assesmentTitle = androidSetup.aDriver.findElement(By.xpath("//android.widget.FrameLayut[@resource-id='com.rgp.breathe:id/containerView']/android.widget.RelativeLayout[@index='0']/android.widget.LinearLayout[@index='0']/android.widget.TextView[@index='1')]")).getText().toString();
        System.out.println(assesmentTitle);
        assert (assesmentTitle.equals("Average (6.1 of 10)"));
    }
}
