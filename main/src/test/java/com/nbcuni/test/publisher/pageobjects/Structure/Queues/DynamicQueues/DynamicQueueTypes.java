package com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues;

import java.util.ArrayList;
import java.util.List;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import com.nbcuni.test.publisher.pageobjects.Content.Delete;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

/*********************************************
 * publisher.nbcuni.com Dynamic Queue Types Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: July 31, 2014
 *********************************************/

public class DynamicQueueTypes {

    private Config config;
    private Integer timeout;
    private WaitFor waitFor;
    private Interact interact;
    private Delete delete;
    
    //PAGE OBJECT CONSTRUCTOR
    public DynamicQueueTypes(Driver webDriver) {
        delete = new Delete(webDriver);
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webDriver, timeout);
        interact = new Interact(webDriver, timeout);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By AddDynamicQueueType_Lnk = By.xpath("//a[text()='Add dynamic queue type']");
    
    private By AllDynamicQueue_Lnks = By.xpath("//table[2]//td[1]//a");
    
    private By Edit_Lnk(String queueType) {
    	return By.xpath("//td[contains(text(), '" + queueType + "')]/..//a[text()='edit']");
    }
    
    private By Delete_Lnk(String queueType) {
    	return By.xpath("//td[contains(text(), '" + queueType + "')]/..//a[text()='delete']");
    }
    
    private By ManageDisplay_Lnk(String queueType) {
    	return By.xpath("//td[contains(text(), '" + queueType + "')]/..//a[text()='manage display']");
    }
    
    
    //PAGE OBJECT METHODS
    public void ClickAddDynamicQueueTypeLnk() throws Exception {
    	
    	Reporter.log("Click the 'Add dynamic queue type' link.");
    	interact.Click(waitFor.ElementVisible(AddDynamicQueueType_Lnk));
    	
    }
    
    public void ClickEditLnk(String dynamicQueueType) throws Exception {
    	
    	Reporter.log("Click the 'edit' link for Dynamic Queue Type '" + dynamicQueueType + ".");
    	interact.Click(waitFor.ElementVisible(Edit_Lnk(dynamicQueueType)));
    	
    }
    
    public void ClickManageDisplayLnk(String dynamicQueueType) throws Exception {
    	
    	Reporter.log("Click the 'manage display' link for Dynamic Queue Type '" + dynamicQueueType + ".");
    	interact.Click(waitFor.ElementVisible(ManageDisplay_Lnk(dynamicQueueType)));
    	
    }
    
    public void DeleteAllDynamicQueues() throws Exception {
    	
    	List<String> allDynamicQueueTitles = new ArrayList<String>();
    	for (WebElement lnk : waitFor.ElementsPresent(AllDynamicQueue_Lnks)) {
    		allDynamicQueueTitles.add(lnk.getText());
    	}
    	
    	for (String queueTitle : allDynamicQueueTitles) {
    		interact.Click(waitFor.ElementVisible(Delete_Lnk(queueTitle)));
    		delete.ClickConfirmBtn();
    	}
    	
    }
    
}

