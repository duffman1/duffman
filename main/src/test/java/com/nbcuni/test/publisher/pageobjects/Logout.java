package com.nbcuni.test.publisher.pageobjects;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com Logout Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 13, 2013
 *********************************************/

public class Logout {

    private Driver webDriver;
    private Config config;
    
    //PAGE OBJECT CONSTRUCTOR
    public Logout(Driver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        config = new Config();
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "(//a[text()='Log out'])[1]")
    private WebElement LogOut_Btn;
    
    
    //PAGE OBJECT METHODS
    public void ClickLogoutBtn() throws Exception {
    	
    	Reporter.log("Click the 'Logout' button.");
    	Thread.sleep(1000); //slight pause required here
    	webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		for (int second = 0; ; second++) {
    		
    		if (second >= 30) {
                Assert.fail("Failed to logout after timeout");
            }
    		
    		Boolean logoutLnkPresent = true;
    		try {
    			LogOut_Btn.getText();
    			logoutLnkPresent = true;
    		}
    		catch (WebDriverException e) {
    			logoutLnkPresent = false;
    		}
    		
            if (logoutLnkPresent == true) {
            	try {
            		LogOut_Btn.click();
            		
            	}
            	catch (WebDriverException e) {
            		webDriver.executeScript("arguments[0].click();", LogOut_Btn);
            		
            	}
            }
            else {
            	break;
            }
            Thread.sleep(500);
        }
    	webDriver.manage().timeouts().implicitlyWait(config.getImplicitWaitTime(), TimeUnit.SECONDS);
    	
    }
    
}

