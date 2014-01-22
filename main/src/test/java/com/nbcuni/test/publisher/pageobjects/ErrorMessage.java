package com.nbcuni.test.publisher.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;

public class ErrorMessage {


    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    
    private static String ErrMessage_Ctr = "//div[@class='messages error']";
    
	public ErrorMessage(final CustomWebDriver custWebDr) {
	    webDriver = custWebDr;
	    ul = new Util(webDriver);
	    
	}
	public void VerifyErrorMessageNotonPage(String txtError) throws Exception {		
        String bodyTxt = webDriver.findElement(By.xpath("//body")).getText();
        Boolean tx = bodyTxt.contains(txtError);
        Assert.assertFalse(bodyTxt.contains(txtError));
		
    }
}