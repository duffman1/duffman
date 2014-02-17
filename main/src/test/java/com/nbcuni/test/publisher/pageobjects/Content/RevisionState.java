package com.nbcuni.test.publisher.pageobjects.Content;

import java.util.List;
import com.nbcuni.test.webdriver.CustomWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

/*********************************************
 * publisher.nbcuni.com Revision State Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 18, 2013
 *********************************************/

public class RevisionState {

    private static CustomWebDriver webDriver;
    
    //PAGE OBJECT CONSTRUCTOR
    public RevisionState(CustomWebDriver webDriver) {
        RevisionState.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private static List<WebElement> RevisionsState_Trs() {
    	return webDriver.findElements(By.xpath("//table[@id='revision-form-table']/tbody/tr"));
    }
    
    private static List<WebElement> Node_Num() {
    	return webDriver.findElements(By.xpath("//*[@id='revision-form-table']/tbody/..//td[@class='views-field views-field-vid']"));
    }
    
    //PAGE OBJECT METHODS
    public void VerifyRevisionCount(Integer revisionCount) throws Exception {
    	
    	Reporter.log("Verify the number of present revisions entries equals '" + revisionCount + "'.");
    	Assert.assertTrue(Node_Num().size() == (revisionCount));
    	Assert.assertTrue(RevisionsState_Trs().size() - 1 == (revisionCount));
    }

}
