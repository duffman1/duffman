package com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Driver.Driver;
import org.testng.Reporter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*********************************************
 * publisher.nbcuni.com MPXLogin Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 16, 2014
 *********************************************/

public class MPXLogin {

    private Driver webDriver;
    private AppLib applib;
    private MPXAssets mpxAssets;
    private WebDriverWait wait;
    
    public MPXLogin(Driver webDriver, AppLib applib) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        this.applib = applib;
        mpxAssets = new MPXAssets(applib);
        wait = new WebDriverWait(webDriver, 10);
    }
    
    //PAGE OBJECT IDENTIFERS
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'login-username')]//input")
    private WebElement Username_Txb;
    
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'login-password')]//input")
    private WebElement Password_Txb;
    
    @FindBy(how = How.XPATH, using = "//button[text()='SIGN IN']")
    private WebElement SignIn_Btn;
    
    
    //PAGE OBJECT METHODS
    public void OpenMPXThePlatform() throws Exception {
    	
    	Reporter.log("Open the mpx platform.");
    	webDriver.navigate().to(applib.getMPXUrl());
    }
    
    public void Login(String userName, String passWord) throws Exception {
    	wait.until(ExpectedConditions.visibilityOf(Username_Txb)).sendKeys(userName);
    	Password_Txb.sendKeys(passWord);
    	SignIn_Btn.click();
        mpxAssets.WaitForAllAssetsToLoad();
        
    }
    
}

