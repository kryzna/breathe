package PageObjects;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by amolverm on 3/29/2016.
 */
public class LeftMenu {
    public WebElement userName,userEmail,leftnav;
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
}
