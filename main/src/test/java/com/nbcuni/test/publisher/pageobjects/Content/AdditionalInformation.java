package com.nbcuni.test.publisher.pageobjects.Content;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
 * publisher.nbcuni.com Additional Information Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.1 Date: October 13, 2014
 *********************************************/

public class AdditionalInformation {

	private Config config;
	private Integer timeout;
	private WaitFor waitFor;
	private Interact interact;
	
    //PAGE OBJECT CONSTRUCTOR
    public AdditionalInformation(Driver webDriver) {
    	config = new Config();
    	timeout = config.getConfigValueInt("WaitForWaitTime");
    	waitFor = new WaitFor(webDriver, timeout);
    	interact = new Interact(webDriver, timeout);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By AdditionalInformation_Lnk = By.xpath("//a/strong[text()='Additional Information']");
    
    private By MovieType_Ddl = By.id("edit-field-movie-type-und");
    
    private By Rating_Ddl = By.id("edit-field-movie-rating-und");
    
    private By PrimaryGenre_Ddl = By.id("edit-field-movie-primary-genre-und");
    
    private By Tags_Txb = By.id("edit-field-content-tags-und");
    
    
    //PAGE OBJECT METHODS
    public void ClickAdditionalInformationLnk() throws Exception {
    	
    	Reporter.log("Click the 'Additional Information' link.");
    	interact.ScrollToTop();
    	interact.Click(waitFor.ElementVisible(AdditionalInformation_Lnk));
    	
    }
    
    public void SelectMovieType(String option) throws Exception {
    	
    	Reporter.log("Select the '" + option + "' from the 'Movie Type' drop down list.");
    	interact.Select(waitFor.ElementVisible(MovieType_Ddl), option);
    	
    }
    
    public void SelectRating(String option) throws Exception {
    	
    	Reporter.log("Select the '" + option + "' from the 'Rating' drop down list.");
    	interact.Select(waitFor.ElementVisible(Rating_Ddl), option);
    	
    }
    
    public void SelectPrimaryGenre(String option) throws Exception {
    	
    	Reporter.log("Select the '" + option + "' from the 'Primary Genre' drop down list.");
    	interact.Select(waitFor.ElementVisible(PrimaryGenre_Ddl), option);
    	
    }
    
    public void EnterTag(String tag) throws Exception {
    	
    	Reporter.log("Enter '" + tag + "' in the 'Tags' text box.");
    	WebElement ele = waitFor.ElementVisible(Tags_Txb);
    	String eleValue = ele.getAttribute("value");
    	if (eleValue.equals("")) {
    		interact.Type(ele, tag);
    	}
    	else {
    		interact.Type(ele, eleValue + ", " + tag);
    	}
    	
    }
    
}

