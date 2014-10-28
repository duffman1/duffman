package com.nbcuni.test.publisher.tests.Commerce;

import org.testng.Reporter;
import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;

public class CreatePubFeatureSetModules extends ParentTest {
	
    /*************************************************************************************
     * TEST CASE - TC4061
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/20632770691
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void CreatePubFeatureSetModules_TC4061() throws Exception {
        
        	Reporter.log("STEP 1");
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
            
        	Reporter.log("STEP 2");
        	taxonomy.NavigateSite("Modules");
        	overlay.SwitchToActiveFrame();
        	Modules modules = new Modules(webDriver);
        	modules.ClickCategoryLnk("Publisher Feature Sets");
        	modules.WaitForFilterVisible("Publisher Blog");
        	modules.WaitForFilterVisible("Publisher Entertainment");
        	modules.WaitForFilterVisible("Publisher Commerce");
        	modules.WaitForFilterVisible("Publisher News");
        	modules.WaitForFilterVisible("Publisher TVE");
        	
    }
}
