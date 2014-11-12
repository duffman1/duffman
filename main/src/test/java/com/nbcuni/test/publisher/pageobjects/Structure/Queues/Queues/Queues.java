package com.nbcuni.test.publisher.pageobjects.Structure.Queues.Queues;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
 * publisher.nbcuni.com Queues Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.1 Date: March 25, 2014
 *********************************************/

public class Queues {

    private Config config;
    private WaitFor waitFor;
    
    //PAGE OBJECT CONSTRUCTOR
    public Queues(Driver webDriver) {
        config = new Config();
        PageFactory.initElements(webDriver, this);
        waitFor = new WaitFor(webDriver, config.getConfigValueInt("WaitForWaitTime"));
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By Title_Txb = By.cssSelector("input[id*='edit-title']");
    
    private By LogMessage_Txa = By.id("edit-event-comment");
    
    private By SaveQueue_Btn = By.cssSelector("input[value='Save queue']");
    
    private By AddPromoQueue_Lnk = By.linkText("Add Promo Queue");
    
    private By QueuesList_Ttl(String queueTitle) {
    	return By.xpath("//table[contains(@class, 'views-table')]//td[contains(text(), '" + queueTitle + "')]/..");
    }
    
    private By EditExtendMenu_Btn(String queueTitle) {
		return By.xpath("//table[contains(@class, 'views-table')]//td[contains(text(), '" + queueTitle + "')]/..//a[text()='operations']");
	}
	
	private By EditMenuDelete_Btn(String queueTitle) {
		return By.xpath("//table[contains(@class, 'views-table')]//td[contains(text(), '" + queueTitle + "')]/..//a[text()='Delete']");
	}
	
	private By EditMenu_Btn(String queueTitle) {
		return By.xpath("//table[contains(@class, 'views-table')]//td[contains(text(), '" + queueTitle + "')]/..//a[text()='Edit']");
	}
	
	private By QueueItem_Txb(String itemTxbIndex) {
		return By.xpath("(//input[contains(@id, 'edit-field-qt-promo-queue')][1])[" + itemTxbIndex + "]");
	}
	
	private By AutoComplete_Opt(String optionTxt) {
    	return By.xpath("//div[@class='reference-autocomplete'][contains(text(),'" + optionTxt + "')]");
    }
    
	
    //PAGE OBJECT METHODS
    public void EnterTitle(String queueTitle) throws Exception {
    	
    	Reporter.log("Enter '" + queueTitle + "' in the 'Title' text box.");
    	WebElement ele = waitFor.ElementVisible(Title_Txb);
    	ele.clear();
    	ele.sendKeys(queueTitle);
    	
    }
    
    public void EnterLogMessageStateChange(String text) throws Exception {
    	
    	Reporter.log("Enter '" + text + "' in the 'Log message for this state change' text area.");
    	WebElement ele = waitFor.ElementVisible(LogMessage_Txa);
    	ele.clear();
    	ele.sendKeys(text);
    	
    }
    
    public void ClickSaveQueueBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save Queue' button.");
    	waitFor.ElementVisible(SaveQueue_Btn).click();
    	
    }
    
    public void ClickAddPromoQueueLnk() throws Exception {
    	
    	Reporter.log("Click the 'Add Promo Queue' link.");
    	waitFor.ElementVisible(AddPromoQueue_Lnk).click();
    	
    }
    
    public void VerifyQueuesInList(List<String> allQueueTitles) throws Exception {
    	
    	for (String queueTitle : allQueueTitles) {
    		Reporter.log("Verify the queue titled '" + queueTitle + "' is present in the Queues list.");
    		waitFor.ElementVisible(QueuesList_Ttl(queueTitle));
    	}
    	
    }
    
    public void ClickEditQueueExtendMenuBtn(String queueTitle) throws Exception {
    	
    	Reporter.log("Click the 'Expand' arrow to extend the edit menu for queue titled '" + queueTitle + "'.");
    	waitFor.ElementVisible(EditExtendMenu_Btn(queueTitle)).click();
    	
    }
    
    public void ClickDeleteQueueMenuBtn(String queueTitle) throws Exception {
    	
    	Reporter.log("Click the 'Delete' button for the queue titled '" + queueTitle + "'.");
    	waitFor.ElementVisible(EditMenuDelete_Btn(queueTitle)).click();
    	
    }
    
    public void ClickEditQueueMenuBtn(String queueTitle) throws Exception {
    	
    	Reporter.log("Click the 'Edit' button for queue titled + '" + queueTitle + "'.");
    	waitFor.ElementVisible(EditMenu_Btn(queueTitle)).click();
    	
    }
    
    public void EnterQueueItem(String queueItemTitle, String itemTxbIndex) throws Exception {
    	
    	Reporter.log("Enter '" + queueItemTitle + "' in the queue item text box indexed '" + itemTxbIndex + "'.");
    	WebElement ele = waitFor.ElementVisible(QueueItem_Txb(itemTxbIndex));
    	ele.sendKeys(queueItemTitle);
    	
    	Reporter.log("Click the '" + queueItemTitle + "' from the auto complete option list.");
    	waitFor.ElementVisible(AutoComplete_Opt(queueItemTitle));
    	ele.sendKeys(Keys.DOWN);
    	ele.sendKeys(Keys.ENTER);
    	waitFor.ElementNotPresent(AutoComplete_Opt(queueItemTitle));
    	
    }
    
    public void VerifyQueueEditDeleteNotPresent(String queueTitle) throws Exception {
    	
    	Reporter.log("Verify the queue titled '" + queueTitle + "' is present.");
    	waitFor.ElementVisible(QueuesList_Ttl(queueTitle));

        Reporter.log("Verify the 'Edit/Delete' menu options are not present for the queue titled '" + queueTitle + "'.");
        waitFor.ElementNotPresent(EditExtendMenu_Btn(queueTitle));
        
    }
    
    
}

