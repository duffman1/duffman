package com.nbcuni.test.publisher.pageobjects.Configuration;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;

/*********************************************
* publisher.nbcuni.com Image Styles Library. Copyright
*
* @author Brandon Clark
* @version 1.0 Date: March 12, 2014
*********************************************/
public class ImageStyles {
		
	private static CustomWebDriver webDriver;
	private static AppLib applib;
	
	//PAGE OBJECT CONSTRUCTOR
	public ImageStyles(CustomWebDriver webDriver, AppLib applib) {
		ImageStyles.webDriver = webDriver;
		ImageStyles.applib = applib;
		PageFactory.initElements(webDriver, this);
		
	}

	//PAGE OBJECT IDENTIFIERS
	@FindBy(how = How.ID, using ="edit-label")
	private static WebElement StyleName_Txb;
	
	@FindBy(how = How.ID, using ="edit-submit")
	private static WebElement CreateNewStyle_Btn;
	
	@FindBy(how = How.CSS, using ="select[name='new']")
	private static WebElement Effect_Ddl;
	
	@FindBy(how = How.ID, using ="edit-add")
	private static WebElement Add_Btn;
	
	@FindBy(how = How.ID, using ="edit-actions-submit")
	private static WebElement UpdateStyle_Btn;
	
	private static WebElement Style_Lnk(String styleName) {
		return webDriver.findElement(By.xpath("//a[text()='" + styleName + "']"));
	}
	
	private static WebElement StyleDelete_Lnk(String styleName) {
		return webDriver.findElement(By.xpath("//a[text()='" + styleName + "']/../..//a[text()='delete']"));
	}
	
	
	//PAGE OBJECT METHODS
	public boolean FocalImageStylePresent(String styleName) throws Exception {

		webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        boolean elPresent = false;

        try {
            Style_Lnk(styleName).isDisplayed();
            elPresent = true;
        }
        catch (Exception e) {
            elPresent = false;
        }

        webDriver.manage().timeouts().implicitlyWait(applib.getImplicitWaitTime(), TimeUnit.SECONDS);
        
        return elPresent;
	}
	
	public void ClickDeleteStyleLnk(String styleName) throws Exception {

		Reporter.log("Click the 'delete' link for style name '" + styleName + "'.");
		StyleDelete_Lnk(styleName).click();
	}
	
	public void EnterStyleName(String name) throws Exception {

		Reporter.log("Enter '" + name + "' in the 'Style name' text box.");
		StyleName_Txb.sendKeys(name);
	}
	
	public void ClickCreateNewStyleBtn() throws Exception {

		Reporter.log("Click the 'Create new style' button.");
		CreateNewStyle_Btn.click();
	}
	
	public void ClickAddBtn() throws Exception {

		Reporter.log("Click the 'Add' button.");
		Add_Btn.click();
	}
	
	public void SelectEffect(String option) throws Exception {

		Reporter.log("Select '" + option + "' from the 'EFFECT' drop down list.");
		new Select(Effect_Ddl).selectByVisibleText(option);
	}
	
	public void ClickUpdateStyleBtn() throws Exception {

		Reporter.log("Click the 'Update style' button.");
		UpdateStyle_Btn.click();
	}
	
}