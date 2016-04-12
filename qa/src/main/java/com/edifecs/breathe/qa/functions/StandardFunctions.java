package com.edifecs.breathe.qa.functions;

import org.openqa.selenium.WebElement;

/**
 * Created by amolverm on 3/29/2016.
 */
public class StandardFunctions {
    public void click(WebElement webElement) {
        webElement.click();
    }

    public String getText(WebElement webElement) {
        return (webElement.getText().toString());
    }

    public void sendText(WebElement webElement, String text) {
        webElement.sendKeys(text);
    }
}
