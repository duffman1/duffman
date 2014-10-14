package com.nbcuni.test.publisher.pageobjects.Content;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com Additional Information Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.1 Date: October 13, 2014
 *********************************************/

public class AdditionalInformation {

	private Driver webDriver;
	private WebDriverWait wait;
	
    //PAGE OBJECT CONSTRUCTOR
    public AdditionalInformation(Driver webDriver) {
    	this.webDriver = webDriver;
    	PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(webDriver, 10);
        
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//a/strong[text()='Additional Information']")
    private WebElement AdditionalInformation_Lnk;
    
    @FindBy(how = How.ID, using = "edit-field-movie-type-und")
    private WebElement MovieType_Ddl;
    
    @FindBy(how = How.ID, using = "edit-field-movie-rating-und")
    private WebElement Rating_Ddl;
    
    @FindBy(how = How.ID, using = "edit-field-movie-primary-genre-und")
    private WebElement PrimaryGenre_Ddl;
    
    @FindBy(how = How.ID, using = "edit-field-content-tags-und")
    private WebElement Tags_Txb;
    
    
    //PAGE OBJECT METHODS
    public void ClickAdditionalInformationLnk() throws Exception {
    	
    	Reporter.log("Click the 'Additional Information' link.");
    	try {
    		AdditionalInformation_Lnk.click();
    	}
    	catch (WebDriverException e) {
    		webDriver.executeScript("arguments[0].click();", AdditionalInformation_Lnk);
    	}
    	
    }
    
    public void SelectMovieType(String option) throws Exception {
    	
    	Reporter.log("Select the '" + option + "' from the 'Movie Type' drop down list.");
    	new Select(wait.until(ExpectedConditions.visibilityOf(MovieType_Ddl))).selectByVisibleText(option);
    }
    
    public void SelectRating(String option) throws Exception {
    	
    	Reporter.log("Select the '" + option + "' from the 'Rating' drop down list.");
    	new Select(wait.until(ExpectedConditions.visibilityOf(Rating_Ddl))).selectByVisibleText(option);
    }
    
    public void SelectPrimaryGenre(String option) throws Exception {
    	
    	Reporter.log("Select the '" + option + "' from the 'Primary Genre' drop down list.");
    	new Select(wait.until(ExpectedConditions.visibilityOf(PrimaryGenre_Ddl))).selectByVisibleText(option);
    }
    
    public void EnterTag(String tag) throws Exception {
    	
    	Reporter.log("Enter '" + tag + "' in the 'Tags' text box.");
    	wait.until(ExpectedConditions.visibilityOf(Tags_Txb));
    	if (Tags_Txb.getAttribute("value").equals("")) {
    		Tags_Txb.sendKeys(tag);
    	}
    	else {
    		Tags_Txb.sendKeys(", " + tag);
    	}
    }
    
}

