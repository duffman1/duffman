package com.nbcuni.test.publisher.pageobjects.Content;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

/**
 * ******************************************
 * publisher.nbcuni.com Revision State Library. Copyright
 *
 * @author Brandon Clark
 * @version 1.0 Date: December 18, 2013
 *          *******************************************
 */

public class RevisionState {

    private Config config;
    private Integer timeout;
    private WaitFor waitFor;

    //PAGE OBJECT CONSTRUCTOR
    public RevisionState(WebDriver webWebWebDriver) {
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webWebWebDriver, timeout);
    }

    //PAGE OBJECT IDENTIFIERS
    private By Node_Num() {
        return By.xpath("//tbody//td[@class='views-field views-field-vid']");
    }

    //PAGE OBJECT METHODS
    public void VerifyRevisionCount(Integer revisionCount) throws Exception {

        Reporter.log("Verify the number of present revisions entries equals '" + revisionCount + "'.");
        Assert.assertTrue(waitFor.ElementsPresent(Node_Num()).size() == (revisionCount));

    }

}

