package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentandEntityRelationships;

import org.testng.Reporter;
import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.Content.CreateDefaultContent;
import com.nbcuni.test.publisher.pageobjects.Content.SelectFile;
import com.nbcuni.test.publisher.pageobjects.Structure.ContentTypes;

public class Relationships extends ParentTest{
	 /*************************************************************************************
     * TEST CASE - TC1390
     * Steps - https://rally1.rallydev.com/#/14663927728ud/detail/testcase/17762511569
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "certify"})
    public void Relationships_TC1390() throws Exception{
    	
    	Reporter.log("STEP 1");
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
    	Reporter.log("SETUP - Create a TV Show");
    	CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver, applib);
    	String tvShowTitle1 = createDefaultContent.TVShow("Draft");
    	
    	Reporter.log("STEP 2");
    	taxonomy.NavigateSite("Structure>>Content types>>Movie>>Manage fields");
    	overlay.SwitchToActiveFrame();
    	
    	Reporter.log("STEP 3");
    	ContentTypes contentTypes = new ContentTypes(webDriver, applib);
    	if (contentTypes.IsFieldPresent("Relationships").equals(false)) {
    		contentTypes.EnterAddExistingField("Relationships");
        	contentTypes.SelectExistingField("Pub TV Relationship: field_tv_shows (TV Shows)");
        	contentParent.ClickSaveBtn();
        	overlay.SwitchToActiveFrame();
        	
        	Reporter.log("STEP 4");
        	com.nbcuni.test.publisher.pageobjects.Structure.ManageFields.Relationships relationships = new com.nbcuni.test.publisher.pageobjects.Structure.ManageFields.Relationships(webDriver);
        	relationships.SelectTVRelationshipWidgetDepth("Show");
        	relationships.ClickSaveSettingsBtn();
        	contentParent.VerifyMessageStatus("Saved Relationships configuration.");
        	overlay.ClickCloseOverlayLnk();
    	}
    	else {
    		Reporter.log("STEP 4 - N/A as Relationships field is already configured");
    		overlay.ClickCloseOverlayLnk();
    	}
    	
    	Reporter.log("STEP 5");
    	taxonomy.NavigateSite("Content>>Add content>>Movie");
    	overlay.SwitchToActiveFrame();
    	BasicInformation basicInformation = new BasicInformation(webDriver);
    	String movieTitle = random.GetCharacterString(15);
    	basicInformation.EnterTitle(movieTitle);
        basicInformation.EnterSynopsis();
        overlay.SwitchToActiveFrame();
        basicInformation.ClickCoverSelectBtn();
        SelectFile selectFile = new SelectFile(webDriver, applib);
        selectFile.SelectDefaultCoverImg();
        overlay.SwitchToActiveFrame();
        
        Reporter.log("STEP 6");
        com.nbcuni.test.publisher.pageobjects.Content.Relationships relationshipsContent = new com.nbcuni.test.publisher.pageobjects.Content.Relationships(webDriver, applib);
        relationshipsContent.SelectShow(tvShowTitle1);
        contentParent.WaitForThrobberNotPresent();
        contentParent.ClickSaveBtn();
        contentParent.VerifyMessageStatus("Movie " + movieTitle + " has been created.");
        
    	Reporter.log("STEP 7 - N/A to Movie content type");
    	
    	Reporter.log("STEP 8");
    	String tvShowTitle = createDefaultContent.TVShow("Draft");
    	String tvSeasonTitle = createDefaultContent.TVSeason("Draft", tvShowTitle);
    	createDefaultContent.TVEpisode("Draft", tvShowTitle, tvSeasonTitle);
    	
    	/* BELOW STEPS COMMENTED OUT DUE TO IMAGE RELATIONSHIP BREAKING EXIF - KNOWN DEFECT
    	Reporter.log("STEP 9");
    	taxonomy.NavigateSite("Structure>>File types>>Image>>Manage fields");
    	overlay.SwitchToActiveFrame();
    	if (contentTypes.IsFieldPresent("Relationships").equals(false)) {
    		contentTypes.EnterAddExistingField("Relationships");
        	contentTypes.SelectExistingField("Pub TV Relationship: field_tv_shows (TV Shows)");
        	contentParent.ClickSaveBtn();
        	overlay.SwitchToActiveFrame();
        	
        	Reporter.log("STEP 10");
        	com.nbcuni.test.publisher.pageobjects.Structure.ManageFields.Relationships relationships = new com.nbcuni.test.publisher.pageobjects.Structure.ManageFields.Relationships(webDriver);
        	relationships.SelectTVRelationshipWidgetDepth("Show");
        	relationships.ClickSaveSettingsBtn();
        	contentParent.VerifyMessageStatus("Saved Relationships configuration.");
        	overlay.ClickCloseOverlayLnk();
    	}
    	else {
    		Reporter.log("STEP 10 - N/A as Relationships field is already configured");
    		overlay.ClickCloseOverlayLnk();
    	}
    	
    	Reporter.log("STEP 11");
    	taxonomy.NavigateSite("Content>>Files");
    	overlay.SwitchToActiveFrame();
    	SearchFor searchFor = new SearchFor(webDriver, applib);
    	searchFor.ClickSearchHeaderColumnLnk("Type");
    	overlay.SwitchToActiveFrame();
    	Content content = new Content(webDriver, applib);
    	String fileTitle = searchFor.GetFirstSearchResult();
    	content.ClickEditMenuBtn(fileTitle);
    	overlay.SwitchToActiveFrame();
    	
    	Reporter.log("STEP 12");
    	relationshipsContent.SelectShow(tvShowTitle);
    	contentParent.ClickSaveBtn();
    	overlay.SwitchToActiveFrame();
    	contentParent.VerifyMessageStatus("Image " + fileTitle + " has been updated.");
    	*/
    	//TODO - additional steps needed as time allows
    	
    	
    }
}
