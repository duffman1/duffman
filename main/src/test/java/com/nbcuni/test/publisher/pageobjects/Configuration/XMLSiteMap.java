package com.nbcuni.test.publisher.pageobjects.Configuration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
* publisher.nbcuni.com XML Site Map Library. Copyright
*
* @author Brandon Clark
* @version 1.1 Date: May 23, 2014
*********************************************/
public class XMLSiteMap {
		
	//PAGE OBJECT CONSTRUCTOR
	public XMLSiteMap(Driver webDriver) {
		PageFactory.initElements(webDriver, this);
		
	}

	//PAGE OBJECT IDENTIFIERS
	@FindBy(how = How.ID, using ="edit-save-custom")
	private WebElement SaveandRestore_Cbx;

	@FindBy(how = How.ID, using ="edit-submit")
	private WebElement RebuildSitemap_Btn;

	@FindBy(how = How.XPATH, using ="//table/..//tr[1]/td[2]/a")
	private WebElement XMLSiteMap_Lnk;

	
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
		
		Reporter.log("Click the 'XMLSitemap Link' with text '" + XMLSiteMap_Lnk.getText() + "'.");
		XMLSiteMap_Lnk.click();
	}
	
}