package com.edifecs.breathe.qa.pageobjects;

import com.edifecs.breathe.qa.functions.Excelreader;
import com.edifecs.breathe.qa.functions.Utility;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;

/**
 * Created by amolverm on 3/29/2016.
 */
public class LoginPage {
    Excelreader myxls;
    public WebElement Patient_loginButton, Patient_signupLink, Patient_Emailid, Patient_Pwd;

    public void setLoginButton(AndroidDriver aDriver) {
        Patient_loginButton = aDriver.findElement(By.id(("sign_in_button")));
    }

    public void setPatientemailid(AndroidDriver aDriver) {
        Patient_Emailid = aDriver.findElement(By.id("input_email"));
    }

    public void setPatient_Password(AndroidDriver aDriver) {
        Patient_Pwd = aDriver.findElement(By.id("input_password"));
    }

    public void loginButtonClick() {
        Patient_loginButton.click();
    }

    public void enter_blankemail_id() {
        Patient_Emailid.sendKeys("");
    }

    public void enter_blankpassword() {
        Patient_Pwd.sendKeys("");
    }

    public void enter_email_id() {
        Utility.loadPropertyFile("config.properties");
        myxls = new Excelreader(System.getProperty("user.dir") + Utility.getValueOf("testExcel"));

        Patient_Emailid.sendKeys(myxls.getCellData("UserData", "PatientEmailid", 2));
    }

    public void enter_password() {
        Utility.loadPropertyFile("config.properties");
        myxls = new Excelreader(System.getProperty("user.dir") + Utility.getValueOf("testExcel"));
        Patient_Pwd.sendKeys(myxls.getCellData("UserData", "PatientPassword", 2));
    }

    /*    public void setSignupLink(){
        signupLink
    }*/
}
