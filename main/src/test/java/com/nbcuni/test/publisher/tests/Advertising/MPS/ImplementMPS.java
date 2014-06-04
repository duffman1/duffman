package com.nbcuni.test.publisher.tests.Advertising.MPS;

import java.util.Arrays;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Blocks;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
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
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void ImplementMPS_TC2901() throws Exception {
        
        	Reporter.log("STEP 1");
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
            
        	Reporter.log("STEP 2");
        	taxonomy.NavigateSite("Modules");
            overlay.SwitchToActiveFrame();
            Modules modules = new Modules(webDriver, applib);
            modules.EnterFilterName("Pub Ads");
            modules.EnableModule("Pub Ads");
            modules.EnterFilterName("MPS");
            modules.EnableModule("MPS");
            modules.EnterFilterName("DART");
            modules.DisableModule("DART");
            modules.EnterFilterName("Doubleclick for Publishers");
            modules.DisableModule("Doubleclick for Publishers");
            overlay.ClickCloseOverlayLnk();
            
            Reporter.log("STEP 3");
            taxonomy.NavigateSite("Reports>>Status report");
            overlay.SwitchToActiveFrame();
            contentParent.VerifyPageContentPresent(Arrays.asList("Ad Tag Style", "MPS"));
            ErrorChecking errorChecking = new ErrorChecking(webDriver, applib);
            errorChecking.VerifyNoMessageErrorsPresent();
            overlay.ClickCloseOverlayLnk();
            
            Reporter.log("STEP 4");
            taxonomy.NavigateSite("Content>>Add content>>Movie");
            overlay.SwitchToActiveFrame();
            BasicInformation basicInformation = new BasicInformation(webDriver);
            basicInformation.ClickBasicInformationTab();
            String movieTitle = random.GetCharacterString(15);
            basicInformation.EnterTitle(movieTitle);
            basicInformation.EnterSynopsis();
            overlay.SwitchToActiveFrame();
            basicInformation.ClickCoverSelectBtn();
            SelectFile selectFile = new SelectFile(webDriver, applib);
            selectFile.SelectDefaultCoverImg();
            overlay.SwitchToActiveFrame();
            AdditionalInformation additionalInformation = new AdditionalInformation(webDriver);
            additionalInformation.ClickAdditionalInformationLnk();
            additionalInformation.SelectMovieType("Syndicated");
            additionalInformation.SelectRating("G");
            additionalInformation.SelectPrimaryGenre("Action");
            contentParent.ClickSaveBtn();
            overlay.switchToDefaultContent();
            contentParent.VerifyMessageStatus("Movie " + movieTitle + " has been created.");
            WorkBench workBench = new WorkBench(webDriver, applib);
            String movieNodeNumber = workBench.GetContentNodeNumber();
            
            Reporter.log("STEP 5");
            contentParent.VerifySourceInPage(Arrays.asList("var mpscall = {\"site\":\"nbc-tonightshow\",\"title\":\"" + movieTitle + "\",\"path\":\"\\/node\\/" + movieNodeNumber,
            		"var mpsopts = {'host':'mps.nbcuni.com'}"));
            
            Reporter.log("STEP 6 through STEP 14");
            contentParent.VerifySourceInPage(Arrays.asList("{\"site\":\"nbc-tonightshow\",\"title\":\"" + movieTitle + "\",\"path\":\"\\/node\\/" + movieNodeNumber + "","\"qs\":\"\",\"cat\":\"content|" + movieTitle + "\",\"content_id\":\"node","\"is_content\":1,\"type\":\"movie\",\"cag\":{\"genre\":\"Action\",\"movie-rating\":\"G\",\"movie-types\":\"Syndicated\"},\"envelope\":\"\",\"pubdate\":"));
            
            Reporter.log("STEP 15");
            webDriver.navigate().to(webDriver.getCurrentUrl() + "?kumud=1");
            contentParent.VerifySourceInPage(Arrays.asList("a3VtdWQ9MQ=="));
            
            Reporter.log("STEP 16");
            taxonomy.NavigateSite("Content>>Add content>>Media Gallery");
        	overlay.SwitchToActiveFrame();
        	String mediaGalleryTitle = random.GetCharacterString(15);
            basicInformation.EnterTitle(mediaGalleryTitle);
            basicInformation.ClickMediaItemsSelectBtn();
            selectFile.SwitchToSelectFileFrm();
            selectFile.ClickViewLibraryBtn();
            selectFile.EnterFileName("HanSolo");
            selectFile.WaitForFileSearchComplete();
            selectFile.ClickApplyBtn();
            selectFile.WaitForFileSearchComplete();
            selectFile.VerifyMediaThumbnailImagePresent("HanSolo", "1");
            selectFile.ClickMediaThumbnailImage("1");
            selectFile.ClickSubmitBtn();
            overlay.SwitchToActiveFrame();
            MediaItems mediaItems = new MediaItems(webDriver);
            mediaItems.VerifyFileImagePresent("HanSolo", "1");
            ContentParent contentParent = new ContentParent(webDriver, applib);
            contentParent.ClickSaveBtn();
            overlay.switchToDefaultContent();
            contentParent.VerifyMessageStatus("Media Gallery " + mediaGalleryTitle + " has been created.");
        	String mediaGalleryNodeNumber = workBench.GetContentNodeNumber();
            String imageFileNumber = workBench.GetFileImageId("1");
            
            Reporter.log("STEP 17");
            applib.openSitePage("/node/" + mediaGalleryNodeNumber + "/" + imageFileNumber);
            
            Reporter.log("STEP 18 through 20");
            //contentParent.VerifySourceInPage(Arrays.asList("\"site\":\"nbc-tonightshow\",\"title\":\"" + mediaGalleryTitle + "\",\"path\":\"node\\/" + mediaGalleryNodeNumber + "\\/" + imageFileNumber + "\",\"qs\":\"\",\"cat\":\"node|" + mediaGalleryNodeNumber + "|" + imageFileNumber + "\",\"content_id\":\"node" + mediaGalleryNodeNumber + "\",\"is_content\":1,\"type\":\"media_gallery\",\"cag\":\"\",\"envelope\":\"\",\"pubdate\":"));
            
            //TODO - a few extra steps as time allows
            
            Reporter.log("STEP 23");
            //contentParent.VerifySourceInPage(Arrays.asList("id=\"mps-ext-load\" src=\"//'+mpsopts.host+'/fetch/ext/load-'+mpscall.site+'.js"));
            
            Reporter.log("STEP 24");
            contentParent.VerifySourceInPage(Arrays.asList("typeof(mps.writeFooter)"));
            
            Reporter.log("STEP 25");
            taxonomy.NavigateSite("Structure>>MPS Blocks>>Add");
            overlay.SwitchToActiveFrame();
            MPSBlocks mpsBlocks = new MPSBlocks(webDriver);
            String uniqueName = random.GetCharacterString(15);
            String blockName = random.GetCharacterString(15);
            mpsBlocks.EnterUniqueName(uniqueName);
            mpsBlocks.EnterBlockName(blockName);
            mpsBlocks.ClickSaveBtn();
            contentParent.VerifyMessageStatus(uniqueName + " has been created.");
            overlay.ClickCloseOverlayLnk();
            
            Reporter.log("STEP 26");
            taxonomy.NavigateSite("Structure>>Blocks");
            overlay.SwitchToActiveFrame();
            Blocks blocks = new Blocks(webDriver);
            blocks.SelectRegion(blockName, "Header");
            blocks.ClickSaveBlocksBtn();
            contentParent.VerifyMessageStatus("The block settings have been updated.");
            overlay.ClickCloseOverlayLnk();
            
            Reporter.log("STEP 27");
            contentParent.VerifySourceInPage(Arrays.asList("mps.getAd('" + blockName + "')"));
            
            Reporter.log("STEP 28 - N/A");
            
            Reporter.log("CLEANUP");
            taxonomy.NavigateSite("Structure>>Blocks");
            overlay.SwitchToActiveFrame();
            blocks.SelectRegion(blockName, "- None -");
            blocks.ClickSaveBlocksBtn();
            overlay.ClickCloseOverlayLnk();
            
    }
}
