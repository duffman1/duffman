package com.nbcuni.test.publisher.tests.homepage;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nbcuni.test.publisher.AppLib;
import com.nbcuni.test.publisher.Search;
import com.nbcuni.test.webdriver.CustomWebDriver;
import com.nbcuni.test.webdriver.WebDriverClientExecution;


public class TC2_VerifyPagination {
	
	private CustomWebDriver cs;
    private AppLib applib;

    /**
     * Instantiate the TestNG Before Class Method.
     * 
     * @param sEnv - environment
     * @throws Exception - error
     */
    @BeforeClass(alwaysRun = true)
    @Parameters("Environment")
    public void startSelenium(String sEnv) {
        try {
            cs = WebDriverClientExecution.getInstance().getDriver();
            applib = new AppLib(cs);
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
    @AfterClass(alwaysRun = true)
    public void stopSelenium() {
        try {
            cs.quit();
        } catch (Exception e) {
            applib.fail(e.toString());
        }

    }

    /*************************************************************************************
     * Step 1 - Launch the browser and go to the NBC.com home page <br>
     * Step 2 - Validate Page Title is correct<br>
     * Step 3 - Validate that menus are displayed and correct
     * 
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(groups = {"full", "smoke" })
    public void verifySearchPage() {
        try {
            Search search = applib.openSearchPage();
            search.performSearch("new shows");
            search.validatePagination();
            
        } catch (Exception e) {
            applib.fail(e.toString());
        }

    }
}
