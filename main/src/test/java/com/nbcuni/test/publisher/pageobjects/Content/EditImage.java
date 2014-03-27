package com.nbcuni.test.publisher.pageobjects.Content;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;

/*********************************************
 * publisher.nbcuni.com Edit Image Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.1 Date: March 26, 2014
 *********************************************/

public class EditImage {

    private static CustomWebDriver webDriver;
    private static AppLib applib;
    
    //PAGE OBJECT CONSTRUCTOR
    public EditImage(CustomWebDriver webDriver, AppLib applib) {
        EditImage.webDriver = webDriver;
        EditImage.applib = applib;
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//input[contains(@id, 'edit-field-file-image-title-text-und-0-value')]")
    private static WebElement TitleText_Txb;
    
    @FindBy(how = How.XPATH, using = "//input[contains(@id, 'edit-field-file-image-alt-text-und-0-value')]")
    private static WebElement AltText_Txb;
    
    @FindBy(how = How.XPATH, using = "//input[contains(@id, 'edit-field-source-und-0-value')]")
    private static WebElement Source_Txb;
    
    @FindBy(how = How.XPATH, using = "//input[contains(@id, 'edit-field-credit-und-0-value')]")
    private static WebElement Credit_Txb;
    
    @FindBy(how = How.XPATH, using = "//input[contains(@id, 'edit-field-copyright-und-0-value')]")
    private static WebElement Copyright_Txb;
    
    @FindBy(how = How.XPATH, using = "//input[contains(@id, 'edit-field-keywords-und-0-value')]")
    private static WebElement Keywords_Txb;
    
    @FindBy(how = How.ID, using = "edit-simple-exif")
    private static WebElement ApplyEmbeddedMetadata_Btn;
    
    @FindBy(how = How.XPATH, using = "//input[@value='Save']")
    private static WebElement Save_Btn;
    
    @FindBy(how = How.ID, using = "edit-submit--2")
    private static WebElement SaveFromEditFrm_Btn;
    
    @FindBy(how = How.CSS, using = "img[title='Close window']")
    private static WebElement CloseWindow_Img;
    
   
    //PAGE OBJECT METHODS
    public void VerifyTitleTextValue(String value) throws Exception {
    	
    	Reporter.log("Assert that the Title Text box value matches '" + value + "'.");
    	Assert.assertEquals(this.ElementReady(TitleText_Txb).getAttribute("value"), value);
    	
    }
    
    public void EnterTitleText(String title) throws Exception {
    	
    	Reporter.log("Enter '" + title + "' in the 'Title Text' text box.");
    	this.ElementReady(TitleText_Txb).clear();
    	TitleText_Txb.sendKeys(title);
    }
    
    public void VerifyAltTextValue(String value) throws Exception {
    	
    	Reporter.log("Assert that the Alt Text box value matches '" + value + "'.");
    	Assert.assertEquals(this.ElementReady(AltText_Txb).getAttribute("value"), value);
    }
    
    public void VerifySourceValue(String value) throws Exception {
    	
    	Reporter.log("Assert that the Source box value matches '" + value + "'.");
    	Assert.assertEquals(this.ElementReady(Source_Txb).getAttribute("value"), value);
    }
    
    public void EnterSource(String source) throws Exception {
    	
    	Reporter.log("Enter '" + source + "' in the 'Source' text box.");
    	this.ElementReady(Source_Txb).clear();
    	Source_Txb.sendKeys(source);
    }
    
    public void VerifyCreditValue(String value) throws Exception {
    	
    	Reporter.log("Assert that the Credit Text box value matches '" + value + "'.");
    	Assert.assertEquals(this.ElementReady(Credit_Txb).getAttribute("value"), value);
    }
    
    public void VerifyCopyrightValue(String value) throws Exception {
    	
    	Reporter.log("Assert that the Copyright Text box value matches '" + value + "'.");
    	Assert.assertEquals(this.ElementReady(Copyright_Txb).getAttribute("value"), value);
    }
    
    public void VerifyKeywordsValue(String value) throws Exception {
    	
    	Reporter.log("Assert that the Keywords Text box value matches '" + value + "'.");
    	Assert.assertEquals(this.ElementReady(Keywords_Txb).getAttribute("value"), value);
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
    
    private WebElement ElementReady(WebElement element) throws Exception {
    	
    	WebElement readyElement = null;
    	
    	for (int second = 0; ; second++) {
            if (second >= applib.getImplicitWaitTime()) {
                Assert.fail("Element is not ready after timeout");}
            try{
            	readyElement = element;
            	readyElement.getAttribute("id");
            	if (readyElement.isDisplayed() == true) {
                	break;
                }
            }
            catch (Exception e){ }
            Thread.sleep(500);
        }
    	
    	return readyElement;
    }
  
}

