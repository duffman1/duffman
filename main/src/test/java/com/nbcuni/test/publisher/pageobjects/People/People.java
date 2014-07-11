package com.nbcuni.test.publisher.pageobjects.People;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.pageobjects.Overlay;

/*********************************************
 * publisher.nbcuni.com People Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.1 Date: July 3, 2014
 *********************************************/

public class People {

	private Driver webDriver;
	private Overlay overlay;
	private AppLib applib;
	
    //PAGE OBJECT CONSTRUCTOR
    public People(Driver webDriver, AppLib applib) {
    	this.webDriver = webDriver;
    	this.applib = applib;
    	PageFactory.initElements(webDriver, this);
        overlay = new Overlay (webDriver, applib);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private WebElement Username_Lnk(String userName) {
    	return webDriver.findElement(By.xpath("//a[contains(text(), '" + userName.substring(0, 10) + "')]"));
    }
    
    private WebElement Edit_Lnk(String userName) {
    	return webDriver.findElement(By.xpath("//a[contains(text(), '" + userName.substring(0, 10) + "')]/../..//a[text()='edit']"));
    }
    
    @FindBy(how = How.CSS, using = "a[title='Go to next page']")
    private WebElement Next_Lnk;
    
    
    //PAGE OBJECT METHODS
    public void ClickUsernameLnk(String userName) throws Exception {
    	
    	Reporter.log("Click the '" + userName + "' link from the 'USERNAME' list.");
    	Username_Lnk(userName).click();
    	
    }
    
    public void ClickEditLnk(String userName) throws Exception {
    	
    	Reporter.log("Click the 'edit' link for '" + userName + "' from the 'USERNAME' list.");
    	Edit_Lnk(userName).click();
    	
    }
    
    public void SeachForUsername(String userName) throws Exception {
    	
    	Reporter.log("Click the 'next >' link until user '" + userName + "' is present.");
    	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	
    	for (int count = 0; count < 100; count++) {
    		try {
    			Username_Lnk(userName);
    			break;
    		}
    		catch (NoSuchElementException e) {
    			Next_Lnk.click();
    			overlay.SwitchToActiveFrame();
    		}
    	}
    	
    	webDriver.manage().timeouts().implicitlyWait(applib.getImplicitWaitTime(), TimeUnit.SECONDS);
    	
    }
    
}

