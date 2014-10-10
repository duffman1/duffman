package com.nbcuni.test.publisher.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com Administration Menu Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: February 10, 2013
 *********************************************/

public class AdministrationMenu {

    //PAGE OBJECT CONSTRUCTOR
    public AdministrationMenu(Driver webDriver, AppLib applib) {
    	PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.ID, using = "edit-admin-menu-position-fixed")
    private WebElement KeepMenuOnTopOfPage_Cbx;
    
    @FindBy(how = How.ID, using = "edit-submit")
    private WebElement SaveConfiguration_Btn;
    
    //PAGE OBJECT METHODS
    public void UnCheckKeepMenuOnTopOfPageCbx() throws Exception {
    	
    	if (KeepMenuOnTopOfPage_Cbx.isSelected() == true) {
    		Reporter.log("Uncheck the 'Keep menu at top of page' check box.");
    		KeepMenuOnTopOfPage_Cbx.click();
    	}
    }
    
    public void ClickSaveConfigurationBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save configuration' button.");
    	SaveConfiguration_Btn.click();
    }
    
    
  
}

