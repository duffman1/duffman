package com.nbcuni.test.publisher.pageobjects.Configuration;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
 * publisher.nbcuni.com MPS Configuration Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.1 Date: July 7, 2014
 *********************************************/

public class MPSConfiguration {

	private Driver webDriver;
	private WaitFor waitFor;
	private Config config;
	
    //PAGE OBJECT CONSTRUCTOR    
    public MPSConfiguration(Driver webDriver) {
    	this.webDriver = webDriver;
    	config = new Config();
    	PageFactory.initElements(webDriver, this);
    	waitFor = new WaitFor(webDriver, config.getConfigValueInt("WaitForWaitTime"));
    }
    
    //PAGE OBJECT IDENTIFIERS    
    private By MPSHost_Txb = By.id("edit-mps-host");
    
    private By IntegrationMethod_Rdb(String integrationMethod) {
    	return By.xpath("//label[text()='" + integrationMethod + " ']/..//input");
    }
    
    private By SiteInstanceOverride_Txb = By.id("edit-mps-site-override");
    
    private By SendQueryStrings_Cbx = By.id("edit-mps-query");
    
    private By Name_Txb(String index) {
    	return By.xpath("(//input[contains(@id, 'name')])[" + index + "]");
    }
    
    private By AllName_Txbs = By.xpath("//input[contains(@id, 'name')]");
    
    private By Value_Txb(String index) {
    	return By.xpath("(//input[contains(@id, 'value')])[" + index + "]");
    }
    
    private By AllValue_Txbs = By.xpath("//input[contains(@id, 'value')]");
    
    private By JSON_Cbx(String index) {
    	return By.xpath("(//input[contains(@id, 'json')])[" + index + "]");
    }
    
    private By AllJSON_Cbxs = By.xpath("//input[contains(@id, 'json')]");
    
    private By AddAnotherOpt_Btn = By.id("edit-add-another-opt");
    
    private By PatternForCategoryField_Txb = By.id("edit-mps-cat-pattern");
    
    private By ReplacementPatterns_Lnk = By.xpath("//fieldset[@id='edit-token-help']/legend//a");
    
    private By BrowseAvailableTokens_Lnk = By.linkText("Browse available tokens.");
    
    private By MPSExpander_Lnk = By.xpath("//td[contains(text(), 'MPS')][1]/span[@class='expander']");
    
    private By MPSCatProperty_Lnk = By.xpath("//a[text()='[mps:cat-pattern:?]']");
    
    private By SyncAdBlocks_Btn = By.xpath("//input[@value='Sync Ad Blocks']");
    
    private By SaveConfiguration_Btn = By.id("edit-submit");
    
    private By MPSCall_Scr = By.xpath("//script[contains(text(), 'mpscall')]");
    
    
    //PAGE OBJECT METHODS
    public void VerifyMPSCallParameters(List<String> parameters) throws Exception {
    	
    	String mpsCallParams = waitFor.ElementPresent(MPSCall_Scr).getAttribute("innerHTML");
    	
    	for (String parameter : parameters) {
    		Reporter.log("Verify the mps call parameter '" + parameter + "' is present in the page source.");
    		Assert.assertTrue(mpsCallParams.contains(parameter), "MPS Call parameter '" + parameter + "' is not present in page source as expected.");
    	}
    	
    }
    
    public Boolean IsMPSEnabled() throws Exception { 
    	
    	Boolean mpsEnabled = false;
    	if (webDriver.getPageSource().contains("mpscall")) {
    		mpsEnabled = true;
    	}
    	
    	return mpsEnabled;
    }

    public void VerifyNoMPSCallsMade() throws Exception {
    	
    	Reporter.log("Verify that no MPS calls were made on the page.");
    	Assert.assertFalse(this.IsMPSEnabled());
    	
    }
    
    public void EnterMPSHost(String host) throws Exception { 
    	
    	Reporter.log("Enter '" + host + "' in the 'MPS Host' text box."); 
    	WebElement ele = waitFor.ElementVisible(MPSHost_Txb);
    	ele.clear();
    	ele.sendKeys(host);
    	
    }
    
    public void ClickIntegrationMethod(String label) throws Exception { 
    	
    	Reporter.log("Click the '" + label + "' radio button."); 
    	waitFor.ElementVisible(IntegrationMethod_Rdb(label)).click();
    	
    }
    
    public void EnterSiteInstanceOverride(String override) throws Exception { 
    	
    	Reporter.log("Enter '" + override + "' in the 'Site Instance Override' text box.");
    	WebElement ele = waitFor.ElementVisible(SiteInstanceOverride_Txb);
    	ele.clear();
    	ele.sendKeys(override);
    	
    }
    
    public void CheckSendQueryStringsCbx() throws Exception { 
    	
    	WebElement ele = waitFor.ElementVisible(SendQueryStrings_Cbx);
    	
    	if (ele.isSelected() == false) {
    		Reporter.log("Check the 'Send Query Strings' check box.");
    		ele.click();
    	}
    	
    }
    
    public void UnCheckSendQueryStringsCbx() throws Exception { 
    	
    	WebElement ele = waitFor.ElementVisible(SendQueryStrings_Cbx);
    	
    	if (ele.isSelected() == true) {
    		Reporter.log("Un-check the 'Send Query Strings' check box.");
    		ele.click();
    	}
    	
    }
    
    public void EnterName(String nameTxt, String index) throws Exception { 
    	
    	Reporter.log("Enter '" + nameTxt + "' in the 'Name' text box with index '" + index + "'.");
    	WebElement ele = waitFor.ElementVisible(Name_Txb(index));
    	ele.clear();
    	ele.click();
    	ele.sendKeys(nameTxt);
    	
    }
    
    public void EnterValue(String valueTxt, String index) throws Exception { 
    	
    	Reporter.log("Enter '" + valueTxt + "' in the 'Value' text box with index '" + index + "'.");
    	WebElement ele = waitFor.ElementVisible(Value_Txb(index));
    	ele.clear();
    	ele.click();
    	ele.sendKeys(valueTxt);
    	
    }
    
    public void CheckJSONCbx(String index) throws Exception { 
    	
    	Reporter.log("Check the 'JSON' check box with index '" + index + "'.");
    	waitFor.ElementVisible(JSON_Cbx(index)).click();
    	
    }
    
    public void ClickAddAnotherOptBtn() throws Exception { 
    	
    	Reporter.log("Click the 'Add another opt' button.");
    	waitFor.ElementVisible(AddAnotherOpt_Btn).click();
    	
    }
    
    public void ClickReplacementPatternsLnk() throws Exception { 
    	
    	Reporter.log("Click the 'REPLACEMENT PATTERNS' link.");
    	waitFor.ElementVisible(ReplacementPatterns_Lnk).click();
    	
    }
    
    public void ClickBrowseAvailableTokensLnk() throws Exception { 
    	
    	Reporter.log("Click the 'Browse available tokens' link.");
    	waitFor.ElementVisible(BrowseAvailableTokens_Lnk).click();
    	
    }
    
    public void ClickMPSExpanderLnk() throws Exception { 
    	
    	Reporter.log("Click the 'MPS' link.");
    	waitFor.ElementVisible(MPSExpander_Lnk).click();
    	
    }
    
    public void VerifyMPSCatPropertyLnkPresent() throws Exception { 
    	
    	Reporter.log("Verify the 'MPS CAT Property' link with text '[mps:cat-pattern:?]' is present.");
    	waitFor.ElementVisible(MPSCatProperty_Lnk);
    	
    }
    
    public void EnterPatternForCategoryField(String pattern) throws Exception { 
    	
    	Reporter.log("Enter '" + pattern + "' in the 'Pattern for Category Field' text box.");
    	WebElement ele = waitFor.ElementVisible(PatternForCategoryField_Txb);
    	ele.clear();
    	ele.sendKeys(pattern);
    	
    }

    public void ClickSyncAdBlocksBtn() throws Exception { 
    	
    	Reporter.log("Click the 'Sync Ad Blocks' button.");
    	waitFor.ElementVisible(SyncAdBlocks_Btn).click();
    	
    }

    public void ClickSaveConfigurationBtn() throws Exception { 
    	
    	Reporter.log("Click the 'Save configuration' button.");
    	waitFor.ElementVisible(SaveConfiguration_Btn).click();
    	
    }
    
    public void CleanAllMPSOptions() throws Exception {
    	
    	Reporter.log("Clear all 'Name' text box values.");
    	for (WebElement Name_Txb : waitFor.ElementsVisible(AllName_Txbs)) {
    		Name_Txb.clear();
    	}
    	
    	Reporter.log("Clear all 'Value' text box values.");
    	for (WebElement Value_Txb : waitFor.ElementsVisible(AllValue_Txbs)) {
    		Value_Txb.clear();
    	}
    	
    	Reporter.log("Uncheck all 'JSON' check boxes.");
    	for (WebElement JSON_Cbx : waitFor.ElementsVisible(AllJSON_Cbxs)) {
    		if (JSON_Cbx.isSelected() == true) {
    			JSON_Cbx.click();
    		}
    	}
    }
    
    
}
