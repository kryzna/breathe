package com.edifecs.breathe.qa.testcases;

import com.edifecs.breathe.qa.functions.Andriodsetup;
import com.edifecs.breathe.qa.pageobjects.LeftMenu;
import com.edifecs.breathe.qa.pageobjects.LoginPage;
import com.edifecs.breathe.qa.pageobjects.Tracking;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created by amolverm on 3/31/2016.
 */
public class TestTracking {
    Andriodsetup andriodsetup = new Andriodsetup();
    LoginPage loginPage = new LoginPage();
    LeftMenu leftmenu = new LeftMenu();
    Tracking tracking = new Tracking();
    List<WebElement> trackingsList,trackingsRowsListDateTime,trackingsRowsListPeakValue;
    @BeforeClass
    public void startApp() throws IOException {
        // This need to be commented if run trough TestNG TestSuite
       // andriodsetup.andySetup();
       // loginPage.setLoginButton(andriodsetup.aDriver);
      //  loginPage.loginButtonClick();
       // leftmenu.getleftnav(andriodsetup.aDriver);
       // leftmenu.leftnavclick();
      // andriodsetup.aDriver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id='com.rgp.breathe:id/nav_view']/android.support.v7.widget.RecyclerView//android.widget.CheckedTextView[contains(@text,'Tracking')]")).click();
      //  andriodsetup.aDriver.findElement(By.xpath("//android.widget.CheckedTextView[@text='Tracking']")).click();
   }
    @BeforeTest
    public void addRows() throws InterruptedException {
        leftmenu.getleftnav(andriodsetup.aDriver);
        leftmenu.leftnavclick();
        leftmenu.setLeftNavTracking(andriodsetup.aDriver);
        leftmenu.getLeftNavTracking().click();

        tracking.setAddButton(andriodsetup.aDriver);
        tracking.clickAddButton();
        tracking.setAddNew(andriodsetup.aDriver);
        tracking.clickAddNew();
        Thread.sleep(5000);
        tracking.setSaveButton(andriodsetup.aDriver);
        tracking.clickSaveButton();
        Thread.sleep(2000);
        tracking.clickAddButton();
        tracking.clickAddNew();
        Thread.sleep(8000);
        tracking.setNumberPicker(andriodsetup.aDriver);
        tracking.getNumberPicker().click();
        tracking.clickSaveButton();
        Thread.sleep(2000);
        tracking.setTrackingElements(andriodsetup.aDriver);
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
        andriodsetup.aDriver.quit();
    }
*/
}
