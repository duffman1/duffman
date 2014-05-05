package com.nbcuni.test.publisher.common;

import com.nbcuni.test.publisher.common.Driver.*;
import com.nbcuni.test.publisher.pageobjects.Overlay;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.Taxonomy.Taxonomy;
import com.nbcuni.test.publisher.common.Driver.Driver;

import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

public class ParentTest {
	
	protected Driver webDriver;
	protected DriverSetup driverSetup;
    protected AppLib applib;
    protected Random random;
    protected Taxonomy taxonomy;
    protected Overlay overlay;
    protected Config config = new Config();
    protected ContentParent contentParent;
    protected StartGridHubNode startGridHubNode;
    
    @BeforeSuite(alwaysRun = true)
    public void startGridAndHub() throws Exception {

    	if (config.getConfigValue("RunMobile").equals("false")) {
    		startGridHubNode = new StartGridHubNode();
    		startGridHubNode.start();
    	}
    }
    
    @BeforeMethod(alwaysRun = true)
    public void startSelenium() throws Exception {
        try {
            
        	driverSetup = new DriverSetup();
        	webDriver = driverSetup.WebDriverSetup();
            applib = new AppLib(webDriver);
            random = new Random();
            taxonomy = new Taxonomy(webDriver);
            overlay = new Overlay(webDriver, applib);
            contentParent = new ContentParent(webDriver, applib);
            
            webDriver.manage().timeouts().pageLoadTimeout(applib.getPageLoadWaitTime(), TimeUnit.SECONDS);
            webDriver.manage().timeouts().implicitlyWait(applib.getImplicitWaitTime(), TimeUnit.SECONDS);
            webDriver.manage().window().maximize();
            
        } catch (Exception e) {
            applib.fail("Failed to start WebDriver and initiate timeouts");
        }

    }

    @AfterMethod(alwaysRun = true)
    public void stopSelenium(ITestResult result) throws Exception {
        
    	try {
    		if (!result.isSuccess()) {
                
    			applib.attachScreenshot(result);  
    		}
        } catch (Exception e) {
        	
        	applib.fail("Failed to capture screenshot");
        }
        
    	try {
    		if (!result.isSuccess()) {
    			webDriver.switchTo().defaultContent();
            	webDriver.executeScript("arguments[0].click();", 
            			webDriver.findElement(By.xpath("//a[text()='Flush all caches']")));
            	Reporter.log("Cache was cleared on test failure");
    		}
    	}
    	catch (Exception e) {
    		
    		applib.fail("Failed to flush cache");
    	}
    	
        try {
        	
        	webDriver.quit();
        } 
        catch (Exception e) {
        
        	applib.fail("Failed to stop WebDriver");
        }
    }
    
    @AfterSuite(alwaysRun = true)
    public void stopGridAndHub() throws Exception {

    	if (config.getConfigValue("RunMobile").equals("false")) {
    		startGridHubNode.stop();
    	}

    }
    
}
