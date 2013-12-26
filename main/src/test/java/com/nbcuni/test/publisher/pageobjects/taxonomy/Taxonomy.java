package com.nbcuni.test.publisher.pageobjects.taxonomy;


import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
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
    private static String Tier1_Content_Tier2_Queues_Lnk = Tier1_Content_Lnk + "/../ul//a[text()='Queues']";
    private static String Tier1_Content_Tier2_Queues_Tier3_AddPromoQueue_Lnk = Tier1_Content_Tier2_Queues_Lnk + "/..//a[text()='Add Promo Queue']";
    private static String MouseOver_Js = "var evObj = document.createEvent('MouseEvents');" + "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" + "arguments[0].dispatchEvent(evObj);";
    
    
    public Taxonomy(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
    
    public void MouseOffTaxonomyItem(String taxonomyItem) throws Exception {
    	//TODO call a script that closes the taxonomy bar
    	Actions action = new Actions(webDriver);
    	action.moveToElement(webDriver.findElement(By.xpath(taxonomyItem)), 500, 500).build().perform();;
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.not(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(taxonomyItem))));
    }
    
    public void MouseOverTier1StructureLnk() throws Exception {
    	
    	webDriver.executeScript(MouseOver_Js, webDriver.findElement(By.xpath(Tier1_Structure_Lnk)));
    }
    
    public void MouseOverTier1ContentLnk() throws Exception {
    	
    	webDriver.executeScript(MouseOver_Js, webDriver.findElement(By.xpath(Tier1_Content_Lnk)));
    }
    
    public void MouseOverTier1StructureTier2ContentTypeLnk() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Tier1_Structure_Tier2_ContentTypes_Lnk)));
    	
    	webDriver.executeScript(MouseOver_Js, webDriver.findElement(By.xpath(Tier1_Structure_Tier2_ContentTypes_Lnk)));
    }
    
    public void MouseOverTier1ContentTier2AddContentLnk() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Tier1_Content_Tier2_AddContent_Lnk)));
    	
    	webDriver.executeScript(MouseOver_Js, webDriver.findElement(By.xpath(Tier1_Content_Tier2_AddContent_Lnk)));
    	
    }
    
    public void ClickTier1StructureTier2ContentTypeTier3AddContentTypeLnk() throws Exception {
    	
    	this.MouseOverTier1StructureLnk();
    	this.MouseOverTier1StructureTier2ContentTypeLnk();
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Tier1_Structure_Tier2_ContentTypes_Tier3_AddContentType_Lnk)));
    	
    	webDriver.click(Tier1_Structure_Tier2_ContentTypes_Tier3_AddContentType_Lnk);
    	this.MouseOffTaxonomyItem(Tier1_Structure_Tier2_ContentTypes_Tier3_AddContentType_Lnk);
    }
    
    public void MouseOverTier1ContentTier2QueuesLnk() throws Exception {
    	
    	this.MouseOverTier1ContentLnk();
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Tier1_Content_Tier2_Queues_Lnk)));
    	
    	webDriver.executeScript(MouseOver_Js, webDriver.findElement(By.xpath(Tier1_Content_Tier2_Queues_Lnk)));
    }
    
    public void ClickTier1ContentTier2QueuesLnk() throws Exception {
    	
    	this.MouseOverTier1ContentLnk();
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Tier1_Content_Tier2_Queues_Lnk)));
    	
    	webDriver.click(Tier1_Content_Tier2_Queues_Lnk);
    	this.MouseOffTaxonomyItem(Tier1_Content_Tier2_Queues_Lnk);
    }
    
    public void ClickTier1ContentTier2QueuesTier3AddPromoQueueLnk() throws Exception {
    	
    	this.MouseOverTier1ContentLnk();
    	this.MouseOverTier1ContentTier2QueuesLnk();
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Tier1_Content_Tier2_Queues_Tier3_AddPromoQueue_Lnk)));
    	
    	webDriver.click(Tier1_Content_Tier2_Queues_Tier3_AddPromoQueue_Lnk);
    	this.MouseOffTaxonomyItem(Tier1_Content_Tier2_Queues_Tier3_AddPromoQueue_Lnk);
    	
    }
    
    public void MouseOverTier1StructureTier2ContentTypeTier3ContentTypeLnk(String contentTypeName) throws Exception {
    	
    	String contentTypeLocator = Tier1_Structure_Tier2_ContentTypes_Lnk + "/..//a[text()='" + contentTypeName + "']";
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(contentTypeLocator)));
    	
    	webDriver.executeScript(MouseOver_Js, webDriver.findElement(By.xpath(contentTypeLocator)));
    	
    }
    
    public void ClickTier1StructureTier2ContentTypeTier3ContentTypeTier4ManageFieldsLnk(String contentTypeName) throws Exception {
    	
    	String contentTypeManageDisplayLocator = Tier1_Structure_Tier2_ContentTypes_Lnk + "/..//a[text()='" + contentTypeName + "']" + "/..//a[text()='Manage fields']";
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(contentTypeManageDisplayLocator)));
    	
    	webDriver.click(contentTypeManageDisplayLocator);
    	this.MouseOffTaxonomyItem(contentTypeManageDisplayLocator);
    	
    }
    
    public void ClickTier1ContentTier2AddContentTier3ContentTypeLnk(String contentTypeName) throws Exception {
    	
    	String contentTypeLocator = Tier1_Content_Tier2_AddContent_Lnk + "/..//a[text()='" + contentTypeName + "']";
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(contentTypeLocator)));
    	
    	webDriver.click(contentTypeLocator);
    	this.MouseOffTaxonomyItem(contentTypeLocator);
    	
    }
    
    public void ClickTier1ModulesLnk() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(Tier1_Modules_Lnk)));
    	webDriver.click(Tier1_Modules_Lnk);
    	this.MouseOffTaxonomyItem(Tier1_Modules_Lnk);
    }
    
    public void ClickHomeLnk() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(Home_Lnk)));
    	webDriver.click(Home_Lnk);
    	this.MouseOffTaxonomyItem(Home_Lnk);	
    }
    
    public void MouseOverTier1StructureTier2DFPAddTagsLnk() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Tier1_Structure_Tier2_DFPAdTags_Lnk)));
    	
    	webDriver.executeScript(MouseOver_Js, webDriver.findElement(By.xpath(Tier1_Structure_Tier2_DFPAdTags_Lnk)));
    	
    }
    
    public void ClickTier1StructureTier2DFPAddTagsTier3GlobalDFPSettingsLnk() throws Exception {
    	
    	this.MouseOverTier1StructureLnk();
    	this.MouseOverTier1StructureTier2DFPAddTagsLnk();
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Tier1_Structure_Tier2_DFPAdTags_Tier3_GlobalDFPSettings_Lnk)));
    	
    	webDriver.click(Tier1_Structure_Tier2_DFPAdTags_Tier3_GlobalDFPSettings_Lnk);
    	this.MouseOffTaxonomyItem(Tier1_Structure_Tier2_DFPAdTags_Tier3_GlobalDFPSettings_Lnk);
    }
    
    public void ClickTier1StructureTier2DFPAddTagsTier3AddLnk() throws Exception {
    	
    	this.MouseOverTier1StructureLnk();
    	this.MouseOverTier1StructureTier2DFPAddTagsLnk();
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Tier1_Structure_Tier2_DFPAdTags_Tier3_Add_Lnk)));
    	
    	webDriver.click(Tier1_Structure_Tier2_DFPAdTags_Tier3_Add_Lnk);
    	this.MouseOffTaxonomyItem(Tier1_Structure_Tier2_DFPAdTags_Tier3_Add_Lnk);
    }
    
    public void ClickTier1StructureTier2BlocksLnk() throws Exception {
    	
    	this.MouseOverTier1StructureLnk();
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Tier1_Structure_Tier2_Blocks_Lnk)));
    	
    	webDriver.findElement(By.xpath((Tier1_Structure_Tier2_Blocks_Lnk))).click();;
    	this.MouseOffTaxonomyItem(Tier1_Structure_Tier2_Blocks_Lnk);
    	
    }
    
    public void ClickTier1ContentTier2AddContentTier3CharacterProfileLnk() throws Exception {
    	
    	this.MouseOverTier1ContentLnk();
    	this.MouseOverTier1ContentTier2AddContentLnk();
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(Tier1_Content_Tier2_AddContent_Tier3_CharacterProfile_Lnk)));
    	webDriver.click(Tier1_Content_Tier2_AddContent_Tier3_CharacterProfile_Lnk);
    	this.MouseOffTaxonomyItem(Tier1_Content_Tier2_AddContent_Tier3_CharacterProfile_Lnk);
    	
    }
    
    public void ClickTier1ContentTier2AddContentTier3MediaGalleryLnk() throws Exception {
    	
    	this.MouseOverTier1ContentLnk();
    	this.MouseOverTier1ContentTier2AddContentLnk();
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(Tier1_Content_Tier2_AddContent_Tier3_MediaGallery_Lnk)));
    	
    	webDriver.click(Tier1_Content_Tier2_AddContent_Tier3_MediaGallery_Lnk);
    	this.MouseOffTaxonomyItem(Tier1_Content_Tier2_AddContent_Tier3_MediaGallery_Lnk);
    	
    }
    
    public void ClickTier1ContentTier2AddContentTier3PostLnk() throws Exception {
    	
    	this.MouseOverTier1ContentLnk();
    	this.MouseOverTier1ContentTier2AddContentLnk();
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(Tier1_Content_Tier2_AddContent_Tier3_Post_Lnk)));
    	
    	webDriver.click(Tier1_Content_Tier2_AddContent_Tier3_Post_Lnk);
    	this.MouseOffTaxonomyItem(Tier1_Content_Tier2_AddContent_Tier3_Post_Lnk);
    }
    
    public void ClickTier1ContentTier2AddContentTier3MovieLnk() throws Exception {
    	
    	this.MouseOverTier1ContentLnk();
    	this.MouseOverTier1ContentTier2AddContentLnk();
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(Tier1_Content_Tier2_AddContent_Tier3_Movie_Lnk)));
    	
    	webDriver.click(Tier1_Content_Tier2_AddContent_Tier3_Movie_Lnk);
    	this.MouseOffTaxonomyItem(Tier1_Content_Tier2_AddContent_Tier3_Movie_Lnk);
    	
    }
    
    public void ClickTier1ContentTier2AddContentTier3PersonLnk() throws Exception {
    	
    	this.MouseOverTier1ContentLnk();
    	this.MouseOverTier1ContentTier2AddContentLnk();
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(Tier1_Content_Tier2_AddContent_Tier3_Person_Lnk)));
    	
    	webDriver.click(Tier1_Content_Tier2_AddContent_Tier3_Person_Lnk);
    	this.MouseOffTaxonomyItem(Tier1_Content_Tier2_AddContent_Tier3_Person_Lnk);
    }
    
    public void ClickTier1ContentTier2AddContentTier3TVEpisodeLnk() throws Exception {
    	
    	this.MouseOverTier1ContentLnk();
    	this.MouseOverTier1ContentTier2AddContentLnk();
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(Tier1_Content_Tier2_AddContent_Tier3_TVEpisode_Lnk)));
    	
    	webDriver.click(Tier1_Content_Tier2_AddContent_Tier3_TVEpisode_Lnk);
    	this.MouseOffTaxonomyItem(Tier1_Content_Tier2_AddContent_Tier3_TVEpisode_Lnk);
    	
    }
    
    public void ClickTier1ContentTier2AddContentTier3TVSeasonLnk() throws Exception {
    	
    	this.MouseOverTier1ContentLnk();
    	this.MouseOverTier1ContentTier2AddContentLnk();
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(Tier1_Content_Tier2_AddContent_Tier3_TVSeason_Lnk)));
    	
    	webDriver.click(Tier1_Content_Tier2_AddContent_Tier3_TVSeason_Lnk);
    	this.MouseOffTaxonomyItem(Tier1_Content_Tier2_AddContent_Tier3_TVSeason_Lnk);
    	
    }
    
    
  
}

