package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentTypesEntities.CustomContentType;

import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.Content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.Structure.ContentTypes;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.testng.annotations.Test;

public class CreateCustomContentType extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE - TC1049
     * Steps - https://rally1.rallydev.com/#/14663927728ud/detail/testcase/17441495573
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "certify"})
    public void CreateCustomContentType_TC1049() throws Exception{
        
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
            
            //Step 2
        	navigation.Structure("Content types");
            
            //Step 3
            ContentTypes contentTypes = new ContentTypes(webWebWebDriver);
            contentTypes.ClickAddContentLnk();
            String contentTypeName = random.GetCharacterString(10);
            contentTypes.EnterName(contentTypeName);
            
            //Step 4
            contentTypes.ClickSaveBtn();
            contentParent.VerifyMessageStatus("The content type " + contentTypeName + " has been added.");
        	
            //Step 5
            navigation.Structure("Content types");
            contentTypes.ClickManageFieldLnk(contentTypeName);
            
            //Step 6
            String newFieldName = random.GetCharacterString(15);
            String newFieldType = "Image";
            contentTypes.EnterAddNewField(newFieldName);
            contentTypes.SelectFieldType(newFieldType);
            contentTypes.ClickSaveBtn();
            contentTypes.ClickSaveBtn();
            contentParent.VerifyMessageStatus("Updated field " + newFieldName + " field settings.");
            contentTypes.ClickSaveBtn();
            contentParent.VerifyMessageStatus("Saved " + newFieldName + " configuration.");
            contentTypes.ClickSaveBtn();
            
            //Step 7
            navigation.AddContent(contentTypeName);
            contentTypes.VerifyFieldSelectBtnPresent(newFieldName);
            
            //Step 8
            BasicInformation basicInformation = new BasicInformation(webWebWebDriver);
            String contentTitle = random.GetCharacterString(15);
            basicInformation.EnterTitle(contentTitle);
            basicInformation.EnterSynopsis();
            contentParent.ClickSaveBtn();
            webWebWebDriver.switchTo().defaultContent();
            contentParent.VerifyMessageStatus(contentTypeName + " " + contentTitle + " has been created.");
            
    }
}
