package com.nbcuni.test.publisher.pageobjects.Structure;


import com.nbcuni.test.publisher.common.Util.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

/*********************************************
* publisher.nbcuni.com View Modes Library. Copyright
*
* @author Brandon Clark
* @version 1.0 Date: October 13, 2014
*********************************************/

public class ViewModes {

	private WebDriver webWebWebDriver;
	private WaitFor waitFor;
	
    //PAGE OBJECT CONSTRUCTOR
    public ViewModes(WebDriver webWebWebDriver) {
    	this.webWebWebDriver = webWebWebDriver;
    	PageFactory.initElements(webWebWebDriver, this);
    	waitFor = new WaitFor(webWebWebDriver, 10);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By AddViewMode_Lnk = By.linkText("Add a view mode");
    
    private WebElement Delete_Lnk(String label) {
    	return webWebWebDriver.findElement(By.xpath("(//td[text()='" + label + "'])[1]/..//a[text()='Delete']"));
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
