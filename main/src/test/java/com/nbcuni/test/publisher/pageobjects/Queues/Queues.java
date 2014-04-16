package com.nbcuni.test.publisher.pageobjects.Queues;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
 * publisher.nbcuni.com Queues Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.1 Date: March 25, 2014
 *********************************************/

public class Queues {

    private Driver webDriver;
    private AppLib applib;
    private WebDriverWait wait;
    
    //PAGE OBJECT CONSTRUCTOR
    public Queues(Driver webDriver, AppLib applib) {
        this.webDriver = webDriver;
        this.applib = applib;
        PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(webDriver, 10);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.CSS, using = "input[id*='edit-title']")
    private WebElement Title_Txb;
    
    @FindBy(how = How.ID, using = "edit-event-comment")
    private WebElement LogMessage_Txa;
    
    @FindBy(how = How.CSS, using = "input[value='Save queue']")
    private WebElement SaveQueue_Btn;
    
    @FindBy(how = How.XPATH, using = "//a[contains(text(), 'Edit')]")
    private WebElement Edit_Tab;
    
    @FindBy(how = How.LINK_TEXT, using = "Add Promo Queue")
    private WebElement AddPromoQueue_Lnk;
    
    private WebElement QueuesList_Ttl(String queueTitle) {
    	return webDriver.findElement(By.xpath("//table[contains(@class, 'views-table')]//td[contains(text(), '" + queueTitle + "')]/.."));
    }
    
    private WebElement EditExtendMenu_Btn(String queueTitle) {
		return webDriver.findElement(By.xpath("//table[contains(@class, 'views-table')]//td[contains(text(), '" + queueTitle + "')]/..//a[text()='operations']"));
	}
	
	private WebElement EditMenuDelete_Btn(String queueTitle) {
		return webDriver.findElement(By.xpath("//table[contains(@class, 'views-table')]//td[contains(text(), '" + queueTitle + "')]/..//a[text()='Delete']"));
	}
	
	private WebElement EditMenu_Btn(String queueTitle) {
		return webDriver.findElement(By.xpath("//table[contains(@class, 'views-table')]//td[contains(text(), '" + queueTitle + "')]/..//a[text()='Edit']"));
	}
	
	private WebElement QueueItem_Txb(String itemTxbIndex) {
		return webDriver.findElement(By.xpath("(//input[contains(@id, 'edit-field-qt-promo-queue')][1])[" + itemTxbIndex + "]"));
	}
	
	private WebElement AutoComplete_Opt(String optionTxt) {
    	return webDriver.findElement(By.xpath("//div[@class='reference-autocomplete'][contains(text(),'" + optionTxt + "')]"));
    }
    
	
    //PAGE OBJECT METHODS
    public void EnterTitle(String queueTitle) throws Exception {
    	
    	Reporter.log("Enter '" + queueTitle + "' in the 'Title' text box.");
    	Title_Txb.clear();
    	Title_Txb.sendKeys(queueTitle);
    }
    
    public void EnterLogMessageStateChange(String text) throws Exception {
    	
    	Reporter.log("Enter '" + text + "' in the 'Log message for this state change' text area.");
    	LogMessage_Txa.clear();
    	LogMessage_Txa.sendKeys(text);
    }
    
    public void ClickSaveQueueBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save Queue' button.");
    	Thread.sleep(1000); //slight pause required here.
    	SaveQueue_Btn.click();
    }
    
    public void ClickAddPromoQueueLnk() throws Exception {
    	
    	Reporter.log("Click the 'Add Promo Queue' link.");
    	AddPromoQueue_Lnk.click();
    }
    
    public void VerifyQueuesInList(List<String> allQueueTitles) throws Exception {
    	
    	for (String queueTitle : allQueueTitles) {
    		Reporter.log("Verify the queue titled '" + queueTitle + "' is present in the Queues list.");
    		QueuesList_Ttl(queueTitle).isDisplayed();
    	}
    }
    
    public void ClickEditQueueExtendMenuBtn(String queueTitle) throws Exception {
    	
    	Reporter.log("Click the 'Expand' arrow to extend the edit menu for queue titled '" + queueTitle + "'.");
    	EditExtendMenu_Btn(queueTitle).click();
    }
    
    public void ClickDeleteQueueMenuBtn(String queueTitle) throws Exception {
    	
    	Reporter.log("Click the 'Delete' button for the queue titled '" + queueTitle + "'.");
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(
    			EditMenuDelete_Btn(queueTitle))).click();
    	
    }
    
    public void ClickEditQueueMenuBtn(String queueTitle) throws Exception {
    	
    	Reporter.log("Click the 'Edit' button for queue titled + '" + queueTitle + "'.");
    	EditMenu_Btn(queueTitle).click();
    }
    
    public void EnterQueueItem(String queueItemTitle, String itemTxbIndex) throws Exception {
    	
    	Reporter.log("Enter '" + queueItemTitle + "' in the queue item text box indexed '" + itemTxbIndex + "'.");
    	QueueItem_Txb(itemTxbIndex).sendKeys(queueItemTitle);
    	
    	Reporter.log("Click the '" + queueItemTitle + "' from the auto complete option list.");
    	wait.until(ExpectedConditions.visibilityOf(AutoComplete_Opt(queueItemTitle)));
    	QueueItem_Txb(itemTxbIndex).sendKeys(Keys.DOWN);
    	QueueItem_Txb(itemTxbIndex).sendKeys(Keys.ENTER);
    	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	for (int second = 0; ; second++){
            if (second >= 60) {
                Assert.fail("AutoComplete option titled '" + queueItemTitle + "' is still present after timeout");}
            try{
            	AutoComplete_Opt(queueItemTitle).isDisplayed();
            }
            catch (Exception e){
            	break;
            }
            Thread.sleep(500);
        }
    	webDriver.manage().timeouts().implicitlyWait(applib.getImplicitWaitTime(), TimeUnit.SECONDS);
    }
    
    public void VerifyQueueEditDeleteNotPresent(String queueTitle) throws Exception {
    	
    	Reporter.log("Verify the queue titled '" + queueTitle + "' is present.");
    	QueuesList_Ttl(queueTitle).isDisplayed();

        webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        boolean elPresent = false;

        Reporter.log("Verify the 'Edit/Delete' menu options are not present for the queue titled '" + queueTitle + "'.");
        try {
        	EditExtendMenu_Btn(queueTitle).isDisplayed();
            elPresent = true;
        }
        catch (Exception e) {
            elPresent = false;
        }

        Assert.assertFalse(elPresent);

        webDriver.manage().timeouts().implicitlyWait(applib.getImplicitWaitTime(), TimeUnit.SECONDS);
    	
    }
    
    
}

