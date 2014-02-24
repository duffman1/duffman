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
    @FindBy(how = How.ID, using = "edit-displays-pub-mpx-video-status")
    private static WebElement PubMPXVideo_Cbx;
    
    @FindBy(how = How.CSS, using = "select[id*='edit-displays-pub-mpx-video-settings-pub-mpx-video-player']")
    private static WebElement PubMPXVideoPlayerForAccount_Ddl;
    
    @FindBy(how = How.ID, using = "edit-actions-submit")
    private static WebElement SaveConfiguration_Btn;
    
    
    //PAGE OBJECT METHODS
    public void ClickPubMPXVideoCbx() throws Exception {
    	
    	if (PubMPXVideo_Cbx.isSelected() == false) {
    		Reporter.log("Click the 'Pub MPX Video' checkbox");
    		PubMPXVideo_Cbx.click();
    	}
    }
    
    public void SelectMPXVideoPlayer(String playerName) throws Exception {
    	
    	Reporter.log("Select the '" + playerName + "' player from the 'Pub MPX Video Player for Account...' drop down list.");
    	wait.until(ExpectedConditions.visibilityOf(PubMPXVideoPlayerForAccount_Ddl));
    	new Select(PubMPXVideoPlayerForAccount_Ddl).selectByVisibleText(playerName);
    }
    
    public void ClickSaveConfigurationBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save configuration' button.");
    	SaveConfiguration_Btn.click();
    }
    
    
    
}

