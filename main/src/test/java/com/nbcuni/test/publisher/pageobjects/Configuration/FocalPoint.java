package com.nbcuni.test.publisher.pageobjects.Configuration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
* publisher.nbcuni.com Focal Point Library. Copyright
*
* @author Brandon Clark
* @version 1.0 Date: March 12, 2014
*********************************************/
public class FocalPoint {
		
	private Config config;
	private Integer timeout;
	private WaitFor waitFor;
	private Interact interact;
	
	//PAGE OBJECT CONSTRUCTOR
	public FocalPoint(Driver webDriver) {
		config = new Config();
		timeout = config.getConfigValueInt("WaitForWaitTime");
		waitFor = new WaitFor(webDriver, timeout);
		interact = new Interact(webDriver, timeout);
		
	}

	//PAGE OBJECT IDENTIFIERS
	private By StandardImageFields_Cbx = By.id("edit-focal-point-enabled-for-image");
	
	private By MediaModuleImageFields_Cbx = By.id("edit-focal-point-enabled-for-media");
	
	private By PreviewImageStyle_Ddl = By.id("edit-focal-point-preview-image-style");
	
	private By SaveConfiguration_Btn = By.id("edit-submit");

	
	//PAGE OBJECT METHODS
	public void ClickStandardImageFieldsCbx() throws Exception {

		WebElement ele = waitFor.ElementVisible(StandardImageFields_Cbx);
		if (ele.isSelected() == false) {
			Reporter.log("Check the 'Standard image fields' check box.");
			interact.Click(ele);
		}
	}
	
	public void ClickMediaModuleImageFieldsCbx() throws Exception {

		WebElement ele = waitFor.ElementVisible(MediaModuleImageFields_Cbx);
		if (ele.isSelected() == false) {
			Reporter.log("Check the 'Media module image fields' check box.");
			ele.click();
		}
		
	}
	
	public void SelectPreviewImageStyle(String option) throws Exception {

		Reporter.log("Select '" + option + "' from the 'Preview Image Style' drop down list.");
		interact.Select(waitFor.ElementVisible(PreviewImageStyle_Ddl), option);
		
	}
	
	public void ClickSaveConfigurationBtn() throws Exception {

		Reporter.log("Click the 'Save configuration' button.");
		interact.Click(waitFor.ElementVisible(SaveConfiguration_Btn));
		
	}

}