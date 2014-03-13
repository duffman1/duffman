package com.nbcuni.test.publisher.pageobjects.Structure;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import com.nbcuni.test.webdriver.CustomWebDriver;

/*********************************************
* publisher.nbcuni.com Manage Display Library. Copyright
*
* @author Brandon Clark
* @version 1.0 Date: March 12, 2014
*********************************************/

public class ManageDisplay {

    //PAGE OBJECT CONSTRUCTOR
    public ManageDisplay(CustomWebDriver webDriver) {
    	PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.ID, using = "edit-fields-field-movie-cover-media-type")
    private static WebElement CoverMediaFormat_Ddl;
    
    @FindBy(how = How.XPATH, using = "//div[@class='field-formatter-summary']/em[contains(text(), 'Default')]")
    private static WebElement DefaultViewMode_Txt;
    
    @FindBy(how = How.ID, using = "edit-submit")
    private static WebElement Save_Btn;

    
    //PAGE OBJECT METHODS
    public void SelectCoverMediaFormat(String option) throws Exception {
    
    	Reporter.log("Select '" + option + "' from the Cover Media 'Format' drop down list.");
    	new Select(CoverMediaFormat_Ddl).selectByVisibleText(option);
    }
    
    public void VerifyDefaultViewModeSelected() throws Exception {
        
    	Reporter.log("Verify the default view mode is selected.");
    	DefaultViewMode_Txt.isDisplayed();
    }
    
    public void ClickSaveBtn() throws Exception {
        
    	Reporter.log("Click the 'Save' button.");
    	Save_Btn.click();
    }
    
}
