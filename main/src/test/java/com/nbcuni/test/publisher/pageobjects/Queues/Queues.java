package com.nbcuni.test.publisher.pageobjects.Queues;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;

/*********************************************
 * publisher.nbcuni.com Queues Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.1 Date: March 25, 2014
 *********************************************/

public class Queues {

    private static CustomWebDriver webDriver;
    private static AppLib applib;
    
    //PAGE OBJECT CONSTRUCTOR
    public Queues(CustomWebDriver webDriver, AppLib applib) {
        Queues.webDriver = webDriver;
        Queues.applib = applib;
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.CSS, using = "input[id*='edit-title']")
    private static WebElement Title_Txb;
    
    @FindBy(how = How.CSS, using = "input[value='Save queue']")
    private static WebElement SaveQueue_Btn;
    
    @FindBy(how = How.XPATH, using = "//a[contains(text(), 'Edit')]")
    private static WebElement Edit_Tab;
    
    @FindBy(how = How.LINK_TEXT, using = "Add Promo Queue")
    private static WebElement AddPromoQueue_Lnk;
    
    private static WebElement QueuesList_Ttl(String queueTitle) {
    	return webDriver.findElement(By.xpath("//table[contains(@class, 'views-table')]//td[contains(text(), '" + queueTitle + "')]/.."));
    }
    
    private static WebElement EditExtendMenu_Btn(String queueTitle) {
		return webDriver.findElement(By.xpath("//table[contains(@class, 'views-table')]//td[contains(text(), '" + queueTitle + "')]/..//a[text()='operations']"));
	}
	
	private static WebElement EditMenuDelete_Btn(String queueTitle) {
		return webDriver.findElement(By.xpath("//table[contains(@class, 'views-table')]//td[contains(text(), '" + queueTitle + "')]/..//a[text()='Delete']"));
	}
	
	private static WebElement EditMenu_Btn(String queueTitle) {
		return webDriver.findElement(By.xpath("//table[contains(@class, 'views-table')]//td[contains(text(), '" + queueTitle + "')]/..//a[text()='Edit']"));
	}
	
	private static WebElement QueueItem_Txb(String itemTxbIndex) {
		return webDriver.findElement(By.xpath("(//input[contains(@id, 'edit-field-qt-promo-queue')][1])[" + itemTxbIndex + "]"));
	}
	
	private static WebElement AutoComplete_Opt(String optionTxt) {
    	return webDriver.findElement(By.xpath("//div[@class='reference-autocomplete'][contains(text(),'" + optionTxt + "')]"));
    }
    
	
    //PAGE OBJECT METHODS
    public void EnterTitle(String queueTitle) throws Exception {
    	
    	Reporter.log("Enter '" + queueTitle + "' in the 'Title' text box.");
    	Title_Txb.clear();
    	Title_Txb.sendKeys(queueTitle);
    }
    
    public void ClickSaveQueueBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save Queue' button.");
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
    	AutoComplete_Opt(queueItemTitle).click();
    	Thread.sleep(1000); //TODO - replace with dynamic wait that waits for auto complete element to no longer be present
    	
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

