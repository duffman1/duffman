package com.nbcuni.test.publisher.pageobjects.Structure;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.component.annotations.Page;
import com.nbcuni.test.publisher.common.Driver.configuration.SeleniumContext;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.testng.Reporter;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.Link;
import ru.yandex.qatools.htmlelements.element.TextInput;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * ******************************************
 * publisher.nbcuni.com ContentTypes Library. Copyright
 *
 * @author Brandon Clark
 * @version 1.1 Date: April 13, 2014
 *          *******************************************
 */

@Configuration
@Scope("prototype")
@Page
public class ContentTypes extends com.nbcuni.test.publisher.pageobjects.Page {

//    private WebDriver webDriver;
//    private Config config;
//    private Integer timeout;
//    private WaitFor waitFor;
//    private Interact interact;

    @Autowired
    SeleniumContext context;

    public ContentTypes() {};

    @PostConstruct
    public void init() {
        webDriver = context.webDriver();
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webDriver, timeout);
        interact = new Interact(webDriver, timeout);
    }

    //PAGE OBJECT CONSTRUCTOR
    public ContentTypes(WebDriver webDriver) {

        this.webDriver = webDriver;
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webDriver, timeout);
        interact = new Interact(webDriver, timeout);

    }

    @FindBy(css = "div#content a[href$='add']")
    private Link addContentTypes;

    @FindBy(css = "div.media-thumbnail img")
    private WebElement image;

    @FindBy(id = "edit-title")
    private TextInput title;

    @FindBy(css = "a.button.browse")
    Button browse;

    //PAGE OBJECT IDENTIFIERS
    private By AddContentType_Lnk = By.linkText("Add content type");

    private By ManageField_Lnk(String lnkTxt) {
        return By.xpath("//td[contains(text(), '" + lnkTxt + "')]/..//a[text()='manage fields']");
    }

    private By ManageDisplay_Lnk(String lnkTxt) {
        return By.xpath("//td[contains(text(), '" + lnkTxt + "')]/..//a[text()='manage display']");
    }

    private By Edit_Lnk(String lnkTxt) {
        return By.xpath("//td[contains(text(), '" + lnkTxt + "')]/..//a[text()='edit']");
    }

    private By Name_Txb = By.id("edit-name");

    private By Save_Btn = By.id("edit-submit");

    private By SaveAddFields_Btn = By.id("edit-save-continue");

    private By AddNewField_Txb = By.xpath("//input[@id='edit-fields-add-new-field-label']");

    private By AddExistingField_Txb = By.id("edit-fields-add-existing-field-label");

    private By FieldType_Ddl = By.id("edit-fields-add-new-field-type");

    private By SelectExistingField_Ddl = By.id("edit-fields-add-existing-field-field-name");

    private By Widget_Ddl = By.id("edit-fields-add-new-field-widget-type");

    private By FieldSelect_Btn(String fieldName) {
        return By.id("edit-field-" + fieldName + "-und-0-browse-button");
    }

    private By Field_Txt(String fieldName) {
        return By.xpath("//td[contains(text(), '" + fieldName + "')]");
    }


    //PAGE OBJECT METHODS
    public void ClickAddContentLnk() throws Exception {

        Reporter.log("Click the 'Add content type'.");
        interact.Click(waitFor.ElementPresent(AddContentType_Lnk));

    }

    public void ClickManageFieldLnk(String lnkTxt) throws Exception {

        Reporter.log("Click the 'manage field' link for content type '" + lnkTxt + "'.");
        interact.Click(waitFor.ElementPresent(ManageField_Lnk(lnkTxt)));

    }

    public void ClickManageDisplayLnk(String lnkTxt) throws Exception {

        Reporter.log("Click the 'manage display' link for content type '" + lnkTxt + "'.");
        interact.Click(waitFor.ElementPresent(ManageDisplay_Lnk(lnkTxt)));

    }

    public void ClickEditLnk(String lnkTxt) throws Exception {

        Reporter.log("Click the 'edit' link for field '" + lnkTxt + "'.");
        interact.Click(waitFor.ElementPresent(Edit_Lnk(lnkTxt)));

    }

    public ContentTypes EnterName(String name) throws Exception {

        Reporter.log("Enter '" + name + "' in the 'Name' text box.");
        interact.Type(waitFor.ElementVisible(Name_Txb), name);
        return this;

    }

    public ContentTypes ClickSaveBtn() throws Exception {

        Reporter.log("Click the 'Save' button.");
        interact.Click(waitFor.ElementVisible(Save_Btn));
        Thread.sleep(1000);
        return this;
    }

    public ContentTypes addContentType() {
        addContentTypes.click();
        return this;
    }

    public ContentTypes ClickSaveAddFieldsBtn() throws Exception {

        Reporter.log("Click the 'Save and add fields' button.");
        interact.Click(waitFor.ElementVisible(SaveAddFields_Btn));
        return this;
    }

    public ContentTypes EnterAddNewField(String fieldName) throws Exception {

        Reporter.log("Enter '" + fieldName + "' in the 'Add new field' text box.");
        interact.Type(waitFor.ElementVisible(AddNewField_Txb), fieldName);
        return this;
    }

    public void EnterAddExistingField(String fieldName) throws Exception {

        Reporter.log("Enter '" + fieldName + "' in the 'Add existing field' text box.");
        interact.Type(waitFor.ElementVisible(AddExistingField_Txb), fieldName);

    }

    public ContentTypes SelectFieldType(String fieldType) throws Exception {

        Reporter.log("Select '" + fieldType + "' from the 'Field Type' drop down list.");
        interact.Select(waitFor.ElementVisible(FieldType_Ddl), fieldType);
        return this;
    }

    public void SelectExistingField(String fieldType) throws Exception {

        Reporter.log("Select '" + fieldType + "' from the 'select existing field' drop down list.");
        interact.Select(waitFor.ElementVisible(SelectExistingField_Ddl), fieldType);

    }

    public ContentTypes SelectWidget(String widget) throws Exception {

        Reporter.log("Select '" + widget + "' from the 'Widget' drop down list.");
        interact.Select(waitFor.ElementVisible(Widget_Ddl), widget);
        return this;
    }

    public void VerifyFieldSelectBtnPresent(String fieldName) throws Exception {

        Reporter.log("Verify the Field 'Select' button is present.");
        waitFor.ElementVisible(FieldSelect_Btn(fieldName));

    }

    public void isRendered() {
         new WebDriverWait(webDriver, 8).until(ExpectedConditions.visibilityOf(image));

    }

    public void setTitle(String title) {
        this.title.sendKeys(title);
    }

    public void browseFile() {
        browse.click();
    }

    public Boolean IsFieldPresent(String fieldName) throws Exception {

        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        Boolean fieldPresent;

        try {
            webDriver.findElement(Field_Txt(fieldName));
            fieldPresent = true;
        } catch (NoSuchElementException e) {
            fieldPresent = false;
        }

        webDriver.manage().timeouts().implicitlyWait(config.getConfigValueInt("ImplicitWaitTime"), TimeUnit.SECONDS);

        return fieldPresent;

    }

}

