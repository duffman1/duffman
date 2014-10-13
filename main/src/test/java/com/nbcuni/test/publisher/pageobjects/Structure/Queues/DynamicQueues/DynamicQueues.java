package com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Driver.Driver;

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
    private AppLib applib;
    
    //PAGE OBJECT CONSTRUCTOR
    public DynamicQueues(Driver webDriver, AppLib applib) {
        this.webDriver = webDriver;
        this.applib = applib;
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private WebElement Edit_Lnk(String dynamicQueueName) {
    	return webDriver.findElement(By.xpath("//a[contains(text(), '" + dynamicQueueName + "')]/../..//a[text()='Edit']"));
    }
    
    private WebElement Status_Lnk(String dynamicQueueName) {
    	return webDriver.findElement(By.xpath("//a[contains(text(), '" + dynamicQueueName + "')]/../..//td[contains(@class,'views-field-status')]"));
    }
    
    
    //PAGE OBJECT METHODS
    public void ClickEditLnk(String dynamicQueueName) throws Exception {
    	
    	Reporter.log("Click the 'edit' link for Dynamic Queue Name '" + dynamicQueueName + ".");
    	Edit_Lnk(dynamicQueueName).click();
    }
    
    public String GetDynamicQueueNodeNumber(String dynamicQueueName) throws Exception {
        
    	return Edit_Lnk(dynamicQueueName).getAttribute("href").replace(applib.getApplicationURL() + 
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
    
}

