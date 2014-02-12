package com.nbcuni.test.publisher.pageobjects.Content;


import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


/*********************************************
 * publisher.nbcuni.com Revision State Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 18, 2013
 *********************************************/

public class RevisionState {

    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    
    private static String RevisionState_Ctr = "//table[@id='revision-form-table']/tbody";
    private static String RevisionsState_Tbl = "//table[@id='revision-form-table']/tbody/tr";
    private static String Body_txt = "//div[@class='field field-name-body field-type-text-with-summary field-label-hidden']";
    private static String Node_num = ".//*[@id='revision-form-table']/tbody/..//td[@class='views-field views-field-vid']";
    public RevisionState(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }

    public void VerifyRevisionCount(Integer RevisionCount) throws Exception {
    	
    	Integer revisionsNode = webDriver.findElements(By.xpath(Node_num)).size();  
    	Integer revisionsRows = (webDriver.findElements(By.xpath(RevisionsState_Tbl)).size())-1;
    	Assert.assertTrue(revisionsNode.intValue() == (RevisionCount));
    	Assert.assertTrue(revisionsRows.intValue() == (RevisionCount));
    	
    }

}

