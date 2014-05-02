package com.nbcuni.test.publisher.pageobjects.Structure.ManageFields;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com Relationships Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: May 1, 2014
 *********************************************/

public class Relationships {

    //PAGE OBJECT CONSTRUCTOR
    public Relationships(Driver webDriver) {
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.ID, using = "edit-instance-widget-settings-depth")
    private WebElement TVRelationshipWidgetDepth_Ddl;
    
    @FindBy(how = How.ID, using = "edit-submit")
    private WebElement SaveSettings_Btn;
    
    
    //PAGE OBJECT METHODS
    public void SelectTVRelationshipWidgetDepth(String depth) throws Exception {
    	
    	Reporter.log("Select '" + depth + "' from the 'TV Relationship Widget Depth' drop down list.");
    	new Select(TVRelationshipWidgetDepth_Ddl).selectByVisibleText(depth);
    }
    
    public void ClickSaveSettingsBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save settings' button.");
    	SaveSettings_Btn.click();
    }
    
}

