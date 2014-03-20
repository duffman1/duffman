package com.nbcuni.test.publisher.pageobjects.Content;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

/*********************************************
 * publisher.nbcuni.com Relationships Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: March 19, 2014
 *********************************************/
public class Relationships {

	CustomWebDriver webDriver;
	WebDriverWait wait;
	
	//PAGE OBJECT CONSTRUCTORS
    public Relationships(CustomWebDriver webDriver, AppLib applib) {
    	this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(webDriver, 10);
    }
    
    //PAGE OBJECT IDENTIFIERS AND SCRIPTS
    @FindBy(how = How.XPATH, using = "//a/strong[text()='Relationships']")
    private static WebElement Relationships_Lnk;
    
    @FindBy(how = How.CSS, using = "select[id*='edit-field-pub-relation-tv-shows-und-0-field-show']")
    private static WebElement Show_Ddl;
    
    @FindBy(how = How.CSS, using = "select[id*='edit-field-pub-relation-tv-shows-und-0-field-season")
    private static WebElement Season_Ddl;
    
    @FindBy(how = How.CSS, using = "select[id*='edit-field-pub-relation-movie")
    private static WebElement Movie_Ddl;
    
    
    //PAGE OBJECT METHODS
    public void ClickRelationshipsLnk() throws Exception {
    
    	Reporter.log("Click the 'Relationships' link.");
    	Relationships_Lnk.click();

    }
    
    public void SelectShow(String showTitle) throws Exception {
        
    	Reporter.log("Select '" + showTitle + "' from the 'Show' drop down list.");
    	new Select(wait.until(ExpectedConditions.visibilityOf(Show_Ddl))).selectByVisibleText(showTitle);

    }
    
    public void SelectMovie(String movieTitle) throws Exception {
        
    	Reporter.log("Select '" + movieTitle + "' from the 'Movie' drop down list.");
    	new Select(wait.until(ExpectedConditions.visibilityOf(Movie_Ddl))).selectByVisibleText(movieTitle);

    }
    
    public void SelectSeason(String seasonName) throws Exception {
        
    	Reporter.log("Select '" + seasonName + "' from the 'Season' drop down list.");
    	 wait.until(new ExpectedCondition<Boolean>(){
    		 @Override
    		 public Boolean apply(WebDriver webDriver) {
    		 WebElement element = Season_Ddl;
    		 return element.isEnabled() && element.isDisplayed();
    		 }
    	}); 
    	new Select(Season_Ddl).selectByVisibleText(seasonName);
    	
    }

}
