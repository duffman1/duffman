package com.nbcuni.test.publisher.pageobjects.Content;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com Publishing Options Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 18, 2013
 *********************************************/

public class PublishingOptions {

    private Driver webDriver;
    
    //PAGE OBJECT CONSTRUCTOR
    public PublishingOptions(Driver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//a/strong[text()='Publishing options']")
    private WebElement PublishingOptions_Lnk;
    
    @FindBy(how = How.ID, using = "edit-revision")
    private WebElement CreateNewRevision_Cbx;
    
    @FindBy(how = How.ID, using = "edit-event")
    private WebElement ModerationState_Ddl;
    
    @FindBy(how = How.XPATH, using = "(//textarea[@id='edit-event-comment'])[1]")
    private WebElement LogMessageStateChange_Txa;
    
    @FindBy(how = How.ID, using = "edit-published")
    private WebElement Published_Cbx;
    
    @FindBy(how = How.ID, using = "edit-field-workbench-assigned-und-0-target-id")
    private WebElement AssignTo_Txb;
    
    private WebElement AutoComplete_Opt(String optionTxt) {
    	return webDriver.findElement(By.xpath("//div[@class='reference-autocomplete'][text()='" + optionTxt + "']"));
    }
    
    private String ScrollUp_Js() {
    	String jsScrollUp = "window.scrollBy(0,-500);";
    	return jsScrollUp;
    }
    
    
    //PAGE OBJECT METHODS
    public void ClickPublishingOptionsLnk() throws Exception {
    	
    	Reporter.log("Scroll the 'Publishing Options' link into view.");
    	PublishingOptions_Lnk.isDisplayed();
    	webDriver.executeScript(ScrollUp_Js());
    	
    	Reporter.log("Click the 'Publishing Options' link.");
    	PublishingOptions_Lnk.click();
    }
    
    public void ClickCreateNewRevisionCbx() throws Exception {
    	
    	Reporter.log("Check the 'Create New Revision' checkbox.");
    	CreateNewRevision_Cbx.click();
    }
    
    public void VerifyCreateNewRevisionCbxChecked() throws Exception {
    	
    	Reporter.log("Verify the 'Create New Revision' checkbox is checked.");
    	Assert.assertTrue(CreateNewRevision_Cbx.isSelected() == true);
    }
    
    public void SelectModerationState(String stateName) throws Exception {
    	
    	Reporter.log("Select '" + stateName + "' from the 'Moderation State' drop down list.");
    	new Select(ModerationState_Ddl).selectByVisibleText(stateName);
    }
    
    public void VerifyModerationStateValue(String stateValue) throws Exception {
    	
    	Reporter.log("Verify the 'Moderation State' drop down list value is '" + stateValue + "'.");
    	Assert.assertEquals(new Select(ModerationState_Ddl).getFirstSelectedOption().getText(), stateValue);
    }
    
    public void EnterMessageForStateChange(String messageTxt) throws Exception {
    	
    	Reporter.log("Enter '" + messageTxt + "' in the 'Message for State Change' text area.");
    	LogMessageStateChange_Txa.sendKeys(messageTxt);
    }
    
    public void UncheckPublishedCbx() throws Exception {
    	
    	if (Published_Cbx.isSelected() == true) {
    		Reporter.log("Uncheck the 'Published' checkbox");
    		Published_Cbx.click();
    	}
    }
    
    public void CheckPublishedCbx() throws Exception {
    	
    	if (Published_Cbx.isSelected() == false) {
    		Reporter.log("Check the 'Published' checkbox");
    		Published_Cbx.click();
    	}
    }
    
    public void VerifyPublishedCbxChecked() throws Exception {
    	
    	Reporter.log("Verify the 'Published' checkbox is checked.");
    	Assert.assertTrue(Published_Cbx.isSelected() == true);
    }
    
    public void VerifyAssignToValue(String userName) throws Exception {
    	
    	Reporter.log("Verify the 'Assign to' text box value is '" + userName + "'.");
    	Assert.assertTrue(AssignTo_Txb.getAttribute("value").contains(userName));
    }
    
    public void EnterAssignTo(String userName) throws Exception {
    	
    	Reporter.log("Enter '" + userName + "' in the 'Assign to' text box.");
    	AssignTo_Txb.clear();
    	AssignTo_Txb.sendKeys(userName);
    	
    	Reporter.log("Click the '" + userName + "' from the auto complete option list.");
    	AutoComplete_Opt(userName).click();
    }
    
    public void VerifyPublishedCbxNotCheckedAndNotEditable() throws Exception {
    	
    	Reporter.log("Verify the 'Published' checkbox is unchecked and is not enabled.");
    	Assert.assertFalse(Published_Cbx.isSelected());
    	Assert.assertFalse(Published_Cbx.isEnabled());
    }
    
}

