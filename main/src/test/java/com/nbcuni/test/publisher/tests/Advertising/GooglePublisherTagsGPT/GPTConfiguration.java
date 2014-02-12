package com.nbcuni.test.publisher.tests.Advertising.GooglePublisherTagsGPT;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.DFPAddTags;
import com.nbcuni.test.publisher.pageobjects.Logout;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.Overlay;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Taxonomy.Taxonomy;

public class GPTConfiguration extends ParentTest{

    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Login to publisher using drupal 1 credentials <br>
     * Step 2 - Click on "Modules"<br>
     * Step 3 - In the filter list, type "DART". If the Dart module is enabled, disable it by unchecking the "Enabled" checkbox, and click the "Save configuration" button<br>
     * Step 4 - In the filter list, type "Doublecheck". Put a check in the "enabled" column next to the "Doublecheck for publishers" module, and click on the "Save configuration" button<br>
     * Step 5 - Click on "Structure" >> "DFP Ad Tags" >> "Global DFP Settings"<br>
     * Step 6 - In the "Network ID" field, type "nbcu", and click on the "Save configuration" button<br>
     * Step 7 - Log out Publisher 7
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(groups = {"full" })
    public void GPTConfiguration_Test() throws Exception {
        
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
        	PageFactory.initElements(webDriver, userLogin);
            userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
            
            //Step 2
            taxonomy.NavigateSite("Modules");
            
            //Step 3
            overlay.SwitchToFrame("Modules");
            Modules modules = new Modules(webDriver, applib);
            PageFactory.initElements(webDriver, modules);
            modules.EnterFilterName("DART");
            modules.DisableModule("DART");
            
            //Step 4
            modules.EnterFilterName("Doubleclick for Publishers");
            modules.EnableModule("Doubleclick for Publishers");
            
            //Step 5
            overlay.ClickCloseOverlayLnk();
            overlay.switchToDefaultContent();
            taxonomy.NavigateSite("Structure>>DFP Ad Tags>>Global DFP Settings");
            
            //Step 6
            DFPAddTags dfpAddTags = new DFPAddTags(webDriver);
            overlay.SwitchToFrame("DFP Ad Tags");
            dfpAddTags.EnterNetworkId("nbcu");
            dfpAddTags.ClickSaveConfigurationBtn();
            dfpAddTags.VerifyConfigurationSaved();
            overlay.ClickCloseOverlayLnk();
            overlay.switchToDefaultContent();
            
            //Step 7 - NA
    }
}
