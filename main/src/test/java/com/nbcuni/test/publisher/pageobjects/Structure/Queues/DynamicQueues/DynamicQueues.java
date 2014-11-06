package com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues;

import java.util.ArrayList;
import java.util.List;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.WaitFor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

/*********************************************
 * publisher.nbcuni.com Dynamic Queue Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: July 31, 2014
 * @author Vineela Juturu
 * @version 1.0 Date: October 13, 2014
 *********************************************/

public class DynamicQueues {

    private Driver webDriver;
    private Config config;
    private WaitFor waitFor;
    
    //PAGE OBJECT CONSTRUCTOR
    public DynamicQueues(Driver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        config = new Config();
        waitFor = new WaitFor(webDriver, 10);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By AddDynamicQueue_Lnk(String dynamicQueueName) {
    	return By.xpath("//a[text()='Add " + dynamicQueueName + "']");
    }
    
    private WebElement Edit_Lnk(String dynamicQueueName) {
    	return webDriver.findElement(By.xpath("//a[contains(text(), '" + dynamicQueueName + "')]/../..//a[text()='Edit']"));
    }
    
    private List<WebElement> AllDynamicQueue_Lnks() {
    	return webDriver.findElements(By.xpath("//div[contains(@class, 'dynamic-queue')]//h2/a"));
    }
    
    private WebElement Status_Lnk(String dynamicQueueName) {
    	return webDriver.findElement(By.xpath("//a[contains(text(), '" + dynamicQueueName + "')]/../..//td[contains(@class,'views-field-status')]"));
    }
    
    
    //PAGE OBJECT METHODS
    public void ClickAddDynamicQueueLnk(String dynamicQueueName) throws Exception {
    	
    	Reporter.log("Click the 'Add " + dynamicQueueName + "' link.");
    	waitFor.ElementVisible(AddDynamicQueue_Lnk(dynamicQueueName)).click();
    	
    }
    
    public void ClickEditLnk(String dynamicQueueName) throws Exception {
    	
    	Reporter.log("Click the 'edit' link for Dynamic Queue Name '" + dynamicQueueName + ".");
    	Edit_Lnk(dynamicQueueName).click();
    }
    
    public String GetDynamicQueueNodeNumber(String dynamicQueueName) throws Exception {
        
    	return Edit_Lnk(dynamicQueueName).getAttribute("href").replace(config.getConfigValueString("AppURL") + 
    			"/dynamic-queue/", "").replace("/edit?destination=admin/content/dynamic-queue", "");

    }
    
    public String GetDynamicQueueStatus(String dynamicQueueName) throws Exception {
        
    	return Status_Lnk(dynamicQueueName).getText();

    }
    
    public void VerifyDynamicQueueStatus(String dynamicQueueName, String status) throws Exception {

    	if (! ((GetDynamicQueueStatus(dynamicQueueName).trim()).equalsIgnoreCase(status.trim())) )
    	    Assert.fail("Dynamic Queue: "+ dynamicQueueName + "status: "+ GetDynamicQueueStatus(dynamicQueueName) + 
    	    		" is not equals to Expected status: "+ status);
    		
    }
    
    public void VerifyVisibleLnkCount(Integer expectedVisibleCount) throws Exception {
    	
    	List<WebElement> allVisibleDQLnks = new ArrayList<WebElement>();
    	for (WebElement dqLnk : AllDynamicQueue_Lnks()) {
    		if (dqLnk.isDisplayed()) {
    			allVisibleDQLnks.add(dqLnk);
    		}
    	}
    	
    	Reporter.log("Verify the visible count of Dynamic Queue Links equals '" + expectedVisibleCount.toString() + "'.");
    	Assert.assertTrue(allVisibleDQLnks.size() == expectedVisibleCount);
    }
    
}

