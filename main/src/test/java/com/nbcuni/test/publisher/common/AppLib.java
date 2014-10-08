package com.nbcuni.test.publisher.common;

import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.pageobjects.UserLogin;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**************************************************************************.
 * NBC.com Application Library. Copyright
 *
 * @author Brandon Clark
 * @version 2.3 Date: June 12, 2014
 ***************************************************************************/

public class AppLib {

	Config config = new Config();
	private WebDriverWait wait;
	
    private Driver webDriver;
    
    public AppLib(Driver webDriver) {
    	this.webDriver = webDriver;
    	wait = new WebDriverWait(webDriver, 10);
    }

    
    
    public void attachScreenshot(ITestResult result) throws Exception {
    	
    	Date date = new Date(result.getEndMillis());
    	SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MMddyyhhmmssa");
    	String screenshotDateTime = dateTimeFormat.format(date);
        String methodName = result.getMethod().getMethodName() + "_" + screenshotDateTime;
        
        String filePath = config.getConfigValueFilePath("PathToScreenshots") + methodName + ".png";
        Reporter.setCurrentTestResult(result); 
        Reporter.log("Screenshot saved to " + filePath + " ");
        System.out.println("Screenshot saved to " + filePath);
        File scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);    
        FileUtils.copyFile(scrFile, new File(filePath));
            
        Reporter.log("<br><br><a href='" + filePath + "'> <img src='" + filePath + "' height='500' width='500'/> </a>");
        Reporter.setCurrentTestResult(null);
      
    }
    
    public UserLogin openApplication() throws Exception {
        Reporter.log("Open url '" + config.getConfigValueString("AppURL") + "'.");
        webDriver.navigate().to(config.getConfigValueString("AppURL"));   
        return new UserLogin(webDriver);
    }
    
    public void openSitePage(String location) throws Exception {
        Reporter.log("Open url '" + config.getConfigValueString("AppURL") + location + "'.");
        webDriver.navigate().to(config.getConfigValueString("AppURL") + location);   
    }
    
    public void openNewWindow() throws Exception {
    	Reporter.log("Open a new browser window.");
    	webDriver.executeScript("window.open()");
    	wait.until(new ExpectedCondition<Boolean>() {
    		public Boolean apply(WebDriver webDriver) {
    			return webDriver.getWindowHandles().size() > 1;
   		 	}
    	});
    }
    
    public void switchToNewWindow(String parentWindow) throws Exception {
    	Reporter.log("Switch to new browser tab/window.");
    	Thread.sleep(1000);
    	for (String window : webDriver.getWindowHandles()) {
	        if (!window.equals(parentWindow)) {
	          webDriver.switchTo().window(window);
	          break;
	          }
	    }
    }
    
    public void switchToParentWindow(String parentWindow) throws Exception {
    	Reporter.log("Switch to the parent tab/window.");
    	webDriver.switchTo().window(parentWindow);
    }
    
    public void refreshPage() throws Exception {
    	Reporter.log("Refresh the page.");
    	webDriver.navigate().refresh();
    }
    
    
}
