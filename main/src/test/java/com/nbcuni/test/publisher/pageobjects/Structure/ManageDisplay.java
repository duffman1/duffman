package com.nbcuni.test.publisher.pageobjects.Structure;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
* publisher.nbcuni.com Manage Display Library. Copyright
*
* @author Brandon Clark
* @version 1.0 Date: March 12, 2014
* @author Vineela Juturu
* @version 1.1 Date: September 26, 2014
*********************************************/

public class ManageDisplay {
	
	private Driver webDriver;

    //PAGE OBJECT CONSTRUCTOR
    public ManageDisplay(Driver webDriver) {
    	this.webDriver = webDriver;
    	PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.ID, using = "edit-fields-field-movie-cover-media-type")
    private WebElement CoverMediaFormat_Ddl;
    
    @FindBy(how = How.XPATH, using = "//div[@class='field-formatter-summary']/em[contains(text(), 'Default')]")
    private WebElement DefaultViewMode_Txt;
    
    @FindBy(how = How.ID, using = "edit-submit")
    public WebElement Save_Btn;
    
    private WebElement ViewMode_Cbx(String modeName) {
    	return webDriver.findElement(By.xpath("//label[text()='"+ modeName +" ']/../input"));
    }
    
    private WebElement Viewmode_Tab(String modeName){
    	return webDriver.findElement(By.xpath("//ul[contains(@class,'secondary')]/li/a[text()='"+ modeName +"']"));
    }
    
    private WebElement Format_Ddl(String fieldLabel) {
    	return webDriver.findElement(By.xpath("//td[contains(text(), '" + fieldLabel + "')]/..//select[contains(@class, 'formatter')]"));
    }
    
    
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
    
    public void ClickViewMode(String viewMode) throws Exception{
    	
    	Reporter.log("Click the '"+ viewMode +"' checkbox.");
    	ViewMode_Cbx(viewMode).click();
    }
    
    public void ClickViewModeTab(String viewMode) throws Exception{
    	
    	Reporter.log("Click the '"+ viewMode +"' Tab.");
    	try {
    		Viewmode_Tab(viewMode).click();
    	}
    	catch (Exception e) {
    		webDriver.executeScript("arguments[0].click();", Viewmode_Tab(viewMode));
    	}
    	
    }

    public void SelectFormat(String fieldLabel, String formatOption) throws Exception{
    	
    	Reporter.log("Select '" + formatOption + "' from the 'FORMAT' drop down list for 'FIELD' with label '" + fieldLabel + "'.");
    	new Select(Format_Ddl(fieldLabel)).selectByVisibleText(formatOption);
    }
}
    

