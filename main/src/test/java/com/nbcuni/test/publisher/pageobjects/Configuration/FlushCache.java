package com.nbcuni.test.publisher.pageobjects.Configuration;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.EmberNav;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * ******************************************
 * publisher.nbcuni.com Flush Cache Library. Copyright
 *
 * @author Brandon Clark
 * @version 1.0 Date: November 7, 2014
 *          *******************************************
 */

public class FlushCache {

    private EmberNav navigation;
    private WaitFor waitFor;
    private ContentParent contentParent;
    private Config config;
    private Integer timeout;
    private Interact interact;

    //PAGE OBJECT CONSTRUCTOR
    public FlushCache(WebDriver webWebWebDriver) {
        navigation = new EmberNav(webWebWebDriver);
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webWebWebDriver, timeout);
        interact = new Interact(webWebWebDriver, timeout);
        contentParent = new ContentParent(webWebWebDriver);
    }

    //PAGE OBJECT IDENTIFIERS
    private By ClearAllCaches_Btn = By.id("edit-clear");


    //PAGE OBJECT METHODS
    public void FlushAllCache() throws Exception {

        navigation.Configuration("Performance");
        interact.Click(waitFor.ElementPresent(ClearAllCaches_Btn));
        contentParent.VerifyMessageStatus("Caches cleared.");

    }

}

