package com.nbcuni.test.publisher.pageobjects.MPX;

import com.nbcuni.test.webdriver.CustomWebDriver;
import junit.framework.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

/*********************************************
 * publisher.nbcuni.com Edit MPX Video Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 8, 2014
 *********************************************/

public class EditMPXVideo {

    //PAGE OBJECT CONSTRUCTOR
    public EditMPXVideo(CustomWebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFERS
    @FindBy(how = How.CSS, using = "input[id*='edit-field-mpx-available-date'][id*='datepicker']")
    private static WebElement MPXMediaAvailableDate_Txb;
    
    @FindBy(how = How.XPATH, using = "//input[contains(@id, 'edit-field-mpx-available-date')][contains(@id, 'timeEntry')]")
    private static WebElement MPXMediaAvailableTime_Txb;
    
    @FindBy(how = How.XPATH, using = "//input[contains(@id, 'edit-field-mpx-expiration-date')][contains(@id, 'datepicker')]")
    private static WebElement MPXMediaExpirationDate_Txb;
    
    @FindBy(how = How.XPATH, using = "//input[contains(@id, 'edit-field-mpx-expiration-date')][contains(@id, 'timeEntry')]")
    private static WebElement MPXMediaExpirationTime_Txb;
    
    @FindBy(how = How.ID, using = "field_mpx_available_date_mpx_override_checkbox")
    private static WebElement OverrideMPXAvailableDate_Cbx;
    
    @FindBy(how = How.ID, using = "field_mpx_expiration_date_mpx_override_checkbox")
    private static WebElement OverrideMPXExpirationDate_Cbx;
    
    @FindBy(how = How.ID, using = "edit-pub-mpx-player-pid")
    private static WebElement PubMPXVideoPlayer_Ddl;
    
    @FindBy(how = How.ID, using = "edit-field-mpx-media-categories-und-0-value")
    private static WebElement MPXMediaCategory1_Txb;
    
    @FindBy(how = How.ID, using = "edit-field-mpx-media-categories-und-1-value")
    private static WebElement MPXMediaCategory2_Txb;
    
    @FindBy(how = How.ID, using = "edit-field-mpx-media-categories-und-2-value")
    private static WebElement MPXMediaCategory3_Txb;
    
    @FindBy(how = How.ID, using = "edit-field-mpx-title-und-0-value")
    private static WebElement MPXMediaTitle_Txb;
    
    @FindBy(how = How.ID, using = "edit-field-mpx-author-und-0-value")
    private static WebElement MPXMediaAuthor_Txb;
    
    @FindBy(how = How.ID, using = "edit-update-mpx-data")
    private static WebElement SyncFromMPX_Btn;
    
    @FindBy(how = How.ID, using = "edit-field-mpx-mpaa-rating-und-0-value")
    private static WebElement MPXMediaMPAARating_Txb;
    
    @FindBy(how = How.ID, using = "edit-field-mpx-mpaa-subratings-und-0-value")
    private static WebElement MPXMediaMPAASubRating1_Txb;
    
    @FindBy(how = How.ID, using = "edit-field-mpx-airdate-und-0-value-datepicker-popup-0")
    private static WebElement MPXMediaAirDate_Txb;
    
    @FindBy(how = How.ID, using = "edit-field-mpx-airdate-und-0-value-timeEntry-popup-1")
    private static WebElement MPXMediaAirTime_Txb;
    
    
    //PAGE OBJECT METHODS
    public void VerifyMPXMediaAvailableDateNullAndDisabled() throws Exception {
        
    	Reporter.log("Verify that the 'Media Availabe Date' text box is disabled and has no value.");
    	Assert.assertTrue(MPXMediaAvailableDate_Txb.getAttribute("value").equals(""));
        Assert.assertTrue(MPXMediaAvailableDate_Txb.isEnabled() == false);
    }

    public void VerifyMPXMediaExpirationDateNullAndDisabled() throws Exception {
        
    	Reporter.log("Verify that the 'Media Expiration Date' text box is disabled and has no value.");
    	Assert.assertTrue(MPXMediaExpirationDate_Txb.getAttribute("value").equals(""));
        Assert.assertTrue(MPXMediaExpirationDate_Txb.isEnabled() == false);
    }

    public void ClickOverrideMPXAvailableDateCbx() throws Exception {
        
    	Reporter.log("Click the 'Override' checkbox for the 'Available Date' check box.");
    	OverrideMPXAvailableDate_Cbx.click();

    }

    public void ClickOverrideMPXExpirationDateCbx() throws Exception {
        
    	Reporter.log("Click the 'Override' checkbox for the 'Expiration Date' check box.");
    	OverrideMPXExpirationDate_Cbx.click();

    }

    public void EnterMPXAvailableDate(String availableDate) throws Exception {

    	Reporter.log("Enter '" + availableDate + "' in the 'Available Date' text box.");
        MPXMediaAvailableDate_Txb.sendKeys(availableDate);
    }

    public void EnterMPXAvailableTime(String availableTime) throws Exception {

    	Reporter.log("Enter '" + availableTime + "' in the 'Available Time' text box.");
        MPXMediaAvailableTime_Txb.click(); //requires click on this input first to prevent "cannot focus element" exception
        MPXMediaAvailableTime_Txb.sendKeys(availableTime);
    }

    public void EnterMPXExpirationDate(String expirationDate) throws Exception {

    	Reporter.log("Enter '" + expirationDate + "' in the 'Expiration Date' text box.");
        MPXMediaExpirationDate_Txb.sendKeys(expirationDate);
    }

    public void EnterMPXExpirationTime(String expirationTime) throws Exception {

        Reporter.log("Enter '" + expirationTime + "' in the 'Expiration Time' text box.");
        MPXMediaExpirationTime_Txb.click(); //requires click on this input first to prevent "cannot focus element" exception
        MPXMediaExpirationTime_Txb.sendKeys(expirationTime);
    }
    
    public void VerifyPubMPXVideoPlayerPresent() throws Exception {
    	
    	Reporter.log("Verify the 'Video Player' drop down list is present and has more than one option in the list.");
    	Assert.assertTrue(new Select(PubMPXVideoPlayer_Ddl).getOptions().size() > 1);
    	
    }
    
    public void VerifyPubMPXVideoPlayerSelectedOption(String selectedPlayerTitle) throws Exception {
    	
    	Reporter.log("Verify '" + selectedPlayerTitle + "' is selected as the 'Video Player'.");
    	Assert.assertTrue(new Select(PubMPXVideoPlayer_Ddl).getFirstSelectedOption().
    			getText().equals(selectedPlayerTitle));
    	
    }
    
    public void SelectPubMPXVideoPlayer(String playerTitle) throws Exception {
    	
    	Reporter.log("Select '" + playerTitle + "' from the 'Video Player' drop down list.");
    	new Select(PubMPXVideoPlayer_Ddl).selectByVisibleText(playerTitle);
    	
    }
    
    public void VerifyMPXMediaCategory1(String categoryTxt) throws Exception {
    	
    	Reporter.log("Verify the value of the first 'Media Category' text box equals '" + categoryTxt + "'.");
    	Assert.assertEquals(categoryTxt, MPXMediaCategory1_Txb.getAttribute("value"));
    	
    }
    
    public void VerifyMPXMediaCategory2(String categoryTxt) throws Exception {
    	
    	Reporter.log("Verify the value of the second 'Media Category' text box equals '" + categoryTxt + "'.");
    	Assert.assertEquals(categoryTxt, MPXMediaCategory2_Txb.getAttribute("value"));
    }
    
    public void VerifyMPXMediaCategory3(String categoryTxt) throws Exception {
    	
    	Reporter.log("Verify the value of the third 'Media Category' text box equals '" + categoryTxt + "'.");
    	Assert.assertEquals(categoryTxt, MPXMediaCategory3_Txb.getAttribute("value"));
    }
    
    public void VerifyMPXMediaTitle(String title) throws Exception {
    	
    	Reporter.log("Verify the value of the 'Media Title' text box equals '" + title + "'.");
    	Assert.assertEquals(title, MPXMediaTitle_Txb.getAttribute("value"));
    }
    
    public void VerifyMPXMediaAuthor(String author) throws Exception {
    	
    	Reporter.log("Verify the value of the 'Media Author' text box equals '" + author + "'.");
    	Assert.assertEquals(author, MPXMediaAuthor_Txb.getAttribute("value"));
    	
    }
    
    public void ClickSyncFromMPXBtn() throws Exception {
        
    	Reporter.log("Click the 'Sync from MPX' button.");
    	SyncFromMPX_Btn.click();

    }
    
    public void VerifyMPXExpirationDate(String date) throws Exception {
    	
    	Reporter.log("Verify the value of the 'Expiration Date' text box equals '" + date + "'.");
    	Assert.assertEquals(date, MPXMediaExpirationDate_Txb.getAttribute("value"));
    	
    }
    
    public void VerifyMPXExpirationTime(String time) throws Exception {
    	
    	Reporter.log("Verify the value of the 'Expiration Time' text box equals '" + time + "'.");
    	Assert.assertEquals(time, MPXMediaExpirationTime_Txb.getAttribute("value"));
    	
    }
    
    public void VerifyMPXMPAARating(String rating) throws Exception {
    	
    	Reporter.log("Verify the value of the 'MPAA Rating' text box equals '" + rating + "'.");
    	Assert.assertEquals(rating, MPXMediaMPAARating_Txb.getAttribute("value"));
    	
    }
    
    public void VerifyMPXMPAASubRating1(String subRating) throws Exception {
    	
    	Reporter.log("Verify the value of the 'MPAA Sub Rating' text box equals '" + subRating + "'.");
    	Assert.assertEquals(subRating, MPXMediaMPAASubRating1_Txb.getAttribute("value"));
    	
    }
    
    public void VerifyMPXAirDate(String date) throws Exception {
    	
    	Reporter.log("Verify the value of the 'Air Date' text box equals '" + date + "'.");
    	Assert.assertEquals(date, MPXMediaAirDate_Txb.getAttribute("value"));
    	
    }
    
    public void VerifyMPXAirTime(String time) throws Exception {
    	
    	Reporter.log("Verify the value of the 'Air Time' text box equals '" + time + "'.");
    	Assert.assertEquals(time, MPXMediaAirTime_Txb.getAttribute("value"));
    	
    }
    
    public void VerifyMPXAvailableDate(String date) throws Exception {
    	
    	Reporter.log("Verify the value of the 'Available Date' text box equals '" + date + "'.");
    	Assert.assertEquals(date, MPXMediaAvailableDate_Txb.getAttribute("value"));
    	
    }
    
    public void VerifyMPXAvailableTime(String time) throws Exception {
    	
    	Reporter.log("Verify the value of the 'Available Time' text box equals '" + time + "'.");
    	Assert.assertEquals(time, MPXMediaAvailableTime_Txb.getAttribute("value"));
    	
    }
  
}

