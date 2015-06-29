package com.nbcuni.test.publisher.pageobjects.Structure;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

/*********************************************
* publisher.nbcuni.com Add New View Library. Copyright
*
* @author Brandon Clark
* @version 1.0 Date: January 4, 2015
*********************************************/

public class AddNewView {

	@SuppressWarnings("unused")
	private WebDriver webWebWebDriver;
	private Config config;
	private Integer timeout;
	private Interact interact;
	private WaitFor waitFor;
	
    //PAGE OBJECT CONSTRUCTOR
    public AddNewView(WebDriver webWebWebDriver) {
    	this.webWebWebDriver = webWebWebDriver;
    	config = new Config();
    	timeout = config.getConfigValueInt("WaitForWaitTime");
    	waitFor = new WaitFor(webWebWebDriver, timeout);
    	interact = new Interact(webWebWebDriver, timeout);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By ViewName_Txb = By.id("edit-human-name");
    
    private By OfType_Ddl = By.id("edit-show-type");
    
    private By Path_Txb = By.xpath("//input[contains(@id, 'edit-page-path')]");
    
    private By SaveAndExit_Btn = By.id("edit-save");
    
    
    //PAGE OBJECT METHODS
    public void EnterViewName(String viewName) throws Exception {
    	
    	Reporter.log("Enter '" + viewName + "' in the 'View name' text box.");
    	interact.Type(waitFor.ElementVisible(ViewName_Txb), viewName);
    	
    }
    
    public void SelectOfType(String type) throws Exception {
    	
    	Reporter.log("Select '" + type + "' from the 'of type' drop down list.");
    	interact.Select(waitFor.ElementVisible(OfType_Ddl), type);
    	
    }
    
    public void EnterPath(String path) throws Exception {
    	
    	Reporter.log("Enter '" + path + "' in the 'Path' text box.");
    	interact.Type(waitFor.ElementVisible(Path_Txb), path);
    	
    }
    
    public void ClickSaveAndExitBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save & exit' button.");
    	interact.Click(waitFor.ElementVisible(SaveAndExit_Btn));
    	
    }
    
}
