package com.nbcuni.test.publisher;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.webdriver.CustomWebDriver;


/*********************************************
 * publisher.nbcuni.com Overlay Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 13, 2013
 *********************************************/

public class Overlay {

    private static CustomWebDriver webDriver;
    
    
    private static String CloseOverlay_Lnk = "//a[@id='overlay-close']";
    private static String CreateCharacterProfile_Frm = "//iframe[@title='Create Character Profile dialog']";
    private static String CreateMovie_Frm = "//iframe[@title='Create Movie dialog']";
    private static String CreatePerson_Frm = "//iframe[@title='Create Person dialog']";
    private static String Modules_Frm = "//iframe[@title='Modules dialog']";
    private static String DFPAddTags_Frm = "//iframe[@title='DFP Ad Tags dialog']";
    private static String AddNewDFPAd_Frm = "//iframe[@title='Add a new DFP ad dialog']";
    private static String CreateMediaGallery_Frm = "//iframe[@title='Create Media Gallery dialog']";
    private static String ContentTypes_Frm = "//iframe[@title='Content types dialog']";
    private static String CreatePost_Frm = "//iframe[@title='Create Post dialog']";
    private static String CreateTVEpisode_Frm = "//iframe[@title='Create TV Episode dialog']";
    private static String CreateTVSeason_Frm = "//iframe[@title='Create TV Season dialog']";
    
    public Overlay(final CustomWebDriver custWebDr) {
    	
    	webDriver = custWebDr;
    }
    
    public void switchToDefaultContent() throws Exception {
        
    	webDriver.switchTo().defaultContent();
    }
    
    public void ClickCloseOverlayLnk() throws Exception {
    	
    	webDriver.click(CloseOverlay_Lnk);
    	
    }
    
    public void SwitchToCreateCharacterProfileFrm() throws Exception {
    	
    	WebElement frm = webDriver.findElement(By.xpath(CreateCharacterProfile_Frm));
    	webDriver.switchTo().frame(frm);
    	
    }
    
    public void SwitchToCreatePostFrm() throws Exception {
    	
    	WebElement frm = webDriver.findElement(By.xpath(CreatePost_Frm));
    	webDriver.switchTo().frame(frm);
    	
    }
    
	public void SwitchToCreateMovieFrm() throws Exception {
    	
    	WebElement frm = webDriver.findElement(By.xpath(CreateMovie_Frm));
    	webDriver.switchTo().frame(frm);
    	
    }
	
	public void SwitchToCreatePersonFrm() throws Exception {
    	
    	WebElement frm = webDriver.findElement(By.xpath(CreatePerson_Frm));
    	webDriver.switchTo().frame(frm);
    	
    }
    
	public void SwitchToEditMovieFrm(String movieTitle) throws Exception {
    	
    	WebElement frm = webDriver.findElement(By.xpath("//iframe[@title='Edit Movie " + movieTitle + " dialog']"));
    	webDriver.switchTo().frame(frm);
    	
    }
	
	public void SwitchToModulesFrm() throws Exception {
    	
    	WebElement frm = webDriver.findElement(By.xpath(Modules_Frm));
    	webDriver.switchTo().frame(frm);
    	
    }
    
	public void SwitchToDFPAddTagsFrm() throws Exception {
    	
    	WebElement frm = webDriver.findElement(By.xpath(DFPAddTags_Frm));
    	webDriver.switchTo().frame(frm);
    	
    }
    
	public void SwitchToAddNewDFPAdFrm() throws Exception {
    	
    	WebElement frm = webDriver.findElement(By.xpath(AddNewDFPAd_Frm));
    	webDriver.switchTo().frame(frm);
    	
    }
	
	public void SwitchToCreateMediaGalleryFrm() throws Exception {
    	
    	WebElement frm = webDriver.findElement(By.xpath(CreateMediaGallery_Frm));
    	webDriver.switchTo().frame(frm);
    	
    }
    
	public void SwitchToAddContentTypeFrm() throws Exception {
    	
    	WebElement frm = webDriver.findElement(By.xpath(ContentTypes_Frm));
    	webDriver.switchTo().frame(frm);
    	
    }
	
	public void SwitchToCreateTVEpisodeFrm() throws Exception {
    	
    	WebElement frm = webDriver.findElement(By.xpath(CreateTVEpisode_Frm));
    	webDriver.switchTo().frame(frm);
    	
    }
	
	public void SwitchToCreateTVSeasonFrm() throws Exception {
    	
    	WebElement frm = webDriver.findElement(By.xpath(CreateTVSeason_Frm));
    	webDriver.switchTo().frame(frm);
    	
    }
    
}

