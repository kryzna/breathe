import Functions.AndroidSetup;
import PageObjects.LeftMenu;
import PageObjects.LoginPage;
import PageObjects.Tracking;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created by amolverm on 3/31/2016.
 */
public class TestTracking {
    AndroidSetup androidSetup = new AndroidSetup();
    LoginPage loginPage = new LoginPage();
    LeftMenu leftmenu = new LeftMenu();
    Tracking tracking = new Tracking();
    List<WebElement> trackingsList,trackingsRowsListDateTime,trackingsRowsListPeakValue;
    @BeforeClass
    public void startApp() throws IOException {
        // This need to be commented if run trough TestNG TestSuite
       // androidSetup.andySetup();
       // loginPage.setLoginButton(androidSetup.aDriver);
      //  loginPage.loginButtonClick();
       // leftmenu.getleftnav(androidSetup.aDriver);
       // leftmenu.leftnavclick();
      // androidSetup.aDriver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id='com.rgp.breathe:id/nav_view']/android.support.v7.widget.RecyclerView//android.widget.CheckedTextView[contains(@text,'Tracking')]")).click();
      //  androidSetup.aDriver.findElement(By.xpath("//android.widget.CheckedTextView[@text='Tracking']")).click();
   }
    @BeforeTest
    public void addRows() throws InterruptedException {
        leftmenu.getleftnav(androidSetup.aDriver);
        leftmenu.leftnavclick();
        leftmenu.setLeftNavTracking(androidSetup.aDriver);
        leftmenu.getLeftNavTracking().click();

        tracking.setAddButton(androidSetup.aDriver);
        tracking.clickAddButton();
        tracking.setAddNew(androidSetup.aDriver);
        tracking.clickAddNew();
        tracking.setSaveButton(androidSetup.aDriver);
        tracking.clickSaveButton();
        Thread.sleep(2000);
        tracking.clickAddButton();
        tracking.clickAddNew();
        Thread.sleep(2000);
        tracking.setNumberPicker(androidSetup.aDriver);
        tracking.getNumberPicker().click();
        tracking.clickSaveButton();
        Thread.sleep(2000);
        tracking.setTrackingElements(androidSetup.aDriver);
    }
    @Test
    // This test case is to check count of Peak Flow

    public void verifyTrackingsCount() throws InterruptedException {
        trackingsList = tracking.getTrackingElements();
        assert (trackingsList.size() == 2);
    }

    @Test
    public void verifyLatestEntry(){
        trackingsRowsListDateTime = tracking.getTrackingRowsDateTime();
        trackingsRowsListPeakValue = tracking.getTrackingRowsPeakValue();
        //System.out.println(trackingsList.get(0).findElement(By.className("android.widget.LinearLayout")).findElement(By.className("android.widget.TextView")).getText().toString());
        //System.out.println(trackingsList.get(0).findElement(By.xpath(".//android.widget.LinearLayout")).getText().toString());
        assert (trackingsRowsListPeakValue.get(0).getText().toString().equals("100 ml/s"));
        //System.out.println(trackingsRowsListDateTime.get(0).getText().toString());
        //System.out.println(trackingsRowsListDateTime.get(1).getText().toString());
        //System.out.println(trackingsRowsListPeakValue.get(0).getText().toString());
        //System.out.println(trackingsRowsListPeakValue.get(1).getText().toString());
    }
  /*  @AfterClass
    public void teardown() throws IOException {
        androidSetup.aDriver.quit();
    }
*/
}
