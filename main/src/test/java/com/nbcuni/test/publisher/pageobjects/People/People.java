package com.nbcuni.test.publisher.pageobjects.People;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com People Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.1 Date: July 3, 2014
 *********************************************/

public class People {

	private Driver webDriver;
	
    //PAGE OBJECT CONSTRUCTOR
    public People(Driver webDriver) {
    	this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
       
    }
    
    //PAGE OBJECT IDENTIFIERS
    private WebElement Username_Lnk(String userName) {
    	return webDriver.findElement(By.xpath("//a[contains(text(), 'Brandon.Clark')]"));
    }
    
    
    //PAGE OBJECT METHODS
    public void ClickUsernameLnk(String userName) throws Exception {
    	
    	Reporter.log("Click the '" + userName + "' link from the 'USERNAME' list.");
    	Username_Lnk(userName).click();
    	
    }
    
}

