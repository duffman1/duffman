package com.nbcuni.test.publisher.pageobjects.Configuration;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import com.nbcuni.test.webdriver.CustomWebDriver;

/*********************************************
* publisher.nbcuni.com Node Clone Module Library. Copyright
*
* @author Mohd Faizan Khan
* @version 1.0 Date: Feb 18, 2014
*********************************************/

public class NodeCloneModule {

    private static CustomWebDriver webDriver;
    
    //PAGE OBJECT CONSTRUCTOR
    public NodeCloneModule(CustomWebDriver webDriver) {
    	NodeCloneModule.webDriver = webDriver;
    	PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.ID, using = "edit-clone-method-prepopulate")
    private static WebElement PrePopulateNodeFormFields_Rdb;
    
    @FindBy(how = How.ID, using = "edit-clone-nodes-without-confirm-1")
    private static WebElement BypassConfirmation_Rdb;
    
    @FindBy(how = How.ID, using = "edit-clone-menu-links-0")
    private static WebElement No_Rdb;
    
    @FindBy(how = How.ID, using = "edit-clone-use-node-type-name")
    private static WebElement UseNodeTypeNameInCloneLink_Cbx;
    
    @FindBy(how = How.ID, using = "edit-submit")
    private static WebElement SaveConfiguration_Btn;
    
    private static List<WebElement> PublishingOptionsResetDefault_Cbxs() {
    	return webDriver.findElements(By.cssSelector("input[id*='edit-clone-reset']"));
    }
    
    private static List<WebElement> OmittedContentTypes_Cbxs() {
    	return webDriver.findElements(By.cssSelector("input[id*='edit-clone-omitted']"));
    }
    
    
    //PAGE OBJECT METHODS
    public void ClickPrePopulateNodeFormFieldsRdb() throws Exception {
    	
    	if (PrePopulateNodeFormFields_Rdb.isSelected() == false) {
    		
    		Reporter.log("Click the 'Pre-populate the node form fields' radio button.");
    		PrePopulateNodeFormFields_Rdb.click();
    	}
    	
    }
    
    public void ClickBypassConfirmationRdb() throws Exception {
     
    	if (BypassConfirmation_Rdb.isSelected() == false) {
    		
    		Reporter.log("Click the 'Bypass confirmation' radio button.");
    		BypassConfirmation_Rdb.click();
    	}
    }
    
    public void ClickCloneMenuLnksNoRdb() throws Exception {
        
    	if (No_Rdb.isSelected() == false) {
    		
    		Reporter.log("Click the 'No' radio button in under the 'Clone menu links' label.");
    		No_Rdb.click();
    	}
    }
    
    public void ClickUseNodeTypeNameInCloneLnkCbx() throws Exception {
        
    	if (UseNodeTypeNameInCloneLink_Cbx.isSelected() == false) {
    		
    		Reporter.log("Check the 'Use node type name in clone link' check box.");
    		UseNodeTypeNameInCloneLink_Cbx.click();
    	}
    }
    
    public void UncheckAllPublishingOptionResetDefaultCbxs() throws Exception {
        
    	List<WebElement> allCbxs = PublishingOptionsResetDefault_Cbxs();
    	
    	for (WebElement el : allCbxs) {
    		if (el.isSelected() == true) {
    			Reporter.log("Uncheck the check box with id '" + el.getAttribute("id") + "' under the 'Should the publishing options be reset...' label.");
    			el.click();
    		}
    	}
    }
    
    public void UncheckAllOmittedContentTypesCbxs() throws Exception {
        
    	List<WebElement> allCbxs = OmittedContentTypes_Cbxs();
    	
    	for (WebElement el : allCbxs) {
    		if (el.isSelected() == true) {
    			Reporter.log("Uncheck the check box with id '" + el.getAttribute("id") + "' under the 'Omitted content types' label.");
    			el.click();
    		}
    	}
    }
    
    public void ClickSaveConfigurationBtn() throws Exception {
        
    	Reporter.log("Click the 'Save configuration' button.");
    	SaveConfiguration_Btn.click();
    }

    public void EnableNodeCloneModuleSetting() throws Exception{

     this.ClickPrePopulateNodeFormFieldsRdb();
     this.ClickBypassConfirmationRdb();
     this.ClickCloneMenuLnksNoRdb();
     this.ClickUseNodeTypeNameInCloneLnkCbx();
     this.UncheckAllPublishingOptionResetDefaultCbxs();
     this.UncheckAllOmittedContentTypesCbxs();
     this.ClickSaveConfigurationBtn();
     
    }
}