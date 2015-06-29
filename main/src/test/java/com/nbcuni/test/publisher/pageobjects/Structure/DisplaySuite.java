package com.nbcuni.test.publisher.pageobjects.Structure;


import com.nbcuni.test.publisher.common.Util.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

/*********************************************
* publisher.nbcuni.com Display Suite Library. Copyright
*
* @author Brandon Clark
* @version 1.0 Date: October 30, 2014
*********************************************/

public class DisplaySuite {
	
	private WaitFor waitFor;
	
    //PAGE OBJECT CONSTRUCTOR
    public DisplaySuite(WebDriver webWebWebDriver) {
    	PageFactory.initElements(webWebWebDriver, this);
    	waitFor = new WaitFor(webWebWebDriver, 10);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By ViewModes_Lnk = By.linkText("View modes");
    
    
    //PAGE OBJECT METHODS
    public void ClickViewModesLnk() throws Exception {
    
    	Reporter.log("Click the 'View modes' link.");
    	waitFor.ElementPresent(ViewModes_Lnk).click();
    	
    }
    
}
    

