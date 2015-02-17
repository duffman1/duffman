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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
            
        Reporter.log("<br><br><a href='" + filePath + "'> <img src='./" + methodName + ".png' height='700' width='700'/> </a>");
        Reporter.setCurrentTestResult(null);
      
    }
    
    public void attachReporterLogging(ITestResult result) throws Exception {
    	
    	Date date = new Date(result.getEndMillis());
    	SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MMddyyhhmmssa");
    	String screenshotDateTime = dateTimeFormat.format(date);
        String methodName = result.getMethod().getMethodName() + "_" + screenshotDateTime;
        
        String filePath = config.getConfigValueFilePath("PathToScreenshots") + methodName + ".txt";
        File testLogFile = new File(filePath);
        testLogFile.createNewFile();
        
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(testLogFile, true));
    	new Reporter();
		List<String> reportResults = Reporter.getOutput(result);
		bufferedWriter.write("TEST STARTED - " + dateTimeFormat.format(result.getStartMillis()));
		bufferedWriter.newLine();
    	for (String reportLn : reportResults) {
    		bufferedWriter.write(reportLn);
    		bufferedWriter.newLine();
    	}
    	bufferedWriter.write("TEST COMPLETED - " + dateTimeFormat.format(result.getStartMillis()));
    	bufferedWriter.close();
        
    }
    
    public UserLogin openApplication() throws Exception {
        Reporter.log("Open url '" + config.getConfigValueString("AppURL") + "'.");
        webDriver.navigate().to(config.getConfigValueString("AppURL"));   
        return new UserLogin(webDriver);
    }
    
    public void openSitePage(String location) throws Exception {
    	location = location.replace(config.getConfigValueString("AppURL"), "");
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
    
    public void goBack() throws Exception {
    	Reporter.log("Navigate back.");
    	webDriver.navigate().back();
    }
    
    
}
