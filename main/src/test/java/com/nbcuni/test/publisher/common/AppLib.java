package com.nbcuni.test.publisher.common;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.webdriver.CustomWebDriver;
import org.testng.Assert;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.Reporter;
import java.io.File;
import java.util.Date;

/**************************************************************************.
 * NBC.com Application Library. Copyright
 *
 * @author Brandon Clark
 * @version 2.0 Date: March 1, 2014
 ***************************************************************************/

public class AppLib {

	Config config = new Config();
	
    private String environment = "";
    private CustomWebDriver webDriver;
    
    public AppLib(CustomWebDriver webDriver) {
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
    
    public void attachScreenshot(ITestResult result) {
    	
        try {
        	
        	String storeScreenshotsTo = System.getProperty("user.dir") + config.getPathToScreenshots();
        	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String fileExtension = dateFormat.format(date).replace("/", "");
            fileExtension = fileExtension.replace(" ", "");
            fileExtension = fileExtension.replace(":", "") + ".png";
            String filePath = storeScreenshotsTo + fileExtension;

            Reporter.setCurrentTestResult(result); 
            Reporter.log("Screenshot saved to " + filePath + " ");
            System.out.println("Screenshot saved to " + filePath);
        	File scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);    
            FileUtils.copyFile(scrFile, new File(filePath));
            
        	Reporter.log("<br><br><a href='" + filePath + "'> <img src='" + filePath + "' height='500' width='500'/> </a>");
        	Reporter.setCurrentTestResult(null);
    		
      } catch (Exception e) {
          Reporter.log("Failed to capture screenshot");
      }
      
    }
    
    public final String getApplicationURL() throws Exception {
        return config.getConfigValue("AppURL");
    }
    
    public final String getEnvironment() throws Exception {
        return this.environment;
    }

    public void fail(String errorMessage) {
        
    	Reporter.log(errorMessage);
    	Assert.fail(errorMessage);
    }
    
    public UserLogin openApplication() throws Exception {
        Reporter.log("Open url '" + this.getApplicationURL() + "'.");
        webDriver.openURL(this.getApplicationURL());        
        return new UserLogin(webDriver);
    }
    
    
}
