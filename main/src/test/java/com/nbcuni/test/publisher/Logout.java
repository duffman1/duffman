package com.nbcuni.test.publisher;


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
    private static String LogOut_Btn = "//li[@class='admin-menu-action']/a[text()='Log out']";
    
    

    public Logout(final CustomWebDriver custWebDr, final AppLib al2) {
        webDriver = custWebDr;
        al = al2;
        ul = new Util(webDriver);
        try {
            if (!webDriver.getTitle().contains(Page_Title)) {
                al.fail("Page Was Not in the User Login Page screen as expected");
            }
        } catch (Exception e) {
            al.fail(e.toString()); 
        }
    }
    
    
    public void LogoutBtnPresent() throws Exception {
    	
    	ul.verifyObjectExists(LogOut_Btn);
    	
    }
    
    
    
    
  
}

