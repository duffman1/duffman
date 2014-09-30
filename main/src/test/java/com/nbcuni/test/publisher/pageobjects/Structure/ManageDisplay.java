package com.nbcuni.test.publisher.pageobjects.Structure;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.openqa.selenium.interactions.Actions;

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
    
    private WebElement Viewmode_Cbx(String modeName) {
    	return webDriver.findElement(By.xpath("//label[text()='"+ modeName +" ']/../input"));
    }
    
    private WebElement Viewmode_Tab(String modeName){
    	return webDriver.findElement(By.xpath("//ul[contains(@class,'secondary')]/li/a[text()='"+ modeName +"']"));
    }
    
    private List<WebElement> DisplayTableRows_Lst() {
    	return webDriver.findElements(By.xpath("//table[@id='field-display-overview']/tbody/tr[@id]"));
    }
    
    @FindBy(how = How.XPATH, using = "//td[contains(text(),'Hidden')]")
    private WebElement Hidden_Row;
  
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
    
    public void ClickViewModecheckbox(String viewMode) throws Exception{
    	
    	Reporter.log("Click the '"+ viewMode +"' checkbox.");
    	Viewmode_Cbx(viewMode).click();
    }
    
    public void ClickViewModeTab(String viewMode) throws Exception{
    	
    	Reporter.log("Click the '"+ viewMode +"' Tab.");
    	Viewmode_Tab(viewMode).click();
    }

    public void draganddroprows_to_hiddensection() throws Exception{
    	
    	Actions builder = new Actions(webDriver);
    	List <WebElement> tableRows = DisplayTableRows_Lst();
    	int listSize = tableRows.size();
    	
    	for(int i=1; i<=listSize-1; i++ ){
			//When Page refresh find element needs to happen every time.
    		//Else Stale element exception occurs.
			WebElement we = webDriver.findElement(By.xpath("//table[@id='field-display-overview']/tbody/tr[@id][1]/td/a/div"));
			builder.dragAndDrop(we, Hidden_Row).perform();
			Thread.sleep(1500);
    	}
    	ClickSaveBtn();
    	
    }
}
    

