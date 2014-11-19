package com.nbcuni.test.publisher.pageobjects.Configuration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
* publisher.nbcuni.com Relationships Settings Library. Copyright
*
* @author Brandon Clark
* @version 1.0 Date: March 19, 2014
*********************************************/
public class RelationshipsSettings {
		
	private Config config;
	private Integer timeout;
	private WaitFor waitFor;
	private Interact interact;
	
	//PAGE OBJECT CONSTRUCTOR
	public RelationshipsSettings(Driver webDriver) {
		config = new Config();
		timeout = config.getConfigValueInt("WaitForWaitTime");
		waitFor = new WaitFor(webDriver, timeout);
		interact = new Interact(webDriver, timeout);
	}

	//PAGE OBJECT IDENTIFIERS
	private By TV_Tab = By.xpath("//a/strong[text()='TV']");
	
	private By Movies_Tab = By.xpath("//a/strong[text()='Movies']");
	
	private By AllContentType_Cbxs = By.xpath("//div[@class='fieldset-wrapper']//input");
	
	private By AllContentType_Cbxs(String contentName) {
		return By.xpath("//label[text()='" + contentName + " ']/../input");
	}
	
	private By RelationshipDepth_Ddl(String contentName) {
		return By.xpath("//label[text()='" + contentName + " ']/../select");
	}
	
	private By SaveConfiguration_Btn = By.id("edit-submit");
	
	
	//PAGE OBJECT METHODS
	public void ClickTVTab() throws Exception {

		Reporter.log("Click the 'TV' tab.");
		interact.Click(waitFor.ElementVisible(TV_Tab));
		
	}
	
	public void ClickMoviesTab() throws Exception {

		Reporter.log("Click the 'Movies' tab.");
		interact.Click(waitFor.ElementVisible(Movies_Tab));
		
	}
	
	public void UncheckAllCheckboxes() throws Exception {
		
		Reporter.log("Uncheck all checked check boxes.");
		for (WebElement cbx : waitFor.ElementsPresent(AllContentType_Cbxs)) {
			if (cbx.isDisplayed() && cbx.isSelected()) {
				interact.Click(cbx);
			}
		}
		
	}
	
	public void CheckContentItemCbx(String contentName) throws Exception {

		for (WebElement cbx : waitFor.ElementsPresent(AllContentType_Cbxs(contentName))) {
			if (cbx.isDisplayed() && cbx.isSelected() == false) {
				Reporter.log("Check the '" + contentName + "' checkbox.");
				interact.Click(cbx);
			}
		}
		
	}
	
	public void SelectRelationshipDepth(String contentName, String option) throws Exception {
		
		Reporter.log("Select '" + option + "' from the '" + contentName + "' drop down list.");
		interact.Select(waitFor.ElementVisible(RelationshipDepth_Ddl(contentName)), option);
		
	}
	
	public void ClickSaveConfigurationBtn() throws Exception {

		Reporter.log("Click the 'Save configuration' button.");
		interact.Click(waitFor.ElementVisible(SaveConfiguration_Btn));
		
	}

}