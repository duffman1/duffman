package com.nbcuni.test.publisher.tests.Advertising.MPS;

import java.util.Arrays;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Blocks;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.MPSConfiguration;
import com.nbcuni.test.publisher.pageobjects.Content.AdditionalInformation;
import com.nbcuni.test.publisher.pageobjects.Content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.Content.MediaItems;
import com.nbcuni.test.publisher.pageobjects.Content.SelectFile;
import com.nbcuni.test.publisher.pageobjects.Content.WorkBench;
import com.nbcuni.test.publisher.pageobjects.ErrorChecking.ErrorChecking;
import com.nbcuni.test.publisher.pageobjects.Structure.MPSBlocks;

public class ImplementMPS extends ParentTest {
	
    /*************************************************************************************
     * TEST CASE - TC2901
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/18554111347
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "mps"})
    public void ImplementMPS_TC2901() throws Exception {
        
        	Reporter.log("STEP 1");
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
            
        	Reporter.log("SETUP");
        	Modules modules = new Modules(webDriver);
        	navigation.Modules();
            modules.EnableModule("Pub Ads");
            modules.DisableModule("Pixelman");
            modules.EnableModule("MPS");
            modules.DisableModule("DART");
            modules.DisableModule("Doubleclick for Publishers");
            navigation.Configuration("MPS Configuration");
            MPSConfiguration mpsConfiguration = new MPSConfiguration(webDriver);
            mpsConfiguration.EnterMPSHost("mps.io");
            mpsConfiguration.ClickIntegrationMethod("Document Write");
            mpsConfiguration.EnterSiteInstanceOverride("pub7-development");
            mpsConfiguration.CheckSendQueryStringsCbx();
            mpsConfiguration.CleanAllMPSOptions();
            mpsConfiguration.ClickSaveConfigurationBtn();
            contentParent.VerifyMessageStatus("The configuration options have been saved.");
            
        	Reporter.log("STEP 2 - N/A");
        	
        	Reporter.log("STEP 3 - N/A");
        	
            Reporter.log("STEP 4");
            navigation.Reports("Status report");
            contentParent.VerifyPageContentPresent(Arrays.asList("Ad Tag Style", "MPS"));
            ErrorChecking errorChecking = new ErrorChecking(webDriver);
            errorChecking.VerifyNoMessageErrorsPresent();
            
            Reporter.log("STEP 5");
            navigation.Home();
            mpsConfiguration.VerifyMPSCallParameters(Arrays.asList("\"site\":\"pub7-development\"", "\"title\":\"Welcome to", "\"path\":\"\\/\"", "\"is_content\":0", "\"type\":\"other\""));
            
            Reporter.log("STEP 6");
            navigation.People();
            mpsConfiguration.VerifyNoMPSCallsMade();
            
            Reporter.log("STEP 7");
            applib.openSitePage("/kfkjdjdkjdjldkjj");
            mpsConfiguration.VerifyMPSCallParameters(Arrays.asList("\"site\":\"pub7-development\"", 
            		"\"path\":\"ERROR/404\"", "\"content_id\":\"ERROR\""));
            applib.openApplication();
            
            Reporter.log("STEP 8 - N/A - COVERED IN SETUP");
            
            Reporter.log("STEP 9");
            navigation.AddContent("Movie");
            BasicInformation basicInformation = new BasicInformation(webDriver);
            basicInformation.ClickBasicInformationTab();
            String movieTitle = random.GetCharacterString(15);
            basicInformation.EnterTitle(movieTitle);
            basicInformation.EnterSynopsis();
            basicInformation.ClickCoverSelectBtn();
            SelectFile selectFile = new SelectFile(webDriver);
            selectFile.SelectDefaultCoverImg();
            AdditionalInformation additionalInformation = new AdditionalInformation(webDriver);
            additionalInformation.ClickAdditionalInformationLnk();
            additionalInformation.SelectMovieType("Syndicated");
            additionalInformation.SelectRating("G");
            additionalInformation.SelectPrimaryGenre("Action");
            contentParent.ClickSaveBtn();
            contentParent.VerifyMessageStatus("Movie " + movieTitle + " has been created.");
            WorkBench workBench = new WorkBench(webDriver);
            String movieNodeNumber = workBench.GetContentNodeNumber();
            
            Reporter.log("STEP 10 through STEP 19");
            mpsConfiguration.VerifyMPSCallParameters(Arrays.asList("\"site\":\"pub7-development\"", "\"title\":\"" + movieTitle + "\"", "\"path\":\"\\/node\\/" + movieNodeNumber + "\"", "\"content_id\":\"node" + movieNodeNumber + "\"", "\"is_content\":1", "\"type\":\"movie\"", "\"cag\":{\"genre\":\"Action\",\"movie-rating\":\"G\",\"movie-types\":\"Syndicated\"}", "\"pubdate\":"));
            
            Reporter.log("STEP 20");
            webDriver.navigate().to(webDriver.getCurrentUrl() + "?kumud=1");
            //contentParent.VerifySourceInPage(Arrays.asList("a3VtdWQ9MQ==")); TODO debug
            
            Reporter.log("STEP 21");
            navigation.AddContent("Media Gallery");
            String mediaGalleryTitle = random.GetCharacterString(15);
            basicInformation.EnterTitle(mediaGalleryTitle);
            basicInformation.ClickMediaItemsSelectBtn();
            selectFile.SwitchToSelectFileFrm();
            selectFile.ClickViewLibraryBtn();
            selectFile.EnterFileName("HanSolo");
            contentParent.WaitForThrobberNotPresent();
            selectFile.ClickApplyBtn();
            contentParent.WaitForThrobberNotPresent();
            selectFile.VerifyMediaThumbnailImagePresent("HanSolo", "1");
            selectFile.ClickMediaThumbnailImage("1");
            selectFile.ClickSubmitBtn();
            webDriver.switchTo().defaultContent();
            MediaItems mediaItems = new MediaItems(webDriver);
            mediaItems.VerifyFileImagePresent("HanSolo", "1");
            ContentParent contentParent = new ContentParent(webDriver);
            contentParent.ClickSaveBtn();
            contentParent.VerifyMessageStatus("Media Gallery " + mediaGalleryTitle + " has been created.");
        	String mediaGalleryNodeNumber = workBench.GetContentNodeNumber();
            String imageFileNumber = workBench.GetFileImageId("1");
            
            Reporter.log("STEP 22");
            applib.openSitePage("/node/" + mediaGalleryNodeNumber + "/" + imageFileNumber);
            
            //TODO - a few extra steps as time allows
            
            Reporter.log("STEP 28");
            contentParent.VerifySourceInPage(Arrays.asList("typeof(mps.writeFooter)"));
            
            Reporter.log("STEP 29");
            navigation.Structure("MPS Blocks");
            MPSBlocks mpsBlocks = new MPSBlocks(webDriver);
            mpsBlocks.ClickAddLnk();
            String blockName = random.GetCharacterString(15);
            mpsBlocks.EnterBlockName(blockName);
            mpsBlocks.ClickSaveBtn();
            contentParent.VerifyMessageStatus(blockName.toLowerCase() + " has been created.");
            
            Reporter.log("STEP 30");
            navigation.Structure("Blocks");
            Blocks blocks = new Blocks(webDriver);
            blocks.SelectRegion(blockName + " (MPS)", "Header");
            blocks.ClickSaveBlocksBtn();
            contentParent.VerifyMessageStatus("The block settings have been updated.");
            
            Reporter.log("STEP 31");
            navigation.Home();
            contentParent.VerifySourceInPage(Arrays.asList("mps.getAd('" + blockName.toLowerCase() + "')"));
            
            Reporter.log("STEP 32 - N/A");
            
            Reporter.log("CLEANUP");
            navigation.Structure("Blocks");
            blocks.SelectRegion(blockName + " (MPS)", "- None -");
            blocks.ClickSaveBlocksBtn();
            
    }
}
