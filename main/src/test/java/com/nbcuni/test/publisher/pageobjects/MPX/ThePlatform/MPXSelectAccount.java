package com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform;


import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Button;
import org.sikuli.script.Key;
import org.sikuli.script.KeyModifier;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;


/*********************************************
 * publisher.nbcuni.com MPX Select Account Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 24, 2014
 *********************************************/

public class MPXSelectAccount {

    private static CustomWebDriver webDriver;
    private static AppLib applib;
    private final Util ul;
    Screen s = new Screen();
    MPXAssets mpxAssets = new MPXAssets(webDriver, applib);
    
    public MPXSelectAccount(final CustomWebDriver custWebDr, AppLib applib) {
        webDriver = custWebDr;
        this.applib = applib;
        ul = new Util(webDriver);
        
    }
    
    private String getImagePath() {
    	
    	String PathToImages = applib.getPathToSikuliImages();
    	return PathToImages;
    }
    
    public void SelectAccount(String accountName) throws Exception {
    	
    	String path = this.getImagePath();
    	mpxAssets.WaitForImgPresent(path + "SelectAccount/AdminPub7QA_Ttl.png");
    	mpxAssets.WaitForAllAssetsToLoad(path);
    	
    	Reporter.log("Enter the account name '" + accountName + "' in the account select text box.");
    	mpxAssets.WaitForImgPresent(path + "SelectAccount/Account_Txb.png");
    	Pattern pImage = new Pattern(path + "SelectAccount/Account_Txb.png").targetOffset(45, 0);
    	Region r = s.exists(pImage, 1);
    	s.doubleClick(r, 1);
    	mpxAssets.ClearInput();
    	s.type(accountName);
    	
    	Reporter.log("Click the account option from the drop down list.");
    	Pattern pImage2 = new Pattern(path + "SelectAccount/Account_Txb.png").targetOffset(40, 15);
    	Region r2 = s.exists(pImage2, 1);
    	s.click(r2, 1);
    	
    	Reporter.log("Click the 'Account' title to leave focus off of the selection.");
    	s.click(path + "SelectAccount/Account_Txb.png");
    	
    	Reporter.log("Wait for account to switch to the newly selected account.");
        	mpxAssets.WaitForImgNotPresent(path + "SelectAccount/SwitchingAccounts_Ctr.png");
    	
    }
    
    
}

