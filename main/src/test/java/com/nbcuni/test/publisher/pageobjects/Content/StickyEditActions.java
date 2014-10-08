package com.nbcuni.test.publisher.pageobjects.Content;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com Sticky Edit Actions Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: March 10, 2014
 *********************************************/

public class StickyEditActions {

    //PAGE OBJECT CONSTRUCTOR
    public StickyEditActions(Driver webDriver) {
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'sticky-element')]/input[@id='edit-submit']")
    private WebElement Save_Btn;
    
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'sticky-element')]/input[@id='edit-preview']")
    private WebElement Preview_Btn;
    
    
    //PAGE OBJECT METHODS
    public void VerifySaveBtnPresent() throws Exception {
    	
    	Reporter.log("Verify the sticky edit 'Save' button is present.");
    	Save_Btn.isDisplayed();
    }
    
    public void ClickSaveBtnPresent() throws Exception {
    	
    	Reporter.log("Click the 'Save' button.");
    	Save_Btn.click();
    }
    
    public void VerifyPreviewBtnPresent() throws Exception {
    	
    	Reporter.log("Verify the sticky edit 'Preview' button is present.");
    	Preview_Btn.isDisplayed();
    }
    
}

