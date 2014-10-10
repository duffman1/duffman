package com.nbcuni.test.publisher.tests.Video.DeIngestingMPXAccountsAndCorrespondingAssets;

import java.util.Arrays;
import java.util.List;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.Content.SelectFile;
import com.nbcuni.test.publisher.pageobjects.ErrorChecking.ErrorChecking;
import com.nbcuni.test.publisher.pageobjects.MPX.Settings;
import com.nbcuni.test.publisher.pageobjects.Structure.ContentTypes;
import com.nbcuni.test.publisher.pageobjects.Structure.FieldCollections;
import com.nbcuni.test.publisher.pageobjects.Structure.ManageFields.Edit;
import com.nbcuni.test.publisher.pageobjects.Structure.ManageFields.FieldSettings;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EntityReferenceVerification extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE - TC1886
     * Steps - https://rally1.rallydev.com/#/14663927728ud/detail/testcase/18169766623
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "mpx"})
    public void EntityReferenceVerification_TC1886() throws Exception{
    	
    	//Setup
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        
        //Setup
    	Settings settings = new Settings(webDriver);
    	settings.ConfigureMPXIfNeeded();
    	
        taxonomy.NavigateSite("Configuration>>Media>>Media: thePlatform mpx settings");
        overlay.SwitchToActiveFrame();
        List<String> configuredAccounts = settings.GetImportAccountSelectedOptions();

        //Setup
        if (configuredAccounts.contains("DB TV")) {
        		
        	//Step 1
        	overlay.ClickCloseOverlayLnk();
        	taxonomy.NavigateSite("Structure>>Content types>>Add content type");
        	overlay.SwitchToActiveFrame();
        	ContentTypes contentTypes = new ContentTypes(webDriver);
            String contentTypeName = "MPX" + random.GetCharacterString(10);
            contentTypes.EnterName(contentTypeName);
            contentTypes.ClickSaveAddFieldsBtn();
        	overlay.SwitchToActiveFrame();
        		
        	//Step 2
        	String mpxVideoEntityFieldTitle = "MPXVideoEntity" + random.GetCharacterString(10);
        	contentTypes.EnterAddNewField(mpxVideoEntityFieldTitle);
        	contentTypes.SelectFieldType("Entity Reference");
        	contentTypes.SelectWidget("Select list");
        	contentTypes.ClickSaveBtn();
        	overlay.SwitchToActiveFrame();
        		
        	//Step 3
        	FieldSettings fieldSettings = new FieldSettings(webDriver, applib);
        	fieldSettings.SelectTargetType("File");
        	fieldSettings.SelectMode("Simple (with optional filter by bundle)");
        	fieldSettings.CheckTargetBundleCbx("MPX Video for Account \"DB TV\" (2312945284) ");
        	fieldSettings.SelectSortBy("Don't sort");
        	fieldSettings.ClickSaveFieldSettingsBtn();
        	overlay.SwitchToActiveFrame();
        	contentParent.VerifyMessageStatus("Updated field " + mpxVideoEntityFieldTitle + " field settings.");
        		
        	//Step 4
        		Edit edit = new Edit(webDriver, applib);
        		edit.ClickSaveSettingsBtn();
        		overlay.SwitchToActiveFrame();
        		contentParent.VerifyMessageStatus("Saved " + mpxVideoEntityFieldTitle + " configuration.");
        		
        		//Step 5
        		String mpxVideoFileFieldTitle = "MPXVideoFile" + random.GetCharacterString(10);
        		contentTypes.EnterAddNewField(mpxVideoFileFieldTitle);
        		contentTypes.SelectFieldType("File");
        		contentTypes.SelectWidget("Media file selector");
        		contentTypes.ClickSaveBtn();
        		overlay.SwitchToActiveFrame();
        		fieldSettings.ClickPublicFilesRdb();
        		fieldSettings.ClickSaveFieldSettingsBtn();
        		overlay.SwitchToActiveFrame();
        		contentParent.VerifyMessageStatus("Updated field " + mpxVideoFileFieldTitle + " field settings.");
        		
        		//Step 6
        		edit.CheckEnabledBrowserPluginCbx("Publisher7 MPX Video");
        		edit.CheckAllowedRemoteMediaTypesCbx("Image");
        		edit.CheckAllowedRemoteMediaTypesCbx("MPX Video for Account \"DB TV\" (2312945284)");
        		edit.CheckAllowedURISchemesCbx("mpx:// (mpx videos)");
        		edit.CheckAllowedURISchemesCbx("public:// (Public files)");
        		edit.CheckAllowedURISchemesCbx("private:// (Private files)");
        		edit.ClickSaveSettingsBtn();
        		overlay.SwitchToActiveFrame();
        		contentParent.VerifyMessageStatus("Saved " + mpxVideoFileFieldTitle + " configuration.");
        		
        		//Step 7
        		String mpxVideoImageFieldTitle = "MPXVideoImage" + random.GetCharacterString(10);
        		contentTypes.EnterAddNewField(mpxVideoImageFieldTitle);
        		contentTypes.SelectFieldType("Image");
        		contentTypes.SelectWidget("Media file selector");
        		contentTypes.ClickSaveBtn();
        		overlay.SwitchToActiveFrame();
        		fieldSettings.ClickPublicFilesRdb();
        		fieldSettings.ClickSaveFieldSettingsBtn();
        		overlay.SwitchToActiveFrame();
        		contentParent.VerifyMessageStatus("Updated field " + mpxVideoImageFieldTitle + " field settings.");
        		
        		//Step 8
        		edit.CheckEnabledBrowserPluginCbx("Publisher7 MPX Video");
        		edit.CheckAllowedRemoteMediaTypesCbx("MPX Video for Account \"DB TV\" (2312945284)");
        		edit.CheckAllowedURISchemesCbx("mpx:// (mpx videos)");
        		edit.CheckAllowedURISchemesCbx("public:// (Public files)");
        		edit.CheckAllowedURISchemesCbx("private:// (Private files)");
        		edit.ClickSaveSettingsBtn();
        		overlay.SwitchToActiveFrame();
        		contentParent.VerifyMessageStatus("Saved " + mpxVideoImageFieldTitle + " configuration.");
        		
        		//Step 9
        		String mpxVideoFieldCollectionsTitle = "MPXVideoFC" + random.GetCharacterString(10);
        		contentTypes.EnterAddNewField(mpxVideoFieldCollectionsTitle);
        		contentTypes.SelectFieldType("Field collection");
        		contentTypes.SelectWidget("Embedded");
        		contentTypes.ClickSaveBtn();
        		overlay.SwitchToActiveFrame();
        		fieldSettings.ClickSaveFieldSettingsBtn();
        		overlay.SwitchToActiveFrame();
        		contentParent.VerifyMessageStatus("Updated field " + mpxVideoFieldCollectionsTitle + " field settings.");
        		edit.ClickSaveSettingsBtn();
        		overlay.SwitchToActiveFrame();
        		contentParent.VerifyMessageStatus("Saved " + mpxVideoFieldCollectionsTitle + " configuration.");
        		overlay.ClickCloseOverlayLnk();
        		
        		//Step 10
        		taxonomy.NavigateSite("Structure>>Field collections");
        		overlay.SwitchToActiveFrame();
        		FieldCollections fieldCollections = new FieldCollections(webDriver, applib);
        		fieldCollections.ClickManageFieldsLnk(mpxVideoFieldCollectionsTitle.toLowerCase());
        		overlay.SwitchToActiveFrame();
        		
        		//Step 11
        		String mpxFCVideoEntityFieldTitle = "FCMPXVideoEntity" + random.GetCharacterString(10);
        		contentTypes.EnterAddNewField(mpxFCVideoEntityFieldTitle);
        		contentTypes.SelectFieldType("Entity Reference");
        		contentTypes.SelectWidget("Select list");
        		contentTypes.ClickSaveBtn();
        		overlay.SwitchToActiveFrame();
        		
        		//Step 12
        		fieldSettings.SelectTargetType("File");
        		fieldSettings.SelectMode("Simple (with optional filter by bundle)");
        		fieldSettings.CheckTargetBundleCbx("MPX Video for Account \"DB TV\" (2312945284) ");
        		fieldSettings.SelectSortBy("Don't sort");
        		fieldSettings.ClickSaveFieldSettingsBtn();
        		overlay.SwitchToActiveFrame();
        		contentParent.VerifyMessageStatus("Updated field " + mpxFCVideoEntityFieldTitle + " field settings.");
        		
        		//Step 13
        		edit.ClickSaveSettingsBtn();
        		overlay.SwitchToActiveFrame();
        		contentParent.VerifyMessageStatus("Saved " + mpxFCVideoEntityFieldTitle + " configuration.");
        		
        		//Step 14
        		String mpxFCVideoFileFieldTitle = "FCMPXVideoFile" + random.GetCharacterString(10);
        		contentTypes.EnterAddNewField(mpxFCVideoFileFieldTitle);
        		contentTypes.SelectFieldType("File");
        		contentTypes.SelectWidget("Media file selector");
        		contentTypes.ClickSaveBtn();
        		overlay.SwitchToActiveFrame();
        		fieldSettings.ClickPublicFilesRdb();
        		fieldSettings.ClickSaveFieldSettingsBtn();
        		overlay.SwitchToActiveFrame();
        		contentParent.VerifyMessageStatus("Updated field " + mpxFCVideoFileFieldTitle + " field settings.");
        		
        		//Step 15
        		edit.CheckEnabledBrowserPluginCbx("Publisher7 MPX Video");
        		edit.CheckAllowedRemoteMediaTypesCbx("Image");
        		edit.CheckAllowedRemoteMediaTypesCbx("MPX Video for Account \"DB TV\" (2312945284)");
        		edit.CheckAllowedURISchemesCbx("mpx:// (mpx videos)");
        		edit.CheckAllowedURISchemesCbx("public:// (Public files)");
        		edit.CheckAllowedURISchemesCbx("private:// (Private files)");
        		edit.ClickSaveSettingsBtn();
        		overlay.SwitchToActiveFrame();
        		contentParent.VerifyMessageStatus("Saved " + mpxFCVideoFileFieldTitle + " configuration.");
        		
        		//Step 16
        		String mpxFCVideoImageFieldTitle = "FCMPXVideoImage" + random.GetCharacterString(10);
        		contentTypes.EnterAddNewField(mpxFCVideoImageFieldTitle);
        		contentTypes.SelectFieldType("Image");
        		contentTypes.SelectWidget("Media file selector");
        		contentTypes.ClickSaveBtn();
        		overlay.SwitchToActiveFrame();
        		fieldSettings.ClickPublicFilesRdb();
        		fieldSettings.ClickSaveFieldSettingsBtn();
        		overlay.SwitchToActiveFrame();
        		contentParent.VerifyMessageStatus("Updated field " + mpxFCVideoImageFieldTitle + " field settings.");
        		
        		//Step 17
        		edit.CheckEnabledBrowserPluginCbx("Publisher7 MPX Video");
        		edit.CheckAllowedRemoteMediaTypesCbx("MPX Video for Account \"DB TV\" (2312945284)");
        		edit.CheckAllowedURISchemesCbx("mpx:// (mpx videos)");
        		edit.CheckAllowedURISchemesCbx("public:// (Public files)");
        		edit.CheckAllowedURISchemesCbx("private:// (Private files)");
        		edit.ClickSaveSettingsBtn();
        		overlay.SwitchToActiveFrame();
        		contentParent.VerifyMessageStatus("Saved " + mpxFCVideoImageFieldTitle + " configuration.");
        		
        		//Step 18
        		contentTypes.ClickSaveBtn();
        		overlay.SwitchToActiveFrame();
        		contentParent.VerifyMessageStatus("Your settings have been saved.");
        		overlay.ClickCloseOverlayLnk();
        		
        		//Step 19
        		taxonomy.NavigateSite("Structure>>Content types>>" + contentTypeName + ">>Manage fields");
        		overlay.SwitchToActiveFrame();
        		
        		//Step 20
        		String mpxPlayerEntityFieldTitle = "MPXPlayerEntity" + random.GetCharacterString(10);
        		contentTypes.EnterAddNewField(mpxPlayerEntityFieldTitle);
        		contentTypes.SelectFieldType("Entity Reference");
        		contentTypes.SelectWidget("Select list");
        		contentTypes.ClickSaveBtn();
        		overlay.SwitchToActiveFrame();
        		
        		//Step 21
        		fieldSettings.SelectTargetType("File");
        		fieldSettings.SelectMode("Simple (with optional filter by bundle)");
        		fieldSettings.CheckTargetBundleCbx("MPX Player");
        		fieldSettings.SelectSortBy("Don't sort");
        		fieldSettings.ClickSaveFieldSettingsBtn();
        		overlay.SwitchToActiveFrame();
        		contentParent.VerifyMessageStatus("Updated field " + mpxPlayerEntityFieldTitle + " field settings.");
        		
        		//Step 22
        		edit.ClickSaveSettingsBtn();
        		overlay.SwitchToActiveFrame();
        		contentParent.VerifyMessageStatus("Saved " + mpxPlayerEntityFieldTitle + " configuration.");
        		
        		//Step 23
        		String mpxPlayerFileFieldTitle = "MPXPlayerFile" + random.GetCharacterString(10);
        		contentTypes.EnterAddNewField(mpxPlayerFileFieldTitle);
        		contentTypes.SelectFieldType("File");
        		contentTypes.SelectWidget("Media file selector");
        		contentTypes.ClickSaveBtn();
        		overlay.SwitchToActiveFrame();
        		fieldSettings.ClickPublicFilesRdb();
        		fieldSettings.ClickSaveFieldSettingsBtn();
        		overlay.SwitchToActiveFrame();
        		contentParent.VerifyMessageStatus("Updated field " + mpxPlayerFileFieldTitle + " field settings.");
        		
        		//Step 24
        		edit.CheckEnabledBrowserPluginCbx("mpxPlayers");
        		edit.CheckAllowedRemoteMediaTypesCbx("MPX Player");
        		edit.CheckAllowedURISchemesCbx("public:// (Public files)");
        		edit.CheckAllowedURISchemesCbx("private:// (Private files)");
        		edit.ClickSaveSettingsBtn();
        		overlay.SwitchToActiveFrame();
        		contentParent.VerifyMessageStatus("Saved " + mpxPlayerFileFieldTitle + " configuration.");
        		
        		//Step 25
        		String mpxPlayerImageFieldTitle = "MPXPlayerImage" + random.GetCharacterString(10);
        		contentTypes.EnterAddNewField(mpxPlayerImageFieldTitle);
        		contentTypes.SelectFieldType("Image");
        		contentTypes.SelectWidget("Media file selector");
        		contentTypes.ClickSaveBtn();
        		overlay.SwitchToActiveFrame();
        		fieldSettings.ClickPublicFilesRdb();
        		fieldSettings.ClickSaveFieldSettingsBtn();
        		overlay.SwitchToActiveFrame();
        		contentParent.VerifyMessageStatus("Updated field " + mpxPlayerImageFieldTitle + " field settings.");
        		
        		//Step 26
        		edit.CheckEnabledBrowserPluginCbx("mpxPlayers");
        		edit.CheckAllowedRemoteMediaTypesCbx("MPX Player");
        		edit.CheckAllowedURISchemesCbx("public:// (Public files)");
        		edit.CheckAllowedURISchemesCbx("private:// (Private files)");
        		edit.ClickSaveSettingsBtn();
        		overlay.SwitchToActiveFrame();
        		contentParent.VerifyMessageStatus("Saved " + mpxPlayerImageFieldTitle + " configuration.");
        		
        		//Step 27
        		String mpxPlayerFieldCollectionsTitle = "MPXPlayerFC" + random.GetCharacterString(10);
        		contentTypes.EnterAddNewField(mpxPlayerFieldCollectionsTitle);
        		contentTypes.SelectFieldType("Field collection");
        		contentTypes.SelectWidget("Embedded");
        		contentTypes.ClickSaveBtn();
        		overlay.SwitchToActiveFrame();
        		fieldSettings.ClickSaveFieldSettingsBtn();
        		overlay.SwitchToActiveFrame();
        		contentParent.VerifyMessageStatus("Updated field " + mpxPlayerFieldCollectionsTitle + " field settings.");
        		edit.ClickSaveSettingsBtn();
        		overlay.SwitchToActiveFrame();
        		contentParent.VerifyMessageStatus("Saved " + mpxPlayerFieldCollectionsTitle + " configuration.");
        		overlay.ClickCloseOverlayLnk();
        		
        		//Step 28
        		taxonomy.NavigateSite("Structure>>Field collections");
        		overlay.SwitchToActiveFrame();
        		fieldCollections.ClickManageFieldsLnk(mpxPlayerFieldCollectionsTitle.toLowerCase());
        		overlay.SwitchToActiveFrame();
        		
        		//Step 29
        		String mpxFCPlayerEntityFieldTitle = "FCMPXPlayerEntity" + random.GetCharacterString(10);
        		contentTypes.EnterAddNewField(mpxFCPlayerEntityFieldTitle);
        		contentTypes.SelectFieldType("Entity Reference");
        		contentTypes.SelectWidget("Select list");
        		contentTypes.ClickSaveBtn();
        		overlay.SwitchToActiveFrame();
        		
        		//Step 30
        		fieldSettings.SelectTargetType("File");
        		fieldSettings.SelectMode("Simple (with optional filter by bundle)");
        		fieldSettings.CheckTargetBundleCbx("MPX Player");
        		fieldSettings.SelectSortBy("Don't sort");
        		fieldSettings.ClickSaveFieldSettingsBtn();
        		overlay.SwitchToActiveFrame();
        		contentParent.VerifyMessageStatus("Updated field " + mpxFCPlayerEntityFieldTitle + " field settings.");
        		edit.ClickSaveSettingsBtn();
        		overlay.SwitchToActiveFrame();
        		contentParent.VerifyMessageStatus("Saved " + mpxFCPlayerEntityFieldTitle + " configuration.");
        		
        		//Step 31
        		String mpxFCPlayerFileFieldTitle = "FCMPXPlayerFile" + random.GetCharacterString(10);
        		contentTypes.EnterAddNewField(mpxFCPlayerFileFieldTitle);
        		contentTypes.SelectFieldType("File");
        		contentTypes.SelectWidget("Media file selector");
        		contentTypes.ClickSaveBtn();
        		overlay.SwitchToActiveFrame();
        		fieldSettings.ClickPublicFilesRdb();
        		fieldSettings.ClickSaveFieldSettingsBtn();
        		overlay.SwitchToActiveFrame();
        		contentParent.VerifyMessageStatus("Updated field " + mpxFCPlayerFileFieldTitle + " field settings.");
        		
        		//Step 32
        		edit.CheckEnabledBrowserPluginCbx("mpxPlayers");
        		edit.CheckAllowedRemoteMediaTypesCbx("Image");
        		edit.CheckAllowedRemoteMediaTypesCbx("MPX Player");
        		edit.CheckAllowedURISchemesCbx("public:// (Public files)");
        		edit.CheckAllowedURISchemesCbx("private:// (Private files)");
        		edit.ClickSaveSettingsBtn();
        		overlay.SwitchToActiveFrame();
        		contentParent.VerifyMessageStatus("Saved " + mpxFCPlayerFileFieldTitle + " configuration.");
        		
        		//Step 33
        		String mpxFCPlayerImageFieldTitle = "FCMPXPlayerImage" + random.GetCharacterString(10);
        		contentTypes.EnterAddNewField(mpxFCPlayerImageFieldTitle);
        		contentTypes.SelectFieldType("Image");
        		contentTypes.SelectWidget("Media file selector");
        		contentTypes.ClickSaveBtn();
        		overlay.SwitchToActiveFrame();
        		fieldSettings.ClickPublicFilesRdb();
        		fieldSettings.ClickSaveFieldSettingsBtn();
        		overlay.SwitchToActiveFrame();
        		contentParent.VerifyMessageStatus("Updated field " + mpxFCPlayerImageFieldTitle + " field settings.");
        		
        		//Step 34
        		edit.CheckEnabledBrowserPluginCbx("mpxPlayers");
        		edit.CheckAllowedRemoteMediaTypesCbx("MPX Player");
        		edit.CheckAllowedURISchemesCbx("public:// (Public files)");
        		edit.CheckAllowedURISchemesCbx("private:// (Private files)");
        		edit.ClickSaveSettingsBtn();
        		overlay.SwitchToActiveFrame();
        		contentParent.VerifyMessageStatus("Saved " + mpxFCPlayerImageFieldTitle + " configuration.");
        		overlay.ClickCloseOverlayLnk();
        		
        		//Step 35
        		taxonomy.NavigateSite("Content>>Add content>>" + contentTypeName);
        		overlay.SwitchToActiveFrame();
        		ErrorChecking errorChecking = new ErrorChecking(webDriver);
        		errorChecking.VerifyNoMessageErrorsPresent();
        		
        		//Steps 36 and 37
        		contentParent.VerifyPageContentPresent(Arrays.asList(mpxVideoEntityFieldTitle, mpxVideoFileFieldTitle, mpxVideoImageFieldTitle, mpxVideoFieldCollectionsTitle.toUpperCase(),
        				mpxFCVideoEntityFieldTitle, mpxFCVideoFileFieldTitle, mpxFCVideoImageFieldTitle,
        					mpxPlayerEntityFieldTitle, mpxPlayerFileFieldTitle, mpxPlayerImageFieldTitle,
        						mpxPlayerFieldCollectionsTitle.toUpperCase(), mpxFCPlayerEntityFieldTitle, mpxFCPlayerFileFieldTitle, mpxFCPlayerImageFieldTitle));
        		
        		//Step 38
        		BasicInformation basicInformation = new BasicInformation(webDriver);
        		String title = "NewMPXContent" + random.GetCharacterString(10);
        		basicInformation.EnterTitle(title);
        		basicInformation.SelectCustomField(mpxVideoEntityFieldTitle, "AutomationDefault");
        		basicInformation.ClickCustomBtn(mpxVideoFileFieldTitle);
        		SelectFile selectFile = new SelectFile(webDriver);
        		selectFile.SwitchToSelectFileFrm();
        		selectFile.EnterTitle("AutomationDefault");
        		selectFile.ClickApplyBtn();
        		contentParent.WaitForThrobberNotPresent();
        		selectFile.ClickSearchResultTtl("AutomationDefault");
        		selectFile.ClickSubmitBtn();
        		overlay.SwitchToActiveFrame();
        		basicInformation.ClickCustomBtn(mpxVideoImageFieldTitle);
        		selectFile.SwitchToSelectFileFrm();
        		selectFile.EnterTitle("AutomationDefault");
        		selectFile.ClickApplyBtn();
        		contentParent.WaitForThrobberNotPresent();
        		selectFile.ClickSearchResultTtl("AutomationDefault");
        		selectFile.ClickSubmitBtn();
        		overlay.SwitchToActiveFrame();
        		contentParent.ClickSaveBtn();
        		overlay.switchToDefaultContent(true);
        		contentParent.VerifyMessageStatus(contentTypeName + " " + title + " has been created.");
        		
        		//Step 39 through 43 - N/A as de-ingestion occurs previously
        	}
        	else {
        		Assert.fail("DB TV account must be configured.");
        	}
        
    }
}
