package com.nbcuni.test.publisher.tests.Advertising.Comscore;

import java.util.Arrays;
import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Logout;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;

public class Pixelman extends ParentTest {
	
    /*************************************************************************************
     * TEST CASE - TC1155
     * Steps - https://rally1.rallydev.com/#/14663927728ud/detail/testcase/17533191589
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "smoke"})
    public void Pixelman_TC1155() throws Exception{
        
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
        //contentParent.VerifySourceNotInPage("//www.nbcudigitaladops.com/hosted/global_header.js");
        //contentParent.VerifySourceNotInPage("//www.nbcudigitaladops.com/hosted/site.js?h=qa5dev_publisher_nbcuni_com_header");
        overlay.switchToDefaultContent(true);
            
        //Step 11
        logout.ClickLogoutBtn();
        
        //Step 12
        //contentParent.VerifySourceNotInPage("//www.nbcudigitaladops.com/hosted/global_header.js");
        //contentParent.VerifySourceNotInPage("//www.nbcudigitaladops.com/hosted/site.js?h=qa5dev_publisher_nbcuni_com_header");
         
    }
}
