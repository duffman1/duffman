package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentandEntityRelationships;

import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.CreateDefaultContent;

public class AddAbilityRelateShowSeasonEpisodeSpecifiableContentTypes extends ParentTest{
	 /*************************************************************************************
     * TEST CASE 
     * Rally steps in work - coming shortly
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void AddAbilityRelateShowSeasonEpisodeSpecifiableContentTypes_Test() throws Exception{
    	
    	//Step 1
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
    	//Step 2
    	/*
    	taxonomy.NavigateSite("Modules");
    	overlay.SwitchToActiveFrame();
    	Modules modules = new Modules(webDriver, applib);
    	modules.EnterFilterName("Pub Movie Relationships");
    	modules.EnableModule("Pub Movie Relationships");
    	modules.EnterFilterName("Pub Relationships");
    	modules.EnableModule("Pub Relationships");
    	modules.EnterFilterName("Pub TV Relationships");
    	modules.EnableModule("Pub TV Relationships");
    	overlay.ClickCloseOverlayLnk();
    	*/
    	
    	//Step 3
    	/*
    	taxonomy.NavigateSite("Configuration>>Content authoring>>Relationships Settings");
    	overlay.SwitchToActiveFrame();
    	RelationshipsSettings relationshipsSettings = new RelationshipsSettings(webDriver);
    	relationshipsSettings.ClickTVTab();
    	relationshipsSettings.UncheckAllCheckboxes();
    	relationshipsSettings.CheckContentItemCbx("Character Profile");
    	relationshipsSettings.SelectRelationshipDepth("Character Profile TV Relationship Depth", "Show");
    	relationshipsSettings.CheckContentItemCbx("TV Episode");
    	relationshipsSettings.SelectRelationshipDepth("TV Episode TV Relationship Depth", "Season");
    	relationshipsSettings.CheckContentItemCbx("TV Season");
    	relationshipsSettings.SelectRelationshipDepth("TV Season TV Relationship Depth", "Show");
    	relationshipsSettings.ClickMoviesTab();
    	relationshipsSettings.UncheckAllCheckboxes();
    	relationshipsSettings.CheckContentItemCbx("Character Profile");
    	relationshipsSettings.ClickSaveConfigurationBtn();
    	contentParent.VerifyMessageStatus("The configuration options have been saved.");
    	overlay.ClickCloseOverlayLnk();
    	*/
    	
    	//Step 4
    	CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver, applib);
    	String tvShowTitle = createDefaultContent.TVShow("Draft");
    	String tvSeasonTitle = createDefaultContent.TVSeason("Draft", tvShowTitle);
    	createDefaultContent.TVEpisode("Draft", tvShowTitle, tvSeasonTitle);
    	//String movieTitle = createDefaultContent.Movie("Draft");
    	//createDefaultContent.CharacterProfile("Draft", tvShowTitle, movieTitle);
    	
    }
}
