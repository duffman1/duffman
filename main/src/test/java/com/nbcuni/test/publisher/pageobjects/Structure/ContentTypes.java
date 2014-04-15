package com.nbcuni.test.publisher.pageobjects.Structure;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;

/*********************************************
 * publisher.nbcuni.com ContentTypes Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.1 Date: April 13, 2014
 *********************************************/

public class ContentTypes {

    private Driver webDriver;
    private ContentParent contentParent;
    
    //PAGE OBJECT CONSTRUCTOR
    public ContentTypes(Driver webDriver, AppLib applib) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        contentParent = new ContentParent(webDriver, applib);
    }
    
    //PAGE OBJECT IDENTIFIERS
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
    
    @FindBy(how = How.ID, using = "edit-fields-add-new-field-type")
    private WebElement FieldType_Ddl;
    
    @FindBy(how = How.ID, using = "edit-fields-add-new-field-widget-type")
    private WebElement Widget_Ddl;
    
    private WebElement FieldSave_Btn(String fieldName) {
    	return webDriver.findElement(By.xpath("//a[@id='edit-field-" + fieldName + "-und-field-" + fieldName + "-und-0-select']"));
    }
    
    
    //PAGE OBJECT METHODS
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
    
    public void VerifyContentTypeSaved(String name) throws Exception {
    	
    	contentParent.VerifyMessageStatus("The content type " + name + " has been added.");
    	
    	Reporter.log("Verify the content type table contains the text '" + name + "'.");
    	Assert.assertTrue(ContentTypeTbl_Ctr.getText().contains(name));
    	
    }
    
    public void EnterAddNewField(String fieldName) throws Exception {
    	
    	Reporter.log("Enter '" + fieldName + "' in the 'Add new field' text box.");
    	AddNewField_Txb.sendKeys(fieldName);
    	
    }
    
    public void SelectFieldType(String fieldType) throws Exception {
    	
    	Reporter.log("Select '" + fieldType + "' from the 'Field Type' drop down list.");
        new Select(FieldType_Ddl).selectByVisibleText(fieldType);
    	
    }
    
    public void SelectWidget(String widget) throws Exception {
    	
    	Reporter.log("Select '" + widget + "' from the 'Widget' drop down list.");
        new Select(Widget_Ddl).selectByVisibleText(widget);
    	
    }
    
    public void VerifyNewFieldSaved(String fieldName) throws Exception {
    	
    	contentParent.VerifyMessageStatus("Updated field " + fieldName + " field settings.");
    	
    }
    
    public void VerifyConfigurationSaved(String fieldName) throws Exception {
    	
    	contentParent.VerifyMessageStatus("Saved " + fieldName + " configuration.");
    	
    }
    
    public void VerifyFieldSaveBtnPresent(String fieldName) throws Exception {
    	
    	Reporter.log("Verify the Field 'Save' button is present.");
    	FieldSave_Btn(fieldName).isDisplayed();
    	
    }
    
}

