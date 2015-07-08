package com.nbcuni.test.publisher.tests.Video.Configuration;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import org.testng.annotations.Test;

public class ConfigurationMultipleMPXAccounts extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE - TC1073
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/17442647774
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "mpx", "broken"})
    public void ConfigurationMultipleMPXAccounts_TC1073() throws Exception {
    	/*DEPRECATED AND REPLACED BY NEW MPX UPLOAD PROCESS
    	//Step 1
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        
        //Step 2
    	navigation.Configuration("Media: thePlatform mpx");
        
        //Setup
        Settings settings = new Settings(webDriver);
        MPXMedia mpxMedia = new MPXMedia(webDriver);
        Cron cron = new Cron(webDriver);
        
        List<String> accountNames = Arrays.asList("DB TV", "NBC.com Stage", "NBC.com Stage - Content Testing", 
        		"NBCU TVE Dev - NBC", "NBCU TVE Dev - Style", "NBCU TVE Stage - Golf Channel", 
        		"NBCU TVE Stage - Syfy");
        
        if (settings.IsMPXConfigured() == true) {
        	
        	settings.VerifyImportAccountOptions(accountNames);
        	
        	Integer numberSelectedAccounts = settings.GetImportAccountSelectedOptions().size();
        	if (!settings.GetImportAccountSelectedOptions().contains("DB TV")) {
        		settings.ExpandAddAccounts();
            	settings.EnterUsername(config.getConfigValueString("MPXUsername"));
            	settings.EnterPassword(config.getConfigValueString("MPXPassword"));
            	settings.ClickSaveBtn();
            	if (numberSelectedAccounts.equals(1)) {
            		settings.SelectImportAccount2("DB TV");
            	}
            	else if (numberSelectedAccounts.equals(2)) {
            		settings.SelectImportAccount3("DB TV");
            	}
            	else {
            		settings.SelectImportAccount4("DB TV");
            	}
            	settings.ClickSetImportAccountBtn();
            	contentParent.VerifyMessageStatus("Setting import account \"DB%20TV\" for account");
            	contentParent.VerifyMessageStatus("Retrieving import account information for \"DB%20TV\".");
            	settings.VerifyImportAccountsDisabled();
            	settings.ClickSaveConfigurationsBtn();
            	contentParent.VerifyMessageStatus("The configuration options have been saved.");
            	navigation.Content("Files", "mpxMedia");
            	mpxMedia.ExpandMPXMedia();
                if (numberSelectedAccounts.equals(1)) {
                	mpxMedia.SelectMPXPlayerForAccount2("AutomationPlayer1");
            	}
            	else if (numberSelectedAccounts.equals(2)) {
            		mpxMedia.SelectMPXPlayerForAccount3("AutomationPlayer1");
            	}
            	else {
            		mpxMedia.SelectMPXPlayerForAccount4("AutomationPlayer1");
            	}
                mpxMedia.ClickSyncMPXMediaNowLnk();
                contentParent.VerifyMessageStatus("Processed video import/update manually for all accounts.");
                cron.ClickRunCronToCompleteImportLnk();
                cron.ClickRunCronBtn();
                contentParent.VerifyMessageStatus("Cron run successfully.");
        	}
        }
        else { 
        	
        	//Step 3
        	settings.EnterUsername(config.getConfigValueString("MPXUsername"));
        	settings.EnterPassword(config.getConfigValueString("MPXPassword"));
        	
        	//Step 4
        	settings.ClickConnectToMPXBtn();
        	contentParent.VerifyMessageStatus("Login successful");
        	settings.VerifyImportAccountOptions(accountNames);
            
        	//Step 5
            settings.SelectImportAccount1("DB TV");
        	settings.ClickSetImportAccountBtn();
        	contentParent.VerifyMessageStatus("Setting import account \"DB%20TV\" for account");
        	contentParent.VerifyMessageStatus("Retrieving import account information for \"DB%20TV\".");
        	settings.VerifyImportAccountsDisabled();
        	
        	//Step 6 TODO
        	
        	//Step 7
        	navigation.Content("Files", "mpxMedia");
        	mpxMedia.ExpandMPXMedia();
            mpxMedia.SelectMPXPlayerForAccount1("AutomationPlayer1");
            mpxMedia.ClickSyncMPXMediaNowLnk();
            contentParent.VerifyMessageStatus("Processed video import/update manually for all accounts.");
        	
        	//Step 8
            cron.ClickRunCronToCompleteImportLnk();
            cron.ClickRunCronBtn();
            contentParent.VerifyMessageStatus("Cron run successfully.");
            
            //Step 9
            navigation.Content("Files", "mpxMedia");
            SearchFor searchFor = new SearchFor(webDriver);
            searchFor.EnterTitle("AutomationDefault");
            searchFor.ClickApplyBtn();
            searchFor.VerifySearchResultsPresent(Arrays.asList("AutomationDefault"));

            //Step 10
            navigation.Configuration("Media: thePlatform mpx");
            settings.ExpandAddAccounts();
        	settings.EnterUsername(config.getConfigValueString("MPXUsername"));
        	settings.EnterPassword(config.getConfigValueString("MPXPassword"));
        	settings.ClickSaveBtn();
        	settings.SelectImportAccount2("NBCU TVE Stage - Golf Channel");
        	settings.ClickSetImportAccountBtn();
        	contentParent.VerifyMessageStatus("Setting import account \"NBCU%20TVE%20Stage%20-%20Golf%20Channel\" for account");
        	contentParent.VerifyMessageStatus("Retrieving import account information for \"NBCU%20TVE%20Stage%20-%20Golf%20Channel\".");
        	settings.VerifyImportAccountsDisabled();
        	settings.ClickSaveConfigurationsBtn();
        	contentParent.VerifyMessageStatus("The configuration options have been saved.");
        	navigation.Content("Files", "mpxMedia");
        	mpxMedia.ExpandMPXMedia();
            mpxMedia.SelectMPXPlayerForAccount2("AutomationPlayer1");
            mpxMedia.ClickSyncMPXMediaNowLnk();
            contentParent.VerifyMessageStatus("Processed video import/update manually for all accounts.");
            cron.ClickRunCronToCompleteImportLnk();
            cron.ClickRunCronBtn();
            contentParent.VerifyMessageStatus("Cron run successfully.");
            navigation.Content("Files", "mpxMedia");
            searchFor.EnterTitle("The Golf Fix 2/4");
            searchFor.ClickApplyBtn();
            searchFor.VerifySearchResultsPresent(Arrays.asList("The Golf Fix 2/4"));
        	
        }
        */
    }
}
