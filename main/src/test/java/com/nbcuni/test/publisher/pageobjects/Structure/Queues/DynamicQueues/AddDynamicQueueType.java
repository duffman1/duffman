package com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import java.util.List;
import java.util.concurrent.TimeUnit;

/*********************************************
 * publisher.nbcuni.com Add Dynamic Queue Type Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: July 30, 2014
 *********************************************/

public class AddDynamicQueueType {

    private Driver webDriver;
    private Config config;
    private Integer timeout;
    private WaitFor waitFor;
    private Interact interact;
    
    //PAGE OBJECT CONSTRUCTOR
    public AddDynamicQueueType(Driver webDriver) {
        this.webDriver = webDriver;
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webDriver, timeout);
        interact = new Interact(webDriver, timeout);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By Name_Txb = By.id("edit-label");
    
    private By EntityType_Ddl = By.id("edit-entity-type");
    
    private By ContentType_Cbx(String contentType) {
    	return By.xpath("//label[text()='" + contentType + " ']/../input");
    }
    
    private By CacheLifetime_Ddl = By.id("edit-cache-lifetime");
    
    private By TaxonomyFilters_Lnk = By.xpath("//a/strong[text()='Taxonomy filters']");
    
    private By TaxonomyFilter_Lnk(String filterName) {
    	return By.xpath("//label[contains(text(), '" + filterName + "')]/../input");
    }
    
    private By Save_Btn = By.id("edit-submit");
    
    private By MachineNameEdit_Lnk = By.xpath("//input[@id='edit-label']/..//a[text()='Edit']");
    
    private By MachineName_Txb = By.id("edit-type");
    
    
    //PAGE OBJECT METHODS
    public void EnterName(String queueTypeName) throws Exception {
    	
    	Reporter.log("Enter '" + queueTypeName + "' in the 'Name' text box.");
    	interact.Type(waitFor.ElementVisible(Name_Txb), queueTypeName);
    	
    }
    
    public void SelectEntityType() throws Exception {
    	
    	WebElement ele = waitFor.ElementVisible(EntityType_Ddl);
    	
    	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	Reporter.log("Select 'Content' or 'Node' from the 'Entity type' drop down list.");
    	try {
    		new Select(ele).selectByVisibleText("Content");
    	}
    	catch (Exception e) {
    		new Select(ele).selectByVisibleText("Node");
    	}
    	webDriver.manage().timeouts().implicitlyWait(config.getConfigValueInt("ImplicitWaitTime"), TimeUnit.SECONDS);
    }
    
    public void SelectCacheLifetime(String lifetime) throws Exception {
    	
    	Reporter.log("Select '" + lifetime + "' from the 'Cache lifetime' drop down list.");
    	interact.Select(waitFor.ElementVisible(CacheLifetime_Ddl), lifetime);
    	
    }
    
    public void ClickTaxonomyFilterLnk() throws Exception {
    	
    	Reporter.log("Click the 'Taxonomy filters' link.");
    	interact.Click(waitFor.ElementVisible(TaxonomyFilters_Lnk));
    	
    }
    
    public void EnableTaxonomyFilters(List<String> filterTypes) throws Exception {
    	
    	for (String filterType : filterTypes) {
    		WebElement ele = waitFor.ElementVisible(TaxonomyFilter_Lnk(filterType));
    		if (!ele.isSelected()) {
    			Reporter.log("Check the '" + filterType + "' check box.");
    			interact.Click(ele);
    		}
    	}
    	
    }
    
    public void EnableContentTypes(List<String> contentTypes) throws Exception {
    	
    	for (String contentType : contentTypes) {
    		WebElement ele = waitFor.ElementVisible(ContentType_Cbx(contentType));
    		if (ele.isSelected() == false) {
    			Reporter.log("Check the '" + contentType + "' check box.");
    			interact.Click(ele);
    		}
    	}
    	
    }
    
    public void ClickSaveBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save' button.");
    	interact.Click(waitFor.ElementVisible(Save_Btn));
    	
    	Thread.sleep(2000); //no verification message generated on save click
    	
    }
    
    public void EnterMachineName(String queueTypeName) throws Exception {
    	
    	Reporter.log("Enter '" + queueTypeName + "' in the 'MachineName' text box.");
    	interact.Type(waitFor.ElementVisible(MachineName_Txb), queueTypeName);
    	
    }
    
    public void ClickMachineNameEditLnk() throws Exception {
    	
    	Reporter.log("Click the 'MachineNameEdit_Lnk' button.");
    	interact.Click(waitFor.ElementVisible(MachineNameEdit_Lnk));
    	
    }
    
    
}

