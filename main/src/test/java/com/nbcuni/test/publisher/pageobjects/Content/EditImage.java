package com.nbcuni.test.publisher.pageobjects.Content;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com Edit Image Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.1 Date: March 26, 2014
 *********************************************/

public class EditImage {

    private Driver webDriver;
    private AppLib applib;
    private WebDriverWait wait;
    
    //PAGE OBJECT CONSTRUCTOR
    public EditImage(Driver webDriver, AppLib applib) {
        this.webDriver = webDriver;
        this.applib = applib;
        PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(webDriver, 10);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//input[contains(@id, 'edit-field-file-image-title-text-und-0-value')]")
    private WebElement TitleText_Txb;
    
    @FindBy(how = How.XPATH, using = "//input[contains(@id, 'edit-field-file-image-alt-text-und-0-value')]")
    private WebElement AltText_Txb;
    
    @FindBy(how = How.XPATH, using = "//input[contains(@id, 'edit-field-source-und-0-value')]")
    private WebElement Source_Txb;
    
    @FindBy(how = How.XPATH, using = "//input[contains(@id, 'edit-field-credit-und-0-value')]")
    private WebElement Credit_Txb;
    
    @FindBy(how = How.XPATH, using = "//input[contains(@id, 'edit-field-copyright-und-0-value')]")
    private WebElement Copyright_Txb;
    
    @FindBy(how = How.XPATH, using = "//input[contains(@id, 'edit-field-keywords-und-0-value')]")
    private WebElement Keywords_Txb;
    
    @FindBy(how = How.ID, using = "edit-simple-exif")
    private WebElement ApplyEmbeddedMetadata_Btn;
    
    @FindBy(how = How.XPATH, using = "//input[@value='Save']")
    private WebElement Save_Btn;
    
    @FindBy(how = How.ID, using = "edit-submit--2")
    private WebElement SaveFromEditFrm_Btn;
    
    @FindBy(how = How.CSS, using = "img[title='Close window']")
    private WebElement CloseWindow_Img;
    
   
    //PAGE OBJECT METHODS
    public void VerifyTitleTextValue(String value) throws Exception {
    	
    	Reporter.log("Assert that the Title Text box value matches '" + value + "'.");
    	Thread.sleep(500); //stale element exception
    	Assert.assertEquals(wait.until(ExpectedConditions.visibilityOf(TitleText_Txb)).getAttribute("value"), value);
    	
    }
    
    public void EnterTitleText(String title) throws Exception {
    	
    	Reporter.log("Enter '" + title + "' in the 'Title Text' text box.");
    	wait.until(ExpectedConditions.visibilityOf(TitleText_Txb)).clear();
    	TitleText_Txb.sendKeys(title);
    }
    
    public void VerifyAltTextValue(String value) throws Exception {
    	
    	Reporter.log("Assert that the Alt Text box value matches '" + value + "'.");
    	Assert.assertEquals(wait.until(ExpectedConditions.visibilityOf(AltText_Txb)).getAttribute("value"), value);
    }
    
    public void VerifySourceValue(String value) throws Exception {
    	
    	Reporter.log("Assert that the Source box value matches '" + value + "'.");
    	Assert.assertEquals(wait.until(ExpectedConditions.visibilityOf(Source_Txb)).getAttribute("value"), value);
    }
    
    public void EnterSource(String source) throws Exception {
    	
    	Reporter.log("Enter '" + source + "' in the 'Source' text box.");
    	wait.until(ExpectedConditions.visibilityOf(Source_Txb)).clear();
    	Source_Txb.sendKeys(source);
    }
    
    public void VerifyCreditValue(String value) throws Exception {
    	
    	Reporter.log("Assert that the Credit Text box value matches '" + value + "'.");
    	Assert.assertEquals(wait.until(ExpectedConditions.visibilityOf(Credit_Txb)).getAttribute("value"), value);
    }
    
    public void VerifyCopyrightValue(String value) throws Exception {
    	
    	Reporter.log("Assert that the Copyright Text box value matches '" + value + "'.");
    	Assert.assertEquals(wait.until(ExpectedConditions.visibilityOf(Copyright_Txb)).getAttribute("value"), value);
    }
    
    public void VerifyKeywordsValue(String value) throws Exception {
    	
    	Reporter.log("Assert that the Keywords Text box value matches '" + value + "'.");
    	Assert.assertEquals(wait.until(ExpectedConditions.visibilityOf(Keywords_Txb)).getAttribute("value"), value);
    }
    
    public void VerifyApplyEmbeddedMetadataBtnPresent() throws Exception {
    	
    	Reporter.log("Verify the 'Apply Embedded Metadata' button is present.");
    	ApplyEmbeddedMetadata_Btn.isDisplayed();
    }
    
    public void ClickApplyEmbeddedMetadataBtn() throws Exception {
    	
    	Reporter.log("Click the 'Apply Embedded Metadata' button.");
    	ApplyEmbeddedMetadata_Btn.click();
    }

    public void ClickSaveBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save' button.");
    	Save_Btn.click();
    }
    
    public void ClickSaveFromEditFrmBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save' button.");
    	SaveFromEditFrm_Btn.click();
    }
    
    public void ClickCloseWindowImg() throws Exception {
    	
    	Reporter.log("Click the 'Close Window' image.");
    	Thread.sleep(1000); //slight pause required here
    	CloseWindow_Img.click();
    	this.WaitForEditImageFrameClose();
    	
    }
    
    public void WaitForEditImageFrameOpen() throws Exception {
    	
    	CloseWindow_Img.isDisplayed();
    	Thread.sleep(500); //stale element exception
    	wait.until(ExpectedConditions.visibilityOf(TitleText_Txb));
    	Thread.sleep(2500); //long pause required here... TODO - figure out dynamic wait
    }

    public void WaitForEditImageFrameClose() throws Exception {
    	
    	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	for (int second = 0; ; second++) {
            if (second >= applib.getImplicitWaitTime()) {
                Assert.fail("Edit image frame is still present after timeout");}
            try{
            	CloseWindow_Img.isDisplayed();
            }
            catch (Exception e){
            	break;
            }
            Thread.sleep(500);
        }
    	webDriver.manage().timeouts().implicitlyWait(applib.getImplicitWaitTime(), TimeUnit.SECONDS);
    }
    
}

