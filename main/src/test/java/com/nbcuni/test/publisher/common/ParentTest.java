package com.nbcuni.test.publisher.common;

import com.nbcuni.test.publisher.common.Driver.DriverSetup;
import com.nbcuni.test.publisher.common.Driver.StartGridHubNode;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.EmberNav;
import com.nbcuni.test.publisher.pageobjects.Taxonomy.Taxonomy;
import com.nbcuni.test.publisher.tests.Setup.A1_TestSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
	
	protected WebDriver webWebWebDriver;
	protected DriverSetup driverSetup;
    protected AppLib applib;
    protected Random random;
    protected Taxonomy taxonomy;
    protected EmberNav navigation;
    protected Config config = new Config();
    protected ContentParent contentParent;
    protected StartGridHubNode startGridHubNode;
    protected Interact interact;
    
    public static Boolean abortTestSuite = false;
    
    @BeforeSuite(alwaysRun = true)
    public void suiteSetup() throws Exception {

    	if (config.getConfigValueString("RunSetupScripts").equals("true")) {
    		driverSetup = new DriverSetup();
        	webWebWebDriver = driverSetup.WebDriverSetup(true);
        	applib = new AppLib(webWebWebDriver);
        	webWebWebDriver.manage().timeouts().pageLoadTimeout(config.getConfigValueInt("PageLoadWaitTime"), TimeUnit.SECONDS);
            webWebWebDriver.manage().timeouts().implicitlyWait(config.getConfigValueInt("ImplicitWaitTime"), TimeUnit.SECONDS);
        	A1_TestSetup testSetup = new A1_TestSetup();
        	abortTestSuite = testSetup.TestSetup_Test(webWebWebDriver, applib);
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
        	webWebWebDriver = driverSetup.WebDriverSetup(runLocally);
            applib = new AppLib(webWebWebDriver);
            random = new Random();
            taxonomy = new Taxonomy(webWebWebDriver);
            navigation = new EmberNav(webWebWebDriver);
            contentParent = new ContentParent(webWebWebDriver);
            interact = new Interact(webWebWebDriver, config.getConfigValueInt("WaitForWaitTime"));
            
            webWebWebDriver.manage().timeouts().pageLoadTimeout(config.getConfigValueInt("PageLoadWaitTime"), TimeUnit.SECONDS);
            webWebWebDriver.manage().timeouts().implicitlyWait(config.getConfigValueInt("ImplicitWaitTime"), TimeUnit.SECONDS);
            webWebWebDriver.manage().window().maximize();
            
        } catch (Exception e) {
        	System.out.println("Failed to start WebDriver and initiate timeouts");
        }

    }

    @AfterMethod(alwaysRun = true)
    public void stopSelenium(ITestResult result) throws Exception {
        
    	if (!result.isSuccess()) {
    		applib.attachScreenshot(result);  
    	}
    	
    	/* TODO - implement this
    	try {
    		applib.attachReporterLogging(result);
    	}
    	catch (Exception e) {
    		System.out.println("Failed to capture reporter logging.");
    	}
        */
    	
    	if (config.getConfigValueString("ClearCacheOnFailure").equals("true")) {
    		//Clear cache in the event of a failure
        	try {
        		if (!result.isSuccess()) {
        			applib.openSitePage("/admin/config/development/performance");
        			webWebWebDriver.switchTo().defaultContent();
        			Thread.sleep(1000);
            		webWebWebDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            		webWebWebDriver.findElement(By.id("edit-clear")).click();
            		Reporter.setCurrentTestResult(result); 
                    Reporter.log("Cache was cleared on test failure");
                    Reporter.setCurrentTestResult(null);
        		}
        	}
        	catch (Exception | AssertionError e) {
        		System.out.println("Failed to flush cache");
        	}
    	}
    	
        try {
        	webWebWebDriver.quit();
        } 
        catch (Exception e) {
        	System.out.println("Failed to stop WebDriver");
        }
    }
    
    @AfterSuite(alwaysRun = true)
    public void suiteTeardown() throws Exception {

    	
    }
    
}
