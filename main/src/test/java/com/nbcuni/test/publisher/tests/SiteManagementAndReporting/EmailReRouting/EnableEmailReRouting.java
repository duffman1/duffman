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

public class EnableEmailReRouting extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Login to P7 as Admin (User 1) ,User is logged in successfully 
     * Step 2 - Navigate to Configuration --> Development --> Reroute Email Enter a valid email address that you (the tester) own and can monitor for receipt of emails.  Click "Save Configuration" ,Email Re Routing is configured 
     * Step 3 - Navigate to People -->  Add User  Complete configuration of a new user account, for email address use a DIFFERENT email than used in step 2, ensure "Notify user of new account' is clicked.  Save user. ,When new user is created, an email should be generated and re routed to the email address configured in step 2. 
	 * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void EnableEmailReRouting_Test() throws Exception{
    	
    	//Step 1
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
    	//Step 1a
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
    	addUser.AddDefaultUser(Arrays.asList("editor"));
        GmailConnect gmailConnect = new GmailConnect(applib);
        String autoEmailSubject = "An administrator created an account for you at Site-Install";
        gmailConnect.VerifyAutoEmailRecieved(autoEmailSubject);
        gmailConnect.DeleteAllAutoEmailsInInbox(autoEmailSubject);
        
    }
}
