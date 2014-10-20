package com.nbcuni.test.publisher.pageobjects.Structure;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
* publisher.nbcuni.com View Modes Library. Copyright
*
* @author Brandon Clark
* @version 1.0 Date: October 13, 2014
*********************************************/

public class ViewModes {

	private Driver webDriver;
	
    //PAGE OBJECT CONSTRUCTOR
    public ViewModes(Driver webDriver) {
    	this.webDriver = webDriver;
    	PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private WebElement Delete_Lnk(String label) {
    	return webDriver.findElement(By.xpath("(//td[text()='" + label + "'])[1]/..//a[text()='Delete']"));
    }
    
    
    //PAGE OBJECT METHODS
    public void ClickDeleteLnk(String label) throws Exception {
    
    	Reporter.log("Click the 'Delete' link for label '" + label + "'.");
    	Delete_Lnk(label).click();
    }
    
    
}
