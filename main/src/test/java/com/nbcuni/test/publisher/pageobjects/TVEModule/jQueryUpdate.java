package com.nbcuni.test.publisher.pageobjects.TVEModule;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com jQuery Update Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: May 29, 2014
 *********************************************/

public class jQueryUpdate {

	private WebDriverWait wait;
	
    //PAGE OBJECT CONSTRUCTOR
    public jQueryUpdate(Driver webDriver) {
    	PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(webDriver, 10);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.ID, using = "edit-jquery-update-jquery-version")
    private WebElement DefaultjQueryVersion_Ddl;
    
    @FindBy(how = How.ID, using = "edit-submit")
    private WebElement SaveConfiguration_Btn;
    
    
    //PAGE OBJECT METHODS
    public void SelectDefaultjQueryVersion(String version) throws Exception {
    	
    	Reporter.log("Select '" + version + "' from the 'Default jQuery Version' drop down list.");
    	new Select(wait.until(ExpectedConditions.visibilityOf(DefaultjQueryVersion_Ddl))).selectByVisibleText(version);
    }
    
    public void ClickSaveConfigurationBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save configuration' button.");
    	Thread.sleep(1000);
    	SaveConfiguration_Btn.click();
    }
    
}

