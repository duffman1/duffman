package com.nbcuni.test.publisher.pageobjects.Configuration;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import java.util.concurrent.TimeUnit;

/**
 * ******************************************
 * publisher.nbcuni.com Image Styles Library. Copyright
 *
 * @author Brandon Clark
 * @version 1.0 Date: March 12, 2014
 *          *******************************************
 */
public class ImageStyles {

    private WebDriver webWebWebDriver;
    private Config config;
    private Integer timeout;
    private WaitFor waitFor;
    private Interact interact;

    //PAGE OBJECT CONSTRUCTOR
    public ImageStyles(WebDriver webWebWebDriver) {
        this.webWebWebDriver = webWebWebDriver;
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webWebWebDriver, timeout);
        interact = new Interact(webWebWebDriver, timeout);

    }

    //PAGE OBJECT IDENTIFIERS
    private By AddStyle_Lnk = By.linkText("Add style");

    private By StyleName_Txb = By.id("edit-label");

    private By CreateNewStyle_Btn = By.id("edit-submit");

    private By Effect_Ddl = By.cssSelector("select[name='new']");

    private By Add_Btn = By.id("edit-add");

    private By UpdateStyle_Btn = By.id("edit-actions-submit");

    private By Style_Lnk(String styleName) {
        return By.xpath("//a[text()='" + styleName + "']");
    }

    private By StyleDelete_Lnk(String styleName) {
        return By.xpath("//a[text()='" + styleName + "']/../..//a[text()='delete']");
    }


    //PAGE OBJECT METHODS
    public void ClickAddStyleLnk() throws Exception {

        Reporter.log("Click the 'Add style' link.");
        interact.Click(waitFor.ElementVisible(AddStyle_Lnk));

    }

    public boolean FocalImageStylePresent(String styleName) throws Exception {

        webWebWebDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        boolean elPresent = false;

        try {
            webWebWebDriver.findElement(Style_Lnk(styleName));
            elPresent = true;
        } catch (Exception e) {
            elPresent = false;
        }

        webWebWebDriver.manage().timeouts().implicitlyWait(config.getConfigValueInt("ImplicitWaitTime"), TimeUnit.SECONDS);

        return elPresent;
    }

    public void ClickDeleteStyleLnk(String styleName) throws Exception {

        Reporter.log("Click the 'delete' link for style name '" + styleName + "'.");
        interact.Click(waitFor.ElementVisible(StyleDelete_Lnk(styleName)));

    }

    public void EnterStyleName(String name) throws Exception {

        Reporter.log("Enter '" + name + "' in the 'Style name' text box.");
        interact.Type(waitFor.ElementVisible(StyleName_Txb), name);

    }

    public void ClickCreateNewStyleBtn() throws Exception {

        Reporter.log("Click the 'Create new style' button.");
        interact.Click(waitFor.ElementVisible(CreateNewStyle_Btn));

    }

    public void ClickAddBtn() throws Exception {

        Reporter.log("Click the 'Add' button.");
        interact.Click(waitFor.ElementVisible(Add_Btn));

    }

    public void SelectEffect(String option) throws Exception {

        Reporter.log("Select '" + option + "' from the 'EFFECT' drop down list.");
        interact.Select(waitFor.ElementVisible(Effect_Ddl), option);

    }

    public void ClickUpdateStyleBtn() throws Exception {

        Reporter.log("Click the 'Update style' button.");
        interact.Click(waitFor.ElementVisible(UpdateStyle_Btn));

    }

}