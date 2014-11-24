package com.nbcuni.test.publisher.tests.Advertising.MPS;

import java.util.Arrays;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Logout;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.People.AddUser;

public class MPSBlockAccess extends ParentTest {
	
    /*************************************************************************************
     * TEST CASE - TC3608
     * Steps - https://rally1.rallydev.com/#/14663927728ud/detail/testcase/20142895489
     *************************************************************************************/
    //@Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void MPSBlockAccess_TC3608() throws Exception {
        
        	Reporter.log("STEP 1");
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
            
        	Reporter.log("SETUP");
        	navigation.Modules();
        	Modules modules = new Modules(webDriver);
        	modules.DisableModule("Pixelman");
        	modules.EnableModule("MPS");
        	
        	Reporter.log("STEP 2");
        	navigation.Structure("MPS Blocks");
        	
        	Reporter.log("STEP 3");
        	AddUser addUser = new AddUser(webDriver);
            String userName = addUser.AddDefaultUser(Arrays.asList("administrator"), false);
            
            Reporter.log("STEP 4");
            Logout logout = new Logout(webDriver);
            logout.ClickLogoutBtn();
            userLogin.Login(userName, "pa55word");
            
            Reporter.log("STEP 5");
            navigation.Structure("MPS Blocks");
            
    }
}
