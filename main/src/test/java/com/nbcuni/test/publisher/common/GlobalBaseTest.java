package com.nbcuni.test.publisher.common;

import com.nbcuni.test.publisher.common.Driver.listeners.SeleniumTestListener;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.EmberNav;
import com.nbcuni.test.publisher.pageobjects.Taxonomy.Taxonomy;
import com.nbcuni.test.publisher.tests.S3.AbstractCustomContext;
import com.nbcuni.test.publisher.tests.Setup.A1_TestSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * Created by kiryl_zayets on 7/1/15.
 */

@TestExecutionListeners(SeleniumTestListener.class)
@ContextConfiguration(locations = {"classpath:configs/global.xml"})
public class GlobalBaseTest extends AbstractCustomContext {

    @Autowired
    protected WebDriver webDriver;
    @Autowired
    protected AppLib appLib;
    @Autowired
    protected EmberNav navigation;
    @Autowired
    protected Taxonomy taxonomy;
    @Autowired
    protected ContentParent contentParent;
    @Autowired
    protected Random random;

    @Autowired
    protected Config config;

    protected Interact interact;


    public static Boolean abortTestSuite = false;

    @PostConstruct
    public void suiteSetup() throws Exception {

         interact = new Interact(webDriver, config.getConfigValueInt("WaitForWaitTime"));
    }

//    @BeforeGroups(groups = {"full"})
//    public void bb() throws Exception {
//        System.out.println("sss");
//        if (config.getConfigValueString("RunSetupScripts").equals("true")) {
//            A1_TestSetup testSetup = new A1_TestSetup();
//            abortTestSuite = testSetup.TestSetup_Test(webDriver, appLib);
//        }
//    }

//    @AfterSuite(alwaysRun = true)
//    public void stopSelenium(ITestResult result) throws Exception {
//
//        if (!result.isSuccess()) {
//            appLib.attachScreenshot(result);
//        }
//
//
//        if (config.getConfigValueString("ClearCacheOnFailure").equals("true")) {
//            //Clear cache in the event of a failure
//            try {
//                if (!result.isSuccess()) {
//                    appLib.openSitePage("/admin/config/development/performance");
//                    webDriver.switchTo().defaultContent();
//                    Thread.sleep(1000);
//                    webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
//                    webDriver.findElement(By.id("edit-clear")).click();
//                    Reporter.setCurrentTestResult(result);
//                    Reporter.log("Cache was cleared on test failure");
//                    Reporter.setCurrentTestResult(null);
//                }
//            } catch (Exception | AssertionError e) {
//                System.out.println("Failed to flush cache");
//            }
//        }
//
//    }


}
