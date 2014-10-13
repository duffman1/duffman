package com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Driver.Driver;


/*********************************************
 * publisher.nbcuni.com Queues Dynamic Revision Library. Copyright
 * 
 * @author Vineela Juturu
 * @version 1.0 Date: October 7, 2014
 *********************************************/


public class DynamicQueueRevisionsList {

		private Driver webDriver;
		private WebDriverWait wait;
		
	    //PAGE OBJECT CONSTRUCTOR
	    public DynamicQueueRevisionsList(Driver webDriver) {
	    	this.webDriver = webDriver;
	        PageFactory.initElements(webDriver, this);
	        wait = new WebDriverWait(webDriver, 10);
	    }
	    
	    //PAGE OBJECT IDENTIFIERS
	    @FindBy(how = How.XPATH, using = "//a[text()='Schedule']")
	    private WebElement Schedule_Tab;
	    
	    List<WebElement> StateFlow_Rws() {
			return webDriver.findElements(By.xpath("//table[contains(@class, 'views-table')]//tr[contains(@class, 'state-flow')]"));
		}
	    
	    public void VerifyRevisionCount(int revisionCount) throws Exception {
	    	
	    	Reporter.log("Verify the count of revisions in the Queues revision list is '" + revisionCount + "'.");
	    	Assert.assertEquals(StateFlow_Rws().size(), revisionCount);
	    }
	    
	    public void clickScheduleTab() throws Exception{
	    	
	    	Reporter.log("Click Schedule Tab");	
	    	Schedule_Tab.click();
	    	
	    }

}
