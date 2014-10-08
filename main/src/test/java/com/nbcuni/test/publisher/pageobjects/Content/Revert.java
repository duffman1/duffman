package com.nbcuni.test.publisher.pageobjects.Content;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com Revert Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: March 13, 2014
 *********************************************/

public class Revert {

    //PAGE OBJECT CONSTRUCTOR
    public Revert(Driver webDriver) {
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.ID, using = "edit-submit")
    private WebElement Revert_Btn;
    
    
    //PAGE OBJECT METHODS
    public void ClickRevertBtn() throws Exception {
    	
    	Reporter.log("Click the 'Revert' button.");
    	Revert_Btn.click();
    }
    
}

