package com.nbcuni.test.publisher.pageobjects.MPX;

import com.nbcuni.test.publisher.common.Driver.Driver;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

/*********************************************
 * publisher.nbcuni.com Edit MPX Video Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 8, 2014
 *********************************************/

public class EditMPXVideo {

	private Driver webDriver;
	private WebDriverWait wait;
	
    //PAGE OBJECT CONSTRUCTOR
    public EditMPXVideo(Driver webDriver) {
    	this.webDriver = webDriver;
    	PageFactory.initElements(webDriver, this);
        wait = (WebDriverWait) new WebDriverWait(webDriver, 10).ignoring(StaleElementReferenceException.class);
    }
    
    //PAGE OBJECT IDENTIFERS
    @FindBy(how = How.CSS, using = "input[id*='edit-field-mpx-available-date'][id*='datepicker']")
    private WebElement MPXMediaAvailableDate_Txb;
    
    @FindBy(how = How.XPATH, using = "//input[contains(@id, 'edit-field-mpx-available-date')][contains(@id, 'timeEntry')]")
    private WebElement MPXMediaAvailableTime_Txb;
    
    @FindBy(how = How.XPATH, using = "//input[contains(@id, 'edit-field-mpx-expiration-date')][contains(@id, 'datepicker')]")
    private WebElement MPXMediaExpirationDate_Txb;
    
    @FindBy(how = How.XPATH, using = "//input[contains(@id, 'edit-field-mpx-expiration-date')][contains(@id, 'timeEntry')]")
    private WebElement MPXMediaExpirationTime_Txb;
    
    @FindBy(how = How.ID, using = "field_mpx_available_date_mpx_override_checkbox")
    private WebElement OverrideMPXAvailableDate_Cbx;
    
    @FindBy(how = How.ID, using = "field_mpx_expiration_date_mpx_override_checkbox")
    private WebElement OverrideMPXExpirationDate_Cbx;
    
    @FindBy(how = How.ID, using = "edit-pub-mpx-player-pid")
    private WebElement PubMPXVideoPlayerBase_Ddl;
    
    private WebElement PubMPXVideoPlayer_Ddl(String playerTitle) {
    	return webDriver.findElement(By.xpath("//select[@id='edit-pub-mpx-player-pid']/optgroup/option[contains(text(), '" + playerTitle + "')]"));
    }
    
    @FindBy(how = How.ID, using = "edit-field-mpx-media-categories-und-0-value")
    private WebElement MPXMediaCategory1_Txb;
    
    @FindBy(how = How.ID, using = "edit-field-mpx-media-categories-und-1-value")
    private WebElement MPXMediaCategory2_Txb;
    
    @FindBy(how = How.ID, using = "edit-field-mpx-media-categories-und-2-value")
    private WebElement MPXMediaCategory3_Txb;
    
    @FindBy(how = How.ID, using = "edit-field-mpx-title-und-0-value")
    private WebElement MPXMediaTitle_Txb;
    
    @FindBy(how = How.ID, using = "edit-field-mpx-author-und-0-value")
    private WebElement MPXMediaAuthor_Txb;
    
    @FindBy(how = How.ID, using = "edit-update-mpx-data")
    private WebElement SyncFromMPX_Btn;
    
    @FindBy(how = How.ID, using = "edit-field-mpx-mpaa-rating-und-0-value")
    private WebElement MPXMediaMPAARating_Txb;
    
    @FindBy(how = How.ID, using = "edit-field-mpx-mpaa-subratings-und-0-value")
    private WebElement MPXMediaMPAASubRating1_Txb;
    
    @FindBy(how = How.ID, using = "edit-field-mpx-airdate-und-0-value-datepicker-popup-0")
    private WebElement MPXMediaAirDate_Txb;
    
    @FindBy(how = How.ID, using = "edit-field-mpx-airdate-und-0-value-timeEntry-popup-1")
    private WebElement MPXMediaAirTime_Txb;
    
    
    //PAGE OBJECT METHODS
    public void VerifyMPXMediaAvailableDateNullAndDisabled() throws Exception {
        
    	Reporter.log("Verify that the 'Media Availabe Date' text box is disabled and has no value.");
    	Assert.assertTrue(MPXMediaAvailableDate_Txb.getAttribute("value").equals(""));
        Assert.assertFalse(MPXMediaAvailableDate_Txb.isEnabled());
    }

    public void VerifyMPXMediaExpirationDateNullAndDisabled() throws Exception {
        
    	Reporter.log("Verify that the 'Media Expiration Date' text box is disabled and has no value.");
    	Assert.assertTrue(MPXMediaExpirationDate_Txb.getAttribute("value").equals(""));
        Assert.assertFalse(MPXMediaExpirationDate_Txb.isEnabled());
    }

    public void CheckOverrideMPXAvailableDateCbx() throws Exception {
        
    	if (OverrideMPXAvailableDate_Cbx.isSelected() == false) {
    		Reporter.log("Click the 'Override' checkbox for the 'Available Date' check box.");
        	OverrideMPXAvailableDate_Cbx.click();
        	Thread.sleep(250);
        	wait.until(new ExpectedCondition<Boolean>() {
        		public Boolean apply(WebDriver webDriver) {
        			return MPXMediaAvailableDate_Txb.isEnabled();
       		 	}
        	});
        	Thread.sleep(500);
    	}
    }
    
    public void UnCheckOverrideMPXAvailableDateCbx() throws Exception {
        
    	if (OverrideMPXAvailableDate_Cbx.isSelected() == true) {
    		Reporter.log("Un-check the 'Override' checkbox for the 'Available Date' check box.");
        	OverrideMPXAvailableDate_Cbx.click();
        	Thread.sleep(250);
        	wait.until(new ExpectedCondition<Boolean>() {
        		public Boolean apply(WebDriver webDriver) {
        			return !MPXMediaAvailableDate_Txb.isEnabled();
       		 	}
        	});
        	Thread.sleep(500);
    	}
    }

    public void CheckOverrideMPXExpirationDateCbx() throws Exception {
        
    	if (OverrideMPXExpirationDate_Cbx.isSelected() == false) {
    		Reporter.log("Click the 'Override' checkbox for the 'Expiration Date' check box.");
        	OverrideMPXExpirationDate_Cbx.click();
        	Thread.sleep(250);
        	wait.until(new ExpectedCondition<Boolean>() {
        		public Boolean apply(WebDriver webDriver) {
        			return MPXMediaExpirationDate_Txb.isEnabled();
       		 	}
        	});
        	Thread.sleep(500);
    	}
    }
    
    public void UnCheckOverrideMPXExpirationDateCbx() throws Exception {
        
    	if (OverrideMPXExpirationDate_Cbx.isSelected() == true) {
    		Reporter.log("Un-check the 'Override' checkbox for the 'Expiration Date' check box.");
        	OverrideMPXExpirationDate_Cbx.click();
        	Thread.sleep(250);
        	wait.until(new ExpectedCondition<Boolean>() {
        		public Boolean apply(WebDriver webDriver) {
        			return !MPXMediaExpirationDate_Txb.isEnabled();
       		 	}
        	});
        	Thread.sleep(500);
    	}
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
    	Assert.assertTrue(new Select(PubMPXVideoPlayerBase_Ddl).getOptions().size() > 1);
    	
    }
    
    public void VerifyPubMPXVideoPlayerSelectedOption(String selectedPlayerTitle) throws Exception {
    	
    	Reporter.log("Verify '" + selectedPlayerTitle + "' is selected as the 'Video Player'.");
    	Assert.assertTrue(new Select(PubMPXVideoPlayerBase_Ddl).getFirstSelectedOption().
    			getText().contains(selectedPlayerTitle));
    	
    }
    
    public void SelectPubMPXVideoPlayer(String playerTitle) throws Exception {
    	
    	Reporter.log("Select '" + playerTitle + "' from the 'Video Player' drop down list.");
    	if (playerTitle.contains("Default Player")) {
    		Select ddl = new Select(PubMPXVideoPlayerBase_Ddl);
    		for (WebElement option : ddl.getOptions()) {
    			if (option.getText().contains("Default Player")) {
    				option.click();
    				break;
    			}
    		}
    	}
    	else {
    		PubMPXVideoPlayer_Ddl(playerTitle).click();
    	}
    	
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

