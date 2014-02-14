package com.nbcuni.test.publisher.tests.SiteManagementAndReporting.EmailReRouting;

import java.net.URL;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.GmailConnect;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.RerouteEmail;
import com.nbcuni.test.publisher.pageobjects.Content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.Content.Delete;
import com.nbcuni.test.publisher.pageobjects.Content.PublishingOptions;
import com.nbcuni.test.publisher.pageobjects.Content.Revisions;
import com.nbcuni.test.publisher.pageobjects.Content.WorkBench;
import com.nbcuni.test.publisher.pageobjects.Facebook.DrupalForFacebook;
import com.nbcuni.test.publisher.pageobjects.Facebook.NodeTypes;
import com.nbcuni.test.publisher.pageobjects.Facebook.Share;
import com.nbcuni.test.publisher.pageobjects.People.AddUser;

public class EnableEmailReRouting extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Login to P7 as Admin (User 1) ,User is logged in successfully 
     * Step 2 - Navigate to Configuration --> Development --> Reroute Email Enter a valid email address that you (the tester) own and can monitor for receipt of emails.  Click "Save Configuration" ,Email Re Routing is configured 
     * Step 3 - Navigate to People -->  Add User  Complete configuration of a new user account, for email address use a DIFFERENT email than used in step 2, ensure "Notify user of new account' is clicked.  Save user. ,When new user is created, an email should be generated and re routed to the email address configured in step 2. 
	 * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(groups = {"full"})
    public void EnableEmailReRouting_Test() throws Exception{
    	
    	//Step 1
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
    	//Step 2
    	taxonomy.NavigateSite("Configuration>>Development>>Reroute Email");
    	overlay.SwitchToActiveFrame();
    	RerouteEmail rerouteEmail = new RerouteEmail(webDriver, applib);
    	rerouteEmail.CheckEnableReroutingCbx();
    	rerouteEmail.EnterEmailAddresses(applib.getGmailAutoEmailUsername());
    	rerouteEmail.ClickSaveConfigurationBtn();
    	ContentParent contentParent = new ContentParent(webDriver, applib);
    	contentParent.VerifyMessageStatus("The configuration options have been saved.");
    	
    	//Step 3
    	overlay.ClickCloseOverlayLnk();
    	taxonomy.NavigateSite("People>>Add user");
        overlay.SwitchToActiveFrame();
    	AddUser addUser = new AddUser(webDriver);
        String userName = random.GetCharacterString(15) + "@" + random.GetCharacterString(15) + ".com";
        addUser.EnterUsername(userName);
        addUser.EnterEmailAddress(userName);
        String passWord = "pa55word"; 
        addUser.EnterPassword(passWord);
        addUser.EnterConfirmPassword(passWord);
        addUser.ClickEditorRoleCbx();
        addUser.CheckNotifyUserNewAccountCbx();
        String firstName = random.GetCharacterString(15);
        addUser.EnterFirstName(firstName);
        String lastName = random.GetCharacterString(15);
        addUser.EnterLastName(lastName);
        addUser.ClickCreateNewAccountBtn();
        contentParent.VerifyMessageStatus("A welcome message with further instructions has been e-mailed to the new user " + userName + ".");
        GmailConnect gmailConnect = new GmailConnect(applib);
        String autoEmailSubject = "An administrator created an account for you at Site-Install";
        gmailConnect.VerifyAutoEmailRecieved(autoEmailSubject);
        gmailConnect.DeleteAllAutoEmailsInInbox(autoEmailSubject);
        
    }
}
