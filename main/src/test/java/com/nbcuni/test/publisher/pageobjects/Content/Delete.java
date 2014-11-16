package com.nbcuni.test.publisher.pageobjects.Content;

import org.openqa.selenium.By;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
 * publisher.nbcuni.com Delete Queue Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 19, 2013
 *********************************************/

public class Delete {

	private Config config;
	private Integer timeout;
	private WaitFor waitFor;
	private Interact interact;
	
    //PAGE OBJECT CONSTRUCTOR
    public Delete(Driver webDriver) {
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webDriver, timeout);
        interact = new Interact(webDriver, timeout);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By Delete_Btn = By.xpath("//input[@value='Delete']");
    
    private By Confirm_Btn = By.xpath("//input[@value='Confirm']");
    
    private By DeleteRevision_Btn = By.xpath("//input[@value='Delete revision']");
    
    private By Cancel_Lnk = By.xpath("//a[text()='Cancel']");
    
    
    //PAGE OBJECT METHODS
    public void ClickDeleteBtn() throws Exception {
    	
    	Reporter.log("Click the 'Delete' button.");
    	interact.Click(waitFor.ElementVisible(Delete_Btn));
    	
    }
    
    public void ClickConfirmBtn() throws Exception {
    	
    	Reporter.log("Click the 'Confirm' button.");
    	interact.Click(waitFor.ElementVisible(Confirm_Btn));
    	
    }
    
    public void ClickDeleteRevisionBtn() throws Exception {
    	
    	Reporter.log("Click the 'Delete revision' button.");
    	interact.Click(waitFor.ElementVisible(DeleteRevision_Btn));
    	
    }
    
    public void ClickCancelLnk() throws Exception {
    	
    	Reporter.log("Click the 'Cancel' link.");
    	interact.Click(waitFor.ElementVisible(Cancel_Lnk));
    	
    }
    
}

