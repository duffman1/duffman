package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentandEntityManagement.URLAlias;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.URLAliases;
import com.nbcuni.test.publisher.pageobjects.Content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.Content.PublishingOptions;
import com.nbcuni.test.publisher.pageobjects.Content.URLPathSettings;
import com.nbcuni.test.publisher.pageobjects.Content.WorkBench;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class AutomaticAlias extends ParentTest {
	
    /*************************************************************************************
     * TEST CASE - TC3303
     * Steps - https://rally1.rallydev.com/#/14663927728ud/detail/testcase/19387125936
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "certify"})
    public void AutomaticAlias_TC3303() throws Exception {
         
        	Reporter.log("STEP 1");
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
            
        	Reporter.log("SETUP");
        	navigation.Configuration("URL aliases", "Settings");
        	URLAliases urlAliases = new URLAliases(webDriver);
        	urlAliases.ClickUpdateActionRdb("Do nothing. Leave the old alias intact.");
        	urlAliases.ClickSaveConfigurationBtn();
        	contentParent.VerifyMessageStatus("The configuration options have been saved.");
        	
        	Reporter.log("STEP 2");
        	navigation.AddContent("Post");
        	String postTitle = random.GetCharacterString(15);
            BasicInformation basicInformation = new BasicInformation(webDriver);
            basicInformation.EnterTitle(postTitle);
            basicInformation.EnterSynopsis();
            URLPathSettings urlPathSettings = new URLPathSettings(webDriver);
            urlPathSettings.ClickURLPathSettingsLnk();
            urlPathSettings.VerifyGenerateAutomateURLAliasChecked();
            PublishingOptions publishingOptions = new PublishingOptions(webDriver);
            publishingOptions.ClickPublishingOptionsLnk();
            publishingOptions.SelectModerationState("Published");
            
            Reporter.log("STEP 3");
            contentParent.ClickSaveBtn();
            contentParent.VerifyMessageStatus("Post " + postTitle + " has been created.");
            String postURL = webDriver.getCurrentUrl();
            
            Reporter.log("STEP 4");
            WorkBench workBench = new WorkBench(webDriver);
            workBench.ClickWorkBenchTab("Edit Draft");
            urlPathSettings.ClickURLPathSettingsLnk();
            urlPathSettings.VerifyURLAlias(postTitle);
            urlPathSettings.VerifyGenerateAutomateURLAliasUnCheckedDisabled();
            
            Reporter.log("STEP 5");
            basicInformation.ClickBasicInformationTab();
            String updatedPostTitle = random.GetCharacterString(15);
            basicInformation.EnterTitle(updatedPostTitle);
            contentParent.ClickSaveBtn();
            contentParent.VerifyMessageStatus("Post " + updatedPostTitle + " has been updated.");
        	urlPathSettings.VerifyContentNodeAlias(postTitle);
        	
        	Reporter.log("STEP 6");
        	workBench.ClickWorkBenchTab("Edit Draft");
            urlPathSettings.ClickURLPathSettingsLnk();
            urlPathSettings.EnterURLAlias("content/" + updatedPostTitle);
            contentParent.ClickSaveBtn();
            contentParent.VerifyMessageStatus("Post " + updatedPostTitle + " has been updated.");
            urlPathSettings.VerifyContentNodeAlias(updatedPostTitle);
            
            Reporter.log("STEP 7");
            navigation.Configuration("URL aliases", "Settings");
            urlAliases.ClickUpdateActionRdb("Create a new alias. Leave the existing alias functioning.");
        	urlAliases.ClickSaveConfigurationBtn();
        	contentParent.VerifyMessageStatus("The configuration options have been saved.");
        	
        	Reporter.log("STEP 8");
        	applib.openSitePage(postURL);
        	workBench.ClickWorkBenchTab("Edit Draft");
            urlPathSettings.ClickURLPathSettingsLnk();
            urlPathSettings.VerifyGenerateAutomateURLAliasCheckedEnabled();
            
            //TODO - Steps 9 and 10 as time allows.
        	
        	
    }
}
