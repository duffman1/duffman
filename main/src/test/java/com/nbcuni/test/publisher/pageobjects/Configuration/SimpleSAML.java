package com.nbcuni.test.publisher.pageobjects.Configuration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
* publisher.nbcuni.com Simple SAML Library. Copyright
*
* @author Brandon Clark
* @version 1.0 Date: July 3, 2014
*********************************************/
public class SimpleSAML {
		
	private Driver webDriver;
	private Config config;
	
	//PAGE OBJECT CONSTRUCTOR
	public SimpleSAML(Driver webDriver) {
		PageFactory.initElements(webDriver, this);
		config = new Config();
		this.webDriver = webDriver;
	}

	//PAGE OBJECT IDENTIFIERS
	@FindBy(how = How.ID, using ="edit-simplesamlphp-auth-activate")
	private WebElement Activate_Cbx;
	
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
	
	@FindBy(how = How.ID, using ="edit-submit")
	private WebElement SaveConfiguration_Btn;
	
	
	//PAGE OBJECT METHODS
	public void VerifyDefaultSettings() throws Exception {

		//TODO - extend for all sites
		if (config.getConfigValue("AppURL").contains("install.qa5")) {
			
			Reporter.log("Verify the value of the 'Installation directory' text box equals '/mnt/www/html/nbcupublisher7qa5/simplesamlphp' and the text box is disabled.");
			Assert.assertEquals(InstallationDirectory_Txb.getAttribute("value"), "/mnt/www/html/nbcupublisher7qa5/simplesamlphp");
			Assert.assertFalse(InstallationDirectory_Txb.isEnabled());
			
			Reporter.log("Verify the value of the 'Authentication source for this SP' text box equals 'pub-qa5install' and the text box is disabled.");
			Assert.assertEquals(AuthenticationSource_Txb.getAttribute("value"), "pub-qa5install");
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
	
	public void ClickSaveConfigurationBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save configuration' button.");
    	Thread.sleep(500);
    	SaveConfiguration_Btn.click();
    	
    }
	
	public void CheckActivateAuthCbx() throws Exception {
    	
		if (Activate_Cbx.isSelected() == false) {
			Reporter.log("Click the 'Activate authentication via SimpleSAMLphp' check box.");
	    	Activate_Cbx.click();
		}
    }
	
	public void UnCheckActivateAuthCbx() throws Exception {
    	
		if (Activate_Cbx.isSelected() == true) {
			Reporter.log("Click the 'Activate authentication via SimpleSAMLphp' check box.");
	    	Activate_Cbx.click();
		}
    }
}