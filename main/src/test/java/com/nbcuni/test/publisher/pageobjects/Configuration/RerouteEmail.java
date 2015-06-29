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
 * publisher.nbcuni.com Reroute Email Library. Copyright
 *
 * @author Brandon Clark
 * @version 1.0 Date: February 13, 2014
 *          *******************************************
 */

public class RerouteEmail {

    private Config config;
    private Integer timeout;
    private WaitFor waitFor;
    private Interact interact;

    //PAGE OBJECT CONSTRUCTOR    
    public RerouteEmail(WebDriver webWebWebDriver) {
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webWebWebDriver, timeout);
        interact = new Interact(webWebWebDriver, timeout);
    }

    //PAGE OBJECT IDENTIFIERS    
    private By EnableRerouting_Cbx = By.id("edit-reroute-email-enable");

    private By EmailAddresses_Txb = By.id("edit-reroute-email-address");

    private By SaveConfiguration_Btn = By.id("edit-submit");


    //PAGE OBJECT METHODS
    public void CheckEnableReroutingCbx() throws Exception {

        WebElement ele = waitFor.ElementVisible(EnableRerouting_Cbx);
        if (ele.isSelected() == false) {
            Reporter.log("Click the 'Enable rerouting' checkbox.");
            interact.Click(ele);
        }

    }

    public void EnterEmailAddresses(String emailAddresses) throws Exception {

        Reporter.log("Enter '" + emailAddresses + "' in the 'Email addresses' text box.");
        interact.Type(waitFor.ElementVisible(EmailAddresses_Txb), emailAddresses);

    }

    public void ClickSaveConfigurationBtn() throws Exception {

        Reporter.log("Click the 'Save configuration' button.");
        interact.Click(waitFor.ElementVisible(SaveConfiguration_Btn));

    }


}