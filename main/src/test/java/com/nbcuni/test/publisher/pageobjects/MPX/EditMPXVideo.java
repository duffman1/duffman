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
    	
    	Select el = new Select(new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			presenceOfElementLocated(By.xpath(PubMPXVideoPlayer_Ddl))));
    	
    	Assert.assertTrue(el.getFirstSelectedOption().equals(selectedPlayerTitle));
    	
    }
    
    public void SelectPubMPXVideoPlayer(String playerTitle) throws Exception {
    	
    	new Select(new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			presenceOfElementLocated(By.xpath(PubMPXVideoPlayer_Ddl)))).selectByVisibleText(playerTitle);
    	
    	
    }
  
}

