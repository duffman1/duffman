package com.nbcuni.test.publisher.common;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

















import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.webdriver.CustomWebDriver;
import com.nbcuni.test.webdriver.CustomWebDriverException;
import com.nbcuni.test.webdriver.Utilities;

/**************************************************************************.
 * NBC.com Application Library. Copyright
 *
 * @author Jeremy Ocampo
 * @version 1.0 Date: September 1, 2013
 ***************************************************************************/


public class AppLib {


    private static final int FOUR_ZERO_FOUR = 404;
    private CustomWebDriver custWebDr = null;
    private final Util ul;

    private String environment = "";
    private String appURL = "";
    private String proxyUrl = "";
    private Integer proxyPort = 0;
    private String pathToMediaContent = "";
    private String pathToScreenshots = "";
    private String moduleOverlaysEnabled = "";

    private static String configFileName = "src" + File.separator + "test" + File.separator + "resources"
            + File.separator + "config.properties";
    private static Properties configProperties = null;
    private static boolean isConfigLoaded = false;
    private static String businessid = "";

    /**
     * @param cs1 Custom Webdriver
     */
    public AppLib(CustomWebDriver cs1) {
        custWebDr = cs1;
        ul = new Util(custWebDr);
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

            appURL = configProperties.getProperty(environment + ".Url");
            proxyUrl = configProperties.getProperty(environment + ".ProxyURL");
            proxyPort = Integer.valueOf(configProperties.getProperty(environment + ".ProxyPort"));
            pathToMediaContent = configProperties.getProperty(environment + ".PathToMediaContent");
            pathToScreenshots = configProperties.getProperty(environment + ".PathToScreenShots");
            moduleOverlaysEnabled = configProperties.getProperty(environment + ".ModuleOverlaysEnabled");
        } catch (Exception e) {
            new CustomWebDriverException(e, custWebDr);
        }
    }

    public String getPathToMedia(){
    	
    	String pathToMedia = System.getProperty("user.dir") + this.pathToMediaContent;
    	return pathToMedia;
    }
    
    /**
     * Returns if overlays are enabled or not
     * 
     * @return Return Boolean
     */
    public Boolean getModuleOverlaysEnabled() {
    	
    	Boolean overlaysEnabled = true;
    	System.out.println(moduleOverlaysEnabled);
    	if (moduleOverlaysEnabled == "false") {
    		overlaysEnabled = false;
    	}
    	
    	return overlaysEnabled;
    }
    
    /**
    * Returns screenshot and embeds in testng reports on failure
    * 
    */
    public void attachScreenshot(ITestResult result) {
    	
        try {
        	
        	String storeScreenshotsTo = System.getProperty("user.dir") + this.pathToScreenshots;
        	//TODO set file naming based on test class name rather than time of screenshot capture
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String fileExtension = dateFormat.format(date).replace("/", "");
            fileExtension = fileExtension.replace(" ", "");
            fileExtension = fileExtension.replace(":", "") + ".png";
            String filePath = storeScreenshotsTo + fileExtension;

            Reporter.setCurrentTestResult(result); 
            Reporter.log("Screenshot saved to " + filePath + " ");
            
        	File scrFile = ((TakesScreenshot) custWebDr).getScreenshotAs(OutputType.FILE);    
            FileUtils.copyFile(scrFile, new File(filePath));
            
        	Reporter.log("<br><br><a href='" + filePath + "'> <img src='" + filePath + "' height='500' width='500'/> </a>");
        	Reporter.setCurrentTestResult(null);
    		
      } catch (Exception e) {
          Reporter.log("Failed to capture screenshot");
      }
      
    }
    
    /**
     * Returns the application URL e.g. http://www.nbc.com
     * 
     * @return Return value application URL
     * @throws Exception Code Error
     */
    public final String getApplicationURL() throws Exception {
        return this.appURL;
    }

    public Proxy getHttpProxy() {
        SocketAddress addr = new InetSocketAddress(this.proxyUrl, this.proxyPort);
        Proxy httpProxy = new Proxy(Proxy.Type.HTTP, addr);
		return httpProxy;
    }
   
    /**
     * @return Return value
     * @throws Exception Code Error
     */
    public final String getEnvironment() throws Exception {
        return this.environment;
    }

    /**
     * @param sEnv environment
     * @throws Exception Code Error
     */
    public final void setEnvironmentInfo(String sEnv) throws Exception {
        Reporter.log("Setting Environment to: " + sEnv);
        Reporter.log("Environment: " + sEnv);
        this.environment = sEnv;
        this.getEnvironmentInfo();
    }


    /**
     * @param err
     * @throws Exception Code Error
     */
    public final void showErrors(ArrayList<String> err) throws Exception {
        String tempErr = "";
        if (!err.isEmpty()) {
            for (String xxx : err) {
                tempErr = tempErr + "\n" + xxx;
                Reporter.log(xxx);
            }
            fail(tempErr);
        }
    }

    /**
     * Method to log Error
     * 
     * @param err
     * @throws Exception
     */
    public void fail(String errorMessage) {
        
    	Reporter.log(errorMessage);
    	Assert.fail(errorMessage);
    }
    

    /**
     * .
     * Validates the page title
     * 
     * @param sPagename Page name
     * @param sMessage - string description of validation
     * @throws Exception Code Error
     */
    public final void validateCorrectPageDisplay(String sPagename) throws Exception {
        String sPage = custWebDr.getTitle();
        ul.compareTextValues(sPage, sPagename);
    }

    /**
     * This method will attain all links on the page and
     * perform a HTTP response verification on the page
     * ensuring that it does not return a error
     * 
     * @throws Exception - error
     */
    public final void verifyHtttpResponseForAllLinksOnPage() throws Exception {
        ArrayList<String> rawLinks = custWebDr.getAllLinksOnPage();
        for (String xxx:rawLinks) {
            if (xxx.startsWith("http:")) {
                URL url = new URL(xxx.trim());
                Reporter.log("URL: " + url);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                int code = connection.getResponseCode();
                if ((code == FOUR_ZERO_FOUR)) {
                    fail("Response Code incorrect");
                }
            }
            if (xxx.startsWith("/")) {
                String baseLocation = custWebDr.getLocation();
                String baseURL = baseLocation.replace("com/", "com");
                String rawLinks2 = baseURL + xxx;
                URL url = new URL(rawLinks2.trim());
                Reporter.log("URL: " + url);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                int code = connection.getResponseCode();
                if ((code == FOUR_ZERO_FOUR)) {
                    fail("Response Code incorrect");
                }
            }
        }
    }
    
    
    /**
     * Opens the browser with application url
     * 
     * @return Return value
     * @throws Exception Code Error
     */
    
    
    public UserLogin openApplication() throws Exception {
        Reporter.log("URL: " + this.getApplicationURL());
        custWebDr.openURL(this.getApplicationURL());
        return new UserLogin(custWebDr);
    }
    
    
    
}
