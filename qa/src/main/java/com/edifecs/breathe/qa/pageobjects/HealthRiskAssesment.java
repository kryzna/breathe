package com.edifecs.breathe.qa.pageobjects;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by amolverm on 3/30/2016.
 */
public class HealthRiskAssesment {

    public WebElement leftnav_Healthassessment,leftnav_Healthassessment_btn,leftnav_Healthassessment_choice_yes,
            getHealtAssessment_questionnairechoice_yes_radio1,getHealtAssessment_questionnairechoice_next,
            getHealtAssessment_questionnairechoice_checkbox1,text_HealtAssessment_completed;

    public void getleftnav_HealtAssessment(AndroidDriver aDriver){
        leftnav_Healthassessment=aDriver.findElement(By.xpath("//android.widget.CheckedTextView[@text='Health Risk Assesment']"));
    }

    public void leftnavHealthAssessmentclick(){
        leftnav_Healthassessment.click();
    }

    public void getleftnav_HealtAssessment_completed(AndroidDriver aDriver){
        leftnav_Healthassessment_btn=aDriver.findElement(By.id("questionary_status_button"));
    }
    public String gettext_HealtAssessment_completed(AndroidDriver aDriver){
        text_HealtAssessment_completed=aDriver.findElement(By.id("questionary_status_button"));
        String txtvalue = text_HealtAssessment_completed.getText();
        return txtvalue;
    }

    public void leftnavHealthAssessment_completed_btnclick(){
        leftnav_Healthassessment_btn.click();
    }

    public void getleftnav_HealtAssessment_questionnairechoice_yes(AndroidDriver aDriver){
        leftnav_Healthassessment_choice_yes=aDriver.findElement(By.id("android:id/button1"));
    }

    public void leftnav_Healthassessment_choice_yesclick(){
        leftnav_Healthassessment_choice_yes.click();
    }

    public void getHealtAssessment_questionnairechoice_yes_radio1(AndroidDriver aDriver){
        getHealtAssessment_questionnairechoice_yes_radio1=aDriver.findElement(By.xpath("//android.widget.RadioButton[@text='YES']"));
        getHealtAssessment_questionnairechoice_yes_radio1.click();
    }



    public void getHealtAssessment_questionnairechoice_next(AndroidDriver aDriver){
        getHealtAssessment_questionnairechoice_next=aDriver.findElement(By.id("android:id/button1"));
        getHealtAssessment_questionnairechoice_next.click();
    }

    public void getHealtAssessment_questionnairechoice_checkbox1(AndroidDriver aDriver){
        getHealtAssessment_questionnairechoice_checkbox1=aDriver.findElement(By.xpath("//android.widget.CheckBox[@text='Two times a week or less']"));
        getHealtAssessment_questionnairechoice_checkbox1.click();
    }

}
