package com.nbcuni.test.publisher.pageobjects.MPX;


import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


/*********************************************
 * publisher.nbcuni.com Edit MPX Video Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 8, 2014
 *********************************************/

public class EditMPXVideo {

    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;

    private static String MPXMediaAvailableDate_Txb = "//input[contains(@id, 'edit-field-mpx-available-date')][contains(@id, 'datepicker')]";
    private static String MPXMediaAvailableTime_Txb = "//input[contains(@id, 'edit-field-mpx-available-date')][contains(@id, 'timeEntry')]";
    private static String MPXMediaExpirationDate_Txb = "//input[contains(@id, 'edit-field-mpx-expiration-date')][contains(@id, 'datepicker')]";
    private static String MPXMediaExpirationTime_Txb = "//input[contains(@id, 'edit-field-mpx-expiration-date')][contains(@id, 'timeEntry')]";
    private static String OverrideMPXAvailableDate_Cbx = "//input[@id='field_mpx_available_date_mpx_override_checkbox']";
    private static String OverrideMPXExpirationDate_Cbx = "//input[@id='field_mpx_expiration_date_mpx_override_checkbox']";
    private static String PubMPXVideoPlayer_Ddl = "//select[@id='edit-pub-mpx-player-pid']";
    private static String MPXMediaCategory1_Txb = "//input[@id='edit-field-mpx-media-categories-und-0-value']";
    private static String MPXMediaCategory2_Txb = "//input[@id='edit-field-mpx-media-categories-und-1-value']";
    private static String MPXMediaCategory3_Txb = "//input[@id='edit-field-mpx-media-categories-und-2-value']";
    private static String MPXMediaTitle_Txb = "//input[@id='edit-field-mpx-title-und-0-value']";
    private static String MPXMediaAuthor_Txb = "//input[@id='edit-field-mpx-author-und-0-value']";
    private static String SyncFromMPX_Btn = "//input[@id='edit-update-mpx-data']";
    private static String MPXMediaMPAARating_Txb = "//input[@id='edit-field-mpx-mpaa-rating-und-0-value']";
    private static String MPXMediaMPAASubRating1_Txb = "//input[@id='edit-field-mpx-mpaa-subratings-und-0-value']";
    private static String MPXMediaAirDate_Txb = "//input[@id='edit-field-mpx-airdate-und-0-value-datepicker-popup-0']";
    private static String MPXMediaAirTime_Txb = "//input[@id='edit-field-mpx-airdate-und-0-value-timeEntry-popup-1']";
    
    
    public EditMPXVideo(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }

    public void VerifyMPXMediaAvailableDateNullAndDisabled() throws Exception {
        WebElement el = new WebDriverWait(webDriver, 10).until(ExpectedConditions.
                visibilityOf(webDriver.findElement(By.xpath(MPXMediaAvailableDate_Txb))));
        Assert.assertTrue(el.getAttribute("value").equals(""));
        Assert.assertTrue(el.isEnabled() == false);
    }

    public void VerifyMPXMediaExpirationDateNullAndDisabled() throws Exception {
        WebElement el = new WebDriverWait(webDriver, 10).until(ExpectedConditions.
                visibilityOf(webDriver.findElement(By.xpath(MPXMediaExpirationDate_Txb))));
        Assert.assertTrue(el.getAttribute("value").equals(""));
        Assert.assertTrue(el.isEnabled() == false);
    }

    public void ClickOverrideMPXAvailableDateCbx() throws Exception {
        new WebDriverWait(webDriver, 10).until((ExpectedConditions.
                elementToBeClickable(By.xpath(OverrideMPXAvailableDate_Cbx)))).click();

    }

    public void ClickOverrideMPXExpirationDateCbx() throws Exception {
        new WebDriverWait(webDriver, 10).until((ExpectedConditions.
                elementToBeClickable(By.xpath(OverrideMPXExpirationDate_Cbx)))).click();

    }

    public void EnterMPXAvailableDate(String availableDate) throws Exception {

        new WebDriverWait(webDriver, 10).until(ExpectedConditions.
                elementToBeClickable(By.xpath(MPXMediaAvailableDate_Txb))).sendKeys(availableDate);
    }

    public void EnterMPXAvailableTime(String availableTime) throws Exception {

        WebElement el = new WebDriverWait(webDriver, 10).until(ExpectedConditions.
                elementToBeClickable(By.xpath(MPXMediaAvailableTime_Txb)));
        el.click(); //requires click on this input first to prevent "cannot focus element" exception
        el.sendKeys(availableTime);
    }

    public void EnterMPXExpirationDate(String expirationDate) throws Exception {

        new WebDriverWait(webDriver, 10).until(ExpectedConditions.
                elementToBeClickable(By.xpath(MPXMediaExpirationDate_Txb))).sendKeys(expirationDate);
    }

    public void EnterMPXExpirationTime(String expirationTime) throws Exception {

        WebElement el = new WebDriverWait(webDriver, 10).until(ExpectedConditions.
                elementToBeClickable(By.xpath(MPXMediaExpirationTime_Txb)));
        el.click(); //requires click on this input first to prevent "cannot focus element" exception
        el.sendKeys(expirationTime);
    }
    
    public void VerifyPubMPXVideoPlayerPresent() throws Exception {
    	
    	Select el = new Select(new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			presenceOfElementLocated(By.xpath(PubMPXVideoPlayer_Ddl))));
    	
    	Assert.assertTrue(el.getOptions().size() > 1);
    	
    }
    
    public void VerifyPubMPXVideoPlayerSelectedOption(String selectedPlayerTitle) throws Exception {
    	
    	Assert.assertTrue(webDriver.findElement(By.
    			xpath("//select[@id='edit-pub-mpx-player-pid']/option[@selected='selected']")).getText().equals(selectedPlayerTitle));
    	
    }
    
    public void SelectPubMPXVideoPlayer(String playerTitle) throws Exception {
    	
    	new Select(new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			presenceOfElementLocated(By.xpath(PubMPXVideoPlayer_Ddl)))).selectByVisibleText(playerTitle);
    	
    	
    }
    
    public void VerifyMPXMediaCategory1(String categoryTxt) throws Exception {
    	
    	Assert.assertTrue(webDriver.findElement(By.xpath(MPXMediaCategory1_Txb)).getAttribute("value")
    			.equals(categoryTxt));
    	
    }
    
    public void VerifyMPXMediaCategory2(String categoryTxt) throws Exception {
    	
    	Assert.assertTrue(webDriver.findElement(By.xpath(MPXMediaCategory2_Txb)).getAttribute("value")
    			.equals(categoryTxt));
    	
    }
    
    public void VerifyMPXMediaCategory3(String categoryTxt) throws Exception {
    	
    	Assert.assertTrue(webDriver.findElement(By.xpath(MPXMediaCategory3_Txb)).getAttribute("value")
    			.equals(categoryTxt));
    	
    }
    
    public void VerifyMPXMediaTitle(String title) throws Exception {
    	
    	Assert.assertTrue(webDriver.findElement(By.xpath(MPXMediaTitle_Txb)).getAttribute("value")
    			.equals(title));
    	
    }
    
    public void VerifyMPXMediaAuthor(String author) throws Exception {
    	
    	Assert.assertTrue(webDriver.findElement(By.xpath(MPXMediaAuthor_Txb)).getAttribute("value")
    			.equals(author));
    	
    }
    
    public void ClickSyncFromMPXBtn() throws Exception {
        
    	webDriver.findElement(By.xpath(SyncFromMPX_Btn)).click();

    }
    
    public void VerifyMPXExpirationDate(String date) throws Exception {
    	
    	Assert.assertTrue(webDriver.findElement(By.xpath(MPXMediaExpirationDate_Txb)).getAttribute("value")
    			.equals(date));
    	
    }
    
    public void VerifyMPXExpirationTime(String time) throws Exception {
    	
    	Assert.assertTrue(webDriver.findElement(By.xpath(MPXMediaExpirationTime_Txb)).getAttribute("value")
    			.equals(time));
    	
    }
    
    public void VerifyMPXMPAARating(String rating) throws Exception {
    	
    	Assert.assertTrue(webDriver.findElement(By.xpath(MPXMediaMPAARating_Txb)).getAttribute("value")
    			.equals(rating));
    	
    }
    
    public void VerifyMPXMPAASubRating1(String subRating) throws Exception {
    	
    	Assert.assertTrue(webDriver.findElement(By.xpath(MPXMediaMPAASubRating1_Txb)).getAttribute("value")
    			.equals(subRating));
    	
    }
    
    public void VerifyMPXAirDate(String date) throws Exception {
    	
    	Assert.assertTrue(webDriver.findElement(By.xpath(MPXMediaAirDate_Txb)).getAttribute("value")
    			.equals(date));
    	
    }
    
    public void VerifyMPXAirTime(String time) throws Exception {
    	
    	Assert.assertTrue(webDriver.findElement(By.xpath(MPXMediaAirTime_Txb)).getAttribute("value")
    			.equals(time));
    	
    }
    
    public void VerifyMPXAvailableDate(String date) throws Exception {
    	
    	Assert.assertTrue(webDriver.findElement(By.xpath(MPXMediaAvailableDate_Txb)).getAttribute("value")
    			.equals(date));
    	
    }
    
    public void VerifyMPXAvailableTime(String time) throws Exception {
    	
    	Assert.assertTrue(webDriver.findElement(By.xpath(MPXMediaAvailableTime_Txb)).getAttribute("value")
    			.equals(time));
    	
    }
  
}

