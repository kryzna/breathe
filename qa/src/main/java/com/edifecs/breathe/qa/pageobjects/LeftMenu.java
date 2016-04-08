package com.edifecs.breathe.qa.pageobjects;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by amolverm on 3/29/2016.
 */
public class LeftMenu {
    public WebElement userName,userEmail,leftnav,leftnav_Healthassessment,leftnav_Healthassessment_btn,leftNavTracking;
    public void setUserName(AndroidDriver aDriver){
        userName=aDriver.findElement(By.id("user_name"));
    }

    public WebElement getUserName(){
        return userName;
    }

    public void setUserEmail(AndroidDriver aDriver){
        userEmail=aDriver.findElement(By.id("user_email"));
    }

    public WebElement getUserEmail(){
        return userEmail;
    }

    public void getleftnav(AndroidDriver aDriver){
        leftnav=aDriver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='Open navigation drawer']"));
    }

    public void leftnavclick(){
        leftnav.click();
    }

    public void setLeftNavTracking(AndroidDriver aDriver){
        leftNavTracking = aDriver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id='com.rgp.breathe:id/nav_view']/android.support.v7.widget.RecyclerView//android.widget.CheckedTextView[contains(@text,'Tracking')]"));
    }

    public WebElement getLeftNavTracking(){
        return leftNavTracking;
    }

}
