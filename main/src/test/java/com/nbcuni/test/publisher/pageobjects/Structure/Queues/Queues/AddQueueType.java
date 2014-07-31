package com.nbcuni.test.publisher.pageobjects.Structure.Queues.Queues;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import java.util.List;

/*********************************************
 * publisher.nbcuni.com Add Queue Type Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: March 26, 2014
 *********************************************/

public class AddQueueType {

    private Driver webDriver;
    
    //PAGE OBJECT CONSTRUCTOR
    public AddQueueType(Driver webDriver, AppLib applib) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.ID, using = "edit-label")
    private WebElement Name_Txb;
    
    @FindBy(how = How.ID, using = "edit-target")
    private WebElement EntityType_Ddl;
    
    private WebElement ContentType_Cbx(String contentType) {
    	return webDriver.findElement(By.xpath("//label[text()='" + contentType + " ']/../input"));
    }
    
    @FindBy(how = How.ID, using = "edit-submit")
    private WebElement SaveQueueType_Btn;
    
    
    //PAGE OBJECT METHODS
    public void EnterName(String queueTypeName) throws Exception {
    	
    	Reporter.log("Enter '" + queueTypeName + "' in the 'Name' text box.");
    	Name_Txb.sendKeys(queueTypeName);
    }
    
    public void SelectEntityType(String entityType) throws Exception {
    	
    	Reporter.log("Select '" + entityType + "' from the 'Entity type' drop down list.");
    	new Select(EntityType_Ddl).selectByVisibleText(entityType);
    }
    
    public void EnableContentTypes(List<String> contentTypes) throws Exception {
    	
    	for (String contentType : contentTypes) {
    		if (ContentType_Cbx(contentType).isSelected() == false) {
    			Reporter.log("Check the '" + contentType + "' check box.");
    			ContentType_Cbx(contentType).click();
    		}
    	}
    }
    
    public void ClickSaveQueueTypeBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save queue type' button.");
    	SaveQueueType_Btn.click();
    }
    
    
}

