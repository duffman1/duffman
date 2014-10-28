package com.nbcuni.test.publisher.pageobjects.Configuration;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
* publisher.nbcuni.com Text Format Library. Copyright
*
* @author Brandon Clark
* @version 1.0 Date: September 16, 2014
*********************************************/
public class TextFormat {
		
	private Driver webDriver;
	
	//PAGE OBJECT CONSTRUCTOR
	public TextFormat(Driver webDriver) {
		this.webDriver = webDriver;
		PageFactory.initElements(webDriver, this);
	}

	//PAGE OBJECT IDENTIFIERS
	private WebElement EnabledFilter_Cbx(String filter) {
		return webDriver.findElement(By.xpath("//label[contains(text(),'" + filter + "')]/../input[@type='checkbox']"));
	}
	
	@FindBy(how = How.ID, using ="edit-actions-submit")
	private WebElement SaveConfiguration_Btn;
	
	@FindBy(how = How.ID, using ="edit-filters-filter-html-settings-allowed-html")
	private WebElement AllowedHTMLTags_Txb;
	
	
	//PAGE OBJECT METHODS
	public void ClickEnabledFilters(List<String> allFilters) throws Exception {
		
		for (String filter : allFilters) {
			if (!EnabledFilter_Cbx(filter).isSelected()) {
				Reporter.log("Click the '" + filter + "' checkbox.");
				EnabledFilter_Cbx(filter).click();
			}
			else {
				Reporter.log("Verify that the '" + filter + "' checkbox is checked.");
			}
		}
	}
	
	public void EnterAllowedHTMLTags(String allowedTags) throws Exception {
		
		Reporter.log("Enter the allowed html tags in the 'Allowed HTML tags' text box.");
		AllowedHTMLTags_Txb.clear();
		AllowedHTMLTags_Txb.sendKeys(allowedTags);
	}
	
	public void ClickSaveConfigurationBtn() throws Exception {
		
		Reporter.log("Click the 'Save configuration' button.");
		SaveConfiguration_Btn.click();
	}
}