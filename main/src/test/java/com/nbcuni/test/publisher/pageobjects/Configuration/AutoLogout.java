package com.nbcuni.test.publisher.pageobjects.Configuration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
* publisher.nbcuni.com Auto Logout Library. Copyright
*
* @author Brandon Clark
* @version 1.0 Date: November 17, 2014
*********************************************/
public class AutoLogout {

	private Driver webDriver;
	private Config config;
	private Integer timeout;
	private WaitFor waitFor;
	private Interact interact;
	
	//PAGE OBJECT CONSTRUCTOR
	public AutoLogout(Driver webDriver) {
		this.webDriver = webDriver;
		config = new Config();
		timeout = config.getConfigValueInt("WaitForWaitTime");
		waitFor = new WaitFor(webDriver, timeout);
		interact = new Interact(webDriver, timeout);
	}

	//PAGE OBJECT IDENTIFIERS
	private By TimeoutValue_Txb = By.id("edit-autologout-timeout");

	private By MaxTimeoutSettings_Txb = By.id("edit-autologout-max-timeout");
	
	private By TimeoutPadding_Txb = By.id("edit-autologout-padding");
	
	private By RoleTimeout_Cbx = By.id("edit-autologout-role-logout");
	
	private By Role_Cbx(String role) {
		return By.xpath("//td[text()='" + role + "']/..//input[@type='checkbox']");
	}
	
	private By RoleTimeout_Txb(String role) {
		return By.xpath("//td[text()='" + role + "']/..//input[@type='text']");
	}
	
	private By RedirectURLAtLogout_Txb = By.id("edit-autologout-redirect-url");
	
	private By DoNotDisplayLogoutDlg_Cbx = By.id("edit-autologout-no-dialog");
	
	private By MessageToDisplayInLogoutDlg_Txa = By.id("edit-autologout-message");
	
	private By MessageToDisplayAfterLogout_Txa = By.id("edit-autologout-inactivity-message");
	
	private By EnforceAutoLogoutOnAdminPages_Cbx = By.id("edit-autologout-enforce-admin");
	
	private By SaveConfiguration_Btn = By.id("edit-submit");
	
	private By AutoLogoutConfirm_Txt = By.xpath("//div[@id='autologout-confirm']");
	
	private By Yes_Btn = By.xpath("//button/span[text()='Yes']");
	
	
	//PAGE OBJECT METHODS
	public void EnterTimeout(String value) throws Exception {

		Reporter.log("Enter '" + value + "' in the 'Timeout value in seconds' text box.");
		interact.Type(waitFor.ElementVisible(TimeoutValue_Txb), value);
		
	}
	
	public void EnterMaxTimeout(String value) throws Exception {
		
		Reporter.log("Enter '" + value + "' in the 'Max timeout setting' text box.");
		interact.Type(waitFor.ElementVisible(MaxTimeoutSettings_Txb), value);
		
	}
	
	public void EnterTimeoutPadding(String value) throws Exception {
		
		Reporter.log("Enter '" + value + "' in the 'Timeout padding' text box.");
		interact.Type(waitFor.ElementVisible(TimeoutPadding_Txb), value);
		
	}
	
	public void CheckRoleTimeout() throws Exception {
		
		WebElement ele = waitFor.ElementVisible(RoleTimeout_Cbx);
		if (!ele.isSelected()) {
			Reporter.log("Check the 'Role Timeout' check box.");
			ele.click();
		}
		
	}
	
	public void CheckRoleByType(String role) throws Exception {
		
		WebElement ele = waitFor.ElementVisible(Role_Cbx(role));
		if (!ele.isSelected()) {
			Reporter.log("Check the '" + role + "' check box.");
			ele.click();
		}
		
	}
	
	public void EnterRoleTimeout(String role, String timeout) throws Exception {
		
		Reporter.log("Enter '" + timeout + "' in the " + role + " 'timeout (seconds)' text box.");
		interact.Type(waitFor.ElementVisible(RoleTimeout_Txb(role)), timeout);
		
	}

	public void UncheckRoleTimeout() throws Exception {
		
		WebElement ele = waitFor.ElementVisible(RoleTimeout_Cbx);
		if (ele.isSelected()) {
			Reporter.log("Uncheck the 'Role Timeout' check box.");
			ele.click();
		}
		
	}
	
	public void EnterRedirectURLAtLogout(String url) throws Exception {
		
		Reporter.log("Enter '" + url + "' in the 'Redirect URL at logout' text box.");
		interact.Type(waitFor.ElementVisible(RedirectURLAtLogout_Txb), url);
		
	}

	public void UncheckDoNotDisplayLogoutDlgCbx() throws Exception {
		
		WebElement ele = waitFor.ElementVisible(DoNotDisplayLogoutDlg_Cbx);
		if (ele.isSelected()) {
			Reporter.log("Uncheck the 'Do not display the logout dialog ' check box.");
			ele.click();
		}
		
	}
	
	public void EnterMessageToDisplayInLogout(String message) throws Exception {
		
		Reporter.log("Enter '" + message + "' in the '' text area.");
		interact.Type(waitFor.ElementVisible(MessageToDisplayInLogoutDlg_Txa), message);
		
	}
	
	public void EnterMessageToDisplayAfterLogout(String message) throws Exception {
		
		Reporter.log("Enter '" + message + "' in the 'Message to display to the user after they are logged out.' text area.");
		interact.Type(waitFor.ElementVisible(MessageToDisplayAfterLogout_Txa), message);
		
	}
	
	public void UncheckEnforceAutoLogoutAdminPages() throws Exception {
		
		WebElement ele = waitFor.ElementVisible(EnforceAutoLogoutOnAdminPages_Cbx);
		if (ele.isSelected()) {
			Reporter.log("Uncheck the 'Enforce auto logout on admin pages' check box.");
			interact.Click(ele);
		}
		
	}
	
	public void ClickSaveConfigurationBtn() throws Exception {
		
		Reporter.log("Click the 'Save configuration' button.");
		interact.Click(waitFor.ElementVisible(SaveConfiguration_Btn));
		
	}
	
	public void VerifyAutoLogoutConfirmationPresent(Integer maxWaitTime) throws Exception {
		
		Reporter.log("Verify the auto logout dialog is present with text 'Your session is about to expire. Do you want to reset it?'.");
		new WaitFor(webDriver, maxWaitTime).ElementContainsText(AutoLogoutConfirm_Txt, "Your session is about to expire. Do you want to reset it?");
		
	}
	
	public void ClickYesBtn() throws Exception {
		
		Reporter.log("Click the 'Yes' button.");
		interact.Click(waitFor.ElementVisible(Yes_Btn));
		
		Reporter.log("Verify the auto logon confirmation dialog closes.");
		waitFor.ElementNotVisible(AutoLogoutConfirm_Txt);
		
	}
	

}