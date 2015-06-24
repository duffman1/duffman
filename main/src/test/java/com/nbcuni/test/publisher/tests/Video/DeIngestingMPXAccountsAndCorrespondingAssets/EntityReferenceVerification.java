package com.nbcuni.test.publisher.tests.Video.DeIngestingMPXAccountsAndCorrespondingAssets;

import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.Content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.Content.SelectFile;
import com.nbcuni.test.publisher.pageobjects.ErrorChecking.ErrorChecking;
import com.nbcuni.test.publisher.pageobjects.MPX.Settings;
import com.nbcuni.test.publisher.pageobjects.Structure.ContentTypes;
import com.nbcuni.test.publisher.pageobjects.Structure.FieldCollections;
import com.nbcuni.test.publisher.pageobjects.Structure.ManageFields.Edit;
import com.nbcuni.test.publisher.pageobjects.Structure.ManageFields.FieldSettings;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

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
    	
    	navigation.Configuration("Media: thePlatform mpx settings");
        List<String> configuredAccounts = settings.GetImportAccountSelectedOptions();

        //Setup
        if (configuredAccounts.contains("DB TV")) {
        		
        	//Step 1
        	navigation.Structure("Content types");
        	ContentTypes contentTypes = new ContentTypes(webDriver);
        	contentTypes.ClickAddContentLnk();
            String contentTypeName = "MPX" + random.GetCharacterString(10);
            contentTypes.EnterName(contentTypeName);
            contentTypes.ClickSaveAddFieldsBtn();
        		
        	//Step 2
        	String mpxVideoEntityFieldTitle = "MPXVideoEntity" + random.GetCharacterString(10);
        	contentTypes.EnterAddNewField(mpxVideoEntityFieldTitle);
        	contentTypes.SelectFieldType("Entity Reference");
        	contentTypes.SelectWidget("Select list");
        	contentTypes.ClickSaveBtn();
        		
        	//Step 3
        	FieldSettings fieldSettings = new FieldSettings(webDriver);
        	fieldSettings.SelectTargetType("File");
        	fieldSettings.SelectMode("Simple (with optional filter by bundle)");
        	fieldSettings.CheckTargetBundleCbx("MPX Video for Account \"DB TV\" (2312945284) ");
        	fieldSettings.SelectSortBy("Don't sort");
        	fieldSettings.ClickSaveFieldSettingsBtn();
        	contentParent.VerifyMessageStatus("Updated field " + mpxVideoEntityFieldTitle + " field settings.");
        		
        	//Step 4
        		Edit edit = new Edit(webDriver, applib);
        		edit.ClickSaveSettingsBtn();
        		contentParent.VerifyMessageStatus("Saved " + mpxVideoEntityFieldTitle + " configuration.");
        		
        		//Step 5
        		String mpxVideoFileFieldTitle = "MPXVideoFile" + random.GetCharacterString(10);
        		contentTypes.EnterAddNewField(mpxVideoFileFieldTitle);
        		contentTypes.SelectFieldType("File");
        		contentTypes.SelectWidget("Media browser");
        		contentTypes.ClickSaveBtn();
        		fieldSettings.ClickPublicFilesRdb();
        		fieldSettings.ClickSaveFieldSettingsBtn();
        		contentParent.VerifyMessageStatus("Updated field " + mpxVideoFileFieldTitle + " field settings.");
        		
        		//Step 6
        		edit.CheckEnabledBrowserPluginCbx("Publisher7 MPX Video");
        		edit.CheckAllowedRemoteMediaTypesCbx("Image");
        		edit.CheckAllowedRemoteMediaTypesCbx("MPX Video for Account \"DB TV\" (2312945284)");
        		edit.CheckAllowedURISchemesCbx("mpx:// (mpx videos)");
        		edit.CheckAllowedURISchemesCbx("public:// (Public files)");
        		edit.CheckAllowedURISchemesCbx("private:// (Private files)");
        		edit.ClickSaveSettingsBtn();
        		contentParent.VerifyMessageStatus("Saved " + mpxVideoFileFieldTitle + " configuration.");
        		
        		//Step 7
        		String mpxVideoImageFieldTitle = "MPXVideoImage" + random.GetCharacterString(10);
        		contentTypes.EnterAddNewField(mpxVideoImageFieldTitle);
        		contentTypes.SelectFieldType("Image");
        		contentTypes.SelectWidget("Media browser");
        		contentTypes.ClickSaveBtn();
        		fieldSettings.ClickPublicFilesRdb();
        		fieldSettings.ClickSaveFieldSettingsBtn();
        		contentParent.VerifyMessageStatus("Updated field " + mpxVideoImageFieldTitle + " field settings.");
        		
        		//Step 8
        		edit.CheckEnabledBrowserPluginCbx("Publisher7 MPX Video");
        		edit.CheckAllowedRemoteMediaTypesCbx("MPX Video for Account \"DB TV\" (2312945284)");
        		edit.CheckAllowedURISchemesCbx("mpx:// (mpx videos)");
        		edit.CheckAllowedURISchemesCbx("public:// (Public files)");
        		edit.CheckAllowedURISchemesCbx("private:// (Private files)");
        		edit.ClickSaveSettingsBtn();
        		contentParent.VerifyMessageStatus("Saved " + mpxVideoImageFieldTitle + " configuration.");
        		
        		//Step 9
        		String mpxVideoFieldCollectionsTitle = "MPXVideoFC" + random.GetCharacterString(10);
        		contentTypes.EnterAddNewField(mpxVideoFieldCollectionsTitle);
        		contentTypes.SelectFieldType("Field collection");
        		contentTypes.SelectWidget("Embedded");
        		contentTypes.ClickSaveBtn();
        		fieldSettings.ClickSaveFieldSettingsBtn();
        		contentParent.VerifyMessageStatus("Updated field " + mpxVideoFieldCollectionsTitle + " field settings.");
        		edit.ClickSaveSettingsBtn();
        		contentParent.VerifyMessageStatus("Saved " + mpxVideoFieldCollectionsTitle + " configuration.");
        		
        		//Step 10
        		navigation.Structure("Field collections");
        		FieldCollections fieldCollections = new FieldCollections(webDriver, applib);
        		fieldCollections.ClickManageFieldsLnk(mpxVideoFieldCollectionsTitle.toLowerCase());
        		
        		//Step 11
        		String mpxFCVideoEntityFieldTitle = "FCMPXVideoEntity" + random.GetCharacterString(10);
        		contentTypes.EnterAddNewField(mpxFCVideoEntityFieldTitle);
        		contentTypes.SelectFieldType("Entity Reference");
        		contentTypes.SelectWidget("Select list");
        		contentTypes.ClickSaveBtn();
        		
        		//Step 12
        		fieldSettings.SelectTargetType("File");
        		fieldSettings.SelectMode("Simple (with optional filter by bundle)");
        		fieldSettings.CheckTargetBundleCbx("MPX Video for Account \"DB TV\" (2312945284) ");
        		fieldSettings.SelectSortBy("Don't sort");
        		fieldSettings.ClickSaveFieldSettingsBtn();
        		contentParent.VerifyMessageStatus("Updated field " + mpxFCVideoEntityFieldTitle + " field settings.");
        		
        		//Step 13
        		edit.ClickSaveSettingsBtn();
        		contentParent.VerifyMessageStatus("Saved " + mpxFCVideoEntityFieldTitle + " configuration.");
        		
        		//Step 14
        		String mpxFCVideoFileFieldTitle = "FCMPXVideoFile" + random.GetCharacterString(10);
        		contentTypes.EnterAddNewField(mpxFCVideoFileFieldTitle);
        		contentTypes.SelectFieldType("File");
        		contentTypes.SelectWidget("Media browser");
        		contentTypes.ClickSaveBtn();
        		fieldSettings.ClickPublicFilesRdb();
        		fieldSettings.ClickSaveFieldSettingsBtn();
        		contentParent.VerifyMessageStatus("Updated field " + mpxFCVideoFileFieldTitle + " field settings.");
        		
        		//Step 15
        		edit.CheckEnabledBrowserPluginCbx("Publisher7 MPX Video");
        		edit.CheckAllowedRemoteMediaTypesCbx("Image");
        		edit.CheckAllowedRemoteMediaTypesCbx("MPX Video for Account \"DB TV\" (2312945284)");
        		edit.CheckAllowedURISchemesCbx("mpx:// (mpx videos)");
        		edit.CheckAllowedURISchemesCbx("public:// (Public files)");
        		edit.CheckAllowedURISchemesCbx("private:// (Private files)");
        		edit.ClickSaveSettingsBtn();
        		contentParent.VerifyMessageStatus("Saved " + mpxFCVideoFileFieldTitle + " configuration.");
        		
        		//Step 16
        		String mpxFCVideoImageFieldTitle = "FCMPXVideoImage" + random.GetCharacterString(10);
        		contentTypes.EnterAddNewField(mpxFCVideoImageFieldTitle);
        		contentTypes.SelectFieldType("Image");
        		contentTypes.SelectWidget("Media browser");
        		contentTypes.ClickSaveBtn();
        		fieldSettings.ClickPublicFilesRdb();
        		fieldSettings.ClickSaveFieldSettingsBtn();
        		contentParent.VerifyMessageStatus("Updated field " + mpxFCVideoImageFieldTitle + " field settings.");
        		
        		//Step 17
        		edit.CheckEnabledBrowserPluginCbx("Publisher7 MPX Video");
        		edit.CheckAllowedRemoteMediaTypesCbx("MPX Video for Account \"DB TV\" (2312945284)");
        		edit.CheckAllowedURISchemesCbx("mpx:// (mpx videos)");
        		edit.CheckAllowedURISchemesCbx("public:// (Public files)");
        		edit.CheckAllowedURISchemesCbx("private:// (Private files)");
        		edit.ClickSaveSettingsBtn();
        		contentParent.VerifyMessageStatus("Saved " + mpxFCVideoImageFieldTitle + " configuration.");
        		
        		//Step 18
        		contentTypes.ClickSaveBtn();
        		contentParent.VerifyMessageStatus("Your settings have been saved.");
        		
        		//Step 19
        		navigation.Structure("Content types");
        		contentTypes.ClickManageFieldLnk(contentTypeName);
        		
        		//Step 20
        		String mpxPlayerEntityFieldTitle = "MPXPlayerEntity" + random.GetCharacterString(10);
        		contentTypes.EnterAddNewField(mpxPlayerEntityFieldTitle);
        		contentTypes.SelectFieldType("Entity Reference");
        		contentTypes.SelectWidget("Select list");
        		contentTypes.ClickSaveBtn();
        		
        		//Step 21
        		fieldSettings.SelectTargetType("File");
        		fieldSettings.SelectMode("Simple (with optional filter by bundle)");
        		fieldSettings.CheckTargetBundleCbx("MPX Player");
        		fieldSettings.SelectSortBy("Don't sort");
        		fieldSettings.ClickSaveFieldSettingsBtn();
        		contentParent.VerifyMessageStatus("Updated field " + mpxPlayerEntityFieldTitle + " field settings.");
        		
        		//Step 22
        		edit.ClickSaveSettingsBtn();
        		contentParent.VerifyMessageStatus("Saved " + mpxPlayerEntityFieldTitle + " configuration.");
        		
        		//Step 23
        		String mpxPlayerFileFieldTitle = "MPXPlayerFile" + random.GetCharacterString(10);
        		contentTypes.EnterAddNewField(mpxPlayerFileFieldTitle);
        		contentTypes.SelectFieldType("File");
        		contentTypes.SelectWidget("Media browser");
        		contentTypes.ClickSaveBtn();
        		fieldSettings.ClickPublicFilesRdb();
        		fieldSettings.ClickSaveFieldSettingsBtn();
        		contentParent.VerifyMessageStatus("Updated field " + mpxPlayerFileFieldTitle + " field settings.");
        		
        		//Step 24
        		edit.CheckEnabledBrowserPluginCbx("mpxPlayers");
        		edit.CheckAllowedRemoteMediaTypesCbx("MPX Player");
        		edit.CheckAllowedURISchemesCbx("public:// (Public files)");
        		edit.CheckAllowedURISchemesCbx("private:// (Private files)");
        		edit.ClickSaveSettingsBtn();
        		contentParent.VerifyMessageStatus("Saved " + mpxPlayerFileFieldTitle + " configuration.");
        		
        		//Step 25
        		String mpxPlayerImageFieldTitle = "MPXPlayerImage" + random.GetCharacterString(10);
        		contentTypes.EnterAddNewField(mpxPlayerImageFieldTitle);
        		contentTypes.SelectFieldType("Image");
        		contentTypes.SelectWidget("Media browser");
        		contentTypes.ClickSaveBtn();
        		fieldSettings.ClickPublicFilesRdb();
        		fieldSettings.ClickSaveFieldSettingsBtn();
        		contentParent.VerifyMessageStatus("Updated field " + mpxPlayerImageFieldTitle + " field settings.");
        		
        		//Step 26
        		edit.CheckEnabledBrowserPluginCbx("mpxPlayers");
        		edit.CheckAllowedRemoteMediaTypesCbx("MPX Player");
        		edit.CheckAllowedURISchemesCbx("public:// (Public files)");
        		edit.CheckAllowedURISchemesCbx("private:// (Private files)");
        		edit.ClickSaveSettingsBtn();
        		contentParent.VerifyMessageStatus("Saved " + mpxPlayerImageFieldTitle + " configuration.");
        		
        		//Step 27
        		String mpxPlayerFieldCollectionsTitle = "MPXPlayerFC" + random.GetCharacterString(10);
        		contentTypes.EnterAddNewField(mpxPlayerFieldCollectionsTitle);
        		contentTypes.SelectFieldType("Field collection");
        		contentTypes.SelectWidget("Embedded");
        		contentTypes.ClickSaveBtn();
        		fieldSettings.ClickSaveFieldSettingsBtn();
        		contentParent.VerifyMessageStatus("Updated field " + mpxPlayerFieldCollectionsTitle + " field settings.");
        		edit.ClickSaveSettingsBtn();
        		contentParent.VerifyMessageStatus("Saved " + mpxPlayerFieldCollectionsTitle + " configuration.");
        		
        		//Step 28
        		navigation.Structure("Field collections");
        		fieldCollections.ClickManageFieldsLnk(mpxPlayerFieldCollectionsTitle.toLowerCase());
        		
        		//Step 29
        		String mpxFCPlayerEntityFieldTitle = "FCMPXPlayerEntity" + random.GetCharacterString(10);
        		contentTypes.EnterAddNewField(mpxFCPlayerEntityFieldTitle);
        		contentTypes.SelectFieldType("Entity Reference");
        		contentTypes.SelectWidget("Select list");
        		contentTypes.ClickSaveBtn();
        		
        		//Step 30
        		fieldSettings.SelectTargetType("File");
        		fieldSettings.SelectMode("Simple (with optional filter by bundle)");
        		fieldSettings.CheckTargetBundleCbx("MPX Player");
        		fieldSettings.SelectSortBy("Don't sort");
        		fieldSettings.ClickSaveFieldSettingsBtn();
        		contentParent.VerifyMessageStatus("Updated field " + mpxFCPlayerEntityFieldTitle + " field settings.");
        		edit.ClickSaveSettingsBtn();
        		contentParent.VerifyMessageStatus("Saved " + mpxFCPlayerEntityFieldTitle + " configuration.");
        		
        		//Step 31
        		String mpxFCPlayerFileFieldTitle = "FCMPXPlayerFile" + random.GetCharacterString(10);
        		contentTypes.EnterAddNewField(mpxFCPlayerFileFieldTitle);
        		contentTypes.SelectFieldType("File");
        		contentTypes.SelectWidget("Media browser");
        		contentTypes.ClickSaveBtn();
        		fieldSettings.ClickPublicFilesRdb();
        		fieldSettings.ClickSaveFieldSettingsBtn();
        		contentParent.VerifyMessageStatus("Updated field " + mpxFCPlayerFileFieldTitle + " field settings.");
        		
        		//Step 32
        		edit.CheckEnabledBrowserPluginCbx("mpxPlayers");
        		edit.CheckAllowedRemoteMediaTypesCbx("Image");
        		edit.CheckAllowedRemoteMediaTypesCbx("MPX Player");
        		edit.CheckAllowedURISchemesCbx("public:// (Public files)");
        		edit.CheckAllowedURISchemesCbx("private:// (Private files)");
        		edit.ClickSaveSettingsBtn();
        		contentParent.VerifyMessageStatus("Saved " + mpxFCPlayerFileFieldTitle + " configuration.");
        		
        		//Step 33
        		String mpxFCPlayerImageFieldTitle = "FCMPXPlayerImage" + random.GetCharacterString(10);
        		contentTypes.EnterAddNewField(mpxFCPlayerImageFieldTitle);
        		contentTypes.SelectFieldType("Image");
        		contentTypes.SelectWidget("Media browser");
        		contentTypes.ClickSaveBtn();
        		fieldSettings.ClickPublicFilesRdb();
        		fieldSettings.ClickSaveFieldSettingsBtn();
        		contentParent.VerifyMessageStatus("Updated field " + mpxFCPlayerImageFieldTitle + " field settings.");
        		
        		//Step 34
        		edit.CheckEnabledBrowserPluginCbx("mpxPlayers");
        		edit.CheckAllowedRemoteMediaTypesCbx("MPX Player");
        		edit.CheckAllowedURISchemesCbx("public:// (Public files)");
        		edit.CheckAllowedURISchemesCbx("private:// (Private files)");
        		edit.ClickSaveSettingsBtn();
        		contentParent.VerifyMessageStatus("Saved " + mpxFCPlayerImageFieldTitle + " configuration.");
        		
        		//Step 35
        		navigation.AddContent(contentTypeName);
        		ErrorChecking errorChecking = new ErrorChecking(webDriver);
        		errorChecking.VerifyNoMessageErrorsPresent();
        		
        		//Steps 36 and 37
        		contentParent.VerifyPageContentPresent(Arrays.asList(mpxVideoEntityFieldTitle, mpxVideoFileFieldTitle, mpxVideoImageFieldTitle, mpxVideoFieldCollectionsTitle,
        				mpxFCVideoEntityFieldTitle, mpxFCVideoFileFieldTitle, mpxFCVideoImageFieldTitle,
        					mpxPlayerEntityFieldTitle, mpxPlayerFileFieldTitle, mpxPlayerImageFieldTitle,
        						mpxPlayerFieldCollectionsTitle, mpxFCPlayerEntityFieldTitle, mpxFCPlayerFileFieldTitle, mpxFCPlayerImageFieldTitle));
        		
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
        		selectFile.WaitForSelectFileFrameClose();
        		basicInformation.ClickCustomBtn(mpxVideoImageFieldTitle);
        		selectFile.SwitchToSelectFileFrm();
        		selectFile.EnterTitle("AutomationDefault");
        		selectFile.ClickApplyBtn();
        		contentParent.WaitForThrobberNotPresent();
        		selectFile.ClickSearchResultTtl("AutomationDefault");
        		selectFile.ClickSubmitBtn();
        		selectFile.WaitForSelectFileFrameClose();
        		contentParent.ClickSaveBtn();
        		contentParent.VerifyMessageStatus(contentTypeName + " " + title + " has been created.");
        		
        		//Step 39 through 43 - N/A as de-ingestion occurs previously
        	}
        	else {
        		Assert.fail("DB TV account must be configured.");
        	}
        
    }
}
