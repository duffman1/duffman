package com.nbcuni.test.publisher.pageobjects.Configuration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;

/*********************************************
* publisher.nbcuni.com XML Site Map Library. Copyright
*
* @author Mohd Faizan Khan
* @version 1.0 Date: Feb, 25, 2014
*********************************************/
public class XMLSiteMap {
		
	private static AppLib applib;
	
	//PAGE OBJECT CONSTRUCTOR
	public XMLSiteMap(CustomWebDriver webDriver, AppLib applib) {
		XMLSiteMap.applib = applib;
		PageFactory.initElements(webDriver, this);
		
	}

	//PAGE OBJECT IDENTIFIERS
	@FindBy(how = How.ID, using ="edit-save-custom")
	private static WebElement SaveandRestore_Cbx;

	@FindBy(how = How.ID, using ="edit-submit")
	private static WebElement RebuildSitemap_Btn;

	@FindBy(how = How.XPATH, using ="//table/..//tr[1]/td[2]/a")
	private static WebElement XMLSiteMap_Lnk;

	
	//PAGE OBJECT METHODS
	public void ClickSaveAndRestoreCbx() throws Exception {

		if (SaveandRestore_Cbx.isSelected() == false) {
			Reporter.log("Check the ' Save and restore any custom inclusion and priority links.' check box.");
			SaveandRestore_Cbx.click();
		}
	}

	public void ClickRebuildSitemapBtn() throws Exception {
		
		Reporter.log("Click the 'Rebuild Sitemap' button.");
		RebuildSitemap_Btn.click();
	}
	
	public void ClickXMLSiteMapLnk() throws Exception {
		
		Reporter.log("Click the 'XMLSitemap Link' with text '" + applib.getEnvironment() + "'.");
		XMLSiteMap_Lnk.click();
	}
	
}