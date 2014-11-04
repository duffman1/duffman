package com.nbcuni.test.publisher.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
 * publisher.nbcuni.com Modules Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 15, 2013
 *********************************************/

public class EmberNav {

    private Driver webDriver;
    private WaitFor waitFor;
    
    //PAGE OBJECT CONSTRUCTOR
    public EmberNav(Driver webDriver) {
    	this.webDriver = webDriver;
    	PageFactory.initElements(webDriver, this);
    	waitFor = new WaitFor(webDriver, 10);
    	
    }
    
    //PAGE OBJECT IDENTIFIERS
    private String partialElement = "";
    
    private By Menu_Lnk = By.xpath("//a[@id='navbar-item--2'][text()='Menu']");
    
    private By Content_Lnk = By.xpath("//a[@id='navbar-link-admin-content'][text()='Content']");
    
    private By AddContent_Lnk = By.xpath("//div[@id='content']//a[text()='Add content']");
    
    private By ContentByTxt_Lnk = By.xpath("//ul[@class='admin-list']//a[text()='" + partialElement + "']");
    
    
    //PAGE OBJECT METHODS
    public void ShowMenu() throws Exception {
    	
    	if (!waitFor.ElementPresent(Content_Lnk).isDisplayed()) {
    		Reporter.log("Click the 'Menu' link to show navigation menu items.");
    		webDriver.findElement(Menu_Lnk).click();
    	}
    	
    	waitFor.ElementVisible(Content_Lnk);
    }
    
    public void HideMenu() throws Exception {
    	
    	if (waitFor.ElementPresent(Content_Lnk).isDisplayed()) {
    		Reporter.log("Click the 'Menu' link to hide navigation menu items.");
    		webDriver.findElement(Menu_Lnk).click();
    	}
    	
    	waitFor.ElementNotVisible(Content_Lnk);
    }
    
    public void ClickContentLnk() throws Exception {
    	
    	Reporter.log("Click the 'Content' menu link.");
    	waitFor.ElementVisible(Content_Lnk).click();
    	
    }
    
    public void ClickAddContentLnk() throws Exception {
    	
    	Reporter.log("Click the 'Add content' link.");
    	waitFor.ElementVisible(AddContent_Lnk).click();
    	
    }
    
    public void ClickContentByTxtLnk(String contentTxt) throws Exception {
    	
    	Reporter.log("Click the '" + contentTxt + "' link.");
    	partialElement = contentTxt;
    	waitFor.ElementVisible(ContentByTxt_Lnk).click();
    	
    }
    
    
    //CONVENIENCE METHODS
    public void AddContent(String contentTxt) throws Exception {
    	
    	this.ShowMenu();
    	this.ClickContentLnk();
    	this.ClickAddContentLnk();
    	this.ClickContentByTxtLnk(contentTxt);
    	
    }
  
}

