package com.nbcuni.test.publisher.pageobjects.Content;

import java.util.concurrent.TimeUnit;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

/*********************************************
 * publisher.nbcuni.com Revisions State Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.1 Date: February 4, 2014
 *********************************************/

public class Revisions {

	private static CustomWebDriver webDriver;
	private static AppLib applib;
    
	//PAGE OBJECT CONSTRUCTOR
	public Revisions(CustomWebDriver webDriver, AppLib applib) {
        Revisions.webDriver = webDriver;
        Revisions.applib = applib;
        PageFactory.initElements(webDriver, this);
    }
	
	//PAGE OBJECT IDENTIFIERS
	@FindBy(how = How.ID, using = "edit-submit")
    private static WebElement UpdateState_Btn;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Revisions')]")
    private static WebElement Revision_Lnk;
	
	@FindBy(how = How.XPATH, using = "(//*[@id='edit-event-comment'])[1]")
    private static WebElement MessageForStateChange_Txa;
	
	@FindBy(how = How.ID, using = "edit-submit")
    private static WebElement DeleteRevision_Btn;
    
	private static WebElement ContentItem_Ttl(String contentItemTtl) {
		return webDriver.findElement(By.xpath("//table[contains(@class, 'views-table')]//a[contains(text(), '" + contentItemTtl + "')]"));
	}
	
	private static WebElement EditExtendMenu_Btn(String contentItemTtl) {
		return webDriver.findElement(By.xpath("//table[contains(@class, 'views-table')]//a[contains(text(), '" + contentItemTtl + "')]/../..//a[text()='operations']"));
	}
	
	private static WebElement EditMenuDelete_Btn(String contentItemTtl) {
		return webDriver.findElement(By.xpath("//table[contains(@class, 'views-table')]//a[contains(text(), '" + contentItemTtl + "')]/../..//a[text()='Delete']"));
	}
	
	private static WebElement EditMenu_Btn(String contentItemTtl) {
		return webDriver.findElement(By.xpath("//table[contains(@class, 'views-table')]//a[contains(text(), '" + contentItemTtl + "')]/../..//a[text()='Edit']"));
	}
	
	private static WebElement ShareMenu_Btn(String contentItemTtl) {
		return webDriver.findElement(By.xpath("//table[contains(@class, 'views-table')]//a[contains(text(), '" + contentItemTtl + "')]/../..//a[text()='Share']"));
	}
	
	
	//PAGE OBJECT METHODS
    public void ClickEditExtendMenuBtn(String contentItemTtl) throws Exception {
    	
    	Reporter.log("Click the 'Edit' extend menu button.");
    	EditExtendMenu_Btn(contentItemTtl).click();
    	Thread.sleep(1000);
    }
    
    public void ClickEditMenuDeleteBtn(String contentItemTtl) throws Exception {
    	
    	Reporter.log("Click the 'Delete' button.");
    	EditMenuDelete_Btn(contentItemTtl).click();
    }
    
    public void ClickEditMenuBtn(String contentItemTtl) throws Exception {
    	
    	Reporter.log("Click the 'Edit' menu button.");
    	EditMenu_Btn(contentItemTtl).click();
    }

    public void ClickShareMenuBtn(String contentItemTtl) throws Exception {
    	
    	Reporter.log("Click the 'Share' menu button.");
    	ShareMenu_Btn(contentItemTtl).click();
    }
    
    public void VerifyContentItemEditDelete(String contentItemTtl) throws Exception {
    	
    	this.ClickEditExtendMenuBtn(contentItemTtl);
    	Reporter.log("Verify Edit menu 'Edit' button is present.");
    	EditMenu_Btn(contentItemTtl).isDisplayed();
    	
    	Reporter.log("Verify Edit menu 'Delete' buttton is present.");
    	EditMenuDelete_Btn(contentItemTtl).isDisplayed();
    	
    }

    public void VerifyContentItemEditDeleteNotPresent(String contentItemTtl) throws Exception {
    	
    	Reporter.log("Wait for the content item titled '" + contentItemTtl + "' to be present in revision list.");
    	ContentItem_Ttl(contentItemTtl).isDisplayed();
    	
    	webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    	boolean elPresent = true;
    	try {
    		
    		EditExtendMenu_Btn(contentItemTtl).isDisplayed();
    		elPresent = true;
    	}
    	catch (Exception e) {
    	
    		elPresent = false;
    	}
    	if (elPresent == true) {
    		Assert.fail("Content item Edit/Delete button present when it should not be");
    	}
    	webDriver.manage().timeouts().implicitlyWait(applib.getImplicitWaitTime(), TimeUnit.SECONDS);
    }
    
    public void ClickUpdateStateBtn() throws Exception {    	
    	
    	Reporter.log("Click the 'Update State' button.");
    	UpdateState_Btn.click();
    }
    
    public void ClickRevisionTab() throws Exception{
    	
    	Reporter.log("Click the 'Revision' link.");
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
    
    
    
}

