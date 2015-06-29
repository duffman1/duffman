package com.nbcuni.test.publisher.pageobjects.Configuration;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

/**
 * ******************************************
 * publisher.nbcuni.com RestWS Schema Mapping Library. Copyright
 *
 * @author Brandon Clark
 * @version 1.1 Date: September 22, 2014
 *          *******************************************
 */
public class RestWSSchemaMapping {

    private Config config;
    private Integer timeout;
    private WaitFor waitFor;
    private Interact interact;

    //PAGE OBJECT CONSTRUCTOR
    public RestWSSchemaMapping(WebDriver webWebWebDriver) {
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webWebWebDriver, timeout);
        interact = new Interact(webWebWebDriver, timeout);
    }

    //PAGE OBJECT IDENTIFIERS
    private By TheShowsSeasons_Ddl = By.id("edit-showseasons");

    private By TheSeasonsEpisodes_Ddl = By.id("edit-seasonepisodes");

    private By TheEpisodesSeason_Ddl = By.id("edit-episodeseason");

    private By TheBlogsShow_Ddl = By.id("edit-blogshow");

    private By TheGallerysShow_Ddl = By.id("edit-galleryshow");

    private By SaveConfiguration_Btn = By.id("edit-submit");


    //PAGE OBJECT METHODS
    public void SelectShowSeason(String option) throws Exception {

        Reporter.log("Select '" + option + "' from 'The show's seasons' drop down list.");
        interact.Select(waitFor.ElementVisible(TheShowsSeasons_Ddl), option);

    }

    public void SelectSeasonsEpisodes(String option) throws Exception {

        Reporter.log("Select '" + option + "' from 'The season's episodes' drop down list.");
        interact.Select(waitFor.ElementVisible(TheSeasonsEpisodes_Ddl), option);

    }

    public void SelectEpisodesSeason(String option) throws Exception {

        Reporter.log("Select '" + option + "' from 'The episode's season' drop down list.");
        interact.Select(waitFor.ElementVisible(TheEpisodesSeason_Ddl), option);

    }

    public void SelectBlogsShow(String option) throws Exception {

        Reporter.log("Select '" + option + "' from 'The blog's show' drop down list.");
        interact.Select(waitFor.ElementVisible(TheBlogsShow_Ddl), option);

    }

    public void SelectGallerysShow(String option) throws Exception {

        Reporter.log("Select '" + option + "' from 'The gallery's show' drop down list.");
        interact.Select(waitFor.ElementVisible(TheGallerysShow_Ddl), option);

    }

    public void ClickSaveConfigurationBtn() throws Exception {

        Reporter.log("Click the 'Save configuration' button.");
        interact.Click(waitFor.ElementVisible(SaveConfiguration_Btn));

    }

}