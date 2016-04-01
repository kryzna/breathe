import Functions.AndroidSetup;
import Functions.Utility;
import Functions.Xls_Reader;
import PageObjects.LoginPage;
import PageObjects.RightMenu;
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
        loginPage.enter_email_id();
        loginPage.enter_password();
        loginPage.loginButtonClick();
        settings.setRightmenuLink(androidSetup.aDriver);
        settings.RightmenuLinkClick();
        settings.setSettingsLink(androidSetup.aDriver);
        settings.SettingsLinkClick();
    }

    @Test(priority = 1)
    public void testmodifyUserName() throws InterruptedException {
        wait = new WebDriverWait(androidSetup.aDriver, 15);


    }
}
