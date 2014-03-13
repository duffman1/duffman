package com.nbcuni.test.publisher.pageobjects.FileTypes;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;

/*********************************************
 * publisher.nbcuni.com Manage File Display Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: February 24, 2014
 *********************************************/

public class ManageFileDisplay {

    private static WebDriverWait wait;
    
    //PAGE OBJECT CONSTRUCTOR
    public ManageFileDisplay(CustomWebDriver webDriver, AppLib applib) {
        PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(webDriver, 10);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.ID, using = "edit-displays-file-field-image-status")
    private static WebElement Image_Cbx;
    
    @FindBy(how = How.ID, using = "edit-displays-pub-mpx-image-status")
    private static WebElement PubMPXImage_Cbx;
    
    @FindBy(how = How.ID, using = "edit-displays-pub-mpx-video-status")
    private static WebElement PubMPXVideo_Cbx;
    
    @FindBy(how = How.CSS, using = "select[id*='edit-displays-pub-mpx-video-settings-pub-mpx-video-player']")
    private static WebElement PubMPXVideoPlayerForAccount_Ddl;
    
    @FindBy(how = How.ID, using = "edit-displays-pub-mpx-image-settings-pub-mpx-image-image-style")
    private static WebElement PubMPXImageStyle_Ddl;
    
    @FindBy(how = How.XPATH, using = "//a/strong[text()='Pub MPX Video']")
    private static WebElement PubMPXVideo_Lnk;
    
    @FindBy(how = How.XPATH, using = "//a/strong[text()='Pub MPX Image']")
    private static WebElement PubMPXImage_Lnk;
    
    @FindBy(how = How.ID, using = "edit-displays-file-field-image-settings-image-style")
    private static WebElement ImageStyle_Ddl;
    
    @FindBy(how = How.ID, using = "edit-actions-submit")
    private static WebElement SaveConfiguration_Btn;
    
    
    //PAGE OBJECT METHODS
    public void CheckImageCbx() throws Exception {
    	
    	if (Image_Cbx.isSelected() == false) {
    		Reporter.log("Check the 'Image' checkbox");
    		Image_Cbx.click();
    	}
    }

    public void ClickPubMPXVideoCbx() throws Exception {
    	
    	if (PubMPXVideo_Cbx.isSelected() == false) {
    		Reporter.log("Click the 'Pub MPX Video' checkbox");
    		PubMPXVideo_Cbx.click();
    	}
    }
    
    public void CheckPubMPXImageCbx() throws Exception {
    	
    	if (PubMPXImage_Cbx.isSelected() == false) {
    		Reporter.log("Check the 'Pub MPX Image' checkbox");
    		PubMPXImage_Cbx.click();
    	}
    }
    
    public void UnCheckPubMPXImageCbx() throws Exception {
    	
    	if (PubMPXImage_Cbx.isSelected() == true) {
    		Reporter.log("Un-Check the 'Pub MPX Image' checkbox");
    		PubMPXImage_Cbx.click();
    	}
    }
    
    public void ClickPubMPXVideoLnk() throws Exception {
    	
    	Reporter.log("Click the 'Pub MPX Video' link.");
    	wait.until(ExpectedConditions.visibilityOf(PubMPXVideo_Lnk)).click();
    }
    
    public void ClickPubMPXImageLnk() throws Exception {
    	
    	Reporter.log("Click the 'Pub MPX Image' link.");
    	wait.until(ExpectedConditions.visibilityOf(PubMPXImage_Lnk)).click();
    }
    
    public void SelectMPXVideoPlayer(String playerName) throws Exception {
    	
    	Reporter.log("Select the '" + playerName + "' player from the 'Pub MPX Video Player for Account...' drop down list.");
    	wait.until(ExpectedConditions.visibilityOf(PubMPXVideoPlayerForAccount_Ddl));
    	new Select(PubMPXVideoPlayerForAccount_Ddl).selectByVisibleText(playerName);
    }
    
    public void SelectMPXImageStyle(String style) throws Exception {
    	
    	Reporter.log("Select the '" + style + "' option from the 'Pub MPX Image Style' drop down list.");
    	wait.until(ExpectedConditions.visibilityOf(PubMPXImageStyle_Ddl));
    	new Select(PubMPXImageStyle_Ddl).selectByVisibleText(style);
    }
    
    public void SelectImageStyle(String style) throws Exception {
    	
    	Reporter.log("Select the '" + style + "' option from the 'Image Style' drop down list.");
    	new Select(ImageStyle_Ddl).selectByVisibleText(style);
    }
    
    public void ClickSaveConfigurationBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save configuration' button.");
    	SaveConfiguration_Btn.click();
    }
    
    
}

