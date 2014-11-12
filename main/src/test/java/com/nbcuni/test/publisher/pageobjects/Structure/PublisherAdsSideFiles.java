package com.nbcuni.test.publisher.pageobjects.Structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.Content.Delete;

/*********************************************
* publisher.nbcuni.com Publisher Ads Side Files Library. Copyright
*
* @author Brandon Clark
* @version 1.0 Date: March 12, 2014
*********************************************/

public class PublisherAdsSideFiles {

	private Config config;
	private WaitFor waitFor;
	private Delete delete;
	private ContentParent contentParent;
	
    //PAGE OBJECT CONSTRUCTOR
    public PublisherAdsSideFiles(Driver webDriver) {
    	config = new Config();
    	PageFactory.initElements(webDriver, this);
    	waitFor = new WaitFor(webDriver, config.getConfigValueInt("WaitForWaitTime"));
    	delete = new Delete(webDriver);
    	contentParent = new ContentParent(webDriver);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By Add_Lnk = By.linkText("Add");
    
    private By Import_Lnk = By.linkText("Import");
    
    private By AdSideFile_Lnk(String linkPath) {
    	return By.xpath("//a[text()='" + linkPath + "']");
    }
    
    private By AdSideFileEdit_Lnk(String adName) {
    	return By.xpath("//td[text()='" + adName + "']/..//a[text()='Edit']");
    }
    
    private By AdSideFileName_Txt(String adName) {
    	return By.xpath("//td[text()='" + adName + "']");
    }
    
    private By AdSideFileStorage_Txt(String adName, String storageType) {
    	return By.xpath("//td[text()='" + adName + "']/../td[contains(@class, 'storage')][text()='" + storageType + "']");
    }
    
    private By AdSideFileExpandEdit_Lnk(String adName) {
    	return By.xpath("//td[text()='" + adName + "']/..//a[text()='open']");
    }
    
    private By AdSideFileNames_Txt = By.xpath("//td[@class='ctools-export-ui-name']");
    	
    private By AdSideFileClone_Lnk(String adName) {
    	return By.xpath("//td[text()='" + adName + "']/..//a[text()='Clone']");
    }
    
    private By AdSideFileDelete_Lnk(String adName) {
    	return By.xpath("//td[text()='" + adName + "']/..//a[text()='Delete']");
    }
    
    
    //PAGE OBJECT METHODS
    public void ClickAddLnk() throws Exception {
    
    	Reporter.log("Click the 'Add' link.");
    	waitFor.ElementVisible(Add_Lnk).click();
    	
    }
    
    public void ClickImportLnk() throws Exception {
        
    	Reporter.log("Click the 'Import' link.");
    	waitFor.ElementVisible(Import_Lnk).click();
    	
    }
    
    public void ClickAdSideFileLnk(String path) throws Exception {
        
    	Reporter.log("Click the Ad Side File link with path '" + path + "'.");
    	waitFor.ElementVisible(AdSideFile_Lnk(path)).click();
    	
    }
    
    public void ClickAdSideFileEditLnk(String adName) throws Exception {
        
    	Reporter.log("Click the Ad Side File 'Edit' link for adName + '" + adName + "'.");
    	waitFor.ElementVisible(AdSideFileEdit_Lnk(adName)).click();
    	
    }
    
    public void VerifyAdSideFileStorageType(String adName, String storageType) throws Exception {
        
    	Reporter.log("Verify storage type for Ad Side File '" + adName + "' is set to '" + storageType + "'.");
    	waitFor.ElementVisible(AdSideFileStorage_Txt(adName, storageType));
    	
    }
    
    public void ClickAdSideFileExpandEditLnk(String adName) throws Exception {
        
    	Reporter.log("Click the Ad Side File expand link for adName + '" + adName + "'.");
    	waitFor.ElementVisible(AdSideFileExpandEdit_Lnk(adName)).click();
    	
    }
    
    public void ClickAdSideFileCloneLnk(String adName) throws Exception {
        
    	Reporter.log("Click the Ad Side File 'Clone' link for adName + '" + adName + "'.");
    	waitFor.ElementVisible(AdSideFileClone_Lnk(adName)).click();
    	
    }
    
    public void ClickAdSideFileDeleteLnk(String adName) throws Exception {
        
    	Reporter.log("Click the Ad Side File 'Delete' link for adName + '" + adName + "'.");
    	waitFor.ElementVisible(AdSideFileDelete_Lnk(adName)).click();
    	
    }
    
    public void VerifyAdSideFileNotPresent(String adName) throws Exception {
        
    	Reporter.log("Verify Ad Side File '" + adName + "' is NOT present.");
    	waitFor.ElementNotPresent(AdSideFileName_Txt(adName));
    	
    }
    
    public void DeleteAllUnwantedSideFiles() throws Exception {
    	
    	List<String> allowedSideFiles = Arrays.asList("AdCentric", "Adconion", "AdInterax", "Atlas", "Checkm8",
    			"Comscore", "DART", "Eyeblaster", "Eyeblaster Secure", "Eyengage", "Eyereturn", "Eyewonder",
    			"Flashtalking", "Flite", "Icompass", "Klipmart", "Mixpo", "Oggi", "Pictela", "Pointroll", "Rovion",
    			"Safecount", "Smartadserver", "Unicast", "Videoegg", "Viewpoint");
    	
    	List<String> notAllowedSideFiles = new ArrayList<String>();
    	for (WebElement el :  waitFor.ElementsVisible(AdSideFileNames_Txt)) {
    		String sideFile = el.getText();
    		if (!allowedSideFiles.contains(sideFile)) {
    			notAllowedSideFiles.add(sideFile);
    		}
    	}
    	
    	for (String sideFile : notAllowedSideFiles) {
    		
    		this.ClickAdSideFileExpandEditLnk(sideFile);
    	    this.ClickAdSideFileDeleteLnk(sideFile);
    	    delete.ClickDeleteBtn();
    	    contentParent.VerifyMessageStatus("The item has been deleted.");
    	    this.VerifyAdSideFileNotPresent(sideFile);
    		
    	}
    	
    }
    
}
