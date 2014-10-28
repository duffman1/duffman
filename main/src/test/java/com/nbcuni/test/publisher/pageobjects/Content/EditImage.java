package com.nbcuni.test.publisher.pageobjects.Content;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
 * publisher.nbcuni.com Edit Image Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.1 Date: March 26, 2014
 *********************************************/

public class EditImage {

    private Driver webDriver;
    private WaitFor waitFor;
    
    //PAGE OBJECT CONSTRUCTOR
    public EditImage(Driver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        waitFor = new WaitFor(webDriver, 10);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private WebElement TitleText_Txb(String index) {
    	return webDriver.findElement(By.xpath("(//input[contains(@id, 'edit-field-file-image-title-text-und-0-value')])[" + index + "]"));
    }
    
    private WebElement AltText_Txb(String index) {
    	return webDriver.findElement(By.xpath("(//input[contains(@id, 'edit-field-file-image-alt-text-und-0-value')])[" + index + "]"));
    }
    
    private WebElement Source_Txb(String index) {
    	return webDriver.findElement(By.xpath("(//input[contains(@id, 'edit-field-source-und-0-value')])[" + index + "]"));
    }
    
    private WebElement Credit_Txb(String index) {
    	return webDriver.findElement(By.xpath("(//input[contains(@id, 'edit-field-credit-und-0-value')])[" + index + "]"));
    }
    
    private WebElement Copyright_Txb(String index) {
    	return webDriver.findElement(By.xpath("(//input[contains(@id, 'edit-field-copyright-und-0-value')])[" + index + "]"));
    }
    
    private WebElement Keywords_Txb(String index) {
    	return webDriver.findElement(By.xpath("(//input[contains(@id, 'edit-field-keywords-und-0-value')])[" + index + "]"));
    }
    
    @FindBy(how = How.ID, using = "edit-simple-exif")
    private WebElement ApplyEmbeddedMetadata_Btn;
    
    private WebElement Save_Btn(String index) {
    	return webDriver.findElement(By.xpath("(//input[@value='Save'])[" + index + "]"));
    }
    
    @FindBy(how = How.ID, using = "edit-submit--2")
    private WebElement SaveFromEditFrm_Btn;
    
    @FindBy(how = How.CSS, using = "img[title='Close window']")
    private WebElement CloseWindow_Img;
    
    @FindBy(how = How.XPATH, using = "//iframe[@id='pdk-player']")
    private WebElement MPXPlayer_Frm;
    
    @FindBy(how = How.XPATH, using = "//div[contains(@id, 'media-edit')]//a[contains(@type, 'video/mpx')]")
    private WebElement MPXVideo_Lnk;
    
    @FindBy(how = How.XPATH, using = "//a[text()='Cancel']")
    private WebElement Cancel_Lnk;
    
    
    //PAGE OBJECT METHODS
    public void VerifyTitleTextValue(String index, String value) throws Exception {
    	
    	Reporter.log("Assert that the Title Text box with index '" + index + "' value matches '" + value + "'.");
    	Assert.assertEquals(waitFor.ElementVisible(TitleText_Txb(index)).getAttribute("value"), value);
    	
    }
    
    public void EnterTitleText(String index, String title) throws Exception {
    	
    	Reporter.log("Enter '" + title + "' in the 'Title Text' text box with index '" + index + "'.");
    	waitFor.ElementVisible(TitleText_Txb(index)).clear();
    	TitleText_Txb(index).sendKeys(title);
    }
    
    public void VerifyAltTextValue(String index, String value) throws Exception {
    	
    	Reporter.log("Assert that the Alt Text box with index '" + index + "' value matches '" + value + "'.");
    	Assert.assertEquals(waitFor.ElementVisible(AltText_Txb(index)).getAttribute("value"), value);
    }
    
    public void EnterAltText(String index, String altTxt) throws Exception {
    	
    	Reporter.log("Enter '" + altTxt + "' in the 'Alt Text' text box with index '" + index + "'.");
    	waitFor.ElementVisible(AltText_Txb(index)).clear();
    	AltText_Txb(index).sendKeys(altTxt);
    }
    
    public void VerifySourceValue(String index, String value) throws Exception {
    	
    	Reporter.log("Assert that the Source box with index '" + index + "' value matches '" + value + "'.");
    	Assert.assertEquals(waitFor.ElementVisible(Source_Txb(index)).getAttribute("value"), value);
    }
    
    public void EnterSource(String index, String source) throws Exception {
    	
    	Reporter.log("Enter '" + source + "' in the 'Source' text box with index '" + index + "'.");
    	waitFor.ElementVisible(Source_Txb(index)).clear();
    	Source_Txb(index).sendKeys(source);
    }
    
    public void VerifyCreditValue(String index, String value) throws Exception {
    	
    	Reporter.log("Assert that the Credit Text box with index '" + index + "' value matches '" + value + "'.");
    	Assert.assertEquals(waitFor.ElementVisible(Credit_Txb(index)).getAttribute("value"), value);
    }
    
    public void EnterCreditValue(String index, String value) throws Exception {
    	
    	Reporter.log("Enter '" + value + "' in the 'Credit' text box with index '" + index + "'.");
    	waitFor.ElementVisible(Credit_Txb(index)).clear();
    	Credit_Txb(index).sendKeys(value);
    }

    public void VerifyCopyrightValue(String index, String value) throws Exception {
    	
    	Reporter.log("Assert that the Copyright Text box with index '" + index + "' value matches '" + value + "'.");
    	Assert.assertEquals(waitFor.ElementVisible(Copyright_Txb(index)).getAttribute("value"), value);
    }
    
    public void EnterCopyright(String index, String value) throws Exception {
    	
    	Reporter.log("Enter '" + value + "' in the 'Copyright' text box with index '" + index + "'.");
    	waitFor.ElementVisible(Copyright_Txb(index)).clear();
    	Copyright_Txb(index).sendKeys(value);
    }

    public void VerifyKeywordsValue(String index, String value) throws Exception {
    	
    	Reporter.log("Assert that the Keywords Text box with index '" + index + "' value matches '" + value + "'.");
    	Assert.assertEquals(waitFor.ElementVisible(Keywords_Txb(index)).getAttribute("value"), value);
    }
    
    public void EnterKeywordsValue(String index, String value) throws Exception {
    	
    	Reporter.log("Enter '" + value + "' in the 'Keywords' text box with index '" + index + "'.");
    	waitFor.ElementVisible(Keywords_Txb(index)).clear();
    	Keywords_Txb(index).sendKeys(value);
    }
    
    public void VerifyApplyEmbeddedMetadataBtnPresent() throws Exception {
    	
    	Reporter.log("Verify the 'Apply Embedded Metadata' button is present.");
    	ApplyEmbeddedMetadata_Btn.isDisplayed();
    }
    
    public void ClickApplyEmbeddedMetadataBtn() throws Exception {
    	
    	Reporter.log("Click the 'Apply Embedded Metadata' button.");
    	ApplyEmbeddedMetadata_Btn.click();
    }

    public void ClickSaveBtn(String index) throws Exception {
    	
    	Reporter.log("Click the 'Save' button with index '" + index + "'.");
    	Save_Btn(index).click();
    }
    
    public void ClickCancelLnk() throws Exception {
    	
    	Reporter.log("Click the 'Cancel' link.");
    	Cancel_Lnk.click();
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
    	waitFor.ElementVisible(TitleText_Txb("1"));
    	Thread.sleep(2500); //long pause required here... TODO - figure out dynamic wait
    }

    public void WaitForEditImageFrameClose() throws Exception {
    	
    	waitFor.ElementNotPresent(CloseWindow_Img);
    	
    }
    
    public void VerifyMPXPlayerPresent() throws Exception {
    	
    	Reporter.log("Verify the MPX Player frame is present.");
    	Assert.assertTrue(MPXPlayer_Frm.isDisplayed());
    	
    }
    
    public void VerifyMPXVideoLnkPresent(String videoTitleTxt) throws Exception {
    	
    	Reporter.log("Verify the MPX video link is present and contains text '" + videoTitleTxt + "'.");
    	Assert.assertTrue(MPXVideo_Lnk.getText().contains(videoTitleTxt));
    	
    }
}

