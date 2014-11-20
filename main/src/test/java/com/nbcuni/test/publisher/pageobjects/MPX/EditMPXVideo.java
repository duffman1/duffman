package com.nbcuni.test.publisher.pageobjects.MPX;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

/*********************************************
 * publisher.nbcuni.com Edit MPX Video Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 8, 2014
 *********************************************/

public class EditMPXVideo {

	private Config config;
	private Integer timeout;
	private WaitFor waitFor;
	private Interact interact;
	
    //PAGE OBJECT CONSTRUCTOR
    public EditMPXVideo(Driver webDriver) {
    	config = new Config();
    	timeout = config.getConfigValueInt("WaitForWaitTime");
    	waitFor = new WaitFor(webDriver, timeout);
        interact = new Interact(webDriver, timeout);
    }
    
    //PAGE OBJECT IDENTIFERS
    private By MPXMediaAvailableDate_Txb = By.cssSelector("input[id*='edit-field-mpx-available-date'][id*='datepicker']");
    
    private By MPXMediaAvailableTime_Txb = By.xpath("//input[contains(@id, 'edit-field-mpx-available-date')][contains(@id, 'timeEntry')]");
    
    private By MPXMediaExpirationDate_Txb = By.xpath("//input[contains(@id, 'edit-field-mpx-expiration-date')][contains(@id, 'datepicker')]");
    
    private By MPXMediaExpirationTime_Txb = By.xpath("//input[contains(@id, 'edit-field-mpx-expiration-date')][contains(@id, 'timeEntry')]");
    
    private By OverrideMPXAvailableDate_Cbx = By.id("field_mpx_available_date_mpx_override_checkbox");
    
    private By OverrideMPXExpirationDate_Cbx = By.id("field_mpx_expiration_date_mpx_override_checkbox");
    
    private By PubMPXVideoPlayerBase_Ddl = By.id("edit-pub-mpx-player-pid");
    
    private By PubMPXVideoPlayer_Ddl(String playerTitle) {
    	return By.xpath("//select[@id='edit-pub-mpx-player-pid']/optgroup/option[contains(text(), '" + playerTitle + "')]");
    }
    
    private By MPXMediaCategory1_Txb = By.id("edit-field-mpx-media-categories-und-0-value");
    
    private By MPXMediaCategory2_Txb = By.id("edit-field-mpx-media-categories-und-1-value");
    
    private By MPXMediaCategory3_Txb = By.id("edit-field-mpx-media-categories-und-2-value");
    
    private By MPXMediaTitle_Txb = By.id("edit-field-mpx-title-und-0-value");
    
    private By MPXMediaAuthor_Txb = By.id("edit-field-mpx-author-und-0-value");
    
    private By SyncFromMPX_Btn = By.id("edit-update-mpx-data");
    
    private By MPXMediaMPAARating_Txb = By.id("edit-field-mpx-mpaa-rating-und-0-value");
    
    private By MPXMediaMPAASubRating1_Txb = By.id("edit-field-mpx-mpaa-subratings-und-0-value");
    
    private By MPXMediaAirDate_Txb = By.id("edit-field-mpx-airdate-und-0-value-datepicker-popup-0");
    
    private By MPXMediaAirTime_Txb = By.id("edit-field-mpx-airdate-und-0-value-timeEntry-popup-1");
    
    
    //PAGE OBJECT METHODS
    public void VerifyMPXMediaAvailableDateNullAndDisabled() throws Exception {
        
    	Reporter.log("Verify that the 'Media Availabe Date' text box is disabled and has no value.");
    	WebElement ele = waitFor.ElementVisible(MPXMediaAvailableDate_Txb);
    	Assert.assertTrue(ele.getAttribute("value").equals(""));
        Assert.assertFalse(ele.isEnabled());
        
    }

    public void VerifyMPXMediaExpirationDateNullAndDisabled() throws Exception {
        
    	Reporter.log("Verify that the 'Media Expiration Date' text box is disabled and has no value.");
    	WebElement ele = waitFor.ElementVisible(MPXMediaExpirationDate_Txb);
    	Assert.assertTrue(ele.getAttribute("value").equals(""));
        Assert.assertFalse(ele.isEnabled());
        
    }

    public void CheckOverrideMPXAvailableDateCbx() throws Exception {
        
    	WebElement ele = waitFor.ElementVisible(OverrideMPXAvailableDate_Cbx);
    	if (!ele.isSelected()) {
    		Reporter.log("Check the 'Override' checkbox for the 'Available Date' check box.");
    		interact.Click(ele);
    		
    		waitFor.ElementEnabled(MPXMediaAvailableDate_Txb);
    	}
    	
    }
    
    public void UnCheckOverrideMPXAvailableDateCbx() throws Exception {
        
    	WebElement ele = waitFor.ElementVisible(OverrideMPXAvailableDate_Cbx);
    	if (ele.isSelected()) {
    		Reporter.log("Un-check the 'Override' checkbox for the 'Available Date' check box.");
        	interact.Click(ele);
    		
        	waitFor.ElementDisabled(MPXMediaAvailableDate_Txb);
    	}
    	
    }

    public void CheckOverrideMPXExpirationDateCbx() throws Exception {
        
    	WebElement ele = waitFor.ElementVisible(OverrideMPXExpirationDate_Cbx);
    	if (!ele.isSelected()) {
    		Reporter.log("Check the 'Override' checkbox for the 'Expiration Date' check box.");
        	interact.Click(ele);
    		
        	waitFor.ElementEnabled(MPXMediaExpirationDate_Txb);
    	}
    	
    }
    
    public void UnCheckOverrideMPXExpirationDateCbx() throws Exception {
        
    	WebElement ele = waitFor.ElementVisible(OverrideMPXExpirationDate_Cbx);
    	if (ele.isSelected()) {
    		Reporter.log("Un-check the 'Override' checkbox for the 'Expiration Date' check box.");
        	interact.Click(ele);
        	
        	waitFor.ElementDisabled(MPXMediaExpirationDate_Txb);
    	}
    	
    }

    public void EnterMPXAvailableDate(String availableDate) throws Exception {

    	Reporter.log("Enter '" + availableDate + "' in the 'Available Date' text box.");
    	interact.Type(waitFor.ElementVisible(MPXMediaAvailableDate_Txb), availableDate);
    	
    }

    public void EnterMPXAvailableTime(String availableTime) throws Exception {

    	Reporter.log("Enter '" + availableTime + "' in the 'Available Time' text box.");
    	interact.Type(waitFor.ElementVisible(MPXMediaAvailableTime_Txb), availableTime);
        
    }

    public void EnterMPXExpirationDate(String expirationDate) throws Exception {

    	Reporter.log("Enter '" + expirationDate + "' in the 'Expiration Date' text box.");
    	interact.Type(waitFor.ElementVisible(MPXMediaExpirationDate_Txb), expirationDate);
    	
    }

    public void EnterMPXExpirationTime(String expirationTime) throws Exception {

        Reporter.log("Enter '" + expirationTime + "' in the 'Expiration Time' text box.");
        interact.Type(waitFor.ElementVisible(MPXMediaExpirationTime_Txb), expirationTime);
        
    }
    
    public void VerifyPubMPXVideoPlayerPresent() throws Exception {
    	
    	Reporter.log("Verify the 'Video Player' drop down list is present and has more than one option in the list.");
    	Assert.assertTrue(new Select(waitFor.ElementVisible(PubMPXVideoPlayerBase_Ddl)).getOptions().size() > 1);
    	
    }
    
    public void VerifyPubMPXVideoPlayerSelectedOption(String selectedPlayerTitle) throws Exception {
    	
    	Reporter.log("Verify '" + selectedPlayerTitle + "' is selected as the 'Video Player'.");
    	Assert.assertTrue(new Select(waitFor.ElementVisible(PubMPXVideoPlayerBase_Ddl)).getFirstSelectedOption().
    			getText().contains(selectedPlayerTitle));
    	
    }
    
    public void SelectPubMPXVideoPlayer(String playerTitle) throws Exception {
    	
    	Reporter.log("Select '" + playerTitle + "' from the 'Video Player' drop down list.");
    	if (playerTitle.contains("Default Player")) {
    		Select ddl = new Select(waitFor.ElementVisible(PubMPXVideoPlayerBase_Ddl));
    		for (WebElement option : ddl.getOptions()) {
    			if (option.getText().contains("Default Player")) {
    				interact.Click(option);
    				break;
    			}
    		}
    	}
    	else {
    		interact.Click(waitFor.ElementVisible(PubMPXVideoPlayer_Ddl(playerTitle)));
    	}
    	
    }
    
    public void VerifyMPXMediaCategory1(String categoryTxt) throws Exception {
    	
    	Reporter.log("Verify the value of the first 'Media Category' text box equals '" + categoryTxt + "'.");
    	Assert.assertEquals(categoryTxt, waitFor.ElementVisible(MPXMediaCategory1_Txb).getAttribute("value"));
    	
    }
    
    public void VerifyMPXMediaCategory2(String categoryTxt) throws Exception {
    	
    	Reporter.log("Verify the value of the second 'Media Category' text box equals '" + categoryTxt + "'.");
    	Assert.assertEquals(categoryTxt, waitFor.ElementVisible(MPXMediaCategory2_Txb).getAttribute("value"));
    }
    
    public void VerifyMPXMediaCategory3(String categoryTxt) throws Exception {
    	
    	Reporter.log("Verify the value of the third 'Media Category' text box equals '" + categoryTxt + "'.");
    	Assert.assertEquals(categoryTxt, waitFor.ElementVisible(MPXMediaCategory3_Txb).getAttribute("value"));
    }
    
    public void VerifyMPXMediaTitle(String title) throws Exception {
    	
    	Reporter.log("Verify the value of the 'Media Title' text box equals '" + title + "'.");
    	Assert.assertEquals(title, waitFor.ElementVisible(MPXMediaTitle_Txb).getAttribute("value"));
    }
    
    public void VerifyMPXMediaAuthor(String author) throws Exception {
    	
    	Reporter.log("Verify the value of the 'Media Author' text box equals '" + author + "'.");
    	Assert.assertEquals(author, waitFor.ElementVisible(MPXMediaAuthor_Txb).getAttribute("value"));
    	
    }
    
    public void ClickSyncFromMPXBtn() throws Exception {
        
    	Reporter.log("Click the 'Sync from MPX' button.");
    	interact.Click(waitFor.ElementVisible(SyncFromMPX_Btn));
    	
    }
    
    public void VerifyMPXExpirationDate(String date) throws Exception {
    	
    	Reporter.log("Verify the value of the 'Expiration Date' text box equals '" + date + "'.");
    	Assert.assertEquals(date, waitFor.ElementVisible(MPXMediaExpirationDate_Txb).getAttribute("value"));
    	
    }
    
    public void VerifyMPXExpirationTime(String time) throws Exception {
    	
    	Reporter.log("Verify the value of the 'Expiration Time' text box equals '" + time + "'.");
    	Assert.assertEquals(time, waitFor.ElementVisible(MPXMediaExpirationTime_Txb).getAttribute("value"));
    	
    }
    
    public void VerifyMPXMPAARating(String rating) throws Exception {
    	
    	Reporter.log("Verify the value of the 'MPAA Rating' text box equals '" + rating + "'.");
    	Assert.assertEquals(rating, waitFor.ElementVisible(MPXMediaMPAARating_Txb).getAttribute("value"));
    	
    }
    
    public void VerifyMPXMPAASubRating1(String subRating) throws Exception {
    	
    	Reporter.log("Verify the value of the 'MPAA Sub Rating' text box equals '" + subRating + "'.");
    	Assert.assertEquals(subRating, waitFor.ElementVisible(MPXMediaMPAASubRating1_Txb).getAttribute("value"));
    	
    }
    
    public void VerifyMPXAirDate(String date) throws Exception {
    	
    	Reporter.log("Verify the value of the 'Air Date' text box equals '" + date + "'.");
    	Assert.assertEquals(date, waitFor.ElementVisible(MPXMediaAirDate_Txb).getAttribute("value"));
    	
    }
    
    public void VerifyMPXAirTime(String time) throws Exception {
    	
    	Reporter.log("Verify the value of the 'Air Time' text box equals '" + time + "'.");
    	Assert.assertEquals(time, waitFor.ElementVisible(MPXMediaAirTime_Txb).getAttribute("value"));
    	
    }
    
    public void VerifyMPXAvailableDate(String date) throws Exception {
    	
    	Reporter.log("Verify the value of the 'Available Date' text box equals '" + date + "'.");
    	Assert.assertEquals(date, waitFor.ElementVisible(MPXMediaAvailableDate_Txb).getAttribute("value"));
    	
    }
    
    public void VerifyMPXAvailableTime(String time) throws Exception {
    	
    	Reporter.log("Verify the value of the 'Available Time' text box equals '" + time + "'.");
    	Assert.assertEquals(time, waitFor.ElementVisible(MPXMediaAvailableTime_Txb).getAttribute("value"));
    	
    }
  
}

