package com.nbcuni.test.publisher.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com DFP Add Tags Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 15, 2013
 *********************************************/

public class DFPAddTags {

    ContentParent contentParent;
    
    //PAGE OBJECT CONSTRUCTOR
    public DFPAddTags(Driver webDriver, AppLib applib) {
        PageFactory.initElements(webDriver, this);
        contentParent = new ContentParent(webDriver);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.ID, using = "edit-dfp-network-id")
    private WebElement NetworkId_Txb;
    
    @FindBy(how = How.ID, using = "edit-submit")
    private WebElement SaveConfiguration_Btn;
    
    @FindBy(how = How.ID, using = "edit-submit")
    private WebElement Save_Btn;
    
    @FindBy(how = How.ID, using = "edit-slot")
    private WebElement AdSlotName_Txb;
    
    @FindBy(how = How.ID, using = "edit-size")
    private WebElement Sizes_Txb;
    
    @FindBy(how = How.ID, using = "edit-adunit")
    private WebElement AdUnitPattern_Txb;
    
    
    //PAGE OBJECT METHODS
    public void EnterNetworkId(String networkIdName) throws Exception {
    	
    	Reporter.log("Enter '" + networkIdName + "' in the 'Network ID' text box.");
    	NetworkId_Txb.clear();
    	NetworkId_Txb.sendKeys(networkIdName);
    }
    
    public void ClickSaveConfigurationBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save configuration' button.");
    	SaveConfiguration_Btn.click();
    }
    
    public void ClickSaveBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save' button.");
    	Save_Btn.click();
    }
    
    public void EnterAdSlotName(String adSlotName) throws Exception {
    	
    	Reporter.log("Enter '" + adSlotName + "' in the 'Ad Slot Name' text box.");
    	AdSlotName_Txb.sendKeys(adSlotName);
    }
    
    public void EnterAdSizes(String adSize) throws Exception {
    	
    	Reporter.log("Enter '" + adSize + "' in the 'Ad Sizes' text box.");
    	Sizes_Txb.sendKeys(adSize);
    }
    
    public void EnterAdUnitPattern(String adPattern) throws Exception {
    	
    	Reporter.log("Enter '" + adPattern + "' in the 'Ad Unit Pattern' text box.");
    	AdUnitPattern_Txb.sendKeys(adPattern);
    	
    }
    
}

