package com.nbcuni.test.publisher.pageobjects.Configuration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import com.nbcuni.test.webdriver.CustomWebDriver;

/*********************************************
* publisher.nbcuni.com Add Focal Point Crop Effect Library. Copyright
*
* @author Brandon Clark
* @version 1.0 Date: March 12, 2014
*********************************************/
public class AddFocalPointCropEffect {
		
	//PAGE OBJECT CONSTRUCTOR
	public AddFocalPointCropEffect(CustomWebDriver webDriver) {
		PageFactory.initElements(webDriver, this);
		
	}

	//PAGE OBJECT IDENTIFIERS
	@FindBy(how = How.ID, using ="edit-data-width")
	private static WebElement Width_Txb;
	
	@FindBy(how = How.ID, using ="edit-data-height")
	private static WebElement Height_Txb;
	
	@FindBy(how = How.ID, using ="edit-submit")
	private static WebElement AddEffect_Btn;
	
	
	//PAGE OBJECT METHODS
	public void EnterWidth(String width) throws Exception {

		Reporter.log("Enter '" + width + "' in the 'Width' text box.");
		Width_Txb.sendKeys(width);
	}
	
	public void EnterHeight(String height) throws Exception {

		Reporter.log("Enter '" + height + "' in the 'Height' text box.");
		Height_Txb.sendKeys(height);
	}
	
	public void ClickAddEffectBtn() throws Exception {

		Reporter.log("Click the 'Add effect' button.");
		AddEffect_Btn.click();
	}

}