package com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.*;
import org.testng.Reporter;

/*********************************************
 * publisher.nbcuni.com MPX Add Player Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 16, 2014
 *********************************************/

public class MPXAddPlayer {

	private Driver webDriver;
	private Config config;
	private WebDriverWait wait;
    private Screen sikuli;
    private MPXAssets mpxAssets;
    
    //PAGE OBJECT CONSTRUCTOR
    public MPXAddPlayer(Driver webDriver) {
    	this.webDriver = webDriver;
    	config = new Config();
    	PageFactory.initElements(webDriver, this);
    	wait = new WebDriverWait(webDriver, 10);
        sikuli = new Screen();
    	mpxAssets = new MPXAssets();
        
    }
    
    private String getImagePath() {
    	
    	return config.getConfigValueFilePath("PathToSikuliImages");
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//a[contains(@class, 'accordion-toggle')][text()='Players']")
    private WebElement Players_Lnk;
    
    @FindBy(how = How.XPATH, using = "//a[text()='All Players']")
    private WebElement AllPlayers_Lnk;
    
    
    //PAGE OBJECT METHODS
    public void ClickPlayersLnk() throws Exception {
    	
    	Reporter.log("Click the 'Players' link.");
    	try {
    		Players_Lnk.click();
    		
    	}
    	catch (WebDriverException e) {
    		webDriver.executeScript("arguments[0].click();", Players_Lnk);
    		
    	}
    }
    
    public void ClickAllPlayersLnk() throws Exception {
    	
    	Reporter.log("Click the 'All Players' link.");
    	wait.until(ExpectedConditions.visibilityOf(AllPlayers_Lnk));
    	try {
    		AllPlayers_Lnk.click();
    		
    	}
    	catch (WebDriverException e) {
    		webDriver.executeScript("arguments[0].click();", AllPlayers_Lnk);
    		
    	}
    	mpxAssets.WaitForImgPresent(getImagePath() + "Players/AllPlayers_Lst.png");
    	mpxAssets.WaitForAllAssetsToLoad();
    	
    }
    
    public void ClickAddBtn() throws Exception {
    	
    	Reporter.log("Click the 'Add' button.");
    	mpxAssets.WaitForImgPresent(getImagePath() + "Common/Add_Btn.png");
    	sikuli.doubleClick(getImagePath() + "Common/Add_Btn.png", 1);
    	mpxAssets.WaitForImgPresent(getImagePath() + "Players/NewPlayer_Lbl.png");
    }
    
    public void EnterPlayerTitle(String playerTitle) throws Exception {
    	
    	Reporter.log("Enter '" + playerTitle + "' in the 'Title' text box.");
    	Thread.sleep(3000); //TODO - bad but necessary pause
    	mpxAssets.WaitForImgPresent(getImagePath() + "Players/PlayerTitle_Txb.png");
    	Pattern pattern = new Pattern(getImagePath() + "Players/PlayerTitle_Txb.png").targetOffset(0, 30);
    	Region region = sikuli.exists(pattern, 1);
    	sikuli.click(region, 1);
    	mpxAssets.ClearInput();
    	sikuli.type(playerTitle);
    }
    
    public void ClickSaveBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save' button.");
    	mpxAssets.WaitForImgPresent(getImagePath() + "Common/Save_Btn.png");
    	sikuli.click(getImagePath() + "Common/Save_Btn.png");
    	Thread.sleep(5000); //long pause necessary as mpx processes player on backend
    }
    
    public void GiveFocusToPlayerItem() throws Exception {
    	
    	Reporter.log("Click the 'Player Title' label to give focus to the player item.");
    	mpxAssets.WaitForImgPresent(getImagePath() + "Players/PlayerTitle_Txb.png");
    	sikuli.click(getImagePath() + "Players/PlayerTitle_Txb.png");
    	
    }
    
    public void ClickDisablePlayerCbx() throws Exception {
    	
    	Reporter.log("Click the 'Disable Player' check box.");
    	mpxAssets.ScrollDownForImgPresent(getImagePath() + "Players/DisableThisPlayer_Cbx.png");
    	sikuli.click(getImagePath() + "Players/DisableThisPlayer_Cbx.png");
    }
    
  
}

