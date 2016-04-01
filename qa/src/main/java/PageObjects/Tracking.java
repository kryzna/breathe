package PageObjects;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amolverm on 3/30/2016.
 */
public class Tracking {
    List<WebElement> trackingElements;
    List<WebElement> trackingRowsDateTime = new ArrayList<WebElement>();
    List<WebElement> trackingRowsPeakValue = new ArrayList<WebElement>();
    WebElement trackingDateTime,trackingPeakValue,addButton,addNew,saveButton,numberPicker;
    public void setTrackingElements(AndroidDriver aDriver){
        trackingElements=aDriver.findElements(By.xpath("//android.support.v7.widget.RecyclerView[@resource-id='com.rgp.breathe:id/tracking_recyclerView']/android.widget.LinearLayout"));
        //tracking= aDriver.findElement(By.xpath("//android.support.v7.widget.RecyclerView[@resource-id='com.rgp.breathe:id/tracking_recyclerView']/android.widget.LinearLayout"));
        if(trackingElements.size()>0) {
            for (int i = 1; i <= trackingElements.size(); i++) {
                trackingDateTime= aDriver.findElement(By.xpath("//android.support.v7.widget.RecyclerView[@resource-id='com.rgp.breathe:id/tracking_recyclerView']/android.widget.LinearLayout["+i+"]/android.widget.LinearLayout[1]/android.widget.TextView[3]"));
                trackingRowsDateTime.add(trackingDateTime);
                trackingPeakValue=aDriver.findElement(By.xpath("//android.support.v7.widget.RecyclerView[@resource-id='com.rgp.breathe:id/tracking_recyclerView']/android.widget.LinearLayout["+i+"]/android.widget.LinearLayout[1]/android.widget.TextView[1]"));
                trackingRowsPeakValue.add(trackingPeakValue);
            }
        }
    }

    public List<WebElement> getTrackingElements(){
        //System.out.println(trackingRows.size());
        return trackingElements;
    }

    public List<WebElement> getTrackingRowsDateTime(){
        //System.out.println(trackingRows.size());
        return trackingRowsDateTime;
    }

    public List<WebElement> getTrackingRowsPeakValue(){
        //System.out.println(trackingRows.size());
        return trackingRowsPeakValue;
    }

    public void setAddButton(AndroidDriver aDriver){
        addButton=aDriver.findElement(By.id("com.rgp.breathe:id/fab"));
    }

    public void clickAddButton(){
        addButton.click();
    }

    public void setAddNew(AndroidDriver aDriver){
        addNew=aDriver.findElement(By.id("com.rgp.breathe:id/snackbar_action"));
    }

    public void clickAddNew(){
        addNew.click();
    }

    public void setSaveButton(AndroidDriver aDriver){
        saveButton=aDriver.findElement(By.id("com.rgp.breathe:id/action_save"));
    }

    public void setNumberPicker(AndroidDriver aDriver){
        numberPicker = aDriver.findElement(By.xpath("//android.widget.NumberPicker[1]/android.widget.Button[1]"));
    }

    public WebElement getNumberPicker(){
        return numberPicker;
    }

    public void clickSaveButton(){
        saveButton.click();
    }
}
