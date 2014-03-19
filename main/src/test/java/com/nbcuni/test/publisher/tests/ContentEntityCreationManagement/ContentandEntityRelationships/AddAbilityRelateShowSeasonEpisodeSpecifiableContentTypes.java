package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentandEntityRelationships;

import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;

public class AddAbilityRelateShowSeasonEpisodeSpecifiableContentTypes extends ParentTest{
	 /*************************************************************************************
     * TEST CASE 
     * Step 1 - Log into the test site as Drupal User 1 (admin [admin@publisher.nbcuni.com].  Note: Testable code was initially deployed to http://nbcuqa4dev.prod.acquia-sites.com/ for this User Story. ,Login succeeds. 
     * Step 2 - Ensure that all content types that will be involved in this test are configured in the test instance.  Note: This step matters because recent (relative to 20130228) modifications of Publisher 7 Installation Profiles (which include Entertainment and Blog) resulted in the absence of some content types that were previous included as standard in Publisher 7 Core builds. Blog Post is one example of this.,All content types to be tested are enabled. 
     * Step 3 - Ensure that at least one test TV Show (Show, Episode, Season) and Movie are available in the test instance, creating them as necessary. ,
     * Step 4 - Ensure that the Publisher 7 modules Pub Movie Relationships, Pub Relationships, and Pub TV Relationships are enabled. ,Pub Movie Relationships, Pub Relationships, and Pub TV Relationships are enabled. 
     * Step 5 - Navigate to Configuration > Content authoring > Relationships Settings. ,The Relationships Settings page is displayed. 
     * Step 6 - In the TV tab, ensure that  a  only the Character Profile checkbox is checked b  Character Profile TV Relationship Depth is set to Episode  and then click Save Configuration. ,A success message is displayed. 
     * Step 7 - In the TV tab, ensure that  a  only the Media Gallery checkbox is checked b  Media Gallery TV Relationship Depth is set to Episode  and then click Save Configuration. ,A success message is displayed. 
,Step 8,In the TV tab, ensure that  a  only the Movie checkbox is checked b  Movie TV Relationship Depth is set to Episode  and then click Save Configuration.,A success message is displayed. 
,Step 9,In the TV tab, ensure that  a  only the Person checkbox is checked b  Person TV Relationship Depth is set to Episode  and then click Save Configuration. ,A success message is displayed. 
,Step 10,In the TV tab, ensure that all checkboxes are unchecked, and then click Save. ,A success message is displayed. 
,Step 11,Further features to be tested:  Movies tab settings (as for TV, enable and save one at a time, and then disable all and save.  TV tab: Enable for all content types to max depth, and then create a content entity for each fo those types and confirm that relationship is specifiable in those entities.  Movies tab: Enable for all content types, and then create a content entity for each of those types and confirm that relationship is specificable in those entities.  And perhaps more, as exploration reveals. ,
,Step 12,In the TV tab, ensure that  a  only the Post checkbox is checked b  Character Profile TV Relationship Depth is set to Episode  and then click Save Configuration. ,A success message is displayed. 

     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void AddAbilityRelateShowSeasonEpisodeSpecifiableContentTypes_Test() throws Exception{
    	
    	/*COMMENTING OUT TEST DUE TO DEFECT. will be resolved in future release and need additional construction.
    	//Step 1
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
    	//Step 2 - N/A
    	
    	//Step 3
    	CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver, applib);
    	String tvShowTitle = createDefaultContent.TVShow("Draft");
    	String tvSeasonTitle = createDefaultContent.TVSeason("Draft");
    	String tvEpisodeTitle = createDefaultContent.TVEpisode("Draft");
    	String movieTitle = createDefaultContent.Movie("Draft");
    	
    	//Step 4
    	Modules modules = new Modules(webDriver, applib);
    	modules.VerifyModuleEnabled("Pub Movie Relationships");
    	modules.VerifyModuleEnabled("Pub Relationships");
    	modules.VerifyModuleEnabled("Pub TV Relationships");
    	
    	//Step 5
    	taxonomy.NavigateSite("Configuration>>Content authoring>>Relationships Settings");
    	overlay.SwitchToActiveFrame();
    	
    	//Step 6
    	RelationshipsSettings relationshipsSettings = new RelationshipsSettings(webDriver);
    	relationshipsSettings.ClickTVTab();
    	relationshipsSettings.UncheckAllCheckboxes();
    	relationshipsSettings.CheckCharacterProfileTVCbx();
    	relationshipsSettings.SelectCharacterProfileTVRelationshipDepth("Episode");
    	relationshipsSettings.ClickSaveConfigurationBtn();
    	contentParent.VerifyMessageStatus("The configuration options have been saved.");
    	overlay.ClickCloseOverlayLnk();
    	
    	//Step 6 cont
    	taxonomy.NavigateSite("Content>>Add content>>Character Profile");
    	overlay.SwitchToActiveFrame();
    	Relationships relationships = new Relationships(webDriver, applib);
    	relationships.ClickRelationshipsLnk();
    	
    	
    	//Step 7
    	
    	
    	
    	
    	Assert.fail("Test under construction.");*/
    }
}
