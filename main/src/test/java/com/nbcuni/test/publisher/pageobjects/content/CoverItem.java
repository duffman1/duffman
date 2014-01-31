package com.nbcuni.test.publisher.pageobjects.content;


import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.pageobjects.Overlay;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXAssets;
import com.nbcuni.test.webdriver.CustomWebDriver;


/*********************************************
 * publisher.nbcuni.com Cover Item Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 16, 2014
 *********************************************/

public class CoverItem {

    private static CustomWebDriver webDriver;
    private AppLib applib;
    private Util ul;
    
    //PAGE OBJECT CONSTRUCTOR
    public CoverItem(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        this.applib = applib;
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//div[contains(@id, 'edit-field-cover-item')]//img")
    private static WebElement CoverItem_Img;
    
    @FindBy(how = How.XPATH, using = "//div[contains(@id, 'edit-field-cover-item')]//a[text()='Edit']")
    private static WebElement Edit_Btn;
    
    @FindBy(how = How.XPATH, using = "//div[contains(@id, 'edit-field-cover-item')]//a[text()='Select']")
    private static WebElement Select_Btn;
    
    
    //PAGE OBJECT METHODS
    public void VerifyFileImagePresent(String imageSrc) throws Exception {
    	
    	Reporter.log("Assert that img source of the Cover Item contains '" + imageSrc + "'.");
    	Assert.assertTrue(CoverItem_Img.getAttribute("src").contains(imageSrc));
    	
    	Reporter.log("Assert the the img is loaded and visible.");
    	boolean imgLoaded;
        for (int second = 0; ; second++){
            if (second >= 30) {
                Assert.fail("Image '" + imageSrc + "' is not fully loaded after timeout");
            }
            imgLoaded = (Boolean) ((JavascriptExecutor)webDriver).executeScript(
            			"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", 
            			CoverItem_Img);
            if (imgLoaded == true){ break;}
            Thread.sleep(500);
        }
    }
    
    public void ClickEditBtn() throws Exception {
    	
    	Reporter.log("Click the Cover Item 'Edit' button.");
    	Edit_Btn.click();
    }
    
    public void ClickSelectBtn() throws Exception {
    	
    	Reporter.log("Click the Cover Item 'Select' button.");
    	Select_Btn.click();
    }
    
    
}
