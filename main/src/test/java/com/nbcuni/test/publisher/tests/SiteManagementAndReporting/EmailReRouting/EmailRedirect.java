package com.nbcuni.test.publisher.tests.SiteManagementAndReporting.EmailReRouting;

import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.Configuration.RerouteEmail;
import com.nbcuni.test.publisher.pageobjects.GmailConnect;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.People.AddUser;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.testng.annotations.Test;

import java.util.Arrays;

public class EmailRedirect extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE - TC1067
     * Steps - https://rally1.rallydev.com/#/14663927728ud/detail/testcase/17441709828
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void EmailRedirect_TC1067() throws Exception {
    	
    	//Step 1
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        
    	//Setup
    	Modules modules = new Modules(webWebWebDriver);
        modules.VerifyModuleEnabled("Reroute emails");
        
    	//Step 2
        navigation.Configuration("Reroute Email");
    	RerouteEmail rerouteEmail = new RerouteEmail(webWebWebDriver);
    	rerouteEmail.CheckEnableReroutingCbx();
    	rerouteEmail.EnterEmailAddresses(config.getConfigValueString("GmailUsername"));
    	rerouteEmail.ClickSaveConfigurationBtn();
    	contentParent.VerifyMessageStatus("The configuration options have been saved.");
    	
    	//Step 3
    	AddUser addUser = new AddUser(webWebWebDriver);
    	addUser.AddDefaultUser(Arrays.asList("editor"), false);
        GmailConnect gmailConnect = new GmailConnect();
        String autoEmailSubject = "An administrator created an account for you at ";
        gmailConnect.VerifyAutoEmailRecieved(autoEmailSubject);
        gmailConnect.DeleteAllAutoEmailsInInbox(autoEmailSubject);
        
    }
}
