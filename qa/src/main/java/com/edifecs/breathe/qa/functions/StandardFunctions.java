package com.edifecs.breathe.qa.functions;

import org.openqa.selenium.WebElement;

/**
 * Created by amolverm on 3/29/2016.
 */
public class StandardFunctions {

    public static void click(WebElement webElement){
        webElement.click();
    }

    public static String getText(WebElement webElement){
        return(webElement.getText().toString());
    }

    public static void sendText(WebElement webElement, String text){

        webElement.sendKeys(text);
    }
}
