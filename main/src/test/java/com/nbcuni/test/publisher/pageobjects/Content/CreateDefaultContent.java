package com.nbcuni.test.publisher.pageobjects.Content;

import org.openqa.selenium.support.PageFactory;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Random;
import com.nbcuni.test.publisher.pageobjects.Overlay;
import com.nbcuni.test.publisher.pageobjects.Taxonomy.Taxonomy;
import com.nbcuni.test.webdriver.CustomWebDriver;

/*********************************************
 * publisher.nbcuni.com Default Content Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 28, 2013
 *********************************************/

public class CreateDefaultContent {

    private static CustomWebDriver webDriver;
    private static AppLib applib;
    
    private static Taxonomy taxonomy;
	private static Overlay overlay;
	private static Random random;
	
    public CreateDefaultContent(CustomWebDriver webDriver, AppLib applib) {
        CreateDefaultContent.webDriver = webDriver;
        CreateDefaultContent.applib = applib;
        taxonomy = new Taxonomy(webDriver);
        PageFactory.initElements(webDriver, taxonomy);
        overlay = new Overlay(webDriver, applib);
        random = new Random();
    }
   
    public String Post(String moderationState) throws Exception {
    	
    	taxonomy.NavigateSite("Content>>Add content>>Post");
        overlay.SwitchToFrame("Create Post");
        ContentParent contentParent = new ContentParent(webDriver, applib);
        BasicInformation basicInformation = new BasicInformation(webDriver);
        String postTitle = random.GetCharacterString(15);
        basicInformation.EnterTitle(postTitle);
        basicInformation.EnterSynopsis();
        overlay.switchToDefaultContent();
        overlay.SwitchToFrame("Create Post");
        basicInformation.ClickCoverSelectBtn();
        SelectFile selectFile = new SelectFile(webDriver, applib);
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

