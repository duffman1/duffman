package com.nbcuni.test.publisher.tests.Advertising.Comscore;

import java.util.Arrays;

import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Logout;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;

public class PixelmanModuleSetupAndVerification extends ParentTest {
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Login to publisher using drupal 1 credentials <br>
     * Step 2 - In the main menu, click Modules, and then type pixelman in the Filter list box<br>
     * Step 3 - Ensure that the Pixelman module is enabled. Enable it if necessary by checking the checkbox and clicking the save button<br>
     * Step 4 - Still working on the modules page, filter on "Dart" and "DoubleClickForPublishers". Ensure that they are disabled. If necessary, disable by unchecking the checkboxes and clicking the save confugration button.<br>
     * Step 5 - In the main menu, click the "Home" icon
     * Step 6 - View the source code of the homepage, and then in turn search for "<script src='//www.nbcudigitaladops.com/hosted/global_header.js> AND <script src='//www.nbcudigitaladops.com/hosted/site.js?h=qa5dev_publisher_nbcuni_com_header'> are present in the page source
     * Step 7 - Log out of the test instance
     * Step 8 - View the source code of the homepage, and then in turn search for "<script src='//www.nbcudigitaladops.com/hosted/global_header.js> AND <script src='//www.nbcudigitaladops.com/hosted/site.js?h=qa5dev_publisher_nbcuni_com_header'> are present in the page source
     * Step 9 - Log into the test instance as drupal user 1, and then in turn ensure that the pub ads and pixelman modules are disabled.<br>
     * Step 10 - View the page source  and ensure the source scripts from Step 6 are NOT present.
     * Step 11 - Log out of the test instance<br>
     * Step 12 - View the source code of the homepage and ensure that the source scripts from Step 6 are NOT present.<br>
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "smoke"})
    public void PixelmanModuleSetupAndVerification_Test() throws Exception{
        
    	//Step 1
        UserLogin userLogin = applib.openApplication();
        userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
        //Step 2 and 3 (truncated)
        taxonomy.NavigateSite("Modules");
    	overlay.SwitchToFrame("Modules");
    	Modules modules = new Modules(webDriver, applib);
    	modules.EnterFilterName("Pixelman");
    	modules.EnableModule("Pixelman");
    	
        //Step 4
        modules.EnterFilterName("DART");
        modules.DisableModule("DART");
        modules.EnterFilterName("Doubleclick for Publishers");
        modules.DisableModule("Doubleclick for Publishers");
            
        //Step 5
        overlay.ClickCloseOverlayLnk();
        taxonomy.NavigateSite("Home");
        taxonomy.NavigateSite("Home>>Flush all caches");
        
        //Step 6
        contentParent.VerifySourceInPage(Arrays.asList("//www.nbcudigitaladops.com/hosted/global_header.js"));
            
        //Step 7
        Logout logout = new Logout(webDriver);
        logout.ClickLogoutBtn();
            
        //Step 8
        contentParent.VerifySourceInPage(Arrays.asList("//www.nbcudigitaladops.com/hosted/global_header.js"));
            
        //Step 9
        userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        taxonomy.NavigateSite("Modules");
        overlay.SwitchToFrame("Modules");
        modules.EnterFilterName("Pub Ads");
        modules.DisableModule("Pub Ads");
        modules.EnterFilterName("Pixelman");
        modules.DisableModule("Pixelman");
        overlay.ClickCloseOverlayLnk();
        
        //Step 9a
        taxonomy.NavigateSite("Home>>Flush all caches");
        taxonomy.NavigateSite("Home");
        
        //Step 10
        contentParent.VerifySourceNotInPage("//www.nbcudigitaladops.com/hosted/global_header.js");
        contentParent.VerifySourceNotInPage("//www.nbcudigitaladops.com/hosted/site.js?h=qa5dev_publisher_nbcuni_com_header");
        overlay.switchToDefaultContent();
            
        //Step 11
        logout.ClickLogoutBtn();
        
        //Step 12
        contentParent.VerifySourceNotInPage("//www.nbcudigitaladops.com/hosted/global_header.js");
        contentParent.VerifySourceNotInPage("//www.nbcudigitaladops.com/hosted/site.js?h=qa5dev_publisher_nbcuni_com_header");
         
    }
}
