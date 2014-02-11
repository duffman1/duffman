package com.nbcuni.test.publisher.tests.Setup;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.AdministrationMenu;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.MPX.Settings;
import com.nbcuni.test.publisher.pageobjects.content.*;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

public class TestSetup extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Login to Pub7 
     * Step 2 - Disable sticky edit actions module
     * Step 3 - Disable devel module
     * Step 4 - Set admin ribbon to stay at top of page
     * Step 5 - Flush all caches
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(groups = {"full" })
    public void TestSetup_Test() throws Exception{
         
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
        	PageFactory.initElements(webDriver, userLogin);
            userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
            
            //Step 2
            taxonomy.NavigateSite("Modules");
        	overlay.SwitchToFrame("Modules");
        	Modules modules = new Modules(webDriver, applib);
        	PageFactory.initElements(webDriver, modules);
        	modules.EnterFilterName("Sticky Edit Actions");
        	modules.DisableModule("Sticky Edit Actions");
            
        	//Step 3
        	modules.EnterFilterName("Devel");
            modules.DisableModule("Devel");
            
            //Step 4
            modules.EnterFilterName("Administration menu");
            modules.ClickConfigureLnk("Administration menu");
            overlay.SwitchToActiveFrame();
            AdministrationMenu administrationMenu = new AdministrationMenu(webDriver, applib);
            PageFactory.initElements(webDriver, administrationMenu);
            administrationMenu.UnCheckKeepMenuOnTopOfPageCbx();
            administrationMenu.ClickSaveConfigurationBtn();
            overlay.ClickCloseOverlayLnk();
            overlay.switchToDefaultContent();
            
            //Step 5
            taxonomy.NavigateSite("Home>>Flush all caches");
            
    }
}
