package com.nbcuni.test.publisher.common;

import com.nbcuni.test.publisher.common.Driver.*;
import com.nbcuni.test.publisher.pageobjects.Overlay;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.Taxonomy.Taxonomy;
import com.nbcuni.test.publisher.common.Driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
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
    protected Overlay overlay;
    protected Config config = new Config();
    protected ContentParent contentParent;
    protected StartGridHubNode startGridHubNode;
    
    public static Boolean abortTestSuite = false;
    
    @BeforeSuite(alwaysRun = true)
    public void startGridAndHub() throws Exception {

    	
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
        
    	try {
    		if (!result.isSuccess()) {
    			webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    			applib.openApplication();
    			webDriver.switchTo().defaultContent();
    			webDriver.navigate().to(webDriver.findElement(By.xpath("//a[text()='Flush all caches']")).getAttribute("href"));
            	new WebDriverWait(webDriver, 10).until(new ExpectedCondition<Boolean>() {
            		public Boolean apply(WebDriver webDriver) {
            			return webDriver.findElement(By.xpath("//div[@class='messages status']")).getText().contains("Every cache cleared");
           		 	}
            	});
            	Reporter.setCurrentTestResult(result); 
            	Reporter.log("Cache was cleared on test failure");
            	Reporter.setCurrentTestResult(null);
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
    public void stopGridAndHub() throws Exception {

    	
    }
    
}
