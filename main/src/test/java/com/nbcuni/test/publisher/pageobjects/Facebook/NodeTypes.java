package com.nbcuni.test.publisher.pageobjects.Facebook;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;


/*********************************************
 * publisher.nbcuni.com Node Types Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 26, 2013
 *********************************************/

public class NodeTypes {

    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    
    private static String Post_Cbx = "//input[@id='edit-facebook-post-types-post']";
    private static String SaveConfiguration_Btn = "//input[@value='Save configuration']";
    private static String Message_Ctr = "//div[@class='messages status']";
    
    public NodeTypes(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
    
    public void EnablePostNode() throws Exception {
    	
    	WebElement Cbx = new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOf(webDriver.findElement(By.xpath(Post_Cbx))));
    	
    	if (Cbx.isSelected() == false) {
    		Cbx.click();
    		webDriver.click(SaveConfiguration_Btn);
    		this.VerifyConfigurationSaved();
    	}
    }
    
    public void VerifyConfigurationSaved() throws Exception{
    	
    	ul.verifyObjectContainsText(Message_Ctr, "The configuration options have been saved.");
    }
    
    
    
  
}

