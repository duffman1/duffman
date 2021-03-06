package com.nbcuni.test.publisher.pageobjects.Configuration;

import org.openqa.selenium.By;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import com.nbcuni.test.publisher.pageobjects.EmberNav;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;

/*********************************************
 * publisher.nbcuni.com Flush Cache Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: November 7, 2014
 *********************************************/

public class FlushCache {

	private EmberNav navigation;
	private WaitFor waitFor;
	private ContentParent contentParent;
	private Config config;
	private Integer timeout;
	private Interact interact;
	
    //PAGE OBJECT CONSTRUCTOR
    public FlushCache(Driver webDriver) {
        navigation = new EmberNav(webDriver);
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webDriver, timeout);
        interact = new Interact(webDriver, timeout);
        contentParent = new ContentParent(webDriver);
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

