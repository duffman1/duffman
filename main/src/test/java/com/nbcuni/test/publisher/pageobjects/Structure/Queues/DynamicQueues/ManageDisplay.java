package com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues;

import com.nbcuni.test.publisher.common.Driver.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

/*********************************************
 * publisher.nbcuni.com Manage Display Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: July 31, 2014
 *********************************************/

public class ManageDisplay {

    //PAGE OBJECT CONSTRUCTOR
    public ManageDisplay(Driver webDriver) {
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//select[@name='fields[entity_list][type]']")
    private WebElement EntityListFormat_Ddl;
    
    @FindBy(how = How.ID, using = "edit-submit")
    private WebElement Save_Btn;
    
    
   //PAGE OBJECT METHODS
   public void SelectEntityListFormat(String option) throws Exception {
	   
	   Reporter.log("Select '" + option + "' from the 'Entity List' Formate drop down list.");
	   new Select(EntityListFormat_Ddl).selectByVisibleText(option);
   }
   
   public void ClickSaveBtn() throws Exception {
	   
	   Reporter.log("Click the 'Save' button.");
	   Save_Btn.click();
   }
    
}

