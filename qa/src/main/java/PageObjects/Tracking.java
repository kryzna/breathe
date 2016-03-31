package PageObjects;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by amolverm on 3/30/2016.
 */
public class Tracking {
    List<WebElement> trackingElements;
    WebElement tracking,userEmail;
    public void setTrackingElements(AndroidDriver aDriver){
        trackingElements=aDriver.findElements(By.id("tracking_recyclerView"));
    }

    public List<WebElement> getUserName(){
        return trackingElements;
    }
}
