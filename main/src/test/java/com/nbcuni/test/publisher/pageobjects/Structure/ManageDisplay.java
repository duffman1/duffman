package com.nbcuni.test.publisher.pageobjects.Structure;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

/*********************************************
* publisher.nbcuni.com Manage Display Library. Copyright
*
* @author Brandon Clark
* @version 1.0 Date: March 12, 2014
* @author Vineela Juturu
* @version 1.1 Date: September 26, 2014
*********************************************/

public class ManageDisplay {
	
	private Config config;
    private Integer timeout;
    private WaitFor waitFor;
    private Interact interact;
    
    //PAGE OBJECT CONSTRUCTOR
    public ManageDisplay(WebDriver webWebWebDriver) {
    	config = new Config();
    	timeout = config.getConfigValueInt("WaitForWaitTime");
    	waitFor = new WaitFor(webWebWebDriver, timeout);
    	interact = new Interact(webWebWebDriver, timeout);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By CoverMediaFormat_Ddl = By.id("edit-fields-field-movie-cover-media-type");
    
    private By DefaultViewMode_Txt = By.xpath("//div[@class='field-formatter-summary']/em[contains(text(), 'Default')]");
    
    public By Save_Btn = By.id("edit-submit");
    
    private By ViewMode_Cbx(String modeName) {
    	return By.xpath("//label[text()='"+ modeName +" ']/../input");
    }
    
    private By Viewmode_Tab(String modeName){
    	return By.xpath("//ul[contains(@class,'secondary')]/li/a[text()='"+ modeName +"']");
    }
    
    private By Format_Ddl(String fieldLabel) {
    	return By.xpath("//td[contains(text(), '" + fieldLabel + "')]/..//select[contains(@class, 'formatter')]");
    }
    
    
    //PAGE OBJECT METHODS
    public void SelectCoverMediaFormat(String option) throws Exception {
    
    	Reporter.log("Select '" + option + "' from the Cover Media 'Format' drop down list.");
    	interact.Select(waitFor.ElementVisible(CoverMediaFormat_Ddl), option);
    	
    }
    
    public void VerifyDefaultViewModeSelected() throws Exception {
        
    	Reporter.log("Verify the default view mode is selected.");
    	waitFor.ElementVisible(DefaultViewMode_Txt);
    	
    }
    
    public void ClickSaveBtn() throws Exception {
        
    	Reporter.log("Click the 'Save' button.");
    	interact.Click(waitFor.ElementVisible(Save_Btn));
    	
    }
    
    public void ClickViewMode(String viewMode) throws Exception{
    	
    	Reporter.log("Click the '"+ viewMode +"' checkbox.");
    	interact.Click(waitFor.ElementVisible(ViewMode_Cbx(viewMode)));
    	
    }
    
    public void ClickViewModeTab(String viewMode) throws Exception{
    	
    	Reporter.log("Click the '"+ viewMode +"' Tab.");
    	interact.Click(waitFor.ElementVisible(Viewmode_Tab(viewMode)));
    	
    }

    public void SelectFormat(String fieldLabel, String formatOption) throws Exception{
    	
    	Reporter.log("Select '" + formatOption + "' from the 'FORMAT' drop down list for 'FIELD' with label '" + fieldLabel + "'.");
    	interact.Select(waitFor.ElementVisible(Format_Ddl(fieldLabel)), formatOption);
    	
    }
}
    

