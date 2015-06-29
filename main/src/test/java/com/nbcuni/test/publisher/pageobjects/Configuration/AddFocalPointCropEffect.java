package com.nbcuni.test.publisher.pageobjects.Configuration;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

;

/**
 * ******************************************
 * publisher.nbcuni.com Add Focal Point Crop Effect Library. Copyright
 *
 * @author Brandon Clark
 * @version 1.0 Date: March 12, 2014
 *          *******************************************
 */
public class AddFocalPointCropEffect {

    private WaitFor waitFor;
    private Config config;
    private Integer timeout;
    private Interact interact;

    //PAGE OBJECT CONSTRUCTOR
    public AddFocalPointCropEffect(WebDriver webWebWebDriver) {
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webWebWebDriver, timeout);
        interact = new Interact(webWebWebDriver, timeout);

    }


    //PAGE OBJECT IDENTIFIERS
    private By Width_Txb = By.id("edit-data-width");

    private By Height_Txb = By.id("edit-data-height");

    private By AddEffect_Btn = By.id("edit-submit");


    //PAGE OBJECT METHODS
    public void EnterWidth(String width) throws Exception {

        Reporter.log("Enter '" + width + "' in the 'Width' text box.");
        interact.Type(waitFor.ElementVisible(Width_Txb), width);

    }

    public void EnterHeight(String height) throws Exception {

        Reporter.log("Enter '" + height + "' in the 'Height' text box.");
        interact.Type(waitFor.ElementVisible(Height_Txb), height);

    }

    public void ClickAddEffectBtn() throws Exception {

        Reporter.log("Click the 'Add effect' button.");
        interact.Click(waitFor.ElementVisible(AddEffect_Btn));

    }

}