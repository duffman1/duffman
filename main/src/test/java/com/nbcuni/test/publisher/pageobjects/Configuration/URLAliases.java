package com.nbcuni.test.publisher.pageobjects.Configuration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
* publisher.nbcuni.com URL Aliases Library. Copyright
*
* @author Brandon Clark
* @version 1.1 Date: June 1, 2014
*********************************************/
public class URLAliases {
		
	private Driver webDriver;
	
	//PAGE OBJECT CONSTRUCTOR
	public URLAliases(Driver webDriver) {
		this.webDriver = webDriver;
		PageFactory.initElements(webDriver, this);
		
	}

	//PAGE OBJECT IDENTIFIERS
	private WebElement UpdateAction_Rdb(String label) {
		return webDriver.findElement(By.xpath("//label[text()='" + label + " ']/../input"));
	}

	@FindBy(how = How.ID, using ="edit-submit")
	private WebElement SaveConfiguration_Btn;

	
	//PAGE OBJECT METHODS
	public void ClickUpdateActionRdb(String label) throws Exception {

		Reporter.log("Click the '" + label + "' radio button.");
		UpdateAction_Rdb(label).click();
	}
	
	public void ClickSaveConfigurationBtn() throws Exception {
		
		Reporter.log("Click the 'Save configuration' button.");
		SaveConfiguration_Btn.click();
	}
	
}