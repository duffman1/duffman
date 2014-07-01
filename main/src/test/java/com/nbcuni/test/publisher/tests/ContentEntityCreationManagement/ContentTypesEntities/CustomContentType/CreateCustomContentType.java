package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentTypesEntities.CustomContentType;

import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.Structure.ContentTypes;

public class CreateCustomContentType extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE - TC1049
     * Steps - https://rally1.rallydev.com/#/14663927728ud/detail/testcase/17441495573
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "smoke" })
    public void CreateCustomContentType_TC1049() throws Exception{
        
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
            
            //Step 2
            taxonomy.NavigateSite("Structure>>Content types>>Add content type");
            overlay.SwitchToActiveFrame();
            
            //Step 3
            ContentTypes contentTypes = new ContentTypes(webDriver, applib);
            String contentTypeName = random.GetCharacterString(10);
            contentTypes.EnterName(contentTypeName);
            
            //Step 4
            contentTypes.ClickSaveBtn();
            contentTypes.VerifyContentTypeSaved(contentTypeName);
            overlay.ClickCloseOverlayLnk();
            
            //Step 5
            taxonomy.NavigateSite("Structure>>Content types>>" + contentTypeName + ">>Manage fields");
            overlay.SwitchToActiveFrame();
            
            //Step 6
            String newFieldName = random.GetCharacterString(15);
            String newFieldType = "Image";
            contentTypes.EnterAddNewField(newFieldName);
            contentTypes.SelectFieldType(newFieldType);
            contentTypes.ClickSaveBtn();
            overlay.SwitchToActiveFrame();
            contentTypes.ClickSaveBtn();
            overlay.SwitchToActiveFrame();
            contentParent.VerifyMessageStatus("Updated field " + newFieldName + " field settings.");
            contentTypes.ClickSaveBtn();
            overlay.SwitchToActiveFrame();
            contentParent.VerifyMessageStatus("Saved " + newFieldName + " configuration.");
            contentTypes.ClickSaveBtn();
            
            //Step 7
            overlay.ClickCloseOverlayLnk();
            taxonomy.NavigateSite("Content>>Add content>>" + contentTypeName);
            overlay.SwitchToActiveFrame();
            contentTypes.VerifyFieldSaveBtnPresent(newFieldName);
            
            //Step 8
            BasicInformation basicInformation = new BasicInformation(webDriver);
            String contentTitle = random.GetCharacterString(15);
            basicInformation.EnterTitle(contentTitle);
            basicInformation.EnterSynopsis();
            overlay.SwitchToActiveFrame();
            contentParent.ClickSaveBtn();
            overlay.switchToDefaultContent();
            contentParent.VerifyMessageStatus(contentTypeName + " " + contentTitle + " has been created.");
            
    }
}
