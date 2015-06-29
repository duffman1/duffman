package com.nbcuni.test.publisher.pageobjects.People;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

/*********************************************
 * publisher.nbcuni.com Apply Permission Set Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: March 25, 2014
 *********************************************/

public class ApplyPermissionSet {

    private Config config;
    private Integer timeout;
    private WaitFor waitFor;
    private Interact interact;
    
    //PAGE OBJECT CONSTRUCTOR
    public ApplyPermissionSet(WebDriver webWebWebDriver) {
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webWebWebDriver, timeout);
        interact = new Interact(webWebWebDriver, timeout);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By Role_Ddl(String role) {
    	return By.xpath("//label[text()='" + role + " ']/../select");
    }
    
    private By ApplyPermissionSets_Btn = By.id("edit-apply");
    
    private By DoIt_Btn = By.id("edit-submit");
    
    
    //PAGE OBJECT METHODS
    public void SelectRolePermissionSet(String role, String option) throws Exception {
    	
    	Reporter.log("Select '" + option + "' from the '" + role + "' drop down list.");
    	interact.Select(waitFor.ElementVisible(Role_Ddl(role)), option);
    	
    }
    
    public void ClickApplyPermissionSetsBtn() throws Exception {
    	
    	Reporter.log("Click the 'Apply Permission Set(s)' button.");
    	interact.Click(waitFor.ElementVisible(ApplyPermissionSets_Btn));
    	
    }
    
    public void ClickDoItBtn() throws Exception {
    	
    	Reporter.log("Click the 'Do it!' button.");
    	interact.Click(waitFor.ElementVisible(DoIt_Btn));
    	
    }
    
}

