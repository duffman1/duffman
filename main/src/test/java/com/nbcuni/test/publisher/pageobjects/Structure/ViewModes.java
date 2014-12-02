package com.nbcuni.test.publisher.pageobjects.Structure;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
* publisher.nbcuni.com View Modes Library. Copyright
*
* @author Brandon Clark
* @version 1.0 Date: October 13, 2014
*********************************************/

public class ViewModes {

	private Driver webDriver;
	private WaitFor waitFor;
	
    //PAGE OBJECT CONSTRUCTOR
    public ViewModes(Driver webDriver) {
    	this.webDriver = webDriver;
    	PageFactory.initElements(webDriver, this);
    	waitFor = new WaitFor(webDriver, 10);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By AddViewMode_Lnk = By.linkText("Add a view mode");
    
    private WebElement Delete_Lnk(String label) {
    	return webDriver.findElement(By.xpath("(//td[text()='" + label + "'])[1]/..//a[text()='Delete']"));
    }
    
    
    //PAGE OBJECT METHODS
    public void ClickAddViewModeLnk() throws Exception {
    	
    	Reporter.log("Click the 'Add a view mode' link.");
    	waitFor.ElementPresent(AddViewMode_Lnk).click();
    	
    }
    
    public void ClickDeleteLnk(String label) throws Exception {
    
    	Reporter.log("Click the 'Delete' link for label '" + label + "'.");
    	Delete_Lnk(label).click();
    }
    
    
}
