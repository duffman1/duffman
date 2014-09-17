package com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
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
    
    //PAGE OBJECT CONSTRUCTOR
    public AddDynamicQueueType(Driver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        config = new Config();
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.ID, using = "edit-label")
    private WebElement Name_Txb;
    
    @FindBy(how = How.ID, using = "edit-entity-type")
    private WebElement EntityType_Ddl;
    
    private WebElement ContentType_Cbx(String contentType) {
    	return webDriver.findElement(By.xpath("//label[text()='" + contentType + " ']/../input"));
    }
    
    @FindBy(how = How.ID, using = "edit-cache-lifetime")
    private WebElement CacheLifetime_Ddl;
    
    @FindBy(how = How.XPATH, using = "//a/strong[text()='Taxonomy filters']")
    private WebElement TaxonomyFilters_Lnk;
    
    private WebElement TaxonomyFilter_Lnk(String filterName) {
    	return webDriver.findElement(By.xpath("//label[contains(text(), '" + filterName + "')]/../input"));
    }
    
    @FindBy(how = How.ID, using = "edit-submit")
    private WebElement Save_Btn;
    
    
    //PAGE OBJECT METHODS
    public void EnterName(String queueTypeName) throws Exception {
    	
    	Reporter.log("Enter '" + queueTypeName + "' in the 'Name' text box.");
    	Name_Txb.sendKeys(queueTypeName);
    }
    
    public void SelectEntityType() throws Exception {
    	
    	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	Reporter.log("Select 'Content' or 'Node' from the 'Entity type' drop down list.");
    	try {
    		new Select(EntityType_Ddl).selectByVisibleText("Content");
    	}
    	catch (Exception e) {
    		new Select(EntityType_Ddl).selectByVisibleText("Node");
    	}
    	webDriver.manage().timeouts().implicitlyWait(config.getImplicitWaitTime(), TimeUnit.SECONDS);
    }
    
    public void SelectCacheLifetime(String lifetime) throws Exception {
    	
    	Reporter.log("Select '" + lifetime + "' from the 'Cache lifetime' drop down list.");
    	new Select(CacheLifetime_Ddl).selectByVisibleText(lifetime);
    }
    
    public void ClickTaxonomyFilterLnk() throws Exception {
    	
    	Reporter.log("Click the 'Taxonomy filters' link.");
    	TaxonomyFilters_Lnk.click();
    }
    
    public void EnableTaxonomyFilters(List<String> filterTypes) throws Exception {
    	
    	for (String filterType : filterTypes) {
    		if (!TaxonomyFilter_Lnk(filterType).isSelected()) {
    			Reporter.log("Check the '" + filterType + "' check box.");
    			TaxonomyFilter_Lnk(filterType).click();
    		}
    	}
    }
    
    public void EnableContentTypes(List<String> contentTypes) throws Exception {
    	
    	for (String contentType : contentTypes) {
    		if (ContentType_Cbx(contentType).isSelected() == false) {
    			Reporter.log("Check the '" + contentType + "' check box.");
    			ContentType_Cbx(contentType).click();
    		}
    	}
    }
    
    public void ClickSaveBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save' button.");
    	Save_Btn.click();
    }
    
    
}

