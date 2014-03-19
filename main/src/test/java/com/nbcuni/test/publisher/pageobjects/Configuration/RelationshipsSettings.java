package com.nbcuni.test.publisher.pageobjects.Configuration;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.nbcuni.test.webdriver.CustomWebDriver;

/*********************************************
* publisher.nbcuni.com Relationships Settings Library. Copyright
*
* @author Brandon Clark
* @version 1.0 Date: March 19, 2014
*********************************************/
public class RelationshipsSettings {
		
	private static CustomWebDriver webDriver;
	private static WebDriverWait wait;
	
	//PAGE OBJECT CONSTRUCTOR
	public RelationshipsSettings(CustomWebDriver webDriver) {
		RelationshipsSettings.webDriver = webDriver;
		PageFactory.initElements(webDriver, this);
		wait = new WebDriverWait(webDriver, 10);
	}

	//PAGE OBJECT IDENTIFIERS
	@FindBy(how = How.XPATH, using ="//a/strong[text()='TV']")
	private static WebElement TV_Tab;
	
	private static List<WebElement> AllContentType_Cbxs() {
		return webDriver.findElements(By.xpath("//div[@class='fieldset-wrapper']//input"));
	}

	@FindBy(how = How.ID, using ="edit-pub-relationships-tv-content-type-character-profile")
	private static WebElement CharacterProfileTV_Cbx;
	
	@FindBy(how = How.ID, using ="edit-pub-relationships-tv-content-type-character-profile-level")
	private static WebElement CharacterProfileTVRelationshipDepth_Ddl;
	
	@FindBy(how = How.ID, using ="edit-submit")
	private static WebElement SaveConfiguration_Btn;
	
	
	//PAGE OBJECT METHODS
	public void ClickTVTab() throws Exception {

		Reporter.log("Click the 'TV' tab.");
		TV_Tab.click();
	}
	
	public void UncheckAllCheckboxes() throws Exception {
		
		for (WebElement cbx : AllContentType_Cbxs()) {
			if (cbx.isDisplayed() && cbx.isSelected()) {
				Reporter.log("Uncheck content item checkbox with id '" + cbx.getAttribute("id") + "'.");
				cbx.click();
			}
		}
	}
	
	public void CheckCharacterProfileTVCbx() throws Exception {

		if (CharacterProfileTV_Cbx.isSelected() == false) {
			Reporter.log("Check the 'Character Profile' check box.");
			CharacterProfileTV_Cbx.click();
		}
	}
	
	public void SelectCharacterProfileTVRelationshipDepth(String option) throws Exception {
		
		Reporter.log("Select '" + option + "' from the 'Character Profile TV Relationship Depth' drop down list.");
		new Select(wait.until(ExpectedConditions.visibilityOf(CharacterProfileTVRelationshipDepth_Ddl))).
			selectByVisibleText(option);
	}
	
	public void ClickSaveConfigurationBtn() throws Exception {

		Reporter.log("Click the 'Save configuration' button.");
		SaveConfiguration_Btn.click();
	}

}