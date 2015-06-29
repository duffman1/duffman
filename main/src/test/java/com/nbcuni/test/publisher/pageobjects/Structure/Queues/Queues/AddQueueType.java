package com.nbcuni.test.publisher.pageobjects.Structure.Queues.Queues;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import java.util.List;

/*********************************************
 * publisher.nbcuni.com Add Queue Type Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: March 26, 2014
 *********************************************/

public class AddQueueType {

    private Config config;
    private Integer timeout;
    private WaitFor waitFor;
    private Interact interact;
    
    //PAGE OBJECT CONSTRUCTOR
    public AddQueueType(WebDriver webWebWebDriver, AppLib applib) {
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webWebWebDriver, timeout);
        interact = new Interact(webWebWebDriver, timeout);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By Name_Txb = By.id("edit-label");
    
    private By EntityType_Ddl = By.id("edit-target");
    
    private By ContentType_Cbx(String contentType) {
    	return By.xpath("//label[text()='" + contentType + " ']/../input");
    }
    
    private By SaveQueueType_Btn = By.id("edit-submit");
    
    
    //PAGE OBJECT METHODS
    public void EnterName(String queueTypeName) throws Exception {
    	
    	Reporter.log("Enter '" + queueTypeName + "' in the 'Name' text box.");
    	interact.Type(waitFor.ElementVisible(Name_Txb), queueTypeName);
    	
    }
    
    public void SelectEntityType(String entityType) throws Exception {
    	
    	Reporter.log("Select '" + entityType + "' from the 'Entity type' drop down list.");
    	interact.Select(waitFor.ElementVisible(EntityType_Ddl), entityType);
    	
    }
    
    public void EnableContentTypes(List<String> contentTypes) throws Exception {
    	
    	for (String contentType : contentTypes) {
    		WebElement ele = waitFor.ElementVisible(ContentType_Cbx(contentType));
    		if (!ele.isSelected()) {
    			Reporter.log("Check the '" + contentType + "' check box.");
    			interact.Click(ele);
    		}
    	}
    	
    }
    
    public void ClickSaveQueueTypeBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save queue type' button.");
    	interact.Click(waitFor.ElementVisible(SaveQueueType_Btn));
    	
    }
    
    
}

