package com.nbcuni.test.publisher.common;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.Random;
import com.nbcuni.test.publisher.pageobjects.Logout;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.Overlay;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.content.ContentTypes;
import com.nbcuni.test.publisher.pageobjects.taxonomy.Taxonomy;
import com.nbcuni.test.webdriver.CustomWebDriver;
import com.nbcuni.test.webdriver.WebDriverClientExecution;


public class ParentTest {
	
	public CustomWebDriver webDriver;
    public AppLib applib;

    /**
     * Instantiate the TestNG Before Class Method.
     * 
     * @param sEnv - environment
     * @throws Exception - error
     */
    @BeforeMethod(alwaysRun = true)
    @Parameters("Environment")
    public void startSelenium(@Optional("PROD") String sEnv) {
        try {
            webDriver = WebDriverClientExecution.getInstance().getDriver();
            applib = new AppLib(webDriver);
            applib.setEnvironmentInfo(sEnv);
            
            webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        } catch (Exception e) {
            applib.fail(e.toString());
        }

    }

    /**
     * Instantiate the TestNG After Class Method.
     *  
     * @throws Exception - error
     */
    @AfterMethod(alwaysRun = true)
    public void stopSelenium(ITestResult result) {
        
    	try {
    	
        	if (!result.isSuccess()) {
        		
                applib.attachScreenshot(result);  

            }
        	
        } catch (Exception e) {
            
        	applib.fail(e.toString());
        }
        
        try {
        	
        	webDriver.quit();
        } 
        catch (Exception e) {
        
        	applib.fail(e.toString());
        }

    }
}
