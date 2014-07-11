package com.nbcuni.test.publisher.tests.SiteManagementAndReporting.EmailReRouting;

import java.util.Arrays;
import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.GmailConnect;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.RerouteEmail;
import com.nbcuni.test.publisher.pageobjects.People.AddUser;

public class EmailRedirect extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE - TC1067
     * Steps - https://rally1.rallydev.com/#/14663927728ud/detail/testcase/17441709828
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void EmailRedirect_TC1067() throws Exception {
    	
    	//Step 1
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
    	//Setup
    	Modules modules = new Modules(webDriver, applib);
        modules.VerifyModuleEnabled("Reroute emails");
        
    	//Step 2
    	taxonomy.NavigateSite("Configuration>>Development>>Reroute Email");
    	overlay.SwitchToActiveFrame();
    	RerouteEmail rerouteEmail = new RerouteEmail(webDriver, applib);
    	rerouteEmail.CheckEnableReroutingCbx();
    	rerouteEmail.EnterEmailAddresses(applib.getGmailAutoEmailUsername());
    	rerouteEmail.ClickSaveConfigurationBtn();
    	contentParent.VerifyMessageStatus("The configuration options have been saved.");
    	overlay.ClickCloseOverlayLnk();
    	
    	//Step 3
    	AddUser addUser = new AddUser(webDriver, applib);
    	addUser.AddDefaultUser(Arrays.asList("editor"), false);
        GmailConnect gmailConnect = new GmailConnect(applib);
        String autoEmailSubject = "An administrator created an account for you at Site-Install";
        gmailConnect.VerifyAutoEmailRecieved(autoEmailSubject);
        gmailConnect.DeleteAllAutoEmailsInInbox(autoEmailSubject);
        
    }
}
