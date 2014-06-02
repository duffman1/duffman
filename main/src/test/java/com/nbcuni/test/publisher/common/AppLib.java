package com.nbcuni.test.publisher.common;

import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.Reporter;
import java.io.File;

/**************************************************************************.
 * NBC.com Application Library. Copyright
 *
 * @author Brandon Clark
 * @version 2.2 Date: May 23, 2014
 ***************************************************************************/

public class AppLib {

	Config config = new Config();
	
    private Driver webDriver;
    
    public AppLib(Driver webDriver) {
    	this.webDriver = webDriver;
    }

    public String getPathToMedia() {
    	
        return config.getPathToMedia();
    }
    
    public String getPathToSikuliImages() {
    	
    	return config.getPathToSikuliImages();
    }
    
    public String getAdmin1Username() {
    	
    	return config.getConfigValue("Admin1Username");
    }
    
    public String getAdmin1Password() {
    	
    	return config.getConfigValue("Admin1Password");
    }
    
    public String getMPXUrl() {
    	
    	return config.getConfigValue("MPXUrl");
    }

    public String getMPXUsername() {
    	
    	return config.getConfigValue("MPXUsername");
    	
    }
    
    public String getMPXPassword() {
    	
    	return config.getConfigValue("MPXPassword");
    	
    }
    
    public int getImplicitWaitTime() {
    	
    	return config.getImplicitWaitTime();
    	
    }
    
    public double getSikuliImageWaitTime() {
    	
    	return config.getSikuliImageWaitTime();
    	
    }
    
    public int getPageLoadWaitTime() {
    	
    	return config.getPageLoadWaitTime();
    	
    }
    
    public String getGmailAutoEmailUsername() {
    	
    	return config.getConfigValue("GmailUsername");
    	
    }
    
    public String getGmailAutoEmailPassword() {
    	
    	return config.getConfigValue("GmailPassword");
    	
    }
    
    public boolean IsErrorCheckingEnabled() {
    	
    	return config.IsErrorCheckingEnabled();
    }
    
    public void attachScreenshot(ITestResult result) throws Exception {
    	
        String methodName = result.getMethod().getMethodName();
        	
        String storeScreenshotsTo = config.getPathToScreenshots();
        String filePath = storeScreenshotsTo + methodName + ".png";

        Reporter.setCurrentTestResult(result); 
        Reporter.log("Screenshot saved to " + filePath + " ");
        System.out.println("Screenshot saved to " + filePath);
        File scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);    
        FileUtils.copyFile(scrFile, new File(filePath));
            
        Reporter.log("<br><br><a href='" + filePath + "'> <img src='" + filePath + "' height='500' width='500'/> </a>");
        Reporter.setCurrentTestResult(null);
      
    }
    
    public final String getApplicationURL() throws Exception {
        return config.getConfigValue("AppURL");
    }
    
    public UserLogin openApplication() throws Exception {
        Reporter.log("Open url '" + this.getApplicationURL() + "'.");
        webDriver.navigate().to(this.getApplicationURL());   
        return new UserLogin(webDriver);
    }
    
    public void openSitePage(String location) throws Exception {
        Reporter.log("Open url '" + this.getApplicationURL() + location + "'.");
        webDriver.navigate().to(this.getApplicationURL() + location);   
    }
    
}
