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
import com.nbcuni.test.publisher.pageobjects.Content.SelectFile;
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
            
            Reporter.log("STEP 5");
            String siteType;
            if (config.getConfigValue("AppURL").contains("update")) {
            	siteType = "update";
            }
            else {
            	siteType = "install";
            }
            contentParent.VerifySourceInPage(Arrays.asList("var mpscall = {'site':'" + siteType + "', 'path':'content/" + movieTitle + "'}",
            		"var mpsopts = {'host':'" + config.getConfigValue("AppURL").replace("http://", "") + "'}"));
            
            Reporter.log("STEP 6 and STEP 7");
            contentParent.VerifySourceInPage(Arrays.asList("var mps = mps || {}; mps.pagevars{\"site\":\"" + siteType + "\",\"title\":\"" + movieTitle + "\",\"path\":\"content\\/" + movieTitle + "\",\"qs\":\"\",\"cat\":\"content|" + movieTitle + "\"", 
            		"{\"genre\":\"Action\",\"movie-rating\":\"G\",\"movie-types\":\"Syndicated\"}"));
            
            Reporter.log("STEP 8");
            webDriver.navigate().to(webDriver.getCurrentUrl() + "?kumud=1");
            contentParent.VerifySourceInPage(Arrays.asList("a3VtdWQ9MQ=="));
            
            Reporter.log("STEP 9");
            contentParent.VerifySourceInPage(Arrays.asList("document.write('<scr'+'ipt id=\"mps-ext-load\" src=\"//'+mpsopts.host+'/fetch/ext/load-'+mpscall.site+'.js\"></scr'+'ipt>')"));
            
            Reporter.log("STEP 10");
            contentParent.VerifySourceInPage(Arrays.asList("typeof(mps.writeFooter)=='function' && mps.writeFooter()"));
            
            Reporter.log("STEP 11");
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
            
            Reporter.log("STEP 12");
            taxonomy.NavigateSite("Structure>>Blocks");
            overlay.SwitchToActiveFrame();
            Blocks blocks = new Blocks(webDriver);
            blocks.SelectRegion(blockName, "Header");
            blocks.ClickSaveBlocksBtn();
            contentParent.VerifyMessageStatus("The block settings have been updated.");
            overlay.ClickCloseOverlayLnk();
            
            Reporter.log("STEP 13");
            contentParent.VerifySourceInPage(Arrays.asList("typeof(mps.getAd)=='function' && document.write(mps.getAd('" + uniqueName + "'))"));
            
            Reporter.log("STEP 14 - N/A");
            
            Reporter.log("CLEANUP");
            taxonomy.NavigateSite("Structure>>Blocks");
            overlay.SwitchToActiveFrame();
            blocks.SelectRegion(blockName, "- None -");
            blocks.ClickSaveBlocksBtn();
            overlay.ClickCloseOverlayLnk();
            
    }
}
