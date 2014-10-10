package com.nbcuni.test.publisher.common.Driver;

/*
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.openqa.grid.common.GridRole;
import org.openqa.grid.common.RegistrationRequest;
import org.openqa.grid.common.SeleniumProtocol;
import org.openqa.grid.internal.utils.GridHubConfiguration;
import org.openqa.grid.internal.utils.SelfRegisteringRemote;
import org.openqa.grid.web.Hub;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.nbcuni.test.publisher.common.Config;
*/

/*********************************************
 * publisher.nbcuni.com Start Grid Hub and Node Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: March 27, 2014
 *********************************************/

public class StartGridHubNode {

    public StartGridHubNode() {
        
    }
    
    /*
    Config config = new Config();
    String hubHost = "localhost";
    int hubPort = Integer.parseInt(config.getConfigValue("LocalWebDriverHubPort"));
    Hub myHub = null;
    SelfRegisteringRemote remoteWebDriverNode = null;
    
    public void start() throws Exception {
    	
    	Logger.getRootLogger().setLevel(org.apache.log4j.Level.OFF);
    	
    	GridHubConfiguration gridHubConfig = new GridHubConfiguration();
        gridHubConfig.setHost(hubHost);
        gridHubConfig.setPort(hubPort);
        myHub = new Hub(gridHubConfig);
        myHub.start();
        
        remoteWebDriverNode = attachNodeToHub(this.BrowserType(),
                GridRole.NODE, 5556, SeleniumProtocol.WebDriver);
    }
    
    public void stop() throws Exception {
    	
    	remoteWebDriverNode.stopRemoteServer();
        myHub.stop();
    }
    
    private SelfRegisteringRemote attachNodeToHub(DesiredCapabilities capability, GridRole role, int nodePort,
            SeleniumProtocol protocol) throws Exception {
        SelfRegisteringRemote node = null;
        RegistrationRequest registrationRequest = RegistrationRequest
                .localWebdriverNoCapabilities();
        capability.setCapability("seleniumProtocol", protocol);
        registrationRequest.addDesiredCapability(capability);
        registrationRequest.setRole(role);
        registrationRequest.setConfiguration(fetchNodeConfiguration(role, nodePort, protocol));
        node = new SelfRegisteringRemote(registrationRequest);
        node.addBrowser(capability, config.getParallelCount());
        node.startRemoteServer();
        node.startRegistrationProcess();
        
        if (config.getConfigValue("Browser").contains("chrome")) {            
            if (System.getProperty("os.name").contains("Windows")) {
                System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            } else {
                System.setProperty("webdriver.chrome.driver", "chromedriver");
            }
        } else if (System.getProperty("os.name").contains("Windows") && config.getConfigValue("Browser").contains("iexplore")){
            System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");                
        }
        return node;
    }

    private Map<String, Object> fetchNodeConfiguration(GridRole role, int portToRun, SeleniumProtocol protocol) throws MalformedURLException {
        Map<String, Object> nodeConfiguration = new HashMap<>();
        nodeConfiguration.put(RegistrationRequest.AUTO_REGISTER, true);
        nodeConfiguration.put(RegistrationRequest.HUB_HOST, myHub.getHost());
        nodeConfiguration.put(RegistrationRequest.HUB_PORT, myHub.getPort());
        nodeConfiguration.put(RegistrationRequest.PORT, portToRun);
        URL remoteURL = new URL("http://" + myHub.getHost() + ":" + portToRun);
        nodeConfiguration.put(RegistrationRequest.PROXY_CLASS,
                "org.openqa.grid.selenium.proxy.DefaultRemoteProxy");
        nodeConfiguration.put(RegistrationRequest.MAX_SESSION, config.getParallelCount());
        nodeConfiguration.put(RegistrationRequest.CLEAN_UP_CYCLE, 2000);
        nodeConfiguration.put(RegistrationRequest.REMOTE_HOST, remoteURL);
        nodeConfiguration.put(RegistrationRequest.MAX_INSTANCES, config.getParallelCount());
        return nodeConfiguration;
    }
    
    private DesiredCapabilities BrowserType() throws Exception {
    	
    	DesiredCapabilities capabilities = new DesiredCapabilities();

        String browser = config.getConfigValue("Browser");
        
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
                break;
            case "android":
                capabilities = DesiredCapabilities.android();
                break;
            case "phantomjs":
                capabilities = DesiredCapabilities.phantomjs();
                break;
            default:
                capabilities = DesiredCapabilities.htmlUnit();
                break;
        }
        
        return capabilities;
    }
    */
}

