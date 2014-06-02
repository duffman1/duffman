package com.nbcuni.test.publisher.pageobjects.Content;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Driver.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

/*********************************************
 * publisher.nbcuni.com URL Path Settings Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: June 1, 2014
 *********************************************/
public class URLPathSettings {

	private Driver webDriver;
	private AppLib applib;
    private WebDriverWait wait;
    
	//PAGE OBJECT CONSTRUCTORS
    public URLPathSettings(Driver webDriver, AppLib applib) {
        this.webDriver = webDriver;
        this.applib = applib;
        PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(webDriver, 10);
    }
    
    //PAGE OBJECT IDENTIFIERS AND SCRIPTS
    @FindBy(how = How.XPATH, using = "//a/strong[text()='URL path settings']")
    private WebElement URLPathSettings_Lnk;
    
    @FindBy(how = How.ID, using = "edit-path-pathauto")
    private WebElement GenerateAutomaticURLAlias_Cbx;
    
    @FindBy(how = How.ID, using = "edit-path-alias")
    private WebElement URLAlias_Txb;
    
    
    //PAGE OBJECT METHODS
    public void ClickURLPathSettingsLnk() throws Exception {
    
    	Reporter.log("Click the 'URL path settings' link.");
    	URLPathSettings_Lnk.click();

    	wait.until(ExpectedConditions.visibilityOf(GenerateAutomaticURLAlias_Cbx));
    }
    
    public void EnterURLAlias(String alias) throws Exception {
        
    	Reporter.log("Enter '" + alias + "' in the 'URL alias' text box.");
    	URLAlias_Txb.clear();
    	URLAlias_Txb.sendKeys(alias);

    }

    public void VerifyURLAlias(String title) throws Exception {
        
    	Reporter.log("Verify the 'URL alias' is 'content/" + title.toLowerCase() + "'.");
    	Assert.assertEquals(URLAlias_Txb.getAttribute("value"), "content/" + title.toLowerCase());

    }

    public void VerifyGenerateAutomateURLAliasChecked() throws Exception {
        
    	Reporter.log("Verify the 'Generate automatic URL alias' check box is checked.");
    	Assert.assertTrue(GenerateAutomaticURLAlias_Cbx.isSelected());

    }
    
    public void VerifyGenerateAutomateURLAliasUnCheckedDisabled() throws Exception {
        
    	Reporter.log("Verify the 'Generate automatic URL alias' check box is un-checked and disabled.");
    	Assert.assertFalse(GenerateAutomaticURLAlias_Cbx.isSelected());
    	Assert.assertFalse(GenerateAutomaticURLAlias_Cbx.isEnabled());

    }
    
    public void VerifyGenerateAutomateURLAliasCheckedEnabled() throws Exception {
        
    	Reporter.log("Verify the 'Generate automatic URL alias' check box is checked and enabled.");
    	Assert.assertTrue(GenerateAutomaticURLAlias_Cbx.isSelected());
    	Assert.assertTrue(GenerateAutomaticURLAlias_Cbx.isEnabled());

    }
    
    public void VerifyContentNodeAlias(String title) throws Exception {
    	
    	Reporter.log("Verify that the content node alias equals 'content/" + title.toLowerCase() + "'.");
        Assert.assertEquals(webDriver.getCurrentUrl().replace(applib.getApplicationURL() + "/", ""), "content/" + title.toLowerCase());
    }

}
