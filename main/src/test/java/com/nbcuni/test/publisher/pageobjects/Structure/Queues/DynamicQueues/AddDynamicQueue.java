package com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues;

import java.util.ArrayList;
import java.util.List;

import com.nbcuni.test.publisher.common.Driver.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

/*********************************************
 * publisher.nbcuni.com Add Dynamic Queue Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: July 30, 2014
 * @author Vineela Juturu
 * @version 1.1 Date September 30, 2014
 *********************************************/

public class AddDynamicQueue {

    private Driver webDriver;
    
    //PAGE OBJECT CONSTRUCTOR
    public AddDynamicQueue(Driver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.ID, using = "edit-title")
    private WebElement Title_Txb;
    
    private List<WebElement> AllTargetBundle_Cbxs() {
    	return webDriver.findElements(By.xpath("//div[@id='edit-settings-target-bundles']//input"));
    }
    
    private WebElement TargetBundle_Cbx(String contentType) {
    	return webDriver.findElement(By.xpath("//label[text()='" + contentType + " ']/../input"));
    }
    
    private WebElement FieldMovieType_Opt(String option) {
    	return webDriver.findElement(By.xpath("//select[@id='edit-settings-terms-field-movie-type']//option[contains(text(), '" + option + "')]"));
    }
    
    private WebElement FieldMoviePrimaryGenre_Opt(String option) {
    	return webDriver.findElement(By.xpath("//select[@id='edit-settings-terms-field-movie-primary-genre']//option[contains(text(), '" + option + "')]"));
    }
    
    private WebElement FieldMovieSecondaryGenre_Opt(String option) {
    	return webDriver.findElement(By.xpath("//select[@id='edit-settings-terms-field-movie-secondary-genre']//option[contains(text(), '" + option + "')]"));
    }
    
    private WebElement FieldMovieRating_Opt(String option) {
    	return webDriver.findElement(By.xpath("//select[@id='edit-settings-terms-field-movie-rating']//option[contains(text(), '" + option + "')]"));
    }
    
    @FindBy(how = How.ID, using = "edit-settings-tv-show-select")
    private WebElement TVShows_Ddl;
    
    @FindBy(how = How.ID, using = "edit-settings-sort-dynamic-queue-modifier-newest")
    private WebElement SortByNewest_Rdb;
    
    @FindBy(how = How.ID, using = "edit-settings-sort-dynamic-queue-modifier-oldest")
    private WebElement SortByOldest_Rdb;
    
    @FindBy(how = How.ID, using = "edit-status")
    private WebElement Published_Cbx;
    
    @FindBy(how = How.ID, using = "edit-submit")
    private WebElement SaveDynamicQueue_Btn;
    
    @FindBy(how = How.ID, using = "edit-settings-pager")
    private WebElement ItemsPerPage_Txb;
    
    @FindBy(how = How.ID, using = "edit-settings-limit")
    private WebElement TotalLimit_Txb;
    
    @FindBy(how = How.ID, using = "edit-event")
    private WebElement ModerationState_Ddl;
    
    @FindBy(how = How.ID, using = "edit-revision")
    private WebElement Createnewrevision_Cbx;
    
    //PAGE OBJECT METHODS
    public void EnterTitle(String queueTitle) throws Exception {
    	
    	Reporter.log("Enter '" + queueTitle + "' in the 'Title' text box.");
    	Title_Txb.click();
    	Title_Txb.clear();
    	Title_Txb.sendKeys(queueTitle);
    }
    
    public void CheckTargetBundle_Cbx(String contentType) throws Exception {
    	
    	if (TargetBundle_Cbx(contentType).isSelected() == false) {
    		Reporter.log("");
    		TargetBundle_Cbx(contentType).click();
    	}
    	
    }
    
    public void VerifyTargetBundlesPresent(List<String> contentTypes) throws Exception {
    	
    	Reporter.log("Verify Target bundle content count equals '" + contentTypes.size() + "'.");
    	Assert.assertEquals(AllTargetBundle_Cbxs().size(), contentTypes.size());
    	
    	List<String> allTargetBundleValues = new ArrayList<String>();
    	for (WebElement cbx : AllTargetBundle_Cbxs()) {
    		allTargetBundleValues.add(cbx.getAttribute("value"));
    	}
    	
    	for (String contentType : contentTypes) {
    		Reporter.log("Verify content type '" + contentType + "' is present in list of Target bundles.");
    		Assert.assertTrue(allTargetBundleValues.contains(contentType.toLowerCase()), "Content type '" + contentType 
    				+ "' is not present in list of target bundles.");
    	}
    }
    
    public void SelectFieldMovieTypeTaxonomy(String option) throws Exception {
    	
    	Reporter.log("Select '" + option + "' from the 'Taxonomy filter for field_movie_type (Movie)' multi-select list.");
    	FieldMovieType_Opt(option).click();
    }
    
    public void SelectFieldMoviePrimaryGenreTaxonomy(String option) throws Exception {
    	
    	Reporter.log("Select '" + option + "' from the 'Taxonomy filter for field_movie_primary_genre (Movie)' multi-select list.");
    	FieldMoviePrimaryGenre_Opt(option).click();
    }
    
    public void SelectFieldMovieSecondaryGenreTaxonomy(String option) throws Exception {
    	
    	Reporter.log("Select '" + option + "' from the 'Taxonomy filter for field_movie_secondary_genre (Movie)' multi-select list.");
    	FieldMovieSecondaryGenre_Opt(option).click();
    }
    
    public void SelectFieldMovieRatingTaxonomy(String option) throws Exception {
    	
    	Reporter.log("Select '" + option + "' from the 'Taxonomy filter for field_movie_rating (Movie)' multi-select list.");
    	FieldMovieRating_Opt(option).click();
    }
    
    public void SelectTVShow(String showTitle) throws Exception {
    	
    	Reporter.log("Select '" + showTitle + "' from the 'TV Shows' drop down list.");
    	new Select(TVShows_Ddl).selectByVisibleText(showTitle);
    }

    public void ClickSortByNewestRdb() throws Exception {
    	
    	Reporter.log("Click the 'Newest' radio button under 'Sort by'.");
    	SortByNewest_Rdb.click();
    }
    
    public void ClickSortByOldestRdb() throws Exception {
    	
    	Reporter.log("Click the 'Oldest' radio button under 'Sort by'.");
    	SortByOldest_Rdb.click();
    }

    public void ClickSaveDynamicQueueBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save Dynamic Queue' button.");
    	SaveDynamicQueue_Btn.click();
    }
    
    public void EnterItemsPerPage(int itemsPerPage){
    	
    	Reporter.log("Set '"+itemsPerPage+"' in ItemsPerPage textbox.");
    	
    	ItemsPerPage_Txb.click();
    	ItemsPerPage_Txb.clear();
    	ItemsPerPage_Txb.sendKeys(String.valueOf(itemsPerPage));
    	
    }
    
    public void EnterTotalItemsLimit(int totalLimit){
    	
    	Reporter.log("Set '"+totalLimit+"' in TotalLimit textbox.");
    	
    	TotalLimit_Txb.click();
    	TotalLimit_Txb.clear();
    	TotalLimit_Txb.sendKeys(String.valueOf(totalLimit));
    	
    }
    
    public void SelectModerationState(String state) throws Exception {
    	
    	Reporter.log("Select '" + state + "' from the 'TV Shows' drop down list.");
    	new Select(ModerationState_Ddl).selectByVisibleText(state);
    }
    
    public void CheckCreatenewrevision_Cbx() throws Exception {
    	
    	if (Createnewrevision_Cbx.isSelected() == false) {
    		Reporter.log("");
    		Createnewrevision_Cbx.click();
    	}
    	
    }
}

