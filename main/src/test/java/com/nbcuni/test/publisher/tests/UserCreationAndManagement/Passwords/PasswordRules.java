package com.nbcuni.test.publisher.tests.UserCreationAndManagement.Passwords;

import java.util.Arrays;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Logout;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.WorkBench;
import com.nbcuni.test.publisher.pageobjects.ErrorChecking.ErrorChecking;
import com.nbcuni.test.publisher.pageobjects.People.AddUser;
import com.nbcuni.test.publisher.pageobjects.People.PasswordPolicies;

public class PasswordRules extends ParentTest {
	
    /*************************************************************************************
     * TEST CASE - TC2185
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/18357208480 
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void PasswordRules_TC2185() throws Exception {
    	
    	Reporter.log("SETUP - create new user and logout");
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
    	AddUser addUser = new AddUser(webDriver);
    	String userPassword = "pa55word";
        String editorUserName = addUser.AddDefaultUser(Arrays.asList("editor"), false);
        Logout logout = new Logout(webDriver);
        logout.ClickLogoutBtn();
        
        Reporter.log("STEP 1");
        userLogin.EnterEmailAddress(editorUserName);
        userLogin.EnterPassword(userPassword);
        userLogin.ClickLoginBtn();
        
        Reporter.log("STEP 2");
        taxonomy.ClickActiveUserMenuLnk();
        WorkBench workBench = new WorkBench(webDriver);
        workBench.ClickWorkBenchTab("Edit");
        overlay.SwitchToActiveFrame();
        
        Reporter.log("STEP 3");
        addUser.EnterPassword(" ");
        addUser.VerifyPasswordStrengthTxtPresent(Arrays.asList("The password is all spaces and will not be saved."));

        Reporter.log("STEP 4");
        addUser.EnterPassword("ABCDEFG1");
        addUser.VerifyPasswordStrengthTxtPresent(Arrays.asList("The password does not include enough variation to be secure.", 
        		"Password must contain at least 2 digits.", "Password must begin and end with a letter.", 
        			"Password must contain at least one lowercase character."));
        addUser.VerifyPasswordStrengthTxtNotPresent(Arrays.asList("Password must be at least 8 characters in length."));
    	
        Reporter.log("STEP 5");
        addUser.EnterPassword("ABCDEF11");
        addUser.VerifyPasswordStrengthTxtPresent(Arrays.asList("The password does not include enough variation to be secure.", 
        		"Password must begin and end with a letter.", "Password must contain at least one lowercase character."));
        addUser.VerifyPasswordStrengthTxtNotPresent(Arrays.asList("Password must be at least 8 characters in length.",
        		"Password must contain at least 2 digits."));
        
        Reporter.log("STEP 6");
        addUser.EnterPassword("ABCDE11H");
        addUser.VerifyPasswordStrengthTxtPresent(Arrays.asList("The password does not include enough variation to be secure.", 
        		"Password must contain at least one lowercase character."));
        addUser.VerifyPasswordStrengthTxtNotPresent(Arrays.asList("Password must be at least 8 characters in length.",
        		"Password must contain at least 2 digits.", "Password must begin and end with a letter."));
        
        Reporter.log("STEP 7");
        addUser.EnterPassword("ABcDE11H");
        addUser.VerifyPasswordStrengthCtrNotPresent();
        
        Reporter.log("STEP 8");
        addUser.EnterPassword(editorUserName);
        addUser.VerifyPasswordStrengthTxtPresent(Arrays.asList("The password does not include enough variation to be secure.",
        		"Password must not contain the username."));
        
        Reporter.log("STEP 9");
        addUser.EnterPassword("ABcDE11H");
        addUser.EnterConfirmPassword("aBcDE11H");
        addUser.VerifyPasswordsMatch(false);
        
        Reporter.log("STEP 10"); //TODO - test needs more steps as time allows to meet all the error checking criteria
        addUser.EnterCurrentPassword(userPassword);
        addUser.EnterPassword("A");
        addUser.EnterConfirmPassword("A");
        contentParent.ClickSaveBtn();
        ErrorChecking errorChecking = new ErrorChecking(webDriver);
        errorChecking.VerifyErrorMessagePresent("Your password has not met the following requirement(s):");
        
        Reporter.log("STEP 11");
        addUser.EnterCurrentPassword(userPassword);
        addUser.EnterPassword("pa555word");
        addUser.EnterConfirmPassword("pa555word");
        addUser.VerifyPasswordsMatch(true);
        contentParent.ClickSaveBtn();
        contentParent.VerifyMessageStatus("The changes have been saved.");
        
        Reporter.log("STEP 12");
        addUser.EnterCurrentPassword("pa555word");
        addUser.EnterPassword("pa555!@#$%^&*word");
        addUser.EnterConfirmPassword("pa555!@#$%^&*word");
        addUser.VerifyPasswordsMatch(true);
        contentParent.ClickSaveBtn();
        contentParent.VerifyMessageStatus("The changes have been saved.");
        
        Reporter.log("STEP 13");
        overlay.ClickCloseOverlayLnk();
        logout.ClickLogoutBtn();
        userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        taxonomy.NavigateSite("Configuration>>People>>Password policies>>List");
        overlay.SwitchToActiveFrame();
        PasswordPolicies passwordPolicies = new PasswordPolicies(webDriver);
        passwordPolicies.ClickViewLnk();
        overlay.SwitchToActiveFrame();
        contentParent.VerifyPageContentPresent(Arrays.asList("Expiration", "365"));
    	
    }
}
