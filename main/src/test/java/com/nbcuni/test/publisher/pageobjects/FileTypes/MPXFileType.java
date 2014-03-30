package com.nbcuni.test.publisher.pageobjects.FileTypes;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com MPX Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 7, 2014
 *********************************************/

public class MPXFileType {

    //PAGE OBJECT CONSTRUCTOR
    public MPXFileType(Driver webDriver) {
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//select[contains(@id, 'url-alias-field')]")
    private WebElement URLAliasField_Ddl;
    
    @FindBy(how = How.XPATH, using = "//input[contains(@id, 'enable-default-field-overrides')]")
    private WebElement EnableMPXValueOverrides_Cbx;
    
    @FindBy(how = How.ID, using = "edit-submit")
    private WebElement Save_Btn;
    
    
    //PAGE OBJECT METHODS
    public void SelectURLAliasField(String fieldName) throws Exception {
    	
    	Reporter.log("Select '" + fieldName + "' from the 'URL Alias Field' drop down list.");
    	new Select(URLAliasField_Ddl).selectByVisibleText(fieldName);
    }
    
    public boolean EnableMPXValueOverrides() throws Exception {
    	
    	boolean isEnabled = false;
    	
    	if (EnableMPXValueOverrides_Cbx.isSelected() == true) {
    		
    		isEnabled = true;
    	}
    	else {
    		
    		Reporter.log("Click the 'Enable MPX Value Overrides' check box.");
    		EnableMPXValueOverrides_Cbx.click();
    	}
    	
    	return isEnabled;
    }
    
    public void ClickSaveBtn () throws Exception {
    	
    	Reporter.log("Click the 'Save' button.");
    	Save_Btn.click();
    }
    
    
  
}

