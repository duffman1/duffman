package com.nbcuni.test.publisher.pageobjects.People;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
 * publisher.nbcuni.com People Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.1 Date: July 3, 2014
 *********************************************/

public class People {

	private Driver webDriver;
	private Config config;
	private Integer timeout;
	private WaitFor waitFor;
	private Interact interact;
	
    //PAGE OBJECT CONSTRUCTOR
    public People(Driver webDriver) {
    	this.webDriver = webDriver;
    	config = new Config();
    	timeout = config.getConfigValueInt("WaitForWaitTime");
    	waitFor = new WaitFor(webDriver, timeout);
    	interact = new Interact(webDriver, timeout);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By AddUser_Lnk = By.xpath("//div[@id='content']//a[text()='Add user']");
    
    private By Username_Lnk(String userName) {
    	return By.xpath("//a[contains(text(), '" + userName.substring(0, 10) + "')]");
    }
    
    private By Edit_Lnk(String userName) {
    	return By.xpath("//a[contains(text(), '" + userName.substring(0, 10) + "')]/../..//a[text()='edit']");
    }
    
    private By Next_Lnk = By.cssSelector("a[title='Go to next page']");
    
    
    //PAGE OBJECT METHODS
    public void ClickAddUserLnk() throws Exception {
    	
    	Reporter.log("Click the 'Add user' link.");
    	interact.Click(waitFor.ElementVisible(AddUser_Lnk));
    	
    }
    public void ClickUsernameLnk(String userName) throws Exception {
    	
    	Reporter.log("Click the '" + userName + "' link from the 'USERNAME' list.");
    	interact.Click(waitFor.ElementVisible(Username_Lnk(userName)));
    	
    }
    
    public void ClickEditLnk(String userName) throws Exception {
    	
    	Reporter.log("Click the 'edit' link for '" + userName + "' from the 'USERNAME' list.");
    	interact.Click(waitFor.ElementVisible(Edit_Lnk(userName)));
    	
    }
    
    public void SeachForUsername(String userName) throws Exception {
    	
    	Reporter.log("Click the 'next >' link until user '" + userName + "' is present.");
    	webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    	
    	for (int count = 0; count < 100; count++) {
    		try {
    			webDriver.findElement(Username_Lnk(userName));
    			break;
    		}
    		catch (NoSuchElementException e) {
    			interact.Click(waitFor.ElementVisible(Next_Lnk));
    		}
    	}
    	
    	webDriver.manage().timeouts().implicitlyWait(config.getConfigValueInt("ImplicitWaitTime"), TimeUnit.SECONDS);
    	
    }
    
}

