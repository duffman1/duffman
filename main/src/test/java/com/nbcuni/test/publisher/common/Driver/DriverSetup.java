package com.nbcuni.test.publisher.common.Driver;

import java.net.URL;
import java.util.Arrays;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import com.nbcuni.test.publisher.common.Config;

/*********************************************
 * publisher.nbcuni.com Driver Setup Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: March 27, 2014
 *********************************************/

public class DriverSetup {

	private Config config = new Config();
    Driver driver = null;
    
    public Driver WebDriverSetup(Boolean runLocally) throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        Boolean isMobileRun;
        if (config.getConfigValueString("RunMobile").equals("true")) {
        	isMobileRun = true;
        }
        else {
        	isMobileRun = false;
        }
        
        String browser = config.getConfigValueString("Browser");
        
        String hubAddress;
        if (isMobileRun.equals(false)) {
        	hubAddress = "http://localhost:" + config.getConfigValueString("RemoteWebDriverHubPort") + "/wd/hub";
        }
        else {
        	hubAddress = "http://localhost:" + config.getConfigValueString("LocalAppiumHubPort") + "/wd/hub";
        }
        
        if (isMobileRun.equals(false)) {
        switch (browser)
        {
            case "firefox":
                capabilities = DesiredCapabilities.firefox();
                break;
            case "iexplore":
                capabilities = DesiredCapabilities.internetExplorer();
                break;
            case "opera":
                capabilities = DesiredCapabilities.opera();
                break;
            case "safari":
                capabilities = DesiredCapabilities.safari();
                break;
            case "chrome":
                capabilities = DesiredCapabilities.chrome();
                capabilities.setCapability("chrome switches", Arrays.asList("--ignore-certificate-errors","--disable-translate",
                        "--start-maximized"));
                break;
            case "android":
                capabilities = DesiredCapabilities.android();
                break;
            case "phantomjs":
                capabilities = DesiredCapabilities.phantomjs();
                capabilities.setJavascriptEnabled(true);
                capabilities.setCapability("PhantomJSDriverService.PHANTOMJS_CLI_ARGS", "--proxy-type=none");
                break;
            default:
                capabilities = DesiredCapabilities.htmlUnit();
                capabilities.setJavascriptEnabled(true);
                break;
        	}
        } 
        else {
        	
        	capabilities.setCapability("device", config.getConfigValueString("Device"));
        	if (config.getConfigValueString("Device").equals("Iphone Simulator")) {
        		capabilities.setCapability("app", "safari");
        	}
        	else {
        		capabilities.setCapability("app", "chrome");
        	}
        }
        
        capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
        
        if (config.getConfigValueString("RunOnGridNetwork").equals("true")) {
        	if (runLocally.equals(true)) {
        		capabilities.setCapability(CapabilityType.VERSION, "local");
        	}
        	else {
        		capabilities.setCapability(CapabilityType.VERSION, "remote");
        	}
        }
        
        try {

             driver = new Driver(new URL(hubAddress), capabilities);
        }
        catch (Exception e) {
        	Assert.fail("Failed to initiate driver");
        }

        return driver;
    }
    
}

