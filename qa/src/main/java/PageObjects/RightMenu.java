package PageObjects;

import Functions.Xls_Reader;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by rohikris on 4/1/2016.
 */
public class RightMenu {

    Xls_Reader myxls;
    public WebElement btn_Rightmenu,lnk_Settings;

    public void setRightmenuLink(AndroidDriver aDriver) {
        btn_Rightmenu = aDriver.findElement(By.xpath("//android.widget.ImageView[@content-desc='More options']"));
    }

    public void RightmenuLinkClick(){
        btn_Rightmenu.click();
    }

    public void setSettingsLink(AndroidDriver aDriver) {
        lnk_Settings = aDriver.findElement(By.xpath("//android.widget.TextView[@text='Settings']"));
    }

    public void SettingsLinkClick(){
        lnk_Settings.click();
    }


}

