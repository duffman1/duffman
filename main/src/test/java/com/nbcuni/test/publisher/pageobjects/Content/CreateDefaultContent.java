package com.nbcuni.test.publisher.pageobjects.Content;

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

    private static Taxonomy taxonomy;
	private static Overlay overlay;
	private static Random random;
	private static BasicInformation basicInformation;
	private static ContentParent contentParent;
	private static PublishingOptions publishingOptions;
	private static SelectFile selectFile;
	private static CharactersInformation charactersInformation;
	private static CoverPhoto coverPhoto;
	private static PersonsInformation personsInformation;
	private static ContentTypes contentTypes;
	
    public CreateDefaultContent(CustomWebDriver webDriver, AppLib applib) {
        taxonomy = new Taxonomy(webDriver);
        overlay = new Overlay(webDriver, applib);
        random = new Random();
        basicInformation = new BasicInformation(webDriver);
        contentParent = new ContentParent(webDriver, applib);
        publishingOptions = new PublishingOptions(webDriver);
        selectFile = new SelectFile(webDriver, applib);
        charactersInformation = new CharactersInformation(webDriver);
        coverPhoto = new CoverPhoto(webDriver);
        personsInformation = new PersonsInformation(webDriver);
        contentTypes = new ContentTypes(webDriver, applib);
    }
   
    public String Post(String moderationState) throws Exception {
    	
    	taxonomy.NavigateSite("Content>>Add content>>Post");
        overlay.SwitchToActiveFrame();
        String postTitle = random.GetCharacterString(15);
        basicInformation.EnterTitle(postTitle);
        basicInformation.EnterSynopsis();
        overlay.SwitchToActiveFrame();
        basicInformation.ClickCoverSelectBtn();
        selectFile.SelectDefaultCoverImg();
        overlay.SwitchToActiveFrame();
        publishingOptions.ClickPublishingOptionsLnk();
        publishingOptions.SelectModerationState(moderationState);
        contentParent.ClickSaveBtn();
        overlay.switchToDefaultContent();
        contentParent.VerifyMessageStatus("Post " + postTitle + " has been created.");
        
        return postTitle;
    }
    
    public String CharacterProfile(String moderationState) throws Exception {
        
    		taxonomy.NavigateSite("Content>>Add content>>Character Profile");
           overlay.SwitchToActiveFrame();
           String characterName = random.GetCharacterString(15);
           charactersInformation.EnterCharacterFirstName(characterName);
           coverPhoto.ClickSelectBtn();
           selectFile.SelectDefaultCoverImg();
           overlay.SwitchToActiveFrame();
           coverPhoto.VerifyFileImagePresent("HanSolo");
           publishingOptions.ClickPublishingOptionsLnk();
           publishingOptions.SelectModerationState(moderationState);
           contentParent.ClickSaveBtn();
           overlay.switchToDefaultContent();
           contentParent.VerifyMessageStatus("Character Profile " + characterName + " has been created.");
                  
           return characterName;
           
       }
    
       public String CustomContent(String moderationState , String CustomeContentType) throws Exception {	
       
        taxonomy.NavigateSite("Content>>Add content>>" + CustomeContentType);
        overlay.SwitchToActiveFrame();
        String contentTitleName = random.GetCharacterString(10);
        basicInformation.EnterTitle(contentTitleName);
        publishingOptions.ClickPublishingOptionsLnk();
        publishingOptions.SelectModerationState(moderationState);
        contentParent.ClickSaveBtn();
        contentParent.VerifyMessageStatus(CustomeContentType +" " + contentTitleName + " has been created.");
        
        return contentTitleName;
       }
       
       public String MediaGallery(String moderationState) throws Exception {
       
        taxonomy.NavigateSite("Content>>Add content>>Media Gallery");
        overlay.SwitchToActiveFrame();
        String title = random.GetCharacterString(15);
        basicInformation.EnterTitle(title);
        basicInformation.ClickCoverSelectBtn();
        selectFile.SelectDefaultCoverImg();
        overlay.SwitchToActiveFrame();
        basicInformation.VerifyCoverImagePresent("HanSolo");
        publishingOptions.ClickPublishingOptionsLnk();
        publishingOptions.SelectModerationState(moderationState);
        contentParent.ClickSaveBtn();
        contentParent.VerifyMessageStatus("Media Gallery " + title + " has been created.");
       
        return title;
        
       }

       public String Movie(String moderationState) throws Exception {
       
        taxonomy.NavigateSite("Content>>Add content>>Movie");
        overlay.SwitchToActiveFrame();
        basicInformation.ClickBasicInformationTab();
        String movieTitle = random.GetCharacterString(15);
        basicInformation.EnterTitle(movieTitle);
        basicInformation.EnterSynopsis();
        overlay.SwitchToActiveFrame();
        basicInformation.ClickCoverSelectBtn();
        selectFile.SelectDefaultCoverImg();
        overlay.SwitchToActiveFrame();
        publishingOptions.ClickPublishingOptionsLnk();
        publishingOptions.SelectModerationState(moderationState);
        contentParent.ClickSaveBtn();
        overlay.switchToDefaultContent();
        contentParent.VerifyMessageStatus("Movie " + movieTitle + " has been created.");
        
        return movieTitle;
       
       }

       public String Person(String moderationState) throws Exception {
       
           taxonomy.NavigateSite("Content>>Add content>>Person");
           overlay.SwitchToActiveFrame();
           String personFirstName = random.GetCharacterString(15);
           personsInformation.EnterFirstName(personFirstName);
           personsInformation.EnterBiography();
           overlay.SwitchToActiveFrame();
           personsInformation.ClickCoverPhotoSelectBtn();
           selectFile.SelectDefaultCoverImg();
           overlay.SwitchToActiveFrame();
           publishingOptions.ClickPublishingOptionsLnk();
           publishingOptions.SelectModerationState(moderationState);
           contentParent.ClickSaveBtn();
           overlay.switchToDefaultContent();
           contentParent.VerifyMessageStatus("Person " + personFirstName + " has been created.");
           
           return personFirstName;
       }

       public String TVEpisode(String moderationState) throws Exception {
       
    	   taxonomy.NavigateSite("Content>>Add content>>TV Episode");
    	   overlay.SwitchToActiveFrame();
    	   basicInformation.ClickBasicInformationTab();
    	   String tvEpisodeTitle = random.GetCharacterString(15);
    	   basicInformation.EnterTitle(tvEpisodeTitle);
    	   basicInformation.EnterEpisodeNumber("1");
    	   basicInformation.EnterSynopsis();
           overlay.SwitchToActiveFrame();
           publishingOptions.ClickPublishingOptionsLnk();
           publishingOptions.SelectModerationState(moderationState);
           contentParent.ClickSaveBtn();
           contentParent.VerifyMessageStatus("TV Episode " + tvEpisodeTitle + " has been created.");
           
           return tvEpisodeTitle;
       }

       public String TVSeason(String moderationState) throws Exception {
       
    	   taxonomy.NavigateSite("Content>>Add content>>TV Season");
           overlay.SwitchToActiveFrame();
           basicInformation.ClickBasicInformationTab();
           String tvSeasonTitle = random.GetCharacterString(15);
           basicInformation.EnterTitle(tvSeasonTitle);
           basicInformation.EnterSeasonNumber("1");
           basicInformation.EnterSynopsis();
           overlay.SwitchToActiveFrame();
           basicInformation.ClickCoverSelectBtn();
           selectFile.SelectDefaultCoverImg();
           overlay.SwitchToActiveFrame();
           publishingOptions.ClickPublishingOptionsLnk();
           publishingOptions.SelectModerationState(moderationState);
           contentParent.ClickSaveBtn();
           contentParent.VerifyMessageStatus("TV Season " + tvSeasonTitle + " has been created.");
           
           return tvSeasonTitle;
       }

       public String AddCustomeContentType(String ContentName) throws Exception{
       
    	   String contentTypeName = ContentName;
    	   taxonomy.NavigateSite("Structure>>Content types>>Add content type");
    	   overlay.SwitchToActiveFrame();
    	   if (ContentName.equalsIgnoreCase("Default")){
    		   contentTypeName = random.GetCharacterString(10);
    	   }
    	   contentTypes.EnterName(contentTypeName);
    	   contentTypes.ClickSaveBtn();
    	   contentTypes.VerifyContentTypeSaved(contentTypeName);
    	   overlay.ClickCloseOverlayLnk();
    	   overlay.switchToDefaultContent();
    	   
    	   return contentTypeName;
       }
    
}

