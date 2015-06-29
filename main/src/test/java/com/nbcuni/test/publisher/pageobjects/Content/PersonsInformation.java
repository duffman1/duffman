package com.nbcuni.test.publisher.pageobjects.Content;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Random;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

/**
 * ******************************************
 * publisher.nbcuni.com PersonsInformation Library. Copyright
 *
 * @author Brandon Clark
 * @version 1.1 Date: September 16, 2014
 *          *******************************************
 */

public class PersonsInformation {

    private WebDriver webWebWebDriver;
    private Config config;
    private Integer timeout;
    private WaitFor waitFor;
    private Interact interact;
    //PAGE OBJECT IDENTIFIERS
    private By FirstName_Txb = By.id("edit-field-person-first-name-und-0-value");
    private By Biography_Frm = By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']");
    private By BiographyW_Frm = By.xpath("//iframe[@id='edit-body-und-0-value_ifr']");
    private By Biography_Txa = By.xpath("//body[contains(@class, 'editable')]");
    private By BiographyT_Txa = By.xpath("//body[@id='tinymce']");
    private By CoverPhotoSelect_Btn = By.xpath("//a[@id='edit-field-person-cover-photo-und-0-browse-button']");

    //PAGE OBJECT CONSTCUTOR
    public PersonsInformation(WebDriver webWebWebDriver) {
        this.webWebWebDriver = webWebWebDriver;
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webWebWebDriver, timeout);
        interact = new Interact(webWebWebDriver, timeout);
    }

    //PAGE OBJECT METHODS
    public void EnterFirstName(String firstName) throws Exception {

        Reporter.log("Enter '" + firstName + "' in the 'First name' text box.");
        interact.Type(waitFor.ElementVisible(FirstName_Txb), firstName);

    }

    public String EnterBiography() throws Exception {

        webWebWebDriver.switchTo().frame(waitFor.OneElementOrAnotherPresent(Biography_Frm, BiographyW_Frm));

        Reporter.log("Enter a random block of text in the 'Biography' text area.");
        Random random = new Random();
        String body = random.GetCharacterString(20) + " " +
                random.GetCharacterString(20) + " " +
                random.GetCharacterString(20) + " " +
                random.GetCharacterString(20);

        interact.Type(waitFor.OneElementOrAnotherPresent(Biography_Txa, BiographyT_Txa), body);

        webWebWebDriver.switchTo().defaultContent();

        return body;
    }

    public void EnterBiography(String biography) throws Exception {

        webWebWebDriver.switchTo().frame(waitFor.OneElementOrAnotherPresent(Biography_Frm, BiographyW_Frm));

        Reporter.log("Enter '" + biography + "' into the 'Biography' text area.");

        interact.Type(waitFor.OneElementOrAnotherPresent(Biography_Txa, BiographyT_Txa), biography);

        webWebWebDriver.switchTo().defaultContent();

    }

    public void ClickCoverPhotoSelectBtn() throws Exception {

        Reporter.log("Click the 'Cover Photo' Select button.");
        interact.Click(waitFor.ElementVisible(CoverPhotoSelect_Btn));

    }

}

