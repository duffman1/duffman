package com.nbcuni.test.publisher.pageobjects.People;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com Password Policies Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: May 6, 2014
 *********************************************/

public class PasswordPolicies {

    //PAGE OBJECT CONSTRUCTOR
    public PasswordPolicies(Driver webDriver) {
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.ID, using = "edit-policies-1-view")
    private WebElement View_Lnk;
    
    
    //PAGE OBJECT METHODS
    public void ClickViewLnk() throws Exception {
    	
    	Reporter.log("Click the 'view' link.");
    	View_Lnk.click();
    }
    
}

