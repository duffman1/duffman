package com.nbcuni.test.publisher.pageobjects.MPX;

import java.util.List;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
/*********************************************
 * publisher.nbcuni.com Deleting MPX Account Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: February 10, 2014
 *********************************************/
public class DeletingMPXAccount {

	private static CustomWebDriver webDriver;
    private static AppLib applib;
    
    //PAGE OBJECT CONSTRUCTORS
    public DeletingMPXAccount(CustomWebDriver custWebDr, AppLib applib) {
        webDriver = custWebDr;
        this.applib = applib;
        
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//div[@class='message'][text()='Completed 4 of 4.']")
    private static WebElement Completed4of4_Msg;
    
    @FindBy(how = How.XPATH, using = "//div[@class='percentage'][text()='100%']")
    private static WebElement OneHundredPercent_Msg;
    
    
    //PAGE OBJECT METHODS
    public void WaitForMPXAccountDeletion() throws Exception{
    
    	Reporter.log("Wait for the 'Delete MPX Account' status step to reach 4 of 4 and 100%");
    	Thread.sleep(1000);
    	Completed4of4_Msg.isDisplayed();
    	OneHundredPercent_Msg.isDisplayed();

    }
    
    

}
