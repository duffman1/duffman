package com.nbcuni.test.publisher.pageobjects.Structure;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
* publisher.nbcuni.com Import Side File Library. Copyright
*
* @author Brandon Clark
* @version 1.0 Date: March 12, 2014
*********************************************/

public class ImportSideFile {

    //PAGE OBJECT CONSTRUCTOR
    public ImportSideFile(Driver webDriver) {
    	PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.ID, using = "edit-import")
    private WebElement SideFileCode_Txa;
    
    @FindBy(how = How.ID, using = "edit-overwrite")
    private WebElement AllowImportOverwriteRecord_Cbx;
    
    @FindBy(how = How.ID, using = "edit-next")
    private WebElement Continue_Btn;
    
    
    //PAGE OBJECT METHODS
    public void EnterSideFileCode(String code) throws Exception {
    
    	Reporter.log("Enter '" + code + "' in the 'Side file code' text area.");
    	SideFileCode_Txa.sendKeys(code);
    }
    
    public void UncheckAllowImportOverwriteRecordCbx() throws Exception {
        
    	if (AllowImportOverwriteRecord_Cbx.isSelected() == true) {
    		Reporter.log("Uncheck the 'Allow import to overwrite an existing record' check box.");
    		AllowImportOverwriteRecord_Cbx.click();
    	}
    }
    
    public void ClickContinueBtn() throws Exception {
        
    	Reporter.log("Click the 'Continue' button.");
    	Continue_Btn.click();
    }
    
}
