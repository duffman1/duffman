package com.nbcuni.test.publisher.tests.SiteManagementAndReporting.AdminViews;
import java.util.Arrays;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.ExecutePHPCode;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;

public class NoWhiteScreen extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE - TC4147
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/20725959397
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void NoWhiteScreen_TC4147() throws Exception {
    	
    	Reporter.log("STEP 1");
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
    	Reporter.log("STEP 2");
    	Modules modules = new Modules(webDriver, applib);
        modules.VerifyModuleEnabled("Devel");
        
    	Reporter.log("STEP 3");
    	Thread.sleep(1000);
    	applib.openSitePage("/devel/php");
        
        Reporter.log("STEP 4");
        ExecutePHPCode executePHPCode = new ExecutePHPCode(webDriver);
        executePHPCode.EnterPHPCode("white();");
        executePHPCode.ClickExecuteBtn();
        contentParent.VerifyPageContentPresent(Arrays.asList("Fatal error: Call to undefined function white()"));
        
    }
}
