package com.nbcuni.test.publisher.pageobjects.Structure;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
 * publisher.nbcuni.com ContentTypes Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.1 Date: April 13, 2014
 *********************************************/

public class ContentTypes {

    private Driver webDriver;
    private Config config;
    private WaitFor waitFor;
    
    //PAGE OBJECT CONSTRUCTOR
    public ContentTypes(Driver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        config = new Config();
        waitFor = new WaitFor(webDriver, 10);
        
    }
    
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
    
    @FindBy(how = How.ID, using = "edit-name")
    private WebElement Name_Txb;
    
    @FindBy(how = How.ID, using = "edit-submit")
    private WebElement Save_Btn;
    
    @FindBy(how = How.ID, using = "edit-save-continue")
    private WebElement SaveAddFields_Btn;
    
    @FindBy(how = How.XPATH, using = "//div[@class='content']//tbody")
    private WebElement ContentTypeTbl_Ctr;
    
    @FindBy(how = How.XPATH, using = "//input[@id='edit-fields-add-new-field-label']")
    private WebElement AddNewField_Txb;
    
    @FindBy(how = How.ID, using = "edit-fields-add-existing-field-label")
    private WebElement AddExistingField_Txb;
    
    @FindBy(how = How.ID, using = "edit-fields-add-new-field-type")
    private WebElement FieldType_Ddl;
    
    @FindBy(how = How.ID, using = "edit-fields-add-existing-field-field-name")
    private WebElement SelectExistingField_Ddl;
    
    @FindBy(how = How.ID, using = "edit-fields-add-new-field-widget-type")
    private WebElement Widget_Ddl;
    
    private WebElement FieldSelect_Btn(String fieldName) {
    	return webDriver.findElement(By.id("edit-field-" + fieldName + "-und-field-" + fieldName + "-und-0-select"));
    }
    
    private WebElement Field_Txt(String fieldName) {
    	return webDriver.findElement(By.xpath("//td[contains(text(), '" + fieldName + "')]"));
    }
    
    
    //PAGE OBJECT METHODS
    public void ClickAddContentLnk() throws Exception {
    	
    	Reporter.log("Click the 'Add content type'.");
    	waitFor.ElementPresent(AddContentType_Lnk).click();
    	
    }
    
    public void ClickManageFieldLnk(String lnkTxt) throws Exception {
    	
    	Reporter.log("Click the 'manage field' link for content type '" + lnkTxt + "'.");
    	waitFor.ElementPresent(ManageField_Lnk(lnkTxt)).click();
    	
    }
    
    public void ClickManageDisplayLnk(String lnkTxt) throws Exception {
    	
    	Reporter.log("Click the 'manage display' link for content type '" + lnkTxt + "'.");
    	waitFor.ElementPresent(ManageDisplay_Lnk(lnkTxt)).click();
    	
    }
    
    public void ClickEditLnk(String lnkTxt) throws Exception {
    	
    	Reporter.log("Click the 'edit' link for field '" + lnkTxt + "'.");
    	waitFor.ElementPresent(Edit_Lnk(lnkTxt)).click();
    	
    }
    
    public void EnterName(String name) throws Exception {
    	
    	Reporter.log("Enter '" + name + "' in the 'Name' text box.");
    	Name_Txb.sendKeys(name);
    	
    }
    
    public void ClickSaveBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save' button.");
    	Save_Btn.click();
    	
    }
    
    public void ClickSaveAddFieldsBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save and add fields' button.");
    	SaveAddFields_Btn.click();
    	
    }
    
    public void EnterAddNewField(String fieldName) throws Exception {
    	
    	Reporter.log("Enter '" + fieldName + "' in the 'Add new field' text box.");
    	AddNewField_Txb.sendKeys(fieldName);
    	
    }
    
    public void EnterAddExistingField(String fieldName) throws Exception {
    	
    	Reporter.log("Enter '" + fieldName + "' in the 'Add existing field' text box.");
    	AddExistingField_Txb.sendKeys(fieldName);
    	
    }
    
    public void SelectFieldType(String fieldType) throws Exception {
    	
    	Reporter.log("Select '" + fieldType + "' from the 'Field Type' drop down list.");
        new Select(FieldType_Ddl).selectByVisibleText(fieldType);
    	
    }
    
    public void SelectExistingField(String fieldType) throws Exception {
    	
    	Reporter.log("Select '" + fieldType + "' from the 'select existing field' drop down list.");
        new Select(SelectExistingField_Ddl).selectByVisibleText(fieldType);
    	
    }
    
    public void SelectWidget(String widget) throws Exception {
    	
    	Reporter.log("Select '" + widget + "' from the 'Widget' drop down list.");
        new Select(Widget_Ddl).selectByVisibleText(widget);
    	
    }
    
    public void VerifyFieldSelectBtnPresent(String fieldName) throws Exception {
    	
    	Reporter.log("Verify the Field 'Select' button is present.");
    	FieldSelect_Btn(fieldName).isDisplayed();
    	
    }
    
    public Boolean IsFieldPresent(String fieldName) throws Exception {
    	
    	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	
    	Boolean fieldPresent;
    	
    	try {
    		Field_Txt(fieldName).isDisplayed();
    		fieldPresent = true;
    	}
    	catch (NoSuchElementException e) {
    		fieldPresent = false;
    	}
    	
    	webDriver.manage().timeouts().implicitlyWait(config.getConfigValueInt("ImplicitWaitTime"), TimeUnit.SECONDS);
    	return fieldPresent;
    }
    
}

