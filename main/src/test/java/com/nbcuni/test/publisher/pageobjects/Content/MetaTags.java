package com.nbcuni.test.publisher.pageobjects.Content;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com Meta Tags Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: March 10, 2014
 *********************************************/

public class MetaTags {

    //PAGE OBJECT CONSTRUCTOR
    public MetaTags(Driver webDriver) {
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//a/strong[text()='Meta tags']")
    private WebElement MetaTags_Lnk;
    
    
    //PAGE OBJECT METHODS
    public void ClickMetaTagsLnk() throws Exception {
    	
    	Reporter.log("Click the 'Meta tags' link.");
    	MetaTags_Lnk.click();
    }
    
    
    
}

