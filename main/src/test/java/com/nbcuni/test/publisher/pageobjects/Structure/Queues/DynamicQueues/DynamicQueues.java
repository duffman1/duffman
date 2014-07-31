package com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Driver.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

/*********************************************
 * publisher.nbcuni.com Dynamic Queue Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: July 31, 2014
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
    	return webDriver.findElement(By.xpath("//a[contains(text(), '" + dynamicQueueName + "')]/../..//a[text()='edit']"));
    }
    
    
    //PAGE OBJECT METHODS
    public void ClickEditLnk(String dynamicQueueName) throws Exception {
    	
    	Reporter.log("Click the 'edit' link for Dynamic Queue Name '" + dynamicQueueName + ".");
    	Edit_Lnk(dynamicQueueName).click();
    }
    
    public String GetDynamicQueueNodeNumber(String dynamicQueueName) throws Exception {
        
    	return Edit_Lnk(dynamicQueueName).getAttribute("href").replace(applib.getApplicationURL() + 
    			"/admin/content/dynamic-queue/manage/", "");

    }
    
}

