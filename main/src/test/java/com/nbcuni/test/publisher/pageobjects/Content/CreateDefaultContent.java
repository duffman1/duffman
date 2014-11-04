package com.nbcuni.test.publisher.pageobjects.Content;

import com.nbcuni.test.publisher.common.Random;
import com.nbcuni.test.publisher.pageobjects.EmberNav;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com Default Content Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 28, 2013
 *********************************************/

public class CreateDefaultContent {
	
	private EmberNav navigation;
	private Random random;
	private BasicInformation basicInformation;
	private ContentParent contentParent;
	private PublishingOptions publishingOptions;
	private SelectFile selectFile;
	private CharactersInformation charactersInformation;
	private CoverPhoto coverPhoto;
	private PersonsInformation personsInformation;
	private CoverMedia coverMedia;
	private Logo logo;
	private Banner banner;
	private Relationships relationships;
	
    public CreateDefaultContent(Driver webDriver) {
        navigation = new EmberNav(webDriver);
        random = new Random();
        basicInformation = new BasicInformation(webDriver);
        contentParent = new ContentParent(webDriver);
        publishingOptions = new PublishingOptions(webDriver);
        selectFile = new SelectFile(webDriver);
        charactersInformation = new CharactersInformation(webDriver);
        coverPhoto = new CoverPhoto(webDriver);
        personsInformation = new PersonsInformation(webDriver);
        coverMedia = new CoverMedia(webDriver);
        logo = new Logo(webDriver);
        banner = new Banner(webDriver);
        relationships = new Relationships(webDriver);
    }
   
    public String Post(String moderationState) throws Exception {
    	
    	navigation.AddContent("Post");
    	String postTitle = random.GetCharacterString(15);
        basicInformation.EnterTitle(postTitle);
        basicInformation.EnterSynopsis();
        basicInformation.ClickCoverSelectBtn();
        selectFile.SelectDefaultCoverImg();
        publishingOptions.ClickPublishingOptionsLnk();
        publishingOptions.SelectModerationState(moderationState);
        contentParent.ClickSaveBtn();
        contentParent.VerifyMessageStatus("Post " + postTitle + " has been created.");
        
        return postTitle;
    }
    
    public String Post(String moderationState, String synopsis) throws Exception {
    	
    	navigation.AddContent("Post");
    	String postTitle = random.GetCharacterString(15);
        basicInformation.EnterTitle(postTitle);
        basicInformation.EnterSynopsis(synopsis);
        basicInformation.ClickCoverSelectBtn();
        selectFile.SelectDefaultCoverImg();
        publishingOptions.ClickPublishingOptionsLnk();
        publishingOptions.SelectModerationState(moderationState);
        contentParent.ClickSaveBtn();
        contentParent.VerifyMessageStatus("Post " + postTitle + " has been created.");
        
        return postTitle;
    }
    
    public String CharacterProfile(String moderationState, String showTitle, String movieTitle, String biography) throws Exception {
        
    	navigation.AddContent("Character Profile");
    	String characterName = random.GetCharacterString(15); 
        charactersInformation.EnterCharacterFirstName(characterName);
        personsInformation.EnterBiography(biography);
        coverPhoto.ClickSelectBtn();
        selectFile.SelectDefaultCoverImg();
        coverPhoto.VerifyFileImagePresent("HanSolo");
        publishingOptions.ClickPublishingOptionsLnk();
        publishingOptions.SelectModerationState(moderationState);
        if (showTitle != null) {
        	relationships.ClickRelationshipsLnk();
        	relationships.SelectShow(showTitle);
        }
        if (movieTitle != null) {
        	relationships.ClickRelationshipsLnk();
        	relationships.SelectMovie(movieTitle);
        }
        contentParent.ClickSaveBtn();
        contentParent.VerifyMessageStatus("Character Profile " + characterName + " has been created.");       
           
        return characterName;
           
    }
       
    public String TVEpisode(String moderationState, String showName, String seasonName) throws Exception {
       
    	navigation.AddContent("TV Episode");
    	basicInformation.ClickBasicInformationTab();
    	String tvEpisodeTitle = random.GetCharacterString(15);
    	basicInformation.EnterTitle(tvEpisodeTitle);
    	basicInformation.EnterEpisodeNumber("1");
    	basicInformation.EnterSynopsis();
        publishingOptions.ClickPublishingOptionsLnk();
        publishingOptions.SelectModerationState(moderationState);
        if (showName != null) {
        	relationships.ClickRelationshipsLnk();
        	relationships.SelectShow(showName);
        	relationships.SelectSeason(seasonName);
        }
        contentParent.ClickSaveBtn();
        contentParent.VerifyMessageStatus("TV Episode " + tvEpisodeTitle + " has been created.");
           
        return tvEpisodeTitle;
    }

       public String TVSeason(String moderationState, String showName) throws Exception {
       
    	   navigation.AddContent("TV Season");
    	   basicInformation.ClickBasicInformationTab();
           String tvSeasonTitle = random.GetCharacterString(15);
           basicInformation.EnterTitle(tvSeasonTitle);
           basicInformation.EnterSeasonNumber("1");
           basicInformation.EnterSynopsis();
           basicInformation.ClickCoverSelectBtn();
           selectFile.SelectDefaultCoverImg();
           publishingOptions.ClickPublishingOptionsLnk();
           publishingOptions.SelectModerationState(moderationState);
           if (showName != null) {
        	   relationships.ClickRelationshipsLnk();
        	   relationships.SelectShow(showName);
           }
           contentParent.ClickSaveBtn();
           contentParent.VerifyMessageStatus("TV Season " + tvSeasonTitle + " has been created.");
           
           return tvSeasonTitle;
       }
       
       public String TVShow(String moderationState) throws Exception {
           
    	   navigation.AddContent("TV Show");
    	   basicInformation.ClickBasicInformationTab();
    	   String tvShowTitle = random.GetCharacterString(15);
    	   basicInformation.EnterTitle(tvShowTitle);
    	   basicInformation.EnterSynopsis();
           logo.ClickSelectBtn();
           selectFile.SelectDefaultCoverImg();
           coverMedia.ClickSelectBtn();
           selectFile.SelectDefaultCoverImg();
           banner.ClickSelectBtn();
           selectFile.SelectDefaultCoverImg();
           publishingOptions.ClickPublishingOptionsLnk();
           publishingOptions.SelectModerationState(moderationState);
           contentParent.ClickSaveBtn();
           contentParent.VerifyMessageStatus("TV Show " + tvShowTitle + " has been created.");
           
           return tvShowTitle;
       }

}

