package com.nbcuni.test.publisher.pageobjects.MPX;

import com.nbcuni.test.publisher.common.Driver.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.ArrayList;
import java.util.List;

/*********************************************
 * publisher.nbcuni.com MPX Media Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 29, 2013
 *********************************************/

public class MPXMedia {

    private Driver webDriver;
    private WebDriverWait wait;
    
    //PAGE OBJECT CONSTRUCTOR
    public MPXMedia(Driver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(webDriver, 10);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//a[text()='mpxMedia']")
    private WebElement MPXMedia_Lnk;
    
    @FindBy(how = How.XPATH, using = "//fieldset[@id='edit-video-sync']//a")
    private WebElement SyncMPXMedia_Lnk;
    
    @FindBy(how = How.XPATH, using = "//input[@value='Sync mpxMedia Now']")
    private WebElement SyncMPXMediaNow_Lnk;
    
    private WebElement SynchMPXMediaForAccount1_Ddl(String playerTitle) {
    	return webDriver.findElement(By.xpath("(//select[contains(@id, 'edit-video-sync-settings')])[1]/optgroup/option[contains(text(), '" + playerTitle + "')]"));
    }
    
    private WebElement SynchMPXMediaForAccount2_Ddl(String playerTitle) {
    	return webDriver.findElement(By.xpath("(//select[contains(@id, 'edit-video-sync-settings')])[2]/optgroup/option[contains(text(), '" + playerTitle + "')]"));
    }
    
    private WebElement SynchMPXMediaForAccount3_Ddl(String playerTitle) {
    	return webDriver.findElement(By.xpath("(//select[contains(@id, 'edit-video-sync-settings')])[3]/optgroup/option[contains(text(), '" + playerTitle + "')]"));
    }
    
    private WebElement SynchMPXMediaForAccount4_Ddl(String playerTitle) {
    	return webDriver.findElement(By.xpath("(//select[contains(@id, 'edit-video-sync-settings')])[4]/optgroup/option[contains(text(), '" + playerTitle + "')]"));
    }
    
    private WebElement ImportAccount_Lbl(String label) {
    	return webDriver.findElement(By.xpath("//label[contains(@for, 'edit-video-sync-settings')][contains(text(), '" + label + "')]"));
    }
    
    private WebElement ImportAccount_Ddl(String accountName) {
    	return webDriver.findElement(By.xpath("//label[contains(@for, 'edit-video-sync-settings')][contains(text(), '" + accountName + "')]/../select"));
    }
    
    private WebElement MPXUnpublishedPlayer_Lnk(String playerTitle) {
    	return webDriver.findElement(By.xpath("//div[@class='messages error']//strong//em[text()='" + playerTitle + "']/../..//a[text()='here']"));
    }
    
    private WebElement MPXLogin_Lnk(String playerTitle) {
    	return webDriver.findElement(By.xpath("//div[@class='messages error']//strong//em[text()='" + playerTitle + "']/../..//a[text()='mpx.theplatform']"));
    }
    
    
    //PAGE OBJECT METHODS
    public void ExpandMPXMedia() throws Exception {
    	
    	if (SyncMPXMediaNow_Lnk.isDisplayed() == false) {
    		
    		Reporter.log("Click the 'Sync MPX Media' link.");
    		SyncMPXMedia_Lnk.click();
    	}
    	
    }

    public void ClickMPXMediaLnk() throws Exception {
    	
    	Reporter.log("Click the 'MPX Media' link.");
    	MPXMedia_Lnk.click();
    }

    public void ClickSyncMPXMediaNowLnk() throws Exception {
    	
    	Reporter.log("Click the 'Sync MPX Media Now' link.");
    	SyncMPXMediaNow_Lnk.click();;
    }
    
    public void VerifyImportAccountLabels(List<String> labels) throws Exception {
    	
    	for (String label : labels) {
    		Reporter.log("Verify that the '" + label + "' Import New Account' drop down list is present.");
    		ImportAccount_Lbl(label).isDisplayed();
    	}
    }
    
    public void VerifyImportAccountPlayerOptions(String accountName, List<String> playerList) throws Exception {
    	
    	List<WebElement> Options = new Select(ImportAccount_Ddl(accountName)).getOptions();
    	
    	List<String> allOptions = new ArrayList<String>();
    	
    	for (WebElement el : Options) {
    		
    		allOptions.add(el.getText());
    	}
    	allOptions.remove("- Select -");
    	
    	Reporter.log("Verify that the list of player options matches the count from mpx.");
    	Assert.assertTrue(allOptions.size() >= playerList.size() - 5 || allOptions.size() <= playerList.size() + 5);
    	
    }
    
    public void SelectMPXPlayerForAccount1(String playerTitle) throws Exception {
    	
    	Reporter.log("Select '" + playerTitle + "' from the first 'Import Player' drop down list.");
    	wait.until(ExpectedConditions.visibilityOf(SynchMPXMediaForAccount1_Ddl(playerTitle))).click();
    	
    }
    
    public void SelectMPXPlayerForAccount2(String playerTitle) throws Exception {
    	
    	Reporter.log("Select '" + playerTitle + "' from the second 'Import Player' drop down list.");
    	wait.until(ExpectedConditions.visibilityOf(SynchMPXMediaForAccount2_Ddl(playerTitle))).click();
    }
    
    public void SelectMPXPlayerForAccount3(String playerTitle) throws Exception {
    	
    	Reporter.log("Select '" + playerTitle + "' from the third 'Import Player' drop down list.");
    	wait.until(ExpectedConditions.visibilityOf(SynchMPXMediaForAccount3_Ddl(playerTitle))).click();
    }

    public void SelectMPXPlayerForAccount4(String playerTitle) throws Exception {

    	Reporter.log("Select '" + playerTitle + "' from the fourth 'Import Player' drop down list.");
    	wait.until(ExpectedConditions.visibilityOf(SynchMPXMediaForAccount4_Ddl(playerTitle))).click();
    }
    
    public void ClickMPXPlayerUnpublishedHereLnk(String playerTitle) throws Exception {

    	Reporter.log("Click the 'click here' link for the unpublished player titled '" + playerTitle + "'.");
    	MPXUnpublishedPlayer_Lnk(playerTitle).click();
    }
    
    public void ClickMPXPlayerLogIntoMPXPlatformLnk(String playerTitle) throws Exception {

    	Reporter.log("Click the MPX 'Login' link for the unpublished player '" + playerTitle + "'.");
        MPXLogin_Lnk(playerTitle).click();
    }
    
}

