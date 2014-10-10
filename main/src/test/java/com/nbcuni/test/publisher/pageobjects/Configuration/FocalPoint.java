package com.nbcuni.test.publisher.pageobjects.Configuration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
* publisher.nbcuni.com Focal Point Library. Copyright
*
* @author Brandon Clark
* @version 1.0 Date: March 12, 2014
*********************************************/
public class FocalPoint {
		
	//PAGE OBJECT CONSTRUCTOR
	public FocalPoint(Driver webDriver) {
		PageFactory.initElements(webDriver, this);
		
	}

	//PAGE OBJECT IDENTIFIERS
	@FindBy(how = How.ID, using ="edit-focal-point-enabled-for-image")
	private WebElement StandardImageFields_Cbx;
	
	@FindBy(how = How.ID, using ="edit-focal-point-enabled-for-media")
	private WebElement MediaModuleImageFields_Cbx;
	
	@FindBy(how = How.ID, using ="edit-focal-point-preview-image-style")
	private WebElement PreviewImageStyle_Ddl;
	
	@FindBy(how = How.ID, using ="edit-submit")
	private WebElement SaveConfiguration_Btn;

	
	//PAGE OBJECT METHODS
	public void ClickStandardImageFieldsCbx() throws Exception {

		if (StandardImageFields_Cbx.isSelected() == false) {
			Reporter.log("Check the 'Standard image fields' check box.");
			StandardImageFields_Cbx.click();
		}
	}
	
	public void ClickMediaModuleImageFieldsCbx() throws Exception {

		if (MediaModuleImageFields_Cbx.isSelected() == false) {
			Reporter.log("Check the 'Media module image fields' check box.");
			MediaModuleImageFields_Cbx.click();
		}
	}
	
	public void SelectPreviewImageStyle(String option) throws Exception {

		Reporter.log("Select '" + option + "' from the 'Preview Image Style' drop down list.");
		new Select(PreviewImageStyle_Ddl).selectByVisibleText(option);
	}
	
	public void ClickSaveConfigurationBtn() throws Exception {

		Reporter.log("Click the 'Save configuration' button.");
		SaveConfiguration_Btn.click();
	}

}