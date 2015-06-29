package com.nbcuni.test.publisher.pageobjects.FileTypes;

import com.nbcuni.test.publisher.common.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import java.util.concurrent.TimeUnit;

/**
 * ******************************************
 * publisher.nbcuni.com Manage Fields Library. Copyright
 *
 * @author Brandon Clark
 * @version 1.0 Date: February 3, 2014
 *          *******************************************
 */

public class ManageFields {

    private WebDriver webWebWebDriver;
    private Config config;

    //PAGE OBJECT CONSTRUCTOR
    public ManageFields(WebDriver webWebWebDriver) {
        this.webWebWebDriver = webWebWebDriver;
        config = new Config();
        PageFactory.initElements(webWebWebDriver, this);
    }

    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.ID, using = "edit-fields-add-new-field-label")
    private WebElement AddNewField_Txb;

    @FindBy(how = How.ID, using = "edit-fields-add-new-field-type")
    private WebElement SelectFieldType_Ddl;

    @FindBy(how = How.ID, using = "edit-submit")
    private WebElement Save_Btn;

    private WebElement FieldLabel_Txt(String labelTxt) {
        return webWebWebDriver.findElement(By.xpath("//td[contains(text(), '" + labelTxt + "')]"));
    }


    //PAGE OBJECT METHODS
    public void EnterAddNewField(String newFieldName) throws Exception {

        Reporter.log("Enter '" + newFieldName + "' in the 'Add new field' text box.");
        AddNewField_Txb.sendKeys(newFieldName);
    }

    public void SelectFieldType(String fieldType) throws Exception {

        Reporter.log("Select '" + fieldType + "' from the 'Select a field type' drop down list.");
        new Select(SelectFieldType_Ddl).selectByVisibleText(fieldType);
    }

    public void ClickSaveBtn() throws Exception {

        Reporter.log("Click the 'Save' button.");
        Save_Btn.click();
    }

    public void ClickSaveFieldSettingsBtn() throws Exception {

        Reporter.log("Click the 'Save field settings' button.");
        Save_Btn.click();
    }

    public boolean FieldLabelExists(String labelTxt) throws Exception {

        boolean fieldExists = false;
        webWebWebDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        try {
            FieldLabel_Txt(labelTxt).isDisplayed();
            fieldExists = true;
        } catch (Exception e) {
            fieldExists = false;
        }
        webWebWebDriver.manage().timeouts().implicitlyWait(config.getConfigValueInt("ImplicitWaitTime"), TimeUnit.SECONDS);
        return fieldExists;
    }


}

