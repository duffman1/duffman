package com.nbcuni.test.publisher.pageobjects.Configuration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
* publisher.nbcuni.com Add Focal Point Crop Effect Library. Copyright
*
* @author Brandon Clark
* @version 1.0 Date: March 12, 2014
*********************************************/
public class AddFocalPointCropEffect {
		
	private WaitFor waitFor;
	private Config config;
	
	//PAGE OBJECT CONSTRUCTOR
	public AddFocalPointCropEffect(Driver webDriver) {
		PageFactory.initElements(webDriver, this);
		config = new Config();
		waitFor = new WaitFor(webDriver, config.getConfigValueInt("WaitForWaitTime"));
	}

	
	//PAGE OBJECT IDENTIFIERS
	private By Width_Txb = By.id("edit-data-width");
	
	private By Height_Txb = By.id("edit-data-height");
	
	private By AddEffect_Btn = By.id("edit-submit");
	
	
	//PAGE OBJECT METHODS
	public void EnterWidth(String width) throws Exception {

		Reporter.log("Enter '" + width + "' in the 'Width' text box.");
		waitFor.ElementVisible(Width_Txb).sendKeys(width);
		
	}
	
	public void EnterHeight(String height) throws Exception {

		Reporter.log("Enter '" + height + "' in the 'Height' text box.");
		waitFor.ElementVisible(Height_Txb).sendKeys(height);
		
	}
	
	public void ClickAddEffectBtn() throws Exception {

		Reporter.log("Click the 'Add effect' button.");
		waitFor.ElementVisible(AddEffect_Btn).click();
		
	}

}