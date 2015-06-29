package com.nbcuni.test.publisher.pageobjects.Content;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

/**
 * ******************************************
 * publisher.nbcuni.com Publishing Options Library. Copyright
 *
 * @author Brandon Clark
 * @version 1.0 Date: December 18, 2013
 *          *******************************************
 */

public class PublishingOptions {

    private Config config;
    private Interact interact;
    private WaitFor waitFor;
    //PAGE OBJECT IDENTIFIERS
    private By PublishingOptions_Lnk = By.xpath("//a/strong[text()='Publishing options']");
    @FindBy(how = How.ID, using = "edit-revision")
    private WebElement CreateNewRevision_Cbx;
    @FindBy(how = How.ID, using = "edit-event")
    private WebElement ModerationState_Ddl;
    @FindBy(how = How.XPATH, using = "(//textarea[@id='edit-event-comment'])[1]")
    private WebElement LogMessageStateChange_Txa;
    @FindBy(how = How.ID, using = "edit-published")
    private WebElement Published_Cbx;
    @FindBy(how = How.ID, using = "edit-field-workbench-assigned-und-0-target-id")
    private WebElement AssignTo_Txb;
    @FindBy(how = How.ID, using = "edit-revision-scheduler-operation")
    private WebElement Operation_Ddl;
    @FindBy(how = How.ID, using = "edit-revision-scheduler-datetime-datepicker-popup-0")
    private WebElement Date_Txb;
    @FindBy(how = How.ID, using = "edit-revision-scheduler-datetime-timeEntry-popup-1")
    private WebElement Time_Txb;

    //PAGE OBJECT CONSTRUCTOR
    public PublishingOptions(WebDriver webWebWebDriver) {
        PageFactory.initElements(webWebWebDriver, this);
        config = new Config();
        Integer timeout = config.getConfigValueInt("WaitForWaitTime");
        interact = new Interact(webWebWebDriver, timeout);
        waitFor = new WaitFor(webWebWebDriver, timeout);
    }

    private By AutoComplete_Opt(String optionTxt) {
        return By.xpath("//div[@class='reference-autocomplete'][text()='" + optionTxt + "']");
    }

    //PAGE OBJECT METHODS
    public void ClickPublishingOptionsLnk() throws Exception {

        Reporter.log("Click the 'Publishing Options' link.");
        interact.ScrollToTop();
        interact.Click(waitFor.ElementVisible(PublishingOptions_Lnk));

    }

    public void ClickCreateNewRevisionCbx() throws Exception {

        Reporter.log("Check the 'Create New Revision' checkbox.");
        CreateNewRevision_Cbx.click();
    }

    public void VerifyCreateNewRevisionCbxChecked() throws Exception {

        Reporter.log("Verify the 'Create New Revision' checkbox is checked.");
        Assert.assertTrue(CreateNewRevision_Cbx.isSelected() == true);
    }

    public void SelectModerationState(String stateName) throws Exception {

        Reporter.log("Select '" + stateName + "' from the 'Moderation State' drop down list.");
        new Select(ModerationState_Ddl).selectByVisibleText(stateName);
    }

    public void VerifyModerationStateValue(String stateValue) throws Exception {

        Reporter.log("Verify the 'Moderation State' drop down list value is '" + stateValue + "'.");
        Assert.assertEquals(new Select(ModerationState_Ddl).getFirstSelectedOption().getText(), stateValue);
    }

    public void EnterMessageForStateChange(String messageTxt) throws Exception {

        Reporter.log("Enter '" + messageTxt + "' in the 'Message for State Change' text area.");
        LogMessageStateChange_Txa.sendKeys(messageTxt);
    }

    public void UncheckPublishedCbx() throws Exception {

        if (Published_Cbx.isSelected() == true) {
            Reporter.log("Uncheck the 'Published' checkbox");
            Published_Cbx.click();
        }
    }

    public void CheckPublishedCbx() throws Exception {

        if (Published_Cbx.isSelected() == false) {
            Reporter.log("Check the 'Published' checkbox");
            Published_Cbx.click();
        }
    }

    public void VerifyPublishedCbxChecked() throws Exception {

        Reporter.log("Verify the 'Published' checkbox is checked.");
        Assert.assertTrue(Published_Cbx.isSelected() == true);
    }

    public void VerifyAssignToValue(String userName) throws Exception {

        Reporter.log("Verify the 'Assign to' text box value is '" + userName + "'.");
        Assert.assertTrue(AssignTo_Txb.getAttribute("value").contains(userName));
    }

    public void EnterAssignTo(String userName) throws Exception {

        Reporter.log("Enter '" + userName + "' in the 'Assign to' text box.");
        AssignTo_Txb.clear();
        AssignTo_Txb.sendKeys(userName);

        Reporter.log("Click the '" + userName + "' from the auto complete option list.");
        waitFor.ElementVisible(AutoComplete_Opt(userName));
        AssignTo_Txb.sendKeys(Keys.DOWN);
        AssignTo_Txb.sendKeys(Keys.ENTER);
        waitFor.ElementNotPresent(AutoComplete_Opt(userName));

    }

    public void VerifyPublishedCbxNotCheckedAndNotEditable() throws Exception {

        Reporter.log("Verify the 'Published' checkbox is unchecked and is not enabled.");
        Assert.assertFalse(Published_Cbx.isSelected());
        Assert.assertFalse(Published_Cbx.isEnabled());
    }

    public void SelectOperation(String operationValue) throws Exception {

        Reporter.log("Select '" + operationValue + "' from the 'Select operation' drop down list.");
        new Select(Operation_Ddl).selectByVisibleText(operationValue);
    }

    public void EnterDate(String date) throws Exception {

        Reporter.log("Enter '" + date + "' in the 'Date' text box.");
        Date_Txb.sendKeys(date);
    }

    public void EnterTime(String time) throws Exception {

        Reporter.log("Enter '" + time + "' in the 'Time' text box.");
        Time_Txb.click();
        Time_Txb.sendKeys(time);
    }

}

