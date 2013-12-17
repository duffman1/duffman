package com.nbcuni.test.publisher;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.webdriver.CustomWebDriver;


/*********************************************
 * publisher.nbcuni.com Media Gallery Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 13, 2013
 *********************************************/

public class MediaGallery {

    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    
    private static String CreateMediaGallery_Frm = "//iframe[@title='Create Media Gallery dialog']";
    private static String Title_Txb = "//input[@id='edit-title']";
    private static String CoverItemSelect_Btn = "//a[@id='edit-field-cover-item-und-0-select']";
    private static String Cover_Img = "//div[@class='media-thumbnail']/img";
    private static String MediaItemSelectMedia_Btn = "//input[@id='edit-field-media-items-und-add-more']";
    private static String Save_Btn = "//input[@id='edit-submit']";
    private static String Message_Ctr = "//div[@class='messages status']";
    
    public MediaGallery(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
   
    public void SwitchToCreateMediaGalleryFrm() throws Exception {
    	
    	WebElement frm = webDriver.findElement(By.xpath(CreateMediaGallery_Frm));
    	webDriver.switchTo().frame(frm);
    	
    }
    
    public void EnterTitle(String titleName) throws Exception{
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(Title_Txb)));
    	webDriver.type(Title_Txb, titleName);
    }
    
    public void ClickCoverItemSelectBtn() throws Exception{
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(CoverItemSelect_Btn)));
    	webDriver.click(CoverItemSelect_Btn);
    }
    
    public void VerifyCoverImagePresent(String imageSrc) throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(
    			ExpectedConditions.elementToBeClickable(By.xpath(Cover_Img)));
    	
    	Assert.assertTrue(webDriver.findElement(By.xpath(Cover_Img)).getAttribute("src").contains(imageSrc));
    	
    	webDriver.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth " +
    			"!= \"undefined\" && arguments[0].naturalWidth > 0", Cover_Img);
    }
    
    public void ClickMediaItemsSelectMediaBtn() throws Exception{
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(MediaItemSelectMedia_Btn)));
    	webDriver.click(MediaItemSelectMedia_Btn);
    }
    
    public void ClickSaveBtn() throws Exception{
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(Save_Btn)));
    	webDriver.click(Save_Btn);
    }
    
    public void VerifyMediaGallerySaved(String mediaGalleryName) {
    	
    	ul.verifyObjectContainsText(Message_Ctr, "Media Gallery " + mediaGalleryName + " has been created.");
    }
    
    
  
}

