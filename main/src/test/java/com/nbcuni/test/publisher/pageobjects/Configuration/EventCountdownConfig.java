package com.nbcuni.test.publisher.pageobjects.Configuration;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
* publisher.nbcuni.com Event Countdown Config Library. Copyright
*
* @author Brandon Clark
* @version 1.0 Date: September 3, 2014
*********************************************/
public class EventCountdownConfig {

	private Driver webDriver;
	private Config config;
	private Integer timeout;
	private WaitFor waitFor;
	private Interact interact;
	
	//PAGE OBJECT CONSTRUCTOR
	public EventCountdownConfig(Driver webDriver) {
		this.webDriver = webDriver;
		config = new Config();
		timeout = config.getConfigValueInt("WaitForWaitTime");
		waitFor = new WaitFor(webDriver, timeout);
		interact = new Interact(webDriver, timeout);
	}

	//PAGE OBJECT IDENTIFIERS
	private By Create_Btn = By.id("edit-create-timer-events-submit");
	
	private By SampleNodesCreated_Txt = By.xpath("//p[text()='The Event Countdown sample nodes have been created.']");
	
	private By Progress_Trb = By.xpath("//div[@class='message'][text()='Content changes are now visible for everybody!']");

	
	//PAGE OBJECT METHODS
	public Boolean AreSampleNodesConfigured() throws Exception {
		
		webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    	boolean sampleNodesConfigured = false;

    	try {
    		webDriver.findElement(SampleNodesCreated_Txt);
            sampleNodesConfigured = true;
        }
        catch (Exception e){
            sampleNodesConfigured = false;
        }
            
    	webDriver.manage().timeouts().implicitlyWait(config.getConfigValueInt("ImplicitWaitTime"), TimeUnit.SECONDS);
    	
    	return sampleNodesConfigured;
	}
	
	public void ClickCreateBtn() throws Exception {

		Reporter.log("Click the 'Create' button.");
		interact.Click(waitFor.ElementVisible(Create_Btn));
		
	}
	
	public void WaitForSampleNodeCreation() throws Exception {
		
		Reporter.log("Wait for the sample nodes to be created.");
		waitFor.ElementVisible(SampleNodesCreated_Txt);
		
		new WaitFor(webDriver, 60).ElementNotPresent(Progress_Trb);
		
	}

	
}