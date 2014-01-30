package com.nbcuni.test.publisher.pageobjects.content;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Random;
import com.nbcuni.test.publisher.pageobjects.Overlay;
import com.nbcuni.test.publisher.pageobjects.taxonomy.Taxonomy;
import com.nbcuni.test.webdriver.CustomWebDriver;


/*********************************************
 * publisher.nbcuni.com Default Content Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 28, 2013
 *********************************************/

public class CreateDefaultContent {

    private static CustomWebDriver webDriver;
    private static AppLib appLib;
    private final Util ul;
    
    Taxonomy taxonomy;
	Overlay overlay;
	Random random;
	
    
    public CreateDefaultContent(final CustomWebDriver custWebDr, AppLib appLib) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        this.appLib = appLib;
        taxonomy = new Taxonomy(webDriver);
        overlay = new Overlay(webDriver);
        random = new Random();
    }
   
    public String Post(String moderationState) throws Exception {
    	
    	taxonomy.NavigateSite("Content>>Add content>>Post");
        overlay.SwitchToFrame("Create Post");
        ContentParent contentParent = new ContentParent(webDriver);
        BasicInformation basicInformation = new BasicInformation(webDriver);
        String postTitle = random.GetCharacterString(15);
        basicInformation.EnterTitle(postTitle);
        basicInformation.EnterSynopsis();
        overlay.switchToDefaultContent();
        overlay.SwitchToFrame("Create Post");
        basicInformation.ClickCoverSelectBtn();
        SelectFile selectFile = new SelectFile(webDriver, appLib);
        PageFactory.initElements(webDriver, selectFile);
        selectFile.SelectDefaultCoverImg();
        overlay.SwitchToFrame("Create Post");
        PublishingOptions publishingOptions = new PublishingOptions(webDriver);
        publishingOptions.ClickPublishingOptionsLnk();
        publishingOptions.SelectModerationState(moderationState);
        contentParent.ClickSaveBtn();
        overlay.switchToDefaultContent();
        contentParent.VerifyMessageStatus("Post " + postTitle + " has been created.");
        
        return postTitle;
    }
    
}

