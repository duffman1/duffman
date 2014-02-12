package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentTypesEntities.CustomContentType;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.ContentTypes;

public class AddingNewCustomContentType extends ParentTest{
	
	
    /*************************************************************************************
     * TEST CASE 3106 Adding new custom content type
     * Step 1 - Login to publisher using drupal 1 credentials <br>
     * Step 2 - Navigate to Structure >> Content Types >> Add Content Types<br>
     * Step 3 - Populate "Name" in the mandatory field<br>
     * Step 4 - Click on "Save content type"<br>
     * Step 5 - Navigate to Structure >> Content Types >> New Content Type >> Manage Fields<br>
     * Step 6 - Under 'Label' column type 'image' in 'Add new field' text box, under 'Field type' column select 'image' in 'Type of data to store' drop down and click on 'Save' button then click on 'Save field settings' button then click on 'Save setting' button. ,Observe that successful message is displayed.<br>
     * Step 7 - Navigate to Content-> Add content-> new custom content type. Observe that the user is taken to the "Create new custom content type" overlay and 'Select' button under 'Image' text is displayed for selecting the image.
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(groups = {"full", "smoke" })
    public void AddingNewCustomContentType_Test() throws Exception{
        
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
        	PageFactory.initElements(webDriver, userLogin);
            userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
            
            //Step 2
            taxonomy.NavigateSite("Structure>>Content types>>Add content type");
            
            //Step 3
            overlay.SwitchToFrame("Content types");
            ContentTypes contentTypes = new ContentTypes(webDriver);
            String contentTypeName = random.GetCharacterString(10);
            contentTypes.EnterName(contentTypeName);
            
            //Step 4
            contentTypes.ClickSaveBtn();
            contentTypes.VerifyContentTypeSaved(contentTypeName);
            overlay.ClickCloseOverlayLnk();
            
            //Step 5
            overlay.switchToDefaultContent();
            taxonomy.NavigateSite("Structure>>Content types>>" + contentTypeName + ">>Manage fields");
            
            //Step 6
            overlay.SwitchToFrame(contentTypeName);
            String newFieldName = random.GetCharacterString(15);
            String newFieldType = "Image";
            contentTypes.AddNewField(newFieldName);
            contentTypes.SelectFieldType(newFieldType);
            contentTypes.ClickSaveBtn();
            contentTypes.ClickSaveBtn();
            contentTypes.VerifyNewFieldSaved(newFieldName);
            contentTypes.ClickSaveBtn();
            contentTypes.VerifyConfigurationSaved(newFieldName);
            contentTypes.ClickSaveBtn();
            
            //Step 7
            overlay.ClickCloseOverlayLnk();
            overlay.switchToDefaultContent();
            taxonomy.NavigateSite("Content>>Add content>>" + contentTypeName);
            overlay.SwitchToFrame(contentTypeName);
            contentTypes.VerifyFieldSaveBtnPresent(newFieldName);
            
        
    }
}
