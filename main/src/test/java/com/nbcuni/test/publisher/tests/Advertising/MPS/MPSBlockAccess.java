package com.nbcuni.test.publisher.tests.Advertising.MPS;

import java.util.Arrays;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Logout;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.People.AddUser;

public class MPSBlockAccess extends ParentTest {
	
    /*************************************************************************************
     * TEST CASE - TC3608
     * Steps - https://rally1.rallydev.com/#/14663927728ud/detail/testcase/20142895489
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void MPSBlockAccess_TC3608() throws Exception {
        
        	Reporter.log("STEP 1");
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
            
        	Reporter.log("STEP 2");
        	taxonomy.NavigateSite("Structure>>MPS Blocks");
        	overlay.SwitchToFrame("MPS Blocks");
        	overlay.ClickCloseOverlayLnk();
        	
        	Reporter.log("STEP 3");
        	AddUser addUser = new AddUser(webDriver, applib);
            String userName = addUser.AddDefaultUser(Arrays.asList("administrator"));
            
            Reporter.log("STEP 4");
            Logout logout = new Logout(webDriver);
            logout.ClickLogoutBtn();
            userLogin.Login(userName, "pa55word");
            
            Reporter.log("STEP 5");
            taxonomy.NavigateSite("Structure>>MPS Blocks");
        	overlay.SwitchToFrame("MPS Blocks");
    }
}