package com.nbcuni.test.publisher.tests.Advertising.Comscore;


import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nbcuni.test.publisher.ContentTypes;
import com.nbcuni.test.publisher.AppLib;
import com.nbcuni.test.publisher.Logout;
import com.nbcuni.test.publisher.Modules;
import com.nbcuni.test.publisher.Overlay;
import com.nbcuni.test.publisher.Taxonomy;
import com.nbcuni.test.publisher.UserLogin;
import com.nbcuni.test.publisher.common.Random;
import com.nbcuni.test.webdriver.CustomWebDriver;
import com.nbcuni.test.webdriver.WebDriverClientExecution;


public class PixelmanModuleSetupAndVerification {
	
	private CustomWebDriver webDriver;
    private AppLib applib;

    /**
     * Instantiate the TestNG Before Class Method.
     * 
     * @param sEnv - environment
     * @throws Exception - error
     */
    @BeforeMethod(alwaysRun = true)
    @Parameters("Environment")
    public void startSelenium(@Optional("PROD") String sEnv) {
        try {
            webDriver = WebDriverClientExecution.getInstance().getDriver();
            applib = new AppLib(webDriver);
            applib.setEnvironmentInfo(sEnv);
        } catch (Exception e) {
            applib.fail(e.toString());
        }

    }

    /**
     * Instantiate the TestNG After Class Method.
     * 
     * @throws Exception - error
     */
    @AfterMethod(alwaysRun = true)
    public void stopSelenium() {
        try {
            webDriver.quit();
        } catch (Exception e) {
            applib.fail(e.toString());
        }

    }

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
    @Test(groups = {"full", "smoke" })
    public void PixelmanModuleSetupAndVerification() throws Exception{
        
    	//Step 1
        UserLogin userLogin = applib.openApplication();
        userLogin.Login("admin@publisher.nbcuni.com", "pa55word");
        
        //Step 2
        Taxonomy taxonomy = new Taxonomy(webDriver);
        taxonomy.ClickTier1ModulesLnk();
        Modules modules = new Modules(webDriver);
        modules.SwitchToModulesFrm();
        modules.EnterFilterName("Pixelman");
            
        //Step 3
        modules.EnableModule("Pixelman");
            
        //Step 4
        modules.EnterFilterName("DART");
        modules.DisableModule("DART");
        modules.EnterFilterName("Doubleclick for Publishers");
        modules.DisableModule("Doubleclick for Publishers");
            
        //Step 5
        Overlay overlay = new Overlay(webDriver);
        overlay.ClickCloseOverlayLnk();
        applib.switchToDefaultContent();
        taxonomy.ClickHomeLnk();
            
        //Step 6
        modules.VerifyModuleSourceInPage("//www.nbcudigitaladops.com/hosted/global_header.js");
        modules.VerifyModuleSourceInPage("//www.nbcudigitaladops.com/hosted/site.js?h=qa5dev_publisher_nbcuni_com_header");
            
        //Step 7
        Logout logout = new Logout(webDriver);
        logout.ClickLogoutBtn();
            
        //Step 8
        modules.VerifyModuleSourceInPage("//www.nbcudigitaladops.com/hosted/global_header.js");
        modules.VerifyModuleSourceInPage("//www.nbcudigitaladops.com/hosted/site.js?h=qa5dev_publisher_nbcuni_com_header");
            
        //Step 9
        userLogin.Login("admin@publisher.nbcuni.com", "pa55word");
        taxonomy.ClickTier1ModulesLnk();
        modules.SwitchToModulesFrm();
        modules.EnterFilterName("Pixelman");
        modules.DisableModule("Pixelman");
        modules.EnterFilterName("Pub Ads");
        modules.DisableModule("Pub Ads");
            
        //Step 10
        modules.VerifyModuleSourceNotInPage("//www.nbcudigitaladops.com/hosted/global_header.js");
        modules.VerifyModuleSourceNotInPage("//www.nbcudigitaladops.com/hosted/site.js?h=qa5dev_publisher_nbcuni_com_header");
        overlay.ClickCloseOverlayLnk();
        applib.switchToDefaultContent();
            
        //Step 11
        logout.ClickLogoutBtn();
            
        //Step 12
        modules.VerifyModuleSourceNotInPage("//www.nbcudigitaladops.com/hosted/global_header.js");
        modules.VerifyModuleSourceNotInPage("//www.nbcudigitaladops.com/hosted/site.js?h=qa5dev_publisher_nbcuni_com_header");
         
    }
}
