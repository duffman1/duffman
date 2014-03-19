package com.nbcuni.test.publisher.pageobjects.Content;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

/*********************************************
 * publisher.nbcuni.com Relationships Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: March 19, 2014
 *********************************************/
public class Relationships {

	//PAGE OBJECT CONSTRUCTORS
    public Relationships(CustomWebDriver webDriver, AppLib applib) {
        PageFactory.initElements(webDriver, this);
        new WebDriverWait(webDriver, 10);
    }
    
    //PAGE OBJECT IDENTIFIERS AND SCRIPTS
    @FindBy(how = How.XPATH, using = "//a/strong[text()='Relationships']")
    private static WebElement Relationships_Lnk;
    
    
    //PAGE OBJECT METHODS
    public void ClickRelationshipsLnk() throws Exception {
    
    	Reporter.log("Click the 'Relationships' link.");
    	Relationships_Lnk.click();

    }

}
