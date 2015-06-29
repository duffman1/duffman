package com.nbcuni.test.publisher.pageobjects.Structure.Queues.Queues;

import com.nbcuni.test.publisher.common.AppLib;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

/*********************************************
 * publisher.nbcuni.com Queue Types Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: March 26, 2014
 *********************************************/

public class QueueTypes {

    private WebDriver webWebWebDriver;
    
    //PAGE OBJECT CONSTRUCTOR
    public QueueTypes(WebDriver webWebWebDriver, AppLib applib) {
        this.webWebWebDriver = webWebWebDriver;
        PageFactory.initElements(webWebWebDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//a[text()='Add queue type']")
    private WebElement AddQueueType_Lnk;
    
    private WebElement DeleteQueueType_Lnk(String queueType) {
    	return webWebWebDriver.findElement(By.xpath("//td[contains(text(), '" + queueType + "')]/..//a[text()='delete']"));
    }
    
    
    //PAGE OBJECT METHODS
    public void ClickAddQueueTypeLnk() throws Exception {
    	
    	Reporter.log("Click the 'Add queue type' link.");
    	AddQueueType_Lnk.click();
    }
    
    public void ClickDeleteQueueLnk(String queueType) throws Exception {
    	
    	Reporter.log("Click the 'delete' link for Queue Type '" + queueType + ".");
    	DeleteQueueType_Lnk(queueType).click();
    }
    
}

