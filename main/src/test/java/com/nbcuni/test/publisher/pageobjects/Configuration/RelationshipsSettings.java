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

import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
* publisher.nbcuni.com Relationships Settings Library. Copyright
*
* @author Brandon Clark
* @version 1.0 Date: March 19, 2014
*********************************************/
public class RelationshipsSettings {
		
	private Driver webDriver;
	private WebDriverWait wait;
	
	//PAGE OBJECT CONSTRUCTOR
	public RelationshipsSettings(Driver webDriver) {
		this.webDriver = webDriver;
		PageFactory.initElements(webDriver, this);
		wait = new WebDriverWait(webDriver, 10);
	}

	//PAGE OBJECT IDENTIFIERS
	@FindBy(how = How.XPATH, using ="//a/strong[text()='TV']")
	private WebElement TV_Tab;
	
	@FindBy(how = How.XPATH, using ="//a/strong[text()='Movies']")
	private WebElement Movies_Tab;
	
	private List<WebElement> AllContentType_Cbxs() {
		return webDriver.findElements(By.xpath("//div[@class='fieldset-wrapper']//input"));
	}

	private List<WebElement> ContentType_Cbx(String contentName) {
		return webDriver.findElements(By.xpath("//label[text()='" + contentName + " ']/../input"));
	}
	
	private WebElement RelationshipDepth_Ddl(String contentName) {
		return webDriver.findElement(By.xpath("//label[text()='" + contentName + " ']/../select"));
	}
	
	@FindBy(how = How.ID, using ="edit-submit")
	private WebElement SaveConfiguration_Btn;
	
	
	//PAGE OBJECT METHODS
	public void ClickTVTab() throws Exception {

		Reporter.log("Click the 'TV' tab.");
		TV_Tab.click();
	}
	
	public void ClickMoviesTab() throws Exception {

		Reporter.log("Click the 'Movies' tab.");
		Movies_Tab.click();
	}
	
	public void UncheckAllCheckboxes() throws Exception {
		
		Reporter.log("Uncheck all checked check boxes.");
		for (WebElement cbx : AllContentType_Cbxs()) {
			if (cbx.isDisplayed() && cbx.isSelected()) {
				cbx.click();
			}
		}
	}
	
	public void CheckContentItemCbx(String contentName) throws Exception {

		for (WebElement cbx : ContentType_Cbx(contentName)) {
			if (cbx.isDisplayed() && cbx.isSelected() == false) {
				Reporter.log("Check the '" + contentName + "' checkbox.");
				cbx.click();
			}
		}
	}
	
	public void SelectRelationshipDepth(String contentName, String option) throws Exception {
		
		Reporter.log("Select '" + option + "' from the '" + contentName + "' drop down list.");
		new Select(wait.until(ExpectedConditions.visibilityOf(RelationshipDepth_Ddl(contentName)))).
			selectByVisibleText(option);
	}
	
	public void ClickSaveConfigurationBtn() throws Exception {

		Reporter.log("Click the 'Save configuration' button.");
		SaveConfiguration_Btn.click();
	}

}