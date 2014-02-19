package com.nbcuni.test.publisher.common;

import com.nbcuni.test.publisher.pageobjects.Overlay;
import com.nbcuni.test.publisher.pageobjects.Taxonomy.Taxonomy;
import com.nbcuni.test.webdriver.CustomWebDriver;
import com.nbcuni.test.webdriver.WebDriverClientExecution;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

public class ParentTest {
	
	protected CustomWebDriver webDriver;
    protected AppLib applib;
    protected Random random;
    protected Taxonomy taxonomy;
    protected Overlay overlay;
    
    @BeforeMethod(alwaysRun = true)
    @Parameters("Environment")
    public void startSelenium(@Optional("PROD") String sEnv) {
        try {
            
        	webDriver = WebDriverClientExecution.getInstance().getDriver();
            applib = new AppLib(webDriver);
            applib.setEnvironmentInfo(sEnv);
            random = new Random();
            taxonomy = new Taxonomy(webDriver);
            overlay = new Overlay(webDriver, applib);
            
            try {
            	webDriver.manage().timeouts().pageLoadTimeout(applib.getPageLoadWaitTime(), TimeUnit.SECONDS);
            	webDriver.manage().timeouts().implicitlyWait(applib.getImplicitWaitTime(), TimeUnit.SECONDS);
            	webDriver.manage().window().maximize();
            }
            catch (Exception e) {
            	Reporter.log("Failed to set timeouts and maximize window");
            }
        } catch (Exception e) {
            applib.fail(e.toString());
        }

    }

    @AfterMethod(alwaysRun = true)
    public void stopSelenium(ITestResult result) throws Exception{
        
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
