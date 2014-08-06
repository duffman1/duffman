package com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues;

import java.util.ArrayList;
import java.util.List;

import com.nbcuni.test.publisher.common.Driver.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

/*********************************************
 * publisher.nbcuni.com Add Dynamic Queue Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: July 30, 2014
 *********************************************/

public class AddDynamicQueue {

    private Driver webDriver;
    
    //PAGE OBJECT CONSTRUCTOR
    public AddDynamicQueue(Driver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.ID, using = "edit-title")
    private WebElement Title_Txb;
    
    private List<WebElement> AllTargetBundle_Cbxs() {
    	return webDriver.findElements(By.xpath("//div[@id='edit-settings-target-bundles']//input"));
    }
    
    private WebElement TargetBundle_Cbx(String contentType) {
    	return webDriver.findElement(By.xpath("//label[text()='" + contentType + " ']/../input"));
    }
    
    @FindBy(how = How.ID, using = "edit-settings-tv-show-select")
    private WebElement TVShows_Ddl;
    
    @FindBy(how = How.ID, using = "edit-settings-sort-dynamic-queue-newest")
    private WebElement SortByNewest_Rdb;
    
    @FindBy(how = How.ID, using = "edit-settings-sort-dynamic-queue-oldest")
    private WebElement SortByOldest_Rdb;
    
    @FindBy(how = How.ID, using = "edit-status")
    private WebElement Published_Cbx;
    
    @FindBy(how = How.ID, using = "edit-submit")
    private WebElement SaveDynamicQueue_Btn;
    
    
    //PAGE OBJECT METHODS
    public void EnterTitle(String queueTitle) throws Exception {
    	
    	Reporter.log("Enter '" + queueTitle + "' in the 'Title' text box.");
    	Title_Txb.sendKeys(queueTitle);
    }
    
    public void CheckTargetBundle_Cbx(String contentType) throws Exception {
    	
    	if (TargetBundle_Cbx(contentType).isSelected() == false) {
    		Reporter.log("");
    		TargetBundle_Cbx(contentType).click();
    	}
    	
    }
    
    public void VerifyTargetBundlesPresent(List<String> contentTypes) throws Exception {
    	
    	Reporter.log("Verify Target bundle content count equals '" + contentTypes.size() + "'.");
    	Assert.assertEquals(AllTargetBundle_Cbxs().size(), contentTypes.size());
    	
    	List<String> allTargetBundleValues = new ArrayList<String>();
    	for (WebElement cbx : AllTargetBundle_Cbxs()) {
    		allTargetBundleValues.add(cbx.getAttribute("value"));
    	}
    	
    	for (String contentType : contentTypes) {
    		Reporter.log("Verify content type '" + contentType + "' is present in list of Target bundles.");
    		Assert.assertTrue(allTargetBundleValues.contains(contentType.toLowerCase()), "Content type '" + contentType 
    				+ "' is not present in list of target bundles.");
    	}
    }
    
    public void SelectTVShow(String showTitle) throws Exception {
    	
    	Reporter.log("Select '" + showTitle + "' from the 'TV Shows' drop down list.");
    	new Select(TVShows_Ddl).selectByVisibleText(showTitle);
    }

    public void ClickSortByNewestRdb() throws Exception {
    	
    	Reporter.log("Click the 'Newest' radio button under 'Sort by'.");
    	SortByNewest_Rdb.click();
    }
    
    public void ClickSortByOldestRdb() throws Exception {
    	
    	Reporter.log("Click the 'Oldest' radio button under 'Sort by'.");
    	SortByOldest_Rdb.click();
    }

    public void ClickSaveDynamicQueueBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save Dynamic Queue' button.");
    	SaveDynamicQueue_Btn.click();
    }
    
    
}

