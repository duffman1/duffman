package com.nbcuni.test.publisher.pageobjects.Structure;

import com.nbcuni.test.publisher.common.Driver.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

/*********************************************
 * publisher.nbcuni.com MPS Blocks Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: April 24, 2014
 *********************************************/

public class MPSBlocks {

    //PAGE OBJECT CONSTRUCTOR
    public MPSBlocks(Driver webDriver) {
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.ID, using = "edit-machine-name")
    private WebElement UniqueName_Txb;
    
    @FindBy(how = How.ID, using = "edit-block-name")
    private WebElement BlockName_Txb;
    
    @FindBy(how = How.ID, using = "edit-submit")
    private WebElement Save_Btn;
    
    
    //PAGE OBJECT METHODS
    public void EnterUniqueName(String uniqueName) throws Exception {
    	
    	Reporter.log("Enter '" + uniqueName + "' in the 'Unique Name' text box.");
    	UniqueName_Txb.sendKeys(uniqueName);
    }
    
    public void EnterBlockName(String blockName) throws Exception {
    	
    	Reporter.log("Enter '" + blockName + "' in the 'Block Name' text box.");
    	BlockName_Txb.sendKeys(blockName);
    }
    
    public void ClickSaveBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save' button.");
    	Save_Btn.click();
    }
    
}
