package com.nbcuni.test.publisher.pageobjects.Content;


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
import org.openqa.selenium.support.PageFactory;
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
 * publisher.nbcuni.com Cover Photo Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 16, 2014
 *********************************************/

public class CoverPhoto {

    private static CustomWebDriver webDriver;
    
    //PAGE OBJECT CONSTRUCTOR
    public CoverPhoto(final CustomWebDriver webDriver) {
        CoverPhoto.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//div[contains(@id, 'field-character-cover-photo')]//img")
    private static WebElement CoverPhoto_Img;
    
    @FindBy(how = How.XPATH, using = "//div[contains(@id, 'field-character-cover-photo')]//a[text()='Edit']")
    private static WebElement Edit_Btn;
    
    @FindBy(how = How.XPATH, using = "//div[contains(@id, 'field-character-cover-photo')]//a[text()='Select']")
    private static WebElement Select_Btn;
    
    
    //PAGE OBJECT METHODS
    public void VerifyFileImagePresent(String imageSrc) throws Exception {
    	
    	Reporter.log("Assert that img source of the Cover Photo contains '" + imageSrc + "'.");
    	Assert.assertTrue(CoverPhoto_Img.getAttribute("src").contains(imageSrc));
    	
    	Reporter.log("Assert the the img is loaded and visible.");
    	boolean imgLoaded;
        for (int second = 0; ; second++){
            if (second >= 30) {
                Assert.fail("Image '" + imageSrc + "' is not fully loaded after timeout");
            }
            imgLoaded = (Boolean) ((JavascriptExecutor)webDriver).executeScript(
            			"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", 
            			CoverPhoto_Img);
            if (imgLoaded == true){ break;}
            Thread.sleep(500);
        }
    }
    
    public void ClickEditBtn() throws Exception {
    	
    	Reporter.log("Click the Cover Photo 'Edit' button.");
    	Edit_Btn.click();
    }
    
    public void ClickSelectBtn() throws Exception {
    	
    	Reporter.log("Click the Cover Photo 'Select' button.");
    	Select_Btn.click();
    }
    
    
}

