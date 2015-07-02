package com.nbcuni.test.publisher.tests.UserCreationAndManagement.Passwords;

import com.nbcuni.test.publisher.common.GlobalBaseTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.ForgotPassword;
import com.nbcuni.test.publisher.pageobjects.GmailConnect;
import com.nbcuni.test.publisher.pageobjects.Logout;
import com.nbcuni.test.publisher.pageobjects.People.AddUser;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.Arrays;

public class PasswordReset extends GlobalBaseTest {
	
    /*************************************************************************************
     * TEST CASE - TC2186
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/18357385092 
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void PasswordReset_TC2186() throws Exception {
    	
    	Reporter.log("SETUP - create new user");
    	UserLogin userLogin = appLib.openApplication();
    	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
    	AddUser addUser = new AddUser(webDriver);
    	String userName = addUser.AddDefaultUser(Arrays.asList("editor"), false);
    	Logout logout = new Logout(webDriver);
    	logout.ClickLogoutBtn();
    	
    	Reporter.log("STEP 1");
    	appLib.openApplication();
    	
    	Reporter.log("STEP 2");
    	ForgotPassword forgotPassword = new ForgotPassword(webDriver);
    	forgotPassword.ClickRequestPasswordLnk();
    	
    	Reporter.log("STEP 3");
    	forgotPassword.EnterEmail(userName);
    	forgotPassword.ClickResetPasswordBtn();
    	contentParent.VerifyMessageStatus("Further instructions have been sent to your e-mail address.");
    	
    	Reporter.log("STEP 4");
    	GmailConnect gmailConnect = new GmailConnect();
        String autoEmailSubject = "Replacement login information for " + userName + " at ";
        gmailConnect.VerifyAutoEmailRecieved(autoEmailSubject);
        gmailConnect.DeleteAllAutoEmailsInInbox(autoEmailSubject);
        
        Reporter.log("STEP 5 and 6 - not available for automation as of yet");
        
        Reporter.log("STEP 7");
        appLib.openApplication();
    	forgotPassword.ClickRequestPasswordLnk();
    	forgotPassword.EnterEmail("notvalid@nbcuni.com");
    	forgotPassword.ClickResetPasswordBtn();
    	contentParent.VerifyMessageError("Sorry, notvalid@nbcuni.com is not recognized as an e-mail address.");
    	
    }
}
