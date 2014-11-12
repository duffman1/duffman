package com.nbcuni.test.publisher.pageobjects.Content;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.WaitFor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

/*********************************************
 * publisher.nbcuni.com Revisions State Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.1 Date: February 4, 2014
 *********************************************/

public class Revisions {

	private Driver webDriver;
	private Config config;
    private WaitFor waitFor;
    
	//PAGE OBJECT CONSTRUCTOR
	public Revisions(Driver webDriver) {
        this.webDriver = webDriver;
        config = new Config();
        PageFactory.initElements(webDriver, this);
        waitFor = new WaitFor(webDriver, config.getConfigValueInt("WaitForWaitTime"));
    }
	
	//PAGE OBJECT IDENTIFIERS
	@FindBy(how = How.ID, using = "edit-submit")
    private WebElement UpdateState_Btn;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Revisions')]")
    private WebElement Revision_Lnk;
	
	@FindBy(how = How.XPATH, using = "(//*[@id='edit-event-comment'])[1]")
    private WebElement MessageForStateChange_Txa;
	
	@FindBy(how = How.ID, using = "edit-submit")
    private WebElement DeleteRevision_Btn;
    
	@FindBy(how = How.ID, using = "edit-event")
    private WebElement ChangeState_Ddl;
	
	private WebElement ContentItem_Ttl(String contentItemTtl) {
		return webDriver.findElement(By.xpath("//table[contains(@class, 'views-table')]//a[contains(text(), '" + contentItemTtl + "')]"));
	}
	
	private By EditExtendMenu_Btn(String contentItemTtl) {
		return By.xpath("//table[contains(@class, 'views-table')]//a[contains(text(), '" + contentItemTtl + "')]/../..//a[text()='operations']");
	}
	
	private By EditMenuDelete_Btn(String contentItemTtl) {
		return By.xpath("//table[contains(@class, 'views-table')]//a[contains(text(), '" + contentItemTtl + "')]/../..//a[text()='Delete']");
	}
	
	private By EditMenu_Btn(String contentItemTtl) {
		return By.xpath("//table[contains(@class, 'views-table')]//a[contains(text(), '" + contentItemTtl + "')]/../..//a[text()='Edit']");
	}
	
	private By ShareMenu_Btn(String contentItemTtl) {
		return By.xpath("//table[contains(@class, 'views-table')]//a[contains(text(), '" + contentItemTtl + "')]/../..//a[text()='Share']");
	}
	
	private By ScheduleMenu_Btn(String contentItemTtl) {
		return By.xpath("//table[contains(@class, 'views-table')]//a[contains(text(), '" + contentItemTtl + "')]/../..//a[text()='Schedule']");
	}
	
	
	//PAGE OBJECT METHODS
    public void ClickEditExtendMenuBtn(String contentItemTtl) throws Exception {
    	
    	Reporter.log("Click the 'Edit' extend menu button.");
    	Thread.sleep(500);
    	waitFor.ElementVisible(EditExtendMenu_Btn(contentItemTtl)).click();
    	Thread.sleep(500); //pauses before and after required - do not edit these sleep times.
    }
    
    public void ClickEditMenuDeleteBtn(String contentItemTtl) throws Exception {
    	
    	Reporter.log("Click the 'Delete' button.");
    	waitFor.ElementVisible(EditMenuDelete_Btn(contentItemTtl)).click();
    }
    
    public void ClickEditMenuBtn(String contentItemTtl) throws Exception {
    	
    	Reporter.log("Click the 'Edit' menu button.");
    	waitFor.ElementVisible(EditMenu_Btn(contentItemTtl)).click();
    	Thread.sleep(250); //slight pause required here for successful frame switch
    }

    public void ClickShareMenuBtn(String contentItemTtl) throws Exception {
    	
    	Reporter.log("Click the 'Share' menu button.");
    	waitFor.ElementVisible(ShareMenu_Btn(contentItemTtl)).click();
    }
    
    public void ClickScheduleMenuBtn(String contentItemTtl) throws Exception {
    	
    	Reporter.log("Click the 'Schedule' menu button.");
    	waitFor.ElementVisible(ScheduleMenu_Btn(contentItemTtl)).click();
    }
    
    public void VerifyContentItemEditDelete(String contentItemTtl) throws Exception {
    	
    	this.ClickEditExtendMenuBtn(contentItemTtl);
    	Reporter.log("Verify Edit menu 'Edit' button is present.");
    	waitFor.ElementVisible(EditMenu_Btn(contentItemTtl));
    	
    	Reporter.log("Verify Edit menu 'Delete' buttton is present.");
    	waitFor.ElementVisible(EditMenuDelete_Btn(contentItemTtl));
    	
    }

    public void VerifyContentItemEditDeleteNotPresent(String contentItemTtl) throws Exception {
    	
    	Reporter.log("Wait for the content item titled '" + contentItemTtl + "' to be present in revision list.");
    	ContentItem_Ttl(contentItemTtl).isDisplayed();
    	
    	Reporter.log("Verify the 'Edit/Delete' buttons are not present.");
    	waitFor.ElementNotPresent(EditExtendMenu_Btn(contentItemTtl));
    	
    }
    
    public void ClickUpdateStateBtn() throws Exception {    	
    	
    	Reporter.log("Click the 'Update State' button.");
    	UpdateState_Btn.click();
    }
    
    public void ClickRevisionTab() throws Exception{
    	
    	Reporter.log("Click the 'Revision' link.");
    	waitFor.ElementVisible(Revision_Lnk);
    	Thread.sleep(500);
    	Revision_Lnk.click();
    }

    public void EnterLogMessageForStateChange(String message) throws Exception {
        
    	Reporter.log("Enter '" + message + "' in the 'Message for state change' text area.");
    	MessageForStateChange_Txa.sendKeys(message);

    }
    
    public void ClickDeleteConfirmBtn() throws Exception {
    	
    	Reporter.log("Click the 'Confirm Delete' button.");
    	DeleteRevision_Btn.click();
    }
    
    public void SelectChangeState(String stateName) throws Exception {
        
        Reporter.log("Select '" + stateName + "' from the 'Change State' drop down list.");
        new Select(waitFor.ElementVisible(ChangeState_Ddl)).selectByVisibleText(stateName);
    }
    
}

