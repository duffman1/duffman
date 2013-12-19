package com.nbcuni.test.publisher.taxonomy;


import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;


/*********************************************
 * publisher.nbcuni.com Taxonomy Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 13, 2013
 *********************************************/

public class Taxonomy {

    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    
    private static String Page_Title = "Site-Install";
    private static String Home_Lnk = "//span[@class='admin-menu-home-icon']";
    private static String Tier1_Structure_Lnk = "//li[contains(@class,'admin-menu-toolbar-category')]//a[text()='Structure']";
    private static String Tier1_Content_Lnk = "//li[contains(@class,'admin-menu-toolbar-category')]//a[text()='Content']";
    private static String Tier1_Modules_Lnk = "//li[contains(@class,'admin-menu-toolbar-category')]//a[text()='Modules']";
    private static String Tier1_Structure_Tier2_ContentTypes_Lnk = Tier1_Structure_Lnk + "/../ul//a[text()='Content types']";
    private static String Tier1_Structure_Tier2_Blocks_Lnk = Tier1_Structure_Lnk + "/../ul//a[text()='Blocks']";
    private static String Tier1_Content_Tier2_AddContent_Lnk = Tier1_Content_Lnk + "/../ul//a[text()='Add content']";
    private static String Tier1_Structure_Tier2_ContentTypes_Tier3_AddContentType_Lnk = Tier1_Structure_Tier2_ContentTypes_Lnk + "/..//a[text()='Add content type']";
    private static String Tier1_Structure_Tier2_DFPAdTags_Lnk = Tier1_Structure_Lnk + "/../ul//a[text()='DFP Ad Tags']";
    private static String Tier1_Structure_Tier2_DFPAdTags_Tier3_GlobalDFPSettings_Lnk = Tier1_Structure_Tier2_DFPAdTags_Lnk + "/..//a[text()='Global DFP Settings']";
    private static String Tier1_Structure_Tier2_DFPAdTags_Tier3_Add_Lnk = Tier1_Structure_Tier2_DFPAdTags_Lnk + "/..//a[text()='Add']";
    private static String Tier1_Content_Tier2_AddContent_Tier3_CharacterProfile_Lnk = Tier1_Content_Tier2_AddContent_Lnk + "/..//a[text()='Character Profile']";
    private static String Tier1_Content_Tier2_AddContent_Tier3_MediaGallery_Lnk = Tier1_Content_Tier2_AddContent_Lnk + "/..//a[text()='Media Gallery']";
    private static String Tier1_Content_Tier2_AddContent_Tier3_Movie_Lnk = Tier1_Content_Tier2_AddContent_Lnk + "/..//a[text()='Movie']";
    private static String Tier1_Content_Tier2_AddContent_Tier3_Person_Lnk = Tier1_Content_Tier2_AddContent_Lnk + "/..//a[text()='Person']";
    private static String Tier1_Content_Tier2_AddContent_Tier3_Post_Lnk = Tier1_Content_Tier2_AddContent_Lnk + "/..//a[text()='Post']";
    private static String Tier1_Content_Tier2_AddContent_Tier3_TVEpisode_Lnk = Tier1_Content_Tier2_AddContent_Lnk + "/..//a[text()='TV Episode']";
    private static String Tier1_Content_Tier2_AddContent_Tier3_TVSeason_Lnk = Tier1_Content_Tier2_AddContent_Lnk + "/..//a[text()='TV Season']";
    
    
    public Taxonomy(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
    
    
    public void MouseOverTier1StructureLnk() throws Exception {
    	
    	Actions action = new Actions(webDriver);
    	action.moveToElement(webDriver.findElement(By.xpath(Tier1_Structure_Lnk))).build().perform();
    	
    }
    
    public void MouseOverTier1ContentLnk() throws Exception {
    	
    	Actions action = new Actions(webDriver);
    	action.moveToElement(webDriver.findElement(By.xpath(Tier1_Content_Lnk))).build().perform();
    	
    }
    
    public void MouseOverTier1StructureTier2ContentTypeLnk() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Tier1_Structure_Tier2_ContentTypes_Lnk)));
    	
    	Actions action = new Actions(webDriver);
    	action.moveToElement(webDriver.findElement(By.xpath(Tier1_Structure_Tier2_ContentTypes_Lnk))).build().perform();
    	
    }
    
    public void MouseOverTier1ContentTier2AddContentLnk() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Tier1_Content_Tier2_AddContent_Lnk)));
    	
    	Actions action = new Actions(webDriver);
    	action.moveToElement(webDriver.findElement(By.xpath(Tier1_Content_Tier2_AddContent_Lnk))).build().perform();
    	
    }
    
    public void ClickTier1StructureTier2ContentTypeTier3AddContentTypeLnk() throws Exception {
    	
    	this.MouseOverTier1StructureLnk();
    	this.MouseOverTier1StructureTier2ContentTypeLnk();
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Tier1_Structure_Tier2_ContentTypes_Tier3_AddContentType_Lnk)));
    	
    	webDriver.click(Tier1_Structure_Tier2_ContentTypes_Tier3_AddContentType_Lnk);
    	
    }
    
    public void MouseOverTier1StructureTier2ContentTypeTier3ContentTypeLnk(String contentTypeName) throws Exception {
    	
    	String contentTypeLocator = Tier1_Structure_Tier2_ContentTypes_Lnk + "/..//a[text()='" + contentTypeName + "']";
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(contentTypeLocator)));
    	
    	Actions action = new Actions(webDriver);
    	action.moveToElement(webDriver.findElement(By.xpath(contentTypeLocator))).build().perform();
 
    	
    }
    
    public void ClickTier1StructureTier2ContentTypeTier3ContentTypeTier4ManageFieldsLnk(String contentTypeName) throws Exception {
    	
    	String contentTypeManageDisplayLocator = Tier1_Structure_Tier2_ContentTypes_Lnk + "/..//a[text()='" + contentTypeName + "']" + "/..//a[text()='Manage fields']";
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(contentTypeManageDisplayLocator)));
    	webDriver.click(contentTypeManageDisplayLocator);
 
    	
    }
    
    public void ClickTier1ContentTier2AddContentTier3ContentTypeLnk(String contentTypeName) throws Exception {
    	
    	String contentTypeLocator = Tier1_Content_Tier2_AddContent_Lnk + "/..//a[text()='" + contentTypeName + "']";
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(contentTypeLocator)));
    	
    	webDriver.click(contentTypeLocator);
 
    	
    }
    
    public void ClickTier1ModulesLnk() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(Tier1_Modules_Lnk)));
    	webDriver.click(Tier1_Modules_Lnk);
    }
    
    public void ClickHomeLnk() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(Home_Lnk)));
    	webDriver.click(Home_Lnk);
    		
    }
    
    public void MouseOverTier1StructureTier2DFPAddTagsLnk() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Tier1_Structure_Tier2_DFPAdTags_Lnk)));
    	
    	Actions action = new Actions(webDriver);
    	action.moveToElement(webDriver.findElement(By.xpath(Tier1_Structure_Tier2_DFPAdTags_Lnk))).build().perform();
 
    	
    }
    
    public void ClickTier1StructureTier2DFPAddTagsTier3GlobalDFPSettingsLnk() throws Exception {
    	
    	this.MouseOverTier1StructureLnk();
    	this.MouseOverTier1StructureTier2DFPAddTagsLnk();
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Tier1_Structure_Tier2_DFPAdTags_Tier3_GlobalDFPSettings_Lnk)));
    	
    	webDriver.click(Tier1_Structure_Tier2_DFPAdTags_Tier3_GlobalDFPSettings_Lnk);
    	
    }
    
    public void ClickTier1StructureTier2DFPAddTagsTier3AddLnk() throws Exception {
    	
    	this.MouseOverTier1StructureLnk();
    	this.MouseOverTier1StructureTier2DFPAddTagsLnk();
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Tier1_Structure_Tier2_DFPAdTags_Tier3_Add_Lnk)));
    	
    	webDriver.click(Tier1_Structure_Tier2_DFPAdTags_Tier3_Add_Lnk);
    	
    }
    
    public void ClickTier1StructureTier2BlocksLnk() throws Exception {
    	
    	this.MouseOverTier1StructureLnk();
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Tier1_Structure_Tier2_Blocks_Lnk)));
    	
    	webDriver.findElement(By.xpath((Tier1_Structure_Tier2_Blocks_Lnk))).click();;
 
    	
    }
    
    public void ClickTier1ContentTier2AddContentTier3CharacterProfileLnk() throws Exception {
    	
    	this.MouseOverTier1ContentLnk();
    	this.MouseOverTier1ContentTier2AddContentLnk();
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(Tier1_Content_Tier2_AddContent_Tier3_CharacterProfile_Lnk)));
    	webDriver.click(Tier1_Content_Tier2_AddContent_Tier3_CharacterProfile_Lnk);
 
    	
    }
    
    public void ClickTier1ContentTier2AddContentTier3MediaGalleryLnk() throws Exception {
    	
    	this.MouseOverTier1ContentLnk();
    	this.MouseOverTier1ContentTier2AddContentLnk();
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(Tier1_Content_Tier2_AddContent_Tier3_MediaGallery_Lnk)));
    	
    	webDriver.click(Tier1_Content_Tier2_AddContent_Tier3_MediaGallery_Lnk);
 
    	
    }
    
    public void ClickTier1ContentTier2AddContentTier3PostLnk() throws Exception {
    	
    	this.MouseOverTier1ContentLnk();
    	this.MouseOverTier1ContentTier2AddContentLnk();
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(Tier1_Content_Tier2_AddContent_Tier3_Post_Lnk)));
    	
    	webDriver.click(Tier1_Content_Tier2_AddContent_Tier3_Post_Lnk);
 
    	
    }
    
    public void ClickTier1ContentTier2AddContentTier3MovieLnk() throws Exception {
    	
    	this.MouseOverTier1ContentLnk();
    	this.MouseOverTier1ContentTier2AddContentLnk();
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(Tier1_Content_Tier2_AddContent_Tier3_Movie_Lnk)));
    	
    	webDriver.click(Tier1_Content_Tier2_AddContent_Tier3_Movie_Lnk);
 
    	
    }
    
    public void ClickTier1ContentTier2AddContentTier3PersonLnk() throws Exception {
    	
    	this.MouseOverTier1ContentLnk();
    	this.MouseOverTier1ContentTier2AddContentLnk();
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(Tier1_Content_Tier2_AddContent_Tier3_Person_Lnk)));
    	
    	webDriver.click(Tier1_Content_Tier2_AddContent_Tier3_Person_Lnk);
 
    	
    }
    
    public void ClickTier1ContentTier2AddContentTier3TVEpisodeLnk() throws Exception {
    	
    	this.MouseOverTier1ContentLnk();
    	this.MouseOverTier1ContentTier2AddContentLnk();
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(Tier1_Content_Tier2_AddContent_Tier3_TVEpisode_Lnk)));
    	
    	webDriver.click(Tier1_Content_Tier2_AddContent_Tier3_TVEpisode_Lnk);
 
    	
    }
    
    public void ClickTier1ContentTier2AddContentTier3TVSeasonLnk() throws Exception {
    	
    	this.MouseOverTier1ContentLnk();
    	this.MouseOverTier1ContentTier2AddContentLnk();
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(Tier1_Content_Tier2_AddContent_Tier3_TVSeason_Lnk)));
    	
    	webDriver.click(Tier1_Content_Tier2_AddContent_Tier3_TVSeason_Lnk);
 
    	
    }
    
    
  
}

