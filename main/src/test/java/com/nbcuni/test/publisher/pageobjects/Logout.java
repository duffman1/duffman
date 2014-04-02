package com.nbcuni.test.publisher.pageobjects;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
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
    	//existing chrome bug where logout link click doesn't work properly
    	if (config.getConfigValue("Browser").equals("chrome")) {
    		webDriver.manage().deleteAllCookies();
    		webDriver.navigate().to(config.getConfigValue("AppURL"));
    	}
    	else {
    		try {
        		LogOut_Btn.click();
        		
        	}
        	catch (WebDriverException e) {
        		webDriver.executeScript("arguments[0].click();", LogOut_Btn);
        		webDriver.navigate().refresh(); //TODO - logout requires a refresh for some reason. Figure out a better way
            	
        	}
        	
    	}
    	
    }
    
    
    
    
  
}

