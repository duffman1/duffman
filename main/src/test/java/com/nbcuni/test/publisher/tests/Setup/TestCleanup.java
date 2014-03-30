package com.nbcuni.test.publisher.tests.Setup;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.testng.annotations.Test;

public class TestCleanup extends ParentTest{
	
    /*************************************************************************************
     * Test executes some common teardown logic after the full suite execution
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "smoke", "mpx"})
    public void TestCleanup_Test() throws Exception{
         
        	//login
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
            
        	//placeholder for any teardown/cleanup logic as needed
            
    }
}
