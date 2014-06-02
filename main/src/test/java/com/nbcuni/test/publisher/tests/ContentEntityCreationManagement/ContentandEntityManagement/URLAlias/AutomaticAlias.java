package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentandEntityManagement.URLAlias;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.URLAliases;
import com.nbcuni.test.publisher.pageobjects.Content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.Content.URLPathSettings;
import com.nbcuni.test.publisher.pageobjects.Content.WorkBench;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class AutomaticAlias extends ParentTest {
	
    /*************************************************************************************
     * TEST CASE - TC3303
     * Steps - https://rally1.rallydev.com/#/14663927728ud/detail/testcase/19387125936
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void AutomaticAlias_TC3303() throws Exception {
         
        	Reporter.log("STEP 1");
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
            
        	Reporter.log("SETUP");
        	taxonomy.NavigateSite("Configuration>>Search and metadata>>URL aliases>>Settings");
        	overlay.SwitchToActiveFrame();
        	URLAliases urlAliases = new URLAliases(webDriver);
        	urlAliases.ClickUpdateActionRdb("Do nothing. Leave the old alias intact.");
        	urlAliases.ClickSaveConfigurationBtn();
        	contentParent.VerifyMessageStatus("The configuration options have been saved.");
        	overlay.ClickCloseOverlayLnk();
        	
        	Reporter.log("STEP 2");
        	taxonomy.NavigateSite("Content>>Add content>>Post");
            overlay.SwitchToActiveFrame();
            String postTitle = random.GetCharacterString(15);
            BasicInformation basicInformation = new BasicInformation(webDriver);
            basicInformation.EnterTitle(postTitle);
            basicInformation.EnterSynopsis();
            overlay.SwitchToActiveFrame();
            URLPathSettings urlPathSettings = new URLPathSettings(webDriver, applib);
            urlPathSettings.ClickURLPathSettingsLnk();
            urlPathSettings.VerifyGenerateAutomateURLAliasChecked();
            
            Reporter.log("STEP 3");
            contentParent.ClickSaveBtn();
            overlay.switchToDefaultContent();
            contentParent.VerifyMessageStatus("Post " + postTitle + " has been created.");
            
            Reporter.log("STEP 4");
            WorkBench workBench = new WorkBench(webDriver, applib);
            workBench.ClickWorkBenchTab("Edit Draft");
            overlay.SwitchToActiveFrame();
            urlPathSettings.ClickURLPathSettingsLnk();
            urlPathSettings.VerifyURLAlias(postTitle);
            urlPathSettings.VerifyGenerateAutomateURLAliasUnCheckedDisabled();
            
            Reporter.log("STEP 5");
            basicInformation.ClickBasicInformationTab();
            String updatedPostTitle = random.GetCharacterString(15);
            basicInformation.EnterTitle(updatedPostTitle);
            contentParent.ClickSaveBtn();
            overlay.switchToDefaultContent();
            contentParent.VerifyMessageStatus("Post " + updatedPostTitle + " has been updated.");
        	urlPathSettings.VerifyContentNodeAlias(postTitle);
        	
        	Reporter.log("STEP 6");
        	workBench.ClickWorkBenchTab("Edit Draft");
            overlay.SwitchToActiveFrame();
            urlPathSettings.ClickURLPathSettingsLnk();
            urlPathSettings.EnterURLAlias("content/" + updatedPostTitle);
            contentParent.ClickSaveBtn();
            overlay.switchToDefaultContent();
            contentParent.VerifyMessageStatus("Post " + updatedPostTitle + " has been updated.");
            urlPathSettings.VerifyContentNodeAlias(updatedPostTitle);
            
            Reporter.log("STEP 7");
            taxonomy.NavigateSite("Configuration>>Search and metadata>>URL aliases>>Settings");
        	overlay.SwitchToActiveFrame();
        	urlAliases.ClickUpdateActionRdb("Create a new alias. Leave the existing alias functioning.");
        	urlAliases.ClickSaveConfigurationBtn();
        	contentParent.VerifyMessageStatus("The configuration options have been saved.");
        	overlay.ClickCloseOverlayLnk();
        	
        	Reporter.log("STEP 8");
        	workBench.ClickWorkBenchTab("Edit Draft");
            overlay.SwitchToActiveFrame();
            urlPathSettings.ClickURLPathSettingsLnk();
            urlPathSettings.VerifyGenerateAutomateURLAliasCheckedEnabled();
            
            //TODO - Steps 9 and 10 as time allows.
        	
        	
    }
}
