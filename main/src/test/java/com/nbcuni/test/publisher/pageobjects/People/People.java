package com.nbcuni.test.publisher.pageobjects.People;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import java.util.concurrent.TimeUnit;

/*********************************************
 * publisher.nbcuni.com People Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.1 Date: July 3, 2014
 *********************************************/

public class People {

	private WebDriver webWebWebDriver;
	private Config config;
	private Integer timeout;
	private WaitFor waitFor;
	private Interact interact;
	
    //PAGE OBJECT CONSTRUCTOR
    public People(WebDriver webWebWebDriver) {
    	this.webWebWebDriver = webWebWebDriver;
    	config = new Config();
    	timeout = config.getConfigValueInt("WaitForWaitTime");
    	waitFor = new WaitFor(webWebWebDriver, timeout);
    	interact = new Interact(webWebWebDriver, timeout);
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
    	webWebWebDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    	
    	for (int count = 0; count < 100; count++) {
    		try {
    			webWebWebDriver.findElement(Username_Lnk(userName));
    			break;
    		}
    		catch (NoSuchElementException e) {
    			interact.Click(waitFor.ElementVisible(Next_Lnk));
    		}
    	}
    	
    	webWebWebDriver.manage().timeouts().implicitlyWait(config.getConfigValueInt("ImplicitWaitTime"), TimeUnit.SECONDS);
    	
    }
    
}

