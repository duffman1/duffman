package com.nbcuni.test.publisher.pageobjects.Content;

import java.util.List;
import com.nbcuni.test.publisher.common.Driver.Driver;
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

    private Driver webDriver;
    
    //PAGE OBJECT CONSTRUCTOR
    public RevisionState(Driver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    List<WebElement> Node_Num() {
    	return webDriver.findElements(By.xpath("//tbody//td[@class='views-field views-field-vid']"));
    }
    
    //PAGE OBJECT METHODS
    public void VerifyRevisionCount(Integer revisionCount) throws Exception {
    	
    	Reporter.log("Verify the number of present revisions entries equals '" + revisionCount + "'.");
    	Assert.assertTrue(Node_Num().size() == (revisionCount));
    	
    }

}

