package com.nbcuni.test.publisher.pageobjects.Content;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import com.nbcuni.test.webdriver.CustomWebDriver;

/*********************************************
 * publisher.nbcuni.com Delete Queue Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 19, 2013
 *********************************************/

public class Delete {

    //PAGE OBJECT CONSTRUCTOR
    public Delete(CustomWebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//input[@value='Delete']")
    private static WebElement Delete_Btn;
    
    @FindBy(how = How.XPATH, using = "//a[text()='Cancel']")
    private static WebElement Cancel_Lnk;
    
    
    //PAGE OBJECT METHODS
    public void ClickDeleteBtn() throws Exception {
    	
    	Reporter.log("Click the 'Delete' button.");
    	Delete_Btn.click();
    }
    
    public void ClickCancelLnk() throws Exception {
    	
    	Reporter.log("Click the 'Cancel' link.");
    	Cancel_Lnk.click();
    }
    
    
    
    
  
}

