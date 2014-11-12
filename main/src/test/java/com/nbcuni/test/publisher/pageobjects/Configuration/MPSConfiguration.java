package com.nbcuni.test.publisher.pageobjects.Configuration;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
	private WebDriverWait wait;
	private WaitFor waitFor;
	private Config config;
	
    //PAGE OBJECT CONSTRUCTOR    
    public MPSConfiguration(Driver webDriver) {
    	this.webDriver = webDriver;
    	config = new Config();
    	PageFactory.initElements(webDriver, this);
    	wait = new WebDriverWait(webDriver, 10);
    	waitFor = new WaitFor(webDriver, config.getConfigValueInt("WaitForWaitTime"));
    }
    
    //PAGE OBJECT IDENTIFIERS    
    @FindBy(how = How.ID, using ="edit-mps-host")
    private WebElement MPSHost_Txb;
    
    private WebElement IntegrationMethod_Rdb(String integrationMethod) {
    	return webDriver.findElement(By.xpath("//label[text()='" + integrationMethod + " ']/..//input"));
    }
    
    @FindBy(how = How.ID, using ="edit-mps-site-override")
    private WebElement SiteInstanceOverride_Txb;
    
    @FindBy(how = How.ID, using ="edit-mps-query")
    private WebElement SendQueryStrings_Cbx;
    
    private WebElement Name_Txb(String index) {
    	return webDriver.findElement(By.xpath("(//input[contains(@id, 'name')])[" + index + "]"));
    }
    
    private List<WebElement> AllName_Txbs() {
    	return webDriver.findElements(By.xpath("//input[contains(@id, 'name')]"));
    }
    
    private WebElement Value_Txb(String index) {
    	return webDriver.findElement(By.xpath("(//input[contains(@id, 'value')])[" + index + "]"));
    }
    
    private List<WebElement> AllValue_Txbs() {
    	return webDriver.findElements(By.xpath("//input[contains(@id, 'value')]"));
    }
    
    private WebElement JSON_Cbx(String index) {
    	return webDriver.findElement(By.xpath("(//input[contains(@id, 'json')])[" + index + "]"));
    }
    
    private List<WebElement> AllJSON_Cbxs() {
    	return webDriver.findElements(By.xpath("//input[contains(@id, 'json')]"));
    }
    
    @FindBy(how = How.ID, using ="edit-add-another-opt")
    private WebElement AddAnotherOpt_Btn;
    
    @FindBy(how = How.ID, using ="edit-mps-cat-pattern")
    private WebElement PatternForCategoryField_Txb;
    
    @FindBy(how = How.XPATH, using ="//fieldset[@id='edit-token-help']/legend//a")
    private WebElement ReplacementPatterns_Lnk;
    
    @FindBy(how = How.XPATH, using ="//td[contains(text(), 'MPS')][1]/span[@class='expander']")
    private WebElement MPSExpander_Lnk;
    
    @FindBy(how = How.XPATH, using ="//a[text()='[mps:cat-pattern:?]']")
    private WebElement MPSCatProperty_Lnk;
    
    private By SyncAdBlocks_Btn = By.xpath("//input[@value='Sync Ad Blocks']");
    
    @FindBy(how = How.XPATH, using ="//div[@class='throbber']")
    private WebElement Spinner_Img;
    
    @FindBy(how = How.ID, using ="edit-submit")
    private WebElement SaveConfiguration_Btn;
    
    @FindBy(how = How.XPATH, using ="//iframe[contains(@id, 'google_ads')][not(contains(@id, 'hidden'))]")
    private WebElement TopMultiAd_Frm;
    
    @FindBy(how = How.XPATH, using ="//img[@class='img_ad']")
    private WebElement Ad_Img;
    
    @FindBy(how = How.XPATH, using ="//script[contains(text(), 'mpscall')]")
    private WebElement MPSCall_Scr;
    
    
    //PAGE OBJECT METHODS
    public void VerifyMPSCallParameters(List<String> parameters) throws Exception {
    	String mpsCallParams = MPSCall_Scr.getAttribute("innerHTML");
    	for (String parameter : parameters) {
    		Reporter.log("Verify the mps call parameter '" + parameter + "' is present in the page source.");
    		Assert.assertTrue(mpsCallParams.contains(parameter), "MPS Call parameter '" + parameter + "' is not present in page source as expected.");
    	}
    }
    
    public Boolean IsMPSEnabled() throws Exception { 
    	
    	Boolean mpsEnabled;
    	if (webDriver.getPageSource().contains("mpscall")) {
    		mpsEnabled = true;
    	}
    	else {
    		mpsEnabled = false;
    	}
    	
    	return mpsEnabled;
    }

    public void VerifyNoMPSCallsMade() throws Exception {
    	
    	Reporter.log("Verify that no MPS calls were made on the page.");
    	Assert.assertFalse(this.IsMPSEnabled());
    }
    
    public void EnterMPSHost(String host) throws Exception { 
    	
    	Reporter.log("Enter '" + host + "' in the 'MPS Host' text box."); 
    	Thread.sleep(500);
    	MPSHost_Txb.clear();
    	MPSHost_Txb.sendKeys(host);
    }
    
    public void ClickIntegrationMethod(String label) throws Exception { 
    	
    	Reporter.log("Click the '" + label + "' radio button."); 
    	IntegrationMethod_Rdb(label).click();
    }
    
    public void EnterSiteInstanceOverride(String override) throws Exception { 
    	
    	Reporter.log("Enter '" + override + "' in the 'Site Instance Override' text box.");
    	SiteInstanceOverride_Txb.clear();
    	SiteInstanceOverride_Txb.sendKeys(override);
    }
    
    public void CheckSendQueryStringsCbx() throws Exception { 
    	
    	if (SendQueryStrings_Cbx.isSelected() == false) {
    		Reporter.log("Check the 'Send Query Strings' check box.");
    		SendQueryStrings_Cbx.click();
    	}
    }
    
    public void UnCheckSendQueryStringsCbx() throws Exception { 
    	
    	if (SendQueryStrings_Cbx.isSelected() == true) {
    		Reporter.log("Un-check the 'Send Query Strings' check box.");
    		SendQueryStrings_Cbx.click();
    	}
    }
    
    public void EnterName(String nameTxt, String index) throws Exception { 
    	
    	Reporter.log("Enter '" + nameTxt + "' in the 'Name' text box with index '" + index + "'.");
    	Name_Txb(index).clear();
    	Name_Txb(index).click();
    	Name_Txb(index).sendKeys(nameTxt);
    }
    
    public void EnterValue(String valueTxt, String index) throws Exception { 
    	
    	Reporter.log("Enter '" + valueTxt + "' in the 'Value' text box with index '" + index + "'.");
    	Value_Txb(index).clear();
    	Value_Txb(index).click();
    	Value_Txb(index).sendKeys(valueTxt);
    }
    
    public void CheckJSONCbx(String index) throws Exception { 
    	
    	Reporter.log("Check the 'JSON' check box with index '" + index + "'.");
    	JSON_Cbx(index).click();
    	
    }
    
    public void ClickAddAnotherOptBtn() throws Exception { 
    	
    	Reporter.log("Click the 'Add another opt' button.");
    	AddAnotherOpt_Btn.click();
    	Thread.sleep(1000);
    }
    
    public void ClickReplacementPatternsLnk() throws Exception { 
    	
    	Reporter.log("Click the 'REPLACEMENT PATTERNS' link.");
    	ReplacementPatterns_Lnk.click();
    	Thread.sleep(1000);
    }
    
    public void ClickMPSExpanderLnk() throws Exception { 
    	
    	Reporter.log("Click the 'MPS' link.");
    	wait.until(ExpectedConditions.visibilityOf(MPSExpander_Lnk)).click();
    	Thread.sleep(1000);
    }
    
    public void VerifyMPSCatPropertyLnkPresent() throws Exception { 
    	
    	Reporter.log("Verify the 'MPS CAT Property' link with text '[mps:cat-pattern:?]' is present.");
    	wait.until(ExpectedConditions.visibilityOf(MPSCatProperty_Lnk));
    }
    
    public void EnterPatternForCategoryField(String pattern) throws Exception { 
    	
    	Reporter.log("Enter '" + pattern + "' in the 'Pattern for Category Field' text box.");
    	PatternForCategoryField_Txb.clear();
    	PatternForCategoryField_Txb.sendKeys(pattern);
    }

    public void ClickSyncAdBlocksBtn() throws Exception { 
    	
    	Reporter.log("Click the 'Sync Ad Blocks' button.");
    	waitFor.ElementVisible(SyncAdBlocks_Btn).click();
    	Thread.sleep(1000);
    }

    public void ClickSaveConfigurationBtn() throws Exception { 
    	
    	Reporter.log("Click the 'Save configuration' button.");
    	SaveConfiguration_Btn.click();
    }
    
    public void VerifyTopMultiAdPresent() throws Exception { 
    	
    	Reporter.log("Verify the Ad image is present.");
    	webDriver.switchTo().frame(TopMultiAd_Frm);
    	Assert.assertTrue((Boolean) ((JavascriptExecutor)webDriver).executeScript(
    			"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", 
    			Ad_Img));
    	Assert.assertEquals(Ad_Img.getAttribute("width"), "300");
    	Assert.assertEquals(Ad_Img.getAttribute("height"), "250");
    	
    }
    
    public void CleanAllMPSOptions() throws Exception {
    	
    	Reporter.log("Clear all 'Name' text box values.");
    	for (WebElement Name_Txb : AllName_Txbs()) {
    		Name_Txb.clear();
    	}
    	
    	Reporter.log("Clear all 'Value' text box values.");
    	for (WebElement Value_Txb : AllValue_Txbs()) {
    		Value_Txb.clear();
    	}
    	
    	Reporter.log("Uncheck all 'JSON' check boxes.");
    	for (WebElement JSON_Cbx : AllJSON_Cbxs()) {
    		if (JSON_Cbx.isSelected() == true) {
    			JSON_Cbx.click();
    		}
    	}
    }
    
    
}