package com.nbcuni.test.publisher.common;

import com.nbcuni.test.publisher.common.Driver.*;
import com.nbcuni.test.publisher.pageobjects.EmberNav;
import com.nbcuni.test.publisher.pageobjects.Overlay;
import com.nbcuni.test.publisher.pageobjects.Configuration.FlushCache;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.Taxonomy.Taxonomy;
import com.nbcuni.test.publisher.tests.Setup.A1_TestSetup;
import com.nbcuni.test.publisher.common.Driver.Driver;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ParentTest {
	
	protected Driver webDriver;
	protected DriverSetup driverSetup;
    protected AppLib applib;
    protected Random random;
    protected Taxonomy taxonomy;
    protected EmberNav navigation;
    protected Overlay overlay;
    protected Config config = new Config();
    protected ContentParent contentParent;
    protected StartGridHubNode startGridHubNode;
    
    public static Boolean abortTestSuite = false;
    
    @BeforeSuite(alwaysRun = true)
    public void suiteSetup() throws Exception {

    	if (config.getConfigValueString("RunSetupScripts").equals("true")) {
    		driverSetup = new DriverSetup();
        	webDriver = driverSetup.WebDriverSetup(true);
        	applib = new AppLib(webDriver);
        	webDriver.manage().timeouts().pageLoadTimeout(config.getConfigValueInt("PageLoadWaitTime"), TimeUnit.SECONDS);
            webDriver.manage().timeouts().implicitlyWait(config.getConfigValueInt("ImplicitWaitTime"), TimeUnit.SECONDS);
        	A1_TestSetup testSetup = new A1_TestSetup();
        	abortTestSuite = testSetup.TestSetup_Test(webDriver, applib);
    	}
    }
    
    @BeforeMethod(alwaysRun = true)
    public void startSelenium(Method method) throws Exception {
        try {
            
        	List<String> allLocalTests = config.getAllLocalTests();
        	Boolean runLocally = false;
        	if (allLocalTests.contains(method.getDeclaringClass().getCanonicalName())) {
        		runLocally = true;
        	}
        	
        	driverSetup = new DriverSetup();
        	webDriver = driverSetup.WebDriverSetup(runLocally);
            applib = new AppLib(webDriver);
            random = new Random();
            taxonomy = new Taxonomy(webDriver);
            navigation = new EmberNav(webDriver);
            overlay = new Overlay(webDriver);
            contentParent = new ContentParent(webDriver);
            
            webDriver.manage().timeouts().pageLoadTimeout(config.getConfigValueInt("PageLoadWaitTime"), TimeUnit.SECONDS);
            webDriver.manage().timeouts().implicitlyWait(config.getConfigValueInt("ImplicitWaitTime"), TimeUnit.SECONDS);
            webDriver.manage().window().maximize();
            
        } catch (Exception e) {
        	System.out.println("Failed to start WebDriver and initiate timeouts");
        }

    }

    @AfterMethod(alwaysRun = true)
    public void stopSelenium(ITestResult result) throws Exception {
        
    	try {
    		if (!result.isSuccess()) {
                
    			applib.attachScreenshot(result);  
    		}
        } catch (Exception e) {
        	System.out.println("Failed to capture screenshot");
        }
        
    	//Clear cache in the event of the very annoying state flow node error
    	try {
    		if (!result.isSuccess()) {
    			if (result.getThrowable().getMessage().toString().contains("StateFlowNode")) {
    				applib.openApplication();
        			webDriver.switchTo().defaultContent();
        			FlushCache flushCache = new FlushCache(webDriver);
        			flushCache.FlushAllCache();
        			Reporter.setCurrentTestResult(result); 
                	Reporter.log("Cache was cleared on test failure");
                	Reporter.setCurrentTestResult(null);
        		}
    		}
    	}
    	catch (Exception e) {
    		System.out.println("Failed to flush cache");
    	}
    	
        try {
        	webDriver.quit();
        } 
        catch (Exception e) {
        	System.out.println("Failed to stop WebDriver");
        }
    }
    
    @AfterSuite(alwaysRun = true)
    public void suiteTeardown() throws Exception {

    	
    }
    
}
