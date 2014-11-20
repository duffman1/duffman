package com.nbcuni.test.publisher.pageobjects.Structure;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
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
    private Integer timeout;
    private WaitFor waitFor;
    private Interact interact;
    
    //PAGE OBJECT CONSTRUCTOR
    public ContentTypes(Driver webDriver) {
        this.webDriver = webDriver;
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webDriver, timeout);
        interact = new Interact(webDriver, timeout);
        
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
    
    private By Name_Txb = By.id("edit-name");
    
    private By Save_Btn = By.id("edit-submit");
    
    private By SaveAddFields_Btn = By.id("edit-save-continue");
    
    private By AddNewField_Txb = By.xpath("//input[@id='edit-fields-add-new-field-label']");
    
    private By AddExistingField_Txb = By.id("edit-fields-add-existing-field-label");
    
    private By FieldType_Ddl = By.id("edit-fields-add-new-field-type");
    
    private By SelectExistingField_Ddl = By.id("edit-fields-add-existing-field-field-name");
    
    private By Widget_Ddl = By.id("edit-fields-add-new-field-widget-type");
    
    private By FieldSelect_Btn(String fieldName) {
    	return By.id("edit-field-" + fieldName + "-und-field-" + fieldName + "-und-0-select");
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
    
    public void EnterName(String name) throws Exception {
    	
    	Reporter.log("Enter '" + name + "' in the 'Name' text box.");
    	interact.Type(waitFor.ElementVisible(Name_Txb), name);
    	
    }
    
    public void ClickSaveBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save' button.");
    	interact.Click(waitFor.ElementVisible(Save_Btn));
    	Thread.sleep(1000);
    	
    }
    
    public void ClickSaveAddFieldsBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save and add fields' button.");
    	interact.Click(waitFor.ElementVisible(SaveAddFields_Btn));
    	
    }
    
    public void EnterAddNewField(String fieldName) throws Exception {
    	
    	Reporter.log("Enter '" + fieldName + "' in the 'Add new field' text box.");
    	interact.Type(waitFor.ElementVisible(AddNewField_Txb), fieldName);
    	
    }
    
    public void EnterAddExistingField(String fieldName) throws Exception {
    	
    	Reporter.log("Enter '" + fieldName + "' in the 'Add existing field' text box.");
    	interact.Type(waitFor.ElementVisible(AddExistingField_Txb), fieldName);
    	
    }
    
    public void SelectFieldType(String fieldType) throws Exception {
    	
    	Reporter.log("Select '" + fieldType + "' from the 'Field Type' drop down list.");
    	interact.Select(waitFor.ElementVisible(FieldType_Ddl), fieldType);
        
    }
    
    public void SelectExistingField(String fieldType) throws Exception {
    	
    	Reporter.log("Select '" + fieldType + "' from the 'select existing field' drop down list.");
    	interact.Select(waitFor.ElementVisible(SelectExistingField_Ddl), fieldType);
        
    }
    
    public void SelectWidget(String widget) throws Exception {
    	
    	Reporter.log("Select '" + widget + "' from the 'Widget' drop down list.");
    	interact.Select(waitFor.ElementVisible(Widget_Ddl), widget);
        
    }
    
    public void VerifyFieldSelectBtnPresent(String fieldName) throws Exception {
    	
    	Reporter.log("Verify the Field 'Select' button is present.");
    	waitFor.ElementVisible(FieldSelect_Btn(fieldName));
    	
    }
    
    public Boolean IsFieldPresent(String fieldName) throws Exception {
    	
    	webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    	
    	Boolean fieldPresent;
    	
    	try {
    		webDriver.findElement(Field_Txt(fieldName));
    		fieldPresent = true;
    	}
    	catch (NoSuchElementException e) {
    		fieldPresent = false;
    	}
    	
    	webDriver.manage().timeouts().implicitlyWait(config.getConfigValueInt("ImplicitWaitTime"), TimeUnit.SECONDS);
    	
    	return fieldPresent;
    	
    }
    
}

