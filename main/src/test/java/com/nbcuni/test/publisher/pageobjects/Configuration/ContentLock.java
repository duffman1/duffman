package com.nbcuni.test.publisher.pageobjects.Configuration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
* publisher.nbcuni.com Content Lock Library. Copyright
*
* @author Brandon Clark
* @version 1.1 Date: November 17, 2014
*********************************************/
public class ContentLock {

	private Config config;
	private Integer timeout;
	private WaitFor waitFor;
	private Interact interact;
	
	//PAGE OBJECT CONSTRUCTOR
	public ContentLock(Driver webDriver) {
		config = new Config();
		timeout = config.getConfigValueInt("WaitForWaitTime");
		waitFor = new WaitFor(webDriver, timeout);
		interact = new Interact(webDriver, timeout);
	}

	//PAGE OBJECT IDENTIFIERS
	private By ShowLockUnlockMessages_Cbx = By.id("edit-content-lock-admin-verbose");

	private By SaveConfiguration_Btn = By.id("edit-submit");


	//PAGE OBJECT METHODS
	public void CheckShowLockUnlockMessagesCbx() throws Exception {

		WebElement ele = waitFor.ElementVisible(ShowLockUnlockMessages_Cbx);
		if (ele.isSelected() == false) {
			Reporter.log("Check the 'Show lock / unlock messages' check box.");
			interact.Click(ele);
		}
		
	}

	public void ClickSaveConfiguration() throws Exception {
		
		Reporter.log("Click the 'Save configuration' button.");
		interact.Click(waitFor.ElementVisible(SaveConfiguration_Btn));
		
	}

}