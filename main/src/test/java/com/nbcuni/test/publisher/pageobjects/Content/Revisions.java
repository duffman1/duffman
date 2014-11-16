package com.nbcuni.test.publisher.pageobjects.Content;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;

import org.openqa.selenium.By;
import org.testng.Reporter;

/*********************************************
 * publisher.nbcuni.com Revisions State Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.1 Date: February 4, 2014
 *********************************************/

public class Revisions {

	private Config config;
	private Integer timeout;
    private WaitFor waitFor;
    private Interact interact;
    
	//PAGE OBJECT CONSTRUCTOR
	public Revisions(Driver webDriver) {
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webDriver, timeout);
        interact = new Interact(webDriver, timeout);
    }
	
	//PAGE OBJECT IDENTIFIERS
	private By UpdateState_Btn = By.id("edit-submit");
	
	private By MessageForStateChange_Txa = By.xpath("(//*[@id='edit-event-comment'])[1]");
	
	private By DeleteRevision_Btn = By.id("edit-submit");
    
	private By ChangeState_Ddl = By.id("edit-event");
	
	private By ContentItem_Ttl(String contentItemTtl) {
		return By.xpath("//table[contains(@class, 'views-table')]//a[contains(text(), '" + contentItemTtl + "')]");
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
    	interact.Click(waitFor.ElementVisible(EditExtendMenu_Btn(contentItemTtl)));
    	
    }
    
    public void ClickEditMenuDeleteBtn(String contentItemTtl) throws Exception {
    	
    	Reporter.log("Click the 'Delete' button.");
    	interact.Click(waitFor.ElementVisible(EditMenuDelete_Btn(contentItemTtl)));
    	
    }
    
    public void ClickEditMenuBtn(String contentItemTtl) throws Exception {
    	
    	Reporter.log("Click the 'Edit' menu button.");
    	interact.Click(waitFor.ElementVisible(EditMenu_Btn(contentItemTtl)));
    	
    }

    public void ClickShareMenuBtn(String contentItemTtl) throws Exception {
    	
    	Reporter.log("Click the 'Share' menu button.");
    	interact.Click(waitFor.ElementVisible(ShareMenu_Btn(contentItemTtl)));
    	
    }
    
    public void ClickScheduleMenuBtn(String contentItemTtl) throws Exception {
    	
    	Reporter.log("Click the 'Schedule' menu button.");
    	interact.Click(waitFor.ElementVisible(ScheduleMenu_Btn(contentItemTtl)));
    	
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
    	waitFor.ElementVisible(ContentItem_Ttl(contentItemTtl));
    	
    	Reporter.log("Verify the 'Edit/Delete' buttons are not present.");
    	waitFor.ElementNotPresent(EditExtendMenu_Btn(contentItemTtl));
    	
    }
    
    public void ClickUpdateStateBtn() throws Exception {    	
    	
    	Reporter.log("Click the 'Update State' button.");
    	interact.Click(waitFor.ElementVisible(UpdateState_Btn));
    	
    }
    
    public void EnterLogMessageForStateChange(String message) throws Exception {
        
    	Reporter.log("Enter '" + message + "' in the 'Message for state change' text area.");
    	interact.Type(waitFor.ElementVisible(MessageForStateChange_Txa), message);
    	
    }
    
    public void ClickDeleteConfirmBtn() throws Exception {
    	
    	Reporter.log("Click the 'Confirm Delete' button.");
    	interact.Click(waitFor.ElementVisible(DeleteRevision_Btn));
    	
    }
    
    public void SelectChangeState(String stateName) throws Exception {
        
        Reporter.log("Select '" + stateName + "' from the 'Change State' drop down list.");
        interact.Select(waitFor.ElementVisible(ChangeState_Ddl), stateName);
        
    }
    
}

