package com.nbcuni.test.publisher.tests.Advertising.MPS;

import java.util.Arrays;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.MPSConfiguration;

public class AddMPSCallsToErrorPages extends ParentTest {
	
    /*************************************************************************************
     * TEST CASE - TC4950
     * Steps - https://rally1.rallydev.com/#/14663927728ud/detail/testcase/22520431481
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void AddMPSCallsToErrorPages_TC4950() throws Exception {
        
        	Reporter.log("STEP 1");
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
            
        	Reporter.log("STEP 2");
        	applib.openSitePage("/junkurl");
        	
        	Reporter.log("STEP 3");
        	MPSConfiguration mpsConfiguration = new MPSConfiguration(webDriver);
            mpsConfiguration.VerifyMPSCallParameters(Arrays.asList("\"site\":\"sandbox\"", 
            		"\"path\":\"ERROR/404\"", "\"content_id\":\"ERROR\""));
    }
}
