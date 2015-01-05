package com.nbcuni.test.publisher.pageobjects.Configuration;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
* publisher.nbcuni.com Simple SAML Library. Copyright
*
* @author Brandon Clark
* @version 1.0 Date: July 3, 2014
*********************************************/
public class SimpleSAML {
		
	private Driver webDriver;
	private Config config;
	private Integer timeout;
	private WaitFor waitFor;
	private Interact interact;
	
	//PAGE OBJECT CONSTRUCTOR
	public SimpleSAML(Driver webDriver) {
		this.webDriver = webDriver;
		PageFactory.initElements(webDriver, this);
		config = new Config();
		timeout = config.getConfigValueInt("WaitForWaitTime");
		waitFor = new WaitFor(webDriver, timeout);
		interact = new Interact(webDriver, timeout);
	}

	//PAGE OBJECT IDENTIFIERS
	private By Activate_Cbx = By.id("edit-simplesamlphp-auth-activate");
	
	@FindBy(how = How.ID, using ="edit-simplesamlphp-auth-installdir")
	private WebElement InstallationDirectory_Txb;
	
	@FindBy(how = How.ID, using ="edit-simplesamlphp-auth-authsource")
	private WebElement AuthenticationSource_Txb;
	
	@FindBy(how = How.ID, using ="edit-file-upload")
	private WebElement UploadCertificate_Upl;
	
	@FindBy(how = How.ID, using ="edit-simplesamlphp-auth-forcehttps")
	private WebElement ForceHttps_Cbx;
	
	@FindBy(how = How.ID, using ="edit-simplesamlphp-auth-user-name")
	private WebElement WhichAttributeUserName_Txb;
	
	@FindBy(how = How.ID, using ="edit-simplesamlphp-auth-unique-id")
	private WebElement WhichAttributeIdentifier_Txb;
	
	@FindBy(how = How.ID, using ="edit-simplesamlphp-auth-mailattr")
	private WebElement WhichAttributeEmail_Txb;
	
	private By RolesAuthenticateSSO_Cbx(String role){
		return By.xpath("//label[text()='" + role + " ']/../input");
	}
	
	@FindBy(how = How.ID, using ="edit-submit")
	private WebElement SaveConfiguration_Btn;
	
	
	//PAGE OBJECT METHODS
	public void VerifyDefaultSettings() throws Exception {

		//TODO - extend for all sites
		if (config.getConfigValueString("AppURL").contains("qa5")) {
			
			Reporter.log("Verify the value of the 'Installation directory' text box equals '/mnt/www/html/nbcupublisher7qa5/simplesamlphp' and the text box is disabled.");
			Assert.assertEquals(InstallationDirectory_Txb.getAttribute("value"), "/mnt/www/html/nbcupublisher7qa5/simplesamlphp");
			Assert.assertFalse(InstallationDirectory_Txb.isEnabled());
			
			Reporter.log("Verify the value of the 'Authentication source for this SP' text box equals 'pub-qa5install' and the text box is disabled.");
			Assert.assertEquals(AuthenticationSource_Txb.getAttribute("value"), "pub-qa5");
			Assert.assertFalse(AuthenticationSource_Txb.isEnabled());
			
			Reporter.log("Verify the ' Force https for login links' check box is checked and disabled.");
			Assert.assertTrue(ForceHttps_Cbx.isSelected());
			Assert.assertFalse(ForceHttps_Cbx.isEnabled());
			
			Reporter.log("Verify the value of the 'Which attribute from simpleSAMLphp should be used as user's name' text box equals 'email' and the text box is disabled.");
			Assert.assertEquals(WhichAttributeUserName_Txb.getAttribute("value"), "email");
			Assert.assertFalse(WhichAttributeUserName_Txb.isEnabled());
			
			Reporter.log("Verify the value of the 'Which attribute from simpleSAMLphp should be used as unique identifier for the user' text box equals 'uid' and the text box is disabled.");
			Assert.assertEquals(WhichAttributeIdentifier_Txb.getAttribute("value"), "uid");
			Assert.assertFalse(WhichAttributeIdentifier_Txb.isEnabled());
			
			Reporter.log("Verify the value of the 'Which attribute from simpleSAMLphp should be used as user mail address' text box equals 'email' and the text box is disabled.");
			Assert.assertEquals(WhichAttributeEmail_Txb.getAttribute("value"), "email");
			Assert.assertFalse(WhichAttributeEmail_Txb.isEnabled());
		}
		
	}
	
	public void EnterFilePath(String pathToFile) throws Exception {
    	
    	Reporter.log("Enter the file path for certificate upload.");
    	webDriver.setFileDetector(new LocalFileDetector());
    	UploadCertificate_Upl.sendKeys(pathToFile);
    	
    }
	
	public void CheckRolesAuthenticateSSO(List<String> allRoles) throws Exception {
		
		for (String role : allRoles) {
			WebElement ele = waitFor.ElementVisible(RolesAuthenticateSSO_Cbx(role));
			if (!ele.isSelected()) {
				Reporter.log("Check the '" + role + "' checkbox.");
				interact.Click(ele);
			}
		}
		
	}
	
	public void UnCheckRolesAuthenticateSSO(List<String> allRoles) throws Exception {
		
		for (String role : allRoles) {
			WebElement ele = waitFor.ElementVisible(RolesAuthenticateSSO_Cbx(role));
			if (ele.isSelected()) {
				Reporter.log("Un-Check the '" + role + "' checkbox.");
				interact.Click(ele);
			}
		}
		
	}
	
	public void ClickSaveConfigurationBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save configuration' button.");
    	Thread.sleep(500);
    	SaveConfiguration_Btn.click();
    	
    }
	
	public void CheckActivateAuthCbx() throws Exception {
    	
		WebElement ele = waitFor.ElementVisible(Activate_Cbx);
		interact.ScrollToTop();
		if (!ele.isSelected()) {
			Reporter.log("Click the 'Activate authentication via SimpleSAMLphp' check box.");
	    	interact.Click(ele);
		}
    }
	
	public void UnCheckActivateAuthCbx() throws Exception {
    	
		WebElement ele = waitFor.ElementVisible(Activate_Cbx);
		interact.ScrollToTop();
		if (ele.isSelected()) {
			Reporter.log("Click the 'Activate authentication via SimpleSAMLphp' check box.");
			interact.Click(ele);
		}
    }
}