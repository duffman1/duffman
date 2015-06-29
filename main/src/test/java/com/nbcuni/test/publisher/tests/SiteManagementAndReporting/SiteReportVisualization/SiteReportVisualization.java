package com.nbcuni.test.publisher.tests.SiteManagementAndReporting.SiteReportVisualization;

import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.common.ParentTest;
import org.testng.annotations.Test;

public class SiteReportVisualization extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE 
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/17266417771
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void SiteReportVisualization_Test() throws Exception{
    	
    	/*COMMENTING TEST OUT - Sruthi is looking into specifics on this test
    	//Step 1
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
    	
    	//Setup
    	Modules modules = new Modules(webDriver);
    	modules.VerifyModuleEnabled("Pub Report Server");
    	
    	//Step 2
    	taxonomy.NavigateSite("Reports>>Publisher Reports");
    	overlay.SwitchToActiveFrame();
    	
    	//Step 3
    	
    	
    	*/
    }
}
