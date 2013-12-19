package com.nbcuni.test.publisher;


import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.webdriver.CustomWebDriver;


/*********************************************
 * publisher.nbcuni.com Logout Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 13, 2013
 *********************************************/

public class Logout {

    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    
    private static String Page_Title = "Site-Install";
    private static String LogOut_Btn = "(//a[text()='Log out'])[1]";
    
    

    public Logout(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
    
    
    public void LogoutBtnPresent() throws Exception {
    	
    	ul.verifyObjectExists(LogOut_Btn);
    	
    }
    
    public void ClickLogoutBtn() {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(LogOut_Btn)));
    	webDriver.click(LogOut_Btn);
    }
    
    
    
    
  
}

