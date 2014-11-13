package com.nbcuni.test.publisher.pageobjects.Content;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
 * publisher.nbcuni.com Edit Image Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.1 Date: March 26, 2014
 *********************************************/

public class EditImage {

    private Config config;
    private WaitFor waitFor;
    private Interact interact;
    
    //PAGE OBJECT CONSTRUCTOR
    public EditImage(Driver webDriver) {
        config = new Config();
        Integer timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webDriver, timeout);
        interact = new Interact(webDriver, timeout);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By TitleText_Txb(String index) {
    	return By.xpath("(//input[contains(@id, 'edit-field-file-image-title-text-und-0-value')])[" + index + "]");
    }
    
    private By AltText_Txb(String index) {
    	return By.xpath("(//input[contains(@id, 'edit-field-file-image-alt-text-und-0-value')])[" + index + "]");
    }
    
    private By Source_Txb(String index) {
    	return By.xpath("(//input[contains(@id, 'edit-field-source-und-0-value')])[" + index + "]");
    }
    
    private By Credit_Txb(String index) {
    	return By.xpath("(//input[contains(@id, 'edit-field-credit-und-0-value')])[" + index + "]");
    }
    
    private By Copyright_Txb(String index) {
    	return By.xpath("(//input[contains(@id, 'edit-field-copyright-und-0-value')])[" + index + "]");
    }
    
    private By Keywords_Txb(String index) {
    	return By.xpath("(//input[contains(@id, 'edit-field-keywords-und-0-value')])[" + index + "]");
    }
    
    private By ApplyEmbeddedMetadata_Btn = By.id("edit-simple-exif");
    
    private By Save_Btn(String index) {
    	return By.xpath("(//input[@value='Save'])[" + index + "]");
    }
    
    private By SaveFromEditFrm_Btn = By.id("edit-submit--2");
    
    private By CloseWindow_Img = By.cssSelector("img[title='Close window']");
    
    private By MPXPlayer_Frm = By.xpath("//iframe[@id='pdk-player']");
    
    private By MPXVideo_Lnk = By.xpath("//div[contains(@id, 'media-edit')]//a[contains(@type, 'video/mpx')]");
    
    private By Cancel_Lnk = By.xpath("//a[text()='Cancel']");
    
    
    //PAGE OBJECT METHODS
    public void VerifyTitleTextValue(String index, String value) throws Exception {
    	
    	Reporter.log("Assert that the Title Text box with index '" + index + "' value matches '" + value + "'.");
    	Assert.assertEquals(waitFor.ElementVisible(TitleText_Txb(index)).getAttribute("value"), value);
    	
    }
    
    public void EnterTitleText(String index, String title) throws Exception {
    	
    	Reporter.log("Enter '" + title + "' in the 'Title Text' text box with index '" + index + "'.");
    	interact.Type(waitFor.ElementVisible(TitleText_Txb(index)), title);
    	
    }
    
    public void VerifyAltTextValue(String index, String value) throws Exception {
    	
    	Reporter.log("Assert that the Alt Text box with index '" + index + "' value matches '" + value + "'.");
    	Assert.assertEquals(waitFor.ElementVisible(AltText_Txb(index)).getAttribute("value"), value);
    }
    
    public void EnterAltText(String index, String altTxt) throws Exception {
    	
    	Reporter.log("Enter '" + altTxt + "' in the 'Alt Text' text box with index '" + index + "'.");
    	interact.Type(waitFor.ElementVisible(AltText_Txb(index)), altTxt);
    	
    }
    
    public void VerifySourceValue(String index, String value) throws Exception {
    	
    	Reporter.log("Assert that the Source box with index '" + index + "' value matches '" + value + "'.");
    	Assert.assertEquals(waitFor.ElementVisible(Source_Txb(index)).getAttribute("value"), value);
    }
    
    public void EnterSource(String index, String source) throws Exception {
    	
    	Reporter.log("Enter '" + source + "' in the 'Source' text box with index '" + index + "'.");
    	interact.Type(waitFor.ElementVisible(Source_Txb(index)), source);
    	
    }
    
    public void VerifyCreditValue(String index, String value) throws Exception {
    	
    	Reporter.log("Assert that the Credit Text box with index '" + index + "' value matches '" + value + "'.");
    	Assert.assertEquals(waitFor.ElementVisible(Credit_Txb(index)).getAttribute("value"), value);
    }
    
    public void EnterCreditValue(String index, String value) throws Exception {
    	
    	Reporter.log("Enter '" + value + "' in the 'Credit' text box with index '" + index + "'.");
    	interact.Type(waitFor.ElementVisible(Credit_Txb(index)), value);
    	
    }

    public void VerifyCopyrightValue(String index, String value) throws Exception {
    	
    	Reporter.log("Assert that the Copyright Text box with index '" + index + "' value matches '" + value + "'.");
    	Assert.assertEquals(waitFor.ElementVisible(Copyright_Txb(index)).getAttribute("value"), value);
    }
    
    public void EnterCopyright(String index, String value) throws Exception {
    	
    	Reporter.log("Enter '" + value + "' in the 'Copyright' text box with index '" + index + "'.");
    	interact.Type(waitFor.ElementVisible(Copyright_Txb(index)), value);
    	
    }

    public void VerifyKeywordsValue(String index, String value) throws Exception {
    	
    	Reporter.log("Assert that the Keywords Text box with index '" + index + "' value matches '" + value + "'.");
    	Assert.assertEquals(waitFor.ElementVisible(Keywords_Txb(index)).getAttribute("value"), value);
    }
    
    public void EnterKeywordsValue(String index, String value) throws Exception {
    	
    	Reporter.log("Enter '" + value + "' in the 'Keywords' text box with index '" + index + "'.");
    	interact.Type(waitFor.ElementVisible(Keywords_Txb(index)), value);
    	
    }
    
    public void VerifyApplyEmbeddedMetadataBtnPresent() throws Exception {
    	
    	Reporter.log("Verify the 'Apply Embedded Metadata' button is present.");
    	waitFor.ElementVisible(ApplyEmbeddedMetadata_Btn);
    	
    }
    
    public void ClickApplyEmbeddedMetadataBtn() throws Exception {
    	
    	Reporter.log("Click the 'Apply Embedded Metadata' button.");
    	interact.Click(waitFor.ElementVisible(ApplyEmbeddedMetadata_Btn));
    	
    }

    public void ClickSaveBtn(String index) throws Exception {
    	
    	Reporter.log("Click the 'Save' button with index '" + index + "'.");
    	interact.Click(waitFor.ElementVisible(Save_Btn(index)));
    	
    }
    
    public void ClickCancelLnk() throws Exception {
    	
    	Reporter.log("Click the 'Cancel' link.");
    	interact.Click(waitFor.ElementVisible(Cancel_Lnk));
    	
    }
    
    public void ClickSaveFromEditFrmBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save' button.");
    	interact.Click(waitFor.ElementPresent(SaveFromEditFrm_Btn));
    	
    }
    
    public void ClickCloseWindowImg() throws Exception {
    	
    	Reporter.log("Click the 'Close Window' image.");
    	interact.Click(waitFor.ElementVisible(CloseWindow_Img));
    	this.WaitForEditImageFrameClose();
    	
    }
    
    public void WaitForEditImageFrameOpen() throws Exception {
    	
    	waitFor.ElementVisible(CloseWindow_Img);
    	waitFor.ElementVisible(TitleText_Txb("1"));
    	
    }

    public void WaitForEditImageFrameClose() throws Exception {
    	
    	waitFor.ElementNotPresent(CloseWindow_Img);
    	
    }
    
    public void VerifyMPXObjectPresent() throws Exception {
    	
    	Reporter.log("Verify the MPX Player frame is present.");
    	WebElement el = waitFor.OneElementOrAnotherPresent(MPXPlayer_Frm, MPXVideo_Lnk);
    	waitFor.ElementVisible(el);
    	
    }
    
}

