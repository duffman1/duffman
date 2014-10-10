package com.nbcuni.test.publisher.tests.Advertising.MPS;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.ExecutePHPCode;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.MPSConfiguration;
import com.nbcuni.test.publisher.pageobjects.Content.AdditionalInformation;
import com.nbcuni.test.publisher.pageobjects.Content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.Content.SelectFile;
import com.nbcuni.test.publisher.pageobjects.Content.URLPathSettings;
import com.nbcuni.test.publisher.pageobjects.Content.WorkBench;

public class MPSSupportTokens extends ParentTest {
	
    /*************************************************************************************
     * TEST CASE - TC3641
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/20183282020
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "mps"})
    public void MPSSupportTokens_TC3641() throws Exception {
        
        	Reporter.log("STEP 1");
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
            
        	Reporter.log("SETUP");
        	Modules modules = new Modules(webDriver);
        	taxonomy.NavigateSite("Modules");
        	overlay.SwitchToActiveFrame();
        	modules.EnterFilterName("Pixelman");
        	modules.DisableModule("Pixelman");
        	modules.EnterFilterName("MPS");
        	modules.EnableModule("MPS");
        	overlay.ClickCloseOverlayLnk();
        	taxonomy.NavigateSite("Configuration>>Web services>>MPS Configuration");
            overlay.SwitchToActiveFrame();
            MPSConfiguration mpsConfiguration = new MPSConfiguration(webDriver);
            mpsConfiguration.EnterPatternForCategoryField("");
            mpsConfiguration.ClickSaveConfigurationBtn();
            contentParent.VerifyMessageStatus("The configuration options have been saved.");
            overlay.ClickCloseOverlayLnk();
            
        	Reporter.log("STEP 2 - N/A");
        	
        	Reporter.log("STEP 3");
        	contentParent.VerifySourceNotInPage("\"cat\":\"content\"");
        	
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
            SelectFile selectFile = new SelectFile(webDriver);
            selectFile.SelectDefaultCoverImg();
            overlay.SwitchToActiveFrame();
            AdditionalInformation additionalInformation = new AdditionalInformation(webDriver);
            additionalInformation.ClickAdditionalInformationLnk();
            additionalInformation.SelectMovieType("Syndicated");
            additionalInformation.SelectRating("G");
            additionalInformation.SelectPrimaryGenre("Action");
            contentParent.ClickSaveBtn();
            overlay.switchToDefaultContent(true);
            contentParent.VerifyMessageStatus("Movie " + movieTitle + " has been created.");
            WorkBench workBench = new WorkBench(webDriver);
            contentParent.VerifySourceInPage(Arrays.asList("\"cat\":\"content\""));
            
            Reporter.log("STEP 5");
            workBench.ClickWorkBenchTab("Edit Draft");
            overlay.SwitchToActiveFrame();
            URLPathSettings urlPathSettings = new URLPathSettings(webDriver);
            urlPathSettings.ClickURLPathSettingsLnk();
            urlPathSettings.UnCheckGenerateAutomaticURLAliasCbx();
            String baseAlias = random.GetCharacterString(10);
            urlPathSettings.EnterURLAlias(baseAlias);
            contentParent.ClickSaveBtn();
            overlay.switchToDefaultContent(true);
            contentParent.VerifyMessageStatus("Movie " + movieTitle + " has been updated.");
            
            Reporter.log("STEP 6");
            contentParent.VerifySourceInPage(Arrays.asList("\"cat\":\"" + baseAlias + "\""));
            
            Reporter.log("STEP 7");
            workBench.ClickWorkBenchTab("Edit Draft");
            overlay.SwitchToActiveFrame();
            urlPathSettings.ClickURLPathSettingsLnk();
            urlPathSettings.EnterURLAlias(baseAlias + "/mps-testing/2arg/3arg/4arg/5arg/6arg");
            contentParent.ClickSaveBtn();
            overlay.switchToDefaultContent(true);
            contentParent.VerifyMessageStatus("Movie " + movieTitle + " has been updated.");
            
            Reporter.log("STEP 8");
            contentParent.VerifySourceInPage(Arrays.asList("\"cat\":\"" + baseAlias + "|mps-testing|2arg\"}"));
            
            Reporter.log("STEP 9");
            taxonomy.NavigateSite("Configuration>>Web services>>MPS Configuration");
            overlay.SwitchToActiveFrame();
            mpsConfiguration.ClickReplacementPatternsLnk();
            mpsConfiguration.ClickMPSExpanderLnk();
            mpsConfiguration.VerifyMPSCatPropertyLnkPresent();
            mpsConfiguration.EnterPatternForCategoryField("[mps:cat-pattern:?]");
            
            Reporter.log("STEP 10");
            mpsConfiguration.ClickSaveConfigurationBtn();
            contentParent.VerifyMessageStatus("The configuration options have been saved.");
            overlay.ClickCloseOverlayLnk();
            
            Reporter.log("STEP 11");
            contentParent.VerifySourceInPage(Arrays.asList("\"cat\":\"" + baseAlias + "|mps-testing|2arg|3arg|4arg\"}"));
            
            Reporter.log("STEP 12");
            taxonomy.NavigateSite("Home>>Development>>Execute PHP Code");
            overlay.SwitchToActiveFrame();
            ExecutePHPCode executePHPCode = new ExecutePHPCode(webDriver);
            executePHPCode.EnterPHPCode("variable_set('mps_cat_pattern', '');echo _mps_build_cat();echo PHP_EOL;variable_set('mps_cat_pattern', '[mps:cat-pattern:?]');echo _mps_build_cat(array('aliased-path' => 'brand/show/season/episode/cast'));echo PHP_EOL;echo _mps_build_cat(array('aliased-path' => 'brand/thisisareallyreallyreallyreallyreallyreallylongurlXXXXXX/season/episode/cast/name'));echo PHP_EOL;echo _mps_build_cat(array('aliased-path' => 'brand/thisisareallyreallyreallyreallyreallyreallylongurlXXXXXX/season/episode/cast/'));echo PHP_EOL;echo _mps_build_cat(array('aliased-path' => 'brand/show//episode/cast'));echo PHP_EOL;echo _mps_build_cat(array('aliased-path' => 'brand/show/season/episode/'));echo PHP_EOL;echo _mps_build_cat(array('aliased-path' => 'brand/show/season//episode//'));echo PHP_EOL;echo _mps_build_cat(array('aliased-path' => '/show/season//'));echo PHP_EOL;echo _mps_build_cat(array('aliased-path' => '/show///'));echo PHP_EOL;echo _mps_build_cat(array('aliased-path' => '/brand/season//episode'));echo PHP_EOL;variable_set('mps_cat_pattern', '[mps:cat-pattern:2]|[mps:cat-pattern:3]');echo _mps_build_cat(array('aliased-path' => 'brand/show/season/episode/cast'));echo PHP_EOL;variable_set('mps_cat_pattern', '[mps:cat-pattern:0]|[mps:cat-pattern:2]|[current-date:custom:d]|[mps:cat-pattern:3]|');echo _mps_build_cat(array('aliased-path' => 'brand/show/season/episode/cast'));variable_set('mps_cat_pattern', '[mps:cat-pattern:?]');echo PHP_EOL;echo '*' . _mps_build_cat(array('aliased-path' => '/')) . '*';");
            executePHPCode.ClickExecuteBtn();
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            String dayOfWeek = dateFormat.format(date);
            executePHPCode.VerifyResponse("Status message\ndevel\nbrand|show|season|episode\nbrand|thisisareallyreallyreallyreallyreallyreallylongurl|season|episode|cast\nbrand|thisisareallyreallyreallyreallyreallyreallylongurl|season|episode|cast\nbrand|show|~|episode\nbrand|show|season\nbrand|show|season|~|episode\n~|show\n\n~|brand|season\nseason|episode\nbrand|season|" + dayOfWeek + "\n**");
            overlay.ClickCloseOverlayLnk();
            
            Reporter.log("CLEANUP");
            taxonomy.NavigateSite("Configuration>>Web services>>MPS Configuration");
            overlay.SwitchToActiveFrame();
            mpsConfiguration.EnterPatternForCategoryField("");
            mpsConfiguration.ClickSaveConfigurationBtn();
    }
}
