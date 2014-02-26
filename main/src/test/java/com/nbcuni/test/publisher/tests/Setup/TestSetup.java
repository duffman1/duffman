package com.nbcuni.test.publisher.tests.Setup;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class TestSetup extends ParentTest{
	
    /*************************************************************************************
     * Test executes some common setup logic prior to full suite execution
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "smoke", "mpx"})
    public void TestSetup_Test() throws Exception{
         
        	//login
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
            
            //disable sticky edit actions module
            taxonomy.NavigateSite("Modules");
        	overlay.SwitchToFrame("Modules");
        	Modules modules = new Modules(webDriver, applib);
        	modules.EnterFilterName("Sticky Edit Actions");
        	modules.DisableModule("Sticky Edit Actions");
            
        	//disable devel module
        	modules.EnterFilterName("Devel");
            modules.DisableModule("Devel");
            
            //disable database logging module
            modules.EnterFilterName("Database logging");
            modules.DisableModule("Database logging");
            overlay.ClickCloseOverlayLnk();
            overlay.switchToDefaultContent();
            
            
            //if Cindia's demo site, set set timezone to UTC
            if (applib.getApplicationURL().contains("demo.publisher7.com")) {
            	webDriver.navigate().to(applib.getApplicationURL() + "/admin/config/regional/settings");
            	new Select(webDriver.findElement(By.id("edit-date-default-timezone"))).selectByValue("UTC");
            	webDriver.findElement(By.id("edit-submit")).click();
            }
            
            //flush all caches
            taxonomy.NavigateSite("Home>>Flush all caches");
            
    }
}
