package com.edifecs.breathe.qa.pageobjects;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amolverm on 3/30/2016.
 */
public class HealthRiskAssesment {

    public WebElement leftnav_Healthassessment,setAssessmentStartButton,elementQuestionnaireTitle,
            getHealtAssessment_questionnairechoice_yes_radio1,getHealtAssessment_questionnairechoice_next,
            getHealtAssessment_questionnairechoice_checkbox1,text_HealtAssessment_completed,elementQuestionTitle;
    public static WebElement elementNextButton,getHealtAssessmentSubmit,questionairescore,elementPreviousButton;
    public static List<WebElement> listAnswerOptions = new ArrayList<WebElement>();


    public void getleftnav_HealtAssessment(AndroidDriver aDriver) {
        leftnav_Healthassessment = aDriver.findElement(
                By.xpath("//android.widget.CheckedTextView[@text='Health Risk Assesment']"));
    }

    public void leftnavHealthAssessmentclick() {
        leftnav_Healthassessment.click();
    }

    //set assessment start button
    public void setAssessmentStartButton(AndroidDriver aDriver){
        setAssessmentStartButton=aDriver.findElement(By.id("questionary_status_button"));
    }

    public WebElement getAssessmentStartButton(){
        return setAssessmentStartButton;
    }
    /*public String gettext_HealtAssessment_completed(AndroidDriver aDriver){
        text_HealtAssessment_completed=aDriver.findElement(By.id("questionary_status_button"));
        String txtvalue = text_HealtAssessment_completed.getText();
        return txtvalue;
    }*/

    public void clickAssessmentStartButton(){
        setAssessmentStartButton.click();
    }

    //set next button element on the question activity
    public void setElementNextButton(AndroidDriver aDriver){
        elementNextButton=aDriver.findElement(By.id("android:id/button1"));
    }

    public static WebElement getElementNextButton(){
        return elementNextButton;
    }

    public void setElementPreviousButton(AndroidDriver aDriver){
        elementPreviousButton=aDriver.findElement(By.id("android:id/button2"));
    }

    public static WebElement getElementPreviousButton(){
        return elementPreviousButton;
    }

    //set the object of question title
    public void setElementQuestionTitle(AndroidDriver aDriver){
        elementQuestionTitle = aDriver.findElement(By.id("com.rgp.breathe:id/question_content"));
    }

    public WebElement getElementQuestionTitle(){
        return elementQuestionTitle;
    }

    public void setElementQuestionnaireTitle(AndroidDriver aDriver){
        elementQuestionnaireTitle = aDriver.findElement(By.id("com.rgp.breathe:id/questionnaire_title"));
    }

    public WebElement getElementQuestionnaireTitle(){
        return elementQuestionnaireTitle;
    }
    //set list of answer choices to the list
    public static void setListAnswerOptions(AndroidDriver aDriver){
        listAnswerOptions=aDriver.findElements(By.xpath("//android.widget.RadioGroup/android.widget.RadioButton"));
    }

    public static List<WebElement> getListAnswerOptions(){
        return listAnswerOptions;
    }

    public static void setListCheckboxAnswerOptions(AndroidDriver aDriver){
        listAnswerOptions=aDriver.findElements(By.xpath("//android.widget.LinearLayout/android.widget.CheckBox"));
    }

    public static List<WebElement> getListCheckboxAnswerOptions(){
        return listAnswerOptions;
    }

    public void leftnav_Healthassessment_choice_yesclick(){
        elementNextButton.click();
    }

    public void getHealtAssessment_questionnairechoice_yes_radio1(AndroidDriver aDriver) {
        getHealtAssessment_questionnairechoice_yes_radio1 =
                aDriver.findElement(By.xpath("//android.widget.RadioButton[@text='YES']"));
        getHealtAssessment_questionnairechoice_yes_radio1.click();
    }


    //next button on the assessment question activity
    public void getHealtAssessment_questionnairechoice_next(AndroidDriver aDriver){
        getHealtAssessment_questionnairechoice_next=aDriver.findElement(By.id("android:id/button1"));
        getHealtAssessment_questionnairechoice_next.click();

    }

    //next button on the assessment question activity
    public void getHealtAssessmentSubmit(AndroidDriver aDriver){
        getHealtAssessmentSubmit=aDriver.findElement(By.id("android:id/buttonSubmit"));
    }

    public void getHealtAssessment_questionnairechoice_checkbox1(AndroidDriver aDriver) {
        getHealtAssessment_questionnairechoice_checkbox1 = aDriver.findElement(
                By.xpath("//android.widget.CheckBox[@text='Two times a week or less']"));
        getHealtAssessment_questionnairechoice_checkbox1.click();
    }
    public void setQuestionairescore(AndroidDriver aDriver)
    {
        questionairescore = aDriver.findElement(By.id("com.rgp.breathe:id/questionnaire_score"));

    }
    public double getQuestionairescore()
    {
        double score =   Double.parseDouble(questionairescore.getText());
        return score;
    }

    public String getAssessmentStartButtonText(){
        String s = setAssessmentStartButton.getText();
        return  s;
    }


}
