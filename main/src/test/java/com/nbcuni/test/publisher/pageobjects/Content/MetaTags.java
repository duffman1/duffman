package com.nbcuni.test.publisher.pageobjects.Content;

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
 * publisher.nbcuni.com Meta Tags Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: March 10, 2014
 *********************************************/

public class MetaTags {

	WebDriverWait wait;
	
    //PAGE OBJECT CONSTRUCTOR
    public MetaTags(Driver webDriver) {
        PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(webDriver, 10);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//a/strong[text()='Meta tags']")
    private WebElement MetaTags_Lnk;
    
    @FindBy(how = How.XPATH, using = "//fieldset[@id='edit-metatags-und-twitter-cards']//a")
    private WebElement TwitterCard_Lnk;
    
    @FindBy(how = How.ID, using = "edit-metatags-und-twitterseeitshowid-value")
    private WebElement SeeItShowIDEpisodeID_Txb;
    
    @FindBy(how = How.ID, using = "edit-metatags-und-twitterseeitidspace-value")
    private WebElement SeeItIDSpace_Ddl;
    
    @FindBy(how = How.ID, using = "edit-metatags-und-twitterseeitcampaignid-value")
    private WebElement SeeItCampaignID_Txb;
    
    @FindBy(how = How.ID, using = "edit-metatags-und-twitterseeitassetid-value")
    private WebElement SeeItAssetID_Txb;
    
    
    //PAGE OBJECT METHODS
    public void ClickMetaTagsLnk() throws Exception {
    	
    	Reporter.log("Click the 'Meta tags' link.");
    	MetaTags_Lnk.click();
    }
    
    public void ExpandTwitterCardMenu() throws Exception {
    	
    	if (SeeItShowIDEpisodeID_Txb.isDisplayed() == false) {
    		Reporter.log("Click the 'TWITTER CARD' link.");
    		TwitterCard_Lnk.click();
    	}
    }
    
    public void EnterSeeItShowIDEpisodeID(String id) throws Exception {
    	
    	Reporter.log("Enter '" + id + "' in the 'See it: ShowID/EpisodeID' text box.");
    	wait.until(ExpectedConditions.visibilityOf(SeeItShowIDEpisodeID_Txb)).clear();
    	SeeItShowIDEpisodeID_Txb.sendKeys(id);
    }
    
    public void SelectSeeItIdSpace(String option) throws Exception {
    	
    	Reporter.log("Select the '" + option + "' from the 'See It: IDSpace' drop down list.");
    	new Select(wait.until(ExpectedConditions.visibilityOf(SeeItIDSpace_Ddl))).selectByVisibleText(option);
    }
    
    public void EnterSeeItCampaignID(String id) throws Exception {
    	
    	Reporter.log("Enter '" + id + "' in the 'See it: Campaign ID' text box.");
    	wait.until(ExpectedConditions.visibilityOf(SeeItCampaignID_Txb)).clear();
    	SeeItCampaignID_Txb.sendKeys(id);
    }
    
    public void EnterSeeItAssetID(String id) throws Exception {
    	
    	Reporter.log("Enter '" + id + "' in the 'See it: Asset ID' text box.");
    	wait.until(ExpectedConditions.visibilityOf(SeeItAssetID_Txb)).clear();
    	SeeItAssetID_Txb.sendKeys(id);
    }
    
}

