package com.nbcuni.test.publisher.pageobjects.Content;

import org.openqa.selenium.By;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
 * publisher.nbcuni.com Image Properties Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.2 Date: January 22, 2015
 *********************************************/

public class ImageProperties {

    private Config config;
    private WaitFor waitFor;
    private Interact interact;
    
    //PAGE OBJECT CONSTRUCTOR
    public ImageProperties(Driver webDriver) {
        config = new Config();
        Integer timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webDriver, timeout);
        interact = new Interact(webDriver, timeout);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By Width_Txb = By.xpath("//label[text()='Width']/..//input");
    
    private By Height_Txb = By.xpath("//label[text()='Height']/..//input");
    
    private By OK_Btn = By.xpath("//span[text()='OK']/../../a[@title='OK']");
    
    
    //PAGE OBJECT METHODS
    public void EnterWidth(String width) throws Exception {
    	
    	Reporter.log("Enter '" + width + "' in the 'Width' text box.");
    	interact.Type(waitFor.ElementVisible(Width_Txb), width);
    	
    }
    
    public void EnterHeight(String height) throws Exception {
    	
    	Reporter.log("Enter '" + height + "' in the 'Height' text box.");
    	interact.Type(waitFor.ElementVisible(Height_Txb), height);
    	
    }
    
    public void ClickOKBtn() throws Exception {
    	
    	Reporter.log("Click the 'OK' button.");
    	interact.Click(waitFor.ElementVisible(OK_Btn));
    	
    }
    
    public void WaitForImagePropertiesFrameClose() throws Exception {
    	
    	waitFor.ElementNotVisible(Width_Txb);
    	
    }
    
    
}

