package com.nbcuni.test.publisher.tests.Advertising.Comscore;

import java.util.Arrays;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;

public class Pixelman extends ParentTest {
	
    /*************************************************************************************
     * TEST CASE - TC1155
     * Steps - https://rally1.rallydev.com/#/14663927728ud/detail/testcase/17533191589
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void Pixelman_TC1155() throws Exception {
        
    	Reporter.log("STEP 1");
        UserLogin userLogin = applib.openApplication();
        userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        
        Reporter.log("STEP 2 -3");
        taxonomy.NavigateSite("Modules");
    	overlay.SwitchToActiveFrame();
    	Modules modules = new Modules(webDriver);
    	modules.EnterFilterName("Pixelman");
    	modules.EnableModule("Pixelman");
    	
        Reporter.log("STEP 4");
        modules.EnterFilterName("DART");
        modules.DisableModule("DART");
        modules.EnterFilterName("Doubleclick for Publishers");
        modules.DisableModule("Doubleclick for Publishers");
            
        Reporter.log("STEP 5");
        overlay.ClickCloseOverlayLnk();
        taxonomy.NavigateSite("Home");
        taxonomy.NavigateSite("Home>>Flush all caches");
        
        Reporter.log("STEP 6");
        contentParent.VerifySourceInPage(Arrays.asList("//www.nbcudigitaladops.com/hosted/global_header.js"));
            
        Reporter.log("CLEANUP");
        taxonomy.NavigateSite("Modules");
        overlay.SwitchToActiveFrame();
        for (String module : Arrays.asList("Pub Ads", "Pixelman")) {
        	modules.EnterFilterName(module);
        	modules.DisableModule(module);
        }
        overlay.ClickCloseOverlayLnk();
        taxonomy.NavigateSite("Home>>Flush all caches");
        
        
    }
}
