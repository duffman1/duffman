package com.nbcuni.test.publisher.pageobjects.Configuration;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
* publisher.nbcuni.com Event Countdown Config Library. Copyright
*
* @author Brandon Clark
* @version 1.0 Date: September 3, 2014
*********************************************/
public class EventCountdownConfig {

	private Driver webDriver;
	private Config config;
	
	//PAGE OBJECT CONSTRUCTOR
	public EventCountdownConfig(Driver webDriver) {
		this.webDriver = webDriver;
		PageFactory.initElements(webDriver, this);
		config = new Config();
	}

	//PAGE OBJECT IDENTIFIERS
	@FindBy(how = How.ID, using ="edit-create-timer-events-submit")
	private WebElement Create_Btn;
	
	@FindBy(how = How.XPATH, using ="//p[text()='The Event Countdown sample nodes have been created.']")
	private WebElement SampleNodesCreated_Txt;
	
	@FindBy(how = How.XPATH, using ="//div[@class='message'][text()='Content changes are now visible for everybody!']")
	private WebElement Progress_Trb;

	
	//PAGE OBJECT METHODS
	public Boolean AreSampleNodesConfigured() throws Exception {
		
		webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	boolean sampleNodesConfigured = false;

    	try {
    		SampleNodesCreated_Txt.getLocation();
            sampleNodesConfigured = true;
        }
        catch (Exception e){
            sampleNodesConfigured = false;
        }
            
    	webDriver.manage().timeouts().implicitlyWait(config.getImplicitWaitTime(), TimeUnit.SECONDS);
    	
    	return sampleNodesConfigured;
	}
	
	public void ClickCreateBtn() throws Exception {

		Reporter.log("Click the 'Create' button.");
		Create_Btn.click();
	}
	
	public void WaitForSampleNodeCreation() throws Exception {
		
		Reporter.log("Wait for the sample nodes to be created.");
		new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(SampleNodesCreated_Txt));
		new WebDriverWait(webDriver, 60).until(ExpectedConditions.not(ExpectedConditions.visibilityOf(Progress_Trb)));
	}

	
}