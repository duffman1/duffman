package com.nbcuni.test.publisher.pageobjects.Structure;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

/*********************************************
* publisher.nbcuni.com Add View Mode Library. Copyright
*
* @author Brandon Clark
* @version 1.0 Date: July 31, 2014
*********************************************/

public class AddViewMode {

	private WebDriver webWebWebDriver;
	
    //PAGE OBJECT CONSTRUCTOR
    public AddViewMode(WebDriver webWebWebDriver) {
    	this.webWebWebDriver = webWebWebDriver;
    	PageFactory.initElements(webWebWebDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.ID, using = "edit-name")
    private WebElement Label_Txb;
    
    private WebElement Entity_Cbx(String entity) {
    	return webWebWebDriver.findElement(By.xpath("//div[@id='edit-entities']//label[text()='" + entity + " ']/../input"));
    }
    
    @FindBy(how = How.ID, using = "edit-submit")
    private WebElement Save_Btn;
    
    
    //PAGE OBJECT METHODS
    public void EnterLabel(String label) throws Exception {
    
    	Reporter.log("Enter '" + label + "' in the 'Label' text box.");
    	Label_Txb.sendKeys(label);
    }
    
    public void CheckEntityCbx(String entity) throws Exception {
        
    	Reporter.log("Click the '" + entity + "' Entity check box.");
    	Entity_Cbx(entity).click();
    }
    
    public void ClickSaveBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save' button.");
    	Save_Btn.click();
    }
    
   
}
