package com.nbcuni.test.publisher.pageobjects.Configuration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;

/*********************************************
* publisher.nbcuni.com Content Lock Library. Copyright
*
* @author Mohd Faizan Khan
* @version 1.0 Date: March 24, 2014
*********************************************/
public class ContentLock {

	//PAGE OBJECT CONSTRUCTOR
	public ContentLock(CustomWebDriver webDriver, AppLib applib) {
		PageFactory.initElements(webDriver, this);
	}

	//PAGE OBJECT IDENTIFIERS
	@FindBy(how = How.ID, using ="edit-content-lock-admin-verbose")
	private static WebElement ShowLockUnlockMessages_Cbx;

	@FindBy(how = How.ID, using ="edit-submit")
	private static WebElement SaveConfiguration_Btn;


	//PAGE OBJECT METHODS
	public void CheckShowLockUnlockMessagesCbx() throws Exception {

		if (ShowLockUnlockMessages_Cbx.isSelected() == false) {
			Reporter.log("Check the 'Show lock / unlock messages' check box.");
			ShowLockUnlockMessages_Cbx.click();
		}
	}

	public void ClickSaveConfiguration() throws Exception {
		
		Reporter.log("Click the 'Save configuration' button.");
		SaveConfiguration_Btn.click();
	}

}