package com.nbcuni.test.publisher.common;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.webdriver.CustomWebDriver;
import com.nbcuni.test.webdriver.CustomWebDriverException;
import com.nbcuni.test.webdriver.Utilities;

import org.testng.Assert;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.*;
import java.util.Date;
import java.util.Properties;

/**************************************************************************.
 * NBC.com Application Library. Copyright
 *
 * @author Jeremy Ocampo
 * @version 1.0 Date: September 1, 2013
 ***************************************************************************/

public class AppLib {

	Config config = new Config();
	
    private CustomWebDriver custWebDr = null;
    private String environment = "";
    private String proxyUrl = "";
    private Integer proxyPort = 0;
    private String pathToMediaContent = "";
    private String pathToScreenshots = "";
    private String pathToSikuliImages = "";
    private String admin1Username = "";
    private String admin1Password = "";
    private String mpxUsername = "";
    private String mpxPassword = "";
    private String implicitWaitTime = "";
    private String pageLoadWaitTime = "";
    private String errorChecksEnabled = "";
    private String mpxUrl = "";
    private String sikuliImageWaitTime = "";

    private static String configFileName = "src" + File.separator + "test" + File.separator + "resources"
            + File.separator + "config.properties";
    private static Properties configProperties = null;
    private static boolean isConfigLoaded = false;
    
    /**
     * @param cs1 Custom Webdriver
     */
    public AppLib(CustomWebDriver cs1) {
        custWebDr = cs1;
        new Util(custWebDr);
        try {
            loadConfig();
        } catch (Exception e) {
            new CustomWebDriverException(e, custWebDr);
        }
    }

    /**
     * loads config file
     */
    private void loadConfig() {
        try {
            if (!isConfigLoaded) {
                File configFile = new File(configFileName);
                configProperties = new Properties();
                InputStream input = null;
                if (configFile.exists()) {
                    try {
                        input = new FileInputStream(configFile);
                        configProperties.load(input);
                    } catch (FileNotFoundException e) {
                        Utilities.logSevereMessage("The config.properties file was not found in this " + "location: "
                                + configFileName);
                    } finally {
                        if (input != null) {
                            input.close();
                        }
                    }
                    isConfigLoaded = true;
                }
            }
        } catch (Exception e) {
            new CustomWebDriverException(e, custWebDr);
        }
    }

    /**
     * gets env
     */
    private void getEnvironmentInfo() {
        try {

            proxyUrl = configProperties.getProperty(environment + ".ProxyURL");
            proxyPort = Integer.valueOf(configProperties.getProperty(environment + ".ProxyPort"));
            pathToMediaContent = configProperties.getProperty(environment + ".PathToMediaContent");
            pathToScreenshots = configProperties.getProperty(environment + ".PathToScreenShots");
            pathToSikuliImages = configProperties.getProperty(environment + ".PathToSikuliImages");
            admin1Username = configProperties.getProperty(environment + ".Admin1UserName");
            admin1Password = configProperties.getProperty(environment + ".Admin1PassWord");
            mpxUsername = configProperties.getProperty(environment + ".MPXUserName");
            mpxPassword = configProperties.getProperty(environment + ".MPXPassWord");
            implicitWaitTime = configProperties.getProperty(environment + ".ImplicitWaitTime");
            pageLoadWaitTime = configProperties.getProperty(environment + ".PageLoadWaitTime");
            errorChecksEnabled = configProperties.getProperty(environment + ".ErrorCheckingEnabled");
            mpxUrl = configProperties.getProperty(environment + ".MPXUrl");
            sikuliImageWaitTime = configProperties.getProperty(environment + ".SikuliImageWaitTime");
            
        } catch (Exception e) {
            new CustomWebDriverException(e, custWebDr);
        }
    }

    public String getPathToMedia() {
    	
    	String filePath = System.getProperty("user.dir") + this.pathToMediaContent;
        return filePath.replace("/", File.separator);
    }
    
    public String getPathToSikuliImages() {
    	
    	String filePath = System.getProperty("user.dir") + this.pathToSikuliImages;
    	return filePath.replace("/", File.separator);
    }
    
    public String getAdmin1Username() {
    	
    	return admin1Username;
    }
    
    public String getAdmin1Password() {
    	
    	return admin1Password;
    }
    
    public String getMPXUrl() {
    	
    	return this.mpxUrl;
    }

    public String getMPXUsername() {
    	
    	return mpxUsername;
    	
    }
    
    public String getMPXPassword() {
    	
    	return mpxPassword;
    	
    }
    
    public int getImplicitWaitTime() {
    	
    	return Integer.parseInt(this.implicitWaitTime);
    	
    }
    
    public double getSikuliImageWaitTime() {
    	
    	return (double) Integer.parseInt(this.sikuliImageWaitTime);
    	
    }
    
    public int getPageLoadWaitTime() {
    	
    	return Integer.parseInt(this.pageLoadWaitTime);
    	
    }
    
    public String getGmailAutoEmailUsername() {
    	
    	return config.getConfigValue("GmailUsername");
    	
    }
    
    public String getGmailAutoEmailPassword() {
    	
    	return config.getConfigValue("GmailPassword");
    	
    }
    
    public boolean IsErrorCheckingEnabled() {
    	
    	boolean checksEnabled = true;
    	if (this.errorChecksEnabled.equals("true")) {
    		checksEnabled = true;
    	}
    	else {
    		checksEnabled = false;
    	}
    	
    	return checksEnabled;
    }
    
    public void attachScreenshot(ITestResult result) {
    	
        try {
        	
        	String storeScreenshotsTo = System.getProperty("user.dir") + this.pathToScreenshots.replace("/", File.separator);
        	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String fileExtension = dateFormat.format(date).replace("/", "");
            fileExtension = fileExtension.replace(" ", "");
            fileExtension = fileExtension.replace(":", "") + ".png";
            String filePath = storeScreenshotsTo + fileExtension;

            Reporter.setCurrentTestResult(result); 
            Reporter.log("Screenshot saved to " + filePath + " ");
            System.out.println("Screenshot saved to " + filePath);
        	File scrFile = ((TakesScreenshot) custWebDr).getScreenshotAs(OutputType.FILE);    
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

    public Proxy getHttpProxy() {
        SocketAddress addr = new InetSocketAddress(this.proxyUrl, this.proxyPort);
        Proxy httpProxy = new Proxy(Proxy.Type.HTTP, addr);
		return httpProxy;
    }
   
    public final String getEnvironment() throws Exception {
        return this.environment;
    }

    public final void setEnvironmentInfo(String sEnv) throws Exception {
        Reporter.log("Setting Environment to: " + sEnv);
        Reporter.log("Environment: " + sEnv);
        this.environment = sEnv;
        this.getEnvironmentInfo();
    }

    public void fail(String errorMessage) {
        
    	Reporter.log(errorMessage);
    	Assert.fail(errorMessage);
    }
    
    public UserLogin openApplication() throws Exception {
        Reporter.log("Open url '" + this.getApplicationURL() + "'.");
        custWebDr.openURL(this.getApplicationURL());        
        return new UserLogin(custWebDr);
    }
    
    
}
