package com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.sikuli.script.Screen;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com MPX Select Account Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.1 Date: March 18, 2014
 *********************************************/

public class MPXSelectAccount {

	private Driver webDriver;
    private AppLib applib;
    private MPXAssets mpxAssets;
    
    //PAGE OBJECT CONSTRUCTOR
    public MPXSelectAccount(Driver webDriver, AppLib applib) {
    	this.webDriver = webDriver;
    	PageFactory.initElements(webDriver, this);
        new Screen();
        this.applib = applib;
        mpxAssets = new MPXAssets(applib);
    }
    
    private String getImagePath() {
    	
    	return applib.getPathToSikuliImages();
    	
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//input[contains(@class, 'tp-shell-account-picker-input')]")
    private WebElement Account_Txb;
    
    private WebElement Account_Opt(String option) {
    	return webDriver.findElement(By.xpath("//a[contains(text(), '" + option + "')]"));
    }
    
    
    //PAGE OBJECT METHODS
    public void ClearAccountName() throws Exception {
    	
    	Reporter.log("Clear the account name in the 'Account' text box.");
    	Account_Txb.clear();
    }
    
    public void ClickAccountOption(String accountOption) throws Exception {
    	
    	Reporter.log("Click account name '" + accountOption + "' from the account option drop down list.");
    	Account_Opt(accountOption).click();
    }
    
    public void WaitForAccountSwitch() throws Exception {
    	
    	Reporter.log("Wait for the 'Switching accounts' spinner to complete.");
    	mpxAssets.WaitForImgPresent(getImagePath() + "SelectAccount/SwitchingAccount_Txt.png");
    	mpxAssets.WaitForImgNotPresent(getImagePath() + "SelectAccount/SwitchingAccount_Txt.png");
    }
    
    public void SelectAccount(String accountName) throws Exception {
    	
    	if (!Account_Txb.getAttribute("value").equals(accountName)) {
    		Thread.sleep(30000); //TODO - long but unfortunate pause that will thankfully go away once mpx migrates totally away from flash
    		this.ClearAccountName();
    		Thread.sleep(3000); //TODO - same as above
    		this.ClickAccountOption(accountName);
    		this.WaitForAccountSwitch();
    	}

    }
    
}

