package com.nbcuni.test.publisher.pageobjects.Twitter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
 * publisher.nbcuni.com Node Types Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: February 19, 2013
 *********************************************/

public class NodeTypes {

    private ContentParent contentParent;
    private Config config;
    private Integer timeout;
    private WaitFor waitFor;
    private Interact interact;
    
    //PAGE OBJECT CONSTRUCTOR
    public NodeTypes(Driver webDriver) {
        contentParent = new ContentParent(webDriver);
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webDriver, timeout);
        interact = new Interact(webDriver, timeout);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By Post_Cbx = By.id("edit-twitter-post-types-post");
    
    private By SaveConfiguration_Btn = By.xpath("//input[@value='Save configuration']");
    
    
    //PAGE OBJECT METHODS
    public void EnablePostNode() throws Exception {
    	
    	WebElement ele = waitFor.ElementVisible(Post_Cbx);
    	if (ele.isSelected() == false) {
    		
    		Reporter.log("Check the 'Post' checkbox.");
    		interact.Click(ele);
    		
    		Reporter.log("Click the 'Save configuration' button.");
    		interact.Click(waitFor.ElementVisible(SaveConfiguration_Btn));
    		
    		contentParent.VerifyMessageStatus("The configuration options have been saved.");
    	}
    }
    
}

