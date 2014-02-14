package com.nbcuni.test.publisher.pageobjects.Configuration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;

/*********************************************
 * publisher.nbcuni.com Reroute Email Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: February 13, 2014
 *********************************************/

public class RerouteEmail {

    //PAGE OBJECT CONSTRUCTOR    
    public RerouteEmail(CustomWebDriver webDriver, AppLib applib) {
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS    
    @FindBy(how = How.ID, using ="edit-reroute-email-enable")
    private static WebElement EnableRerouting_Cbx;
    
    @FindBy(how = How.ID, using ="edit-reroute-email-address")
    private static WebElement EmailAddresses_Txb;
    
    @FindBy(how = How.ID, using ="edit-submit")
    private static WebElement SaveConfiguration_Btn;
    
   
    //PAGE OBJECT METHODS
    public void CheckEnableReroutingCbx() throws Exception { 
    	
    	if (EnableRerouting_Cbx.isSelected() == false) {
    		Reporter.log("Click the 'Enable rerouting' checkbox.");
    		EnableRerouting_Cbx.click();
    	}
    }
    
    public void EnterEmailAddresses(String emailAddresses) throws Exception {
    	
    	Reporter.log("Enter '" + emailAddresses + "' in the 'Email addresses' text box.");
    	EmailAddresses_Txb.clear();
    	EmailAddresses_Txb.sendKeys(emailAddresses);
    }
    
    public void ClickSaveConfigurationBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save configuration' button.");
    	SaveConfiguration_Btn.click();
    }
    
    
    
}