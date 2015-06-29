package com.nbcuni.test.publisher.pageobjects.Configuration;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

/**
 * ******************************************
 * publisher.nbcuni.com Entity Tracker Library. Copyright
 *
 * @author Brandon Clark
 * @version 1.0 Date: October 10, 2014
 *          *******************************************
 */
public class EntityTracker {

    private Config config;
    private Integer timeout;
    private WaitFor waitFor;
    private Interact interact;

    //PAGE OBJECT CONSTRUCTOR
    public EntityTracker(WebDriver webWebWebDriver) {
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webWebWebDriver, timeout);
        interact = new Interact(webWebWebDriver, timeout);
    }

    //PAGE OBJECT IDENTIFIERS
    private By Role_Cbx(String role) {
        return By.xpath("//label[text()='" + role + " ']/../input[contains(@id, 'edit-entity-tracker-roles')]");
    }

    private By SaveConfiguration_Btn = By.id("edit-submit");


    //PAGE OBJECT METHODS
    public void CheckRoleCbx(String roleName) throws Exception {

        WebElement ele = waitFor.ElementVisible(Role_Cbx(roleName));
        if (!ele.isSelected()) {
            Reporter.log("Check the '" + roleName + "' check box.");
            interact.Click(ele);
        }

    }

    public void ClickSaveConfigurationBtn() throws Exception {

        Reporter.log("Click the 'Save configuration' button.");
        interact.Click(waitFor.ElementVisible(SaveConfiguration_Btn));

    }

}