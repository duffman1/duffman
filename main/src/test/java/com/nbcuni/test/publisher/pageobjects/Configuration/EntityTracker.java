package com.nbcuni.test.publisher.pageobjects.Configuration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
* publisher.nbcuni.com Entity Tracker Library. Copyright
*
* @author Brandon Clark
* @version 1.0 Date: October 10, 2014
*********************************************/
public class EntityTracker {
		
	private Driver webDriver;
	
	//PAGE OBJECT CONSTRUCTOR
	public EntityTracker(Driver webDriver) {
		this.webDriver = webDriver;
		PageFactory.initElements(webDriver, this);
		
	}

	//PAGE OBJECT IDENTIFIERS
	private WebElement Role_Cbx(String role) {
		return webDriver.findElement(By.xpath("//label[text()='" + role + " ']/../input[contains(@id, 'edit-entity-tracker-roles')]"));
	}

	@FindBy(how = How.ID, using ="edit-submit")
	private WebElement SaveConfiguration_Btn;

	
	//PAGE OBJECT METHODS
	public void CheckRoleCbx(String roleName) throws Exception {

		if (!Role_Cbx(roleName).isSelected()) {
			Reporter.log("Check the '" + roleName + "' check box.");
			Role_Cbx(roleName).click();
		}
	}

	public void ClickSaveConfigurationBtn() throws Exception {
		
		Reporter.log("Click the 'Save configuration' button.");
		SaveConfiguration_Btn.click();
	}
	
}