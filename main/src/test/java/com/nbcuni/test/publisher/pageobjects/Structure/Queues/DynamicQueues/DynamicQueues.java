package com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.ArrayList;
import java.util.List;

/*********************************************
 * publisher.nbcuni.com Dynamic Queue Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: July 31, 2014
 * @author Vineela Juturu
 * @version 1.0 Date: October 13, 2014
 *********************************************/

public class DynamicQueues {

    private Config config;
    private WaitFor waitFor;
    private Interact interact;
    private Integer timeout;
    
    //PAGE OBJECT CONSTRUCTOR
    public DynamicQueues(WebDriver webWebWebDriver) {
        PageFactory.initElements(webWebWebDriver, this);
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webWebWebDriver, timeout);
        interact = new Interact(webWebWebDriver, timeout);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By AddDynamicQueue_Lnk(String dynamicQueueName) {
    	return By.xpath("//div[@id='content']//a[text()='Add " + dynamicQueueName + "']");
    }
    
    private By Edit_Lnk(String dynamicQueueName) {
    	return By.xpath("//a[contains(text(), '" + dynamicQueueName + "')]/../..//a[text()='Edit']");
    }
    
    private By AllDynamicQueue_Lnks = By.xpath("//div[contains(@class, 'dynamic-queue')]//h2/a");
    
    private By Status_Lnk(String dynamicQueueName) {
    	return By.xpath("//a[contains(text(), '" + dynamicQueueName + "')]/../..//td[contains(@class,'views-field-status')]");
    }
    
    
    //PAGE OBJECT METHODS
    public void ClickAddDynamicQueueLnk(String dynamicQueueName) throws Exception {
    	
    	Reporter.log("Click the 'Add " + dynamicQueueName + "' link.");
    	interact.Click(waitFor.ElementVisible(AddDynamicQueue_Lnk(dynamicQueueName)));
    	
    }
    
    public void ClickEditLnk(String dynamicQueueName) throws Exception {
    	
    	Reporter.log("Click the 'edit' link for Dynamic Queue Name '" + dynamicQueueName + ".");
    	interact.Click(waitFor.ElementVisible(Edit_Lnk(dynamicQueueName)));
    	
    }
    
    public String GetDynamicQueueNodeNumber(String dynamicQueueName) throws Exception {
        
    	return waitFor.ElementVisible(Edit_Lnk(dynamicQueueName)).getAttribute("href").replace(config.getConfigValueString("AppURL") + 
    			"/dynamic-queue/", "").replace("/edit?destination=admin/content/dynamic-queue", "");

    }
    
    public void VerifyDynamicQueueStatus(String dynamicQueueName, String status) throws Exception {

    	Reporter.log("Verify dynamic queue '" + dynamicQueueName + "' status is '" + status + "'.");
    	waitFor.ElementContainsText(Status_Lnk(dynamicQueueName), status);
    	
    }
    
    public void VerifyVisibleLnkCount(Integer expectedVisibleCount) throws Exception {
    	
    	List<WebElement> allVisibleDQLnks = new ArrayList<WebElement>();
    	for (WebElement dqLnk : waitFor.ElementsVisible(AllDynamicQueue_Lnks)) {
    		if (dqLnk.isDisplayed()) {
    			allVisibleDQLnks.add(dqLnk);
    		}
    	}
    	
    	Reporter.log("Verify the visible count of Dynamic Queue Links equals '" + expectedVisibleCount.toString() + "'.");
    	Assert.assertTrue(allVisibleDQLnks.size() == expectedVisibleCount);
    	
    }
    
}

