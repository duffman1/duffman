package com.nbcuni.test.publisher.tests.Setup;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;

import org.testng.annotations.Test;

public class TestSetup extends ParentTest{
	
    /*************************************************************************************
     * Test executes some common setup logic prior to full suite execution
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "smoke", "mpx"})
    public void TestSetup_Test() throws Exception{
         
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
            
            //Step 2
            taxonomy.NavigateSite("Modules");
        	overlay.SwitchToFrame("Modules");
        	Modules modules = new Modules(webDriver, applib);
        	modules.EnterFilterName("Sticky Edit Actions");
        	modules.DisableModule("Sticky Edit Actions");
            
        	//Step 3
        	modules.EnterFilterName("Devel");
            modules.DisableModule("Devel");
            
            //Step 4
            modules.EnterFilterName("Database logging");
            modules.DisableModule("Database logging");
            overlay.ClickCloseOverlayLnk();
            overlay.switchToDefaultContent();
            
            //Step 5
            taxonomy.NavigateSite("Home>>Flush all caches");
            
    }
}
