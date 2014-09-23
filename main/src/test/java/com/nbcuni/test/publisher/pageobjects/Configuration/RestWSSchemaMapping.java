package com.nbcuni.test.publisher.pageobjects.Configuration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
* publisher.nbcuni.com RestWS Schema Mapping Library. Copyright
*
* @author Brandon Clark
* @version 1.1 Date: September 22, 2014
*********************************************/
public class RestWSSchemaMapping {
		
	//PAGE OBJECT CONSTRUCTOR
	public RestWSSchemaMapping(Driver webDriver) {
		PageFactory.initElements(webDriver, this);
		
	}

	//PAGE OBJECT IDENTIFIERS
	@FindBy(how = How.ID, using ="edit-showseasons")
	private WebElement TheShowsSeasons_Ddl;

	@FindBy(how = How.ID, using ="edit-seasonepisodes")
	private WebElement TheSeasonsEpisodes_Ddl;

	@FindBy(how = How.ID, using ="edit-episodeseason")
	private WebElement TheEpisodesSeason_Ddl;
	
	@FindBy(how = How.ID, using ="edit-blogshow")
	private WebElement TheBlogsShow_Ddl;
	
	@FindBy(how = How.ID, using ="edit-galleryshow")
	private WebElement TheGallerysShow_Ddl;

	@FindBy(how = How.ID, using ="edit-submit")
	private WebElement SaveConfiguration_Btn;
	
	
	//PAGE OBJECT METHODS
	public void SelectShowSeason(String option) throws Exception {
		
		Reporter.log("Select '" + option + "' from 'The show's seasons' drop down list.");
		new Select(TheShowsSeasons_Ddl).selectByVisibleText(option);
	}
	
	public void SelectSeasonsEpisodes(String option) throws Exception {
		
		Reporter.log("Select '" + option + "' from 'The season's episodes' drop down list.");
		new Select(TheSeasonsEpisodes_Ddl).selectByVisibleText(option);
	}
	
	public void SelectEpisodesSeason(String option) throws Exception {
		
		Reporter.log("Select '" + option + "' from 'The episode's season' drop down list.");
		new Select(TheEpisodesSeason_Ddl).selectByVisibleText(option);
	}
	
	public void SelectBlogsShow(String option) throws Exception {
		
		Reporter.log("Select '" + option + "' from 'The blog's show' drop down list.");
		new Select(TheBlogsShow_Ddl).selectByVisibleText(option);
	}
	
	public void SelectGallerysShow(String option) throws Exception {
		
		Reporter.log("Select '" + option + "' from 'The gallery's show' drop down list.");
		new Select(TheGallerysShow_Ddl).selectByVisibleText(option);
	}
	
	public void ClickSaveConfigurationBtn() throws Exception {
		
		Reporter.log("Click the 'Save configuration' button.");
		SaveConfiguration_Btn.click();
	}
	
}