package com.nbcuni.test.publisher.tests.Advertising.MPS;

import java.util.Arrays;
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
     * TEST CASE 
     * steps and rally tc soon to follow
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void ImplementMPS_Test() throws Exception {
        
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
            
        	//Step 2
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
            //modules.EnterFilterName("Overlay");
            //modules.DisableModule("Overlay");
            overlay.ClickCloseOverlayLnk();
            
            //Step 3
            taxonomy.NavigateSite("Reports>>Status report");
            overlay.SwitchToActiveFrame();
            contentParent.VerifyPageContentPresent(Arrays.asList("Ad Tag Style", "MPS"));
            ErrorChecking errorChecking = new ErrorChecking(webDriver, applib);
            errorChecking.VerifyNoMessageErrorsPresent();
            overlay.ClickCloseOverlayLnk();
            
            //Step 4
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
            
            //Step 5 and 6
            //contentParent.VerifySourceInPage(Arrays.asList("var mps = mps || {}; mps.pagevars{\"site\":\"update\",\"title\":\"" + movieTitle + "", "{\"genre\":\"Action\",\"movie-rating\":\"G\",\"movie-types\":\"Syndicated\"},\"field\":[]}"));
            
            //Step 7
            webDriver.navigate().to(webDriver.getCurrentUrl() + "?kumud=1");
            contentParent.VerifySourceInPage(Arrays.asList("a3VtdWQ9MQ=="));
            
            //Step 8
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
            
            //Step 9
            taxonomy.NavigateSite("Structure>>Blocks");
            overlay.SwitchToActiveFrame();
            Blocks blocks = new Blocks(webDriver);
            blocks.SelectRegion(blockName, "Header");
            blocks.ClickSaveBlocksBtn();
            contentParent.VerifyMessageStatus("The block settings have been updated.");
            
            //Step 10 - N/A
            
            //Cleanup
            blocks.SelectRegion(blockName, "- None -");
            blocks.ClickSaveBlocksBtn();
            overlay.ClickCloseOverlayLnk();
            
    }
}
