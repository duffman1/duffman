package com.nbcuni.test.publisher.tests.Core.Profiles;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.Content.SelectFile;
import com.nbcuni.test.publisher.pageobjects.Content.WorkBench;
import com.nbcuni.test.publisher.pageobjects.Structure.ContentTypes;
import com.nbcuni.test.publisher.pageobjects.Structure.ManageFields.Edit;

public class FileFieldsTokenBasedPath extends ParentTest {
	
    /*************************************************************************************
     * TEST CASE - TC4355
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/20894225063 
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void FileFieldsTokenBasedPath_TC4355() throws Exception{
        
    	Reporter.log("STEP 1");
        UserLogin userLogin = applib.openApplication();
        userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        
        Reporter.log("STEP 2");
        taxonomy.NavigateSite("Structure>>Content types>>Add content type");
        overlay.SwitchToActiveFrame();
        ContentTypes contentTypes = new ContentTypes(webDriver);
        String contentTypeName = random.GetCharacterString(10);
        contentTypes.EnterName(contentTypeName);
        contentTypes.ClickSaveAddFieldsBtn();
        overlay.SwitchToActiveFrame();
        
        Reporter.log("STEP 3");
        String newFileFieldName = random.GetCharacterString(15);
        contentTypes.EnterAddNewField(newFileFieldName);
        contentTypes.SelectFieldType("File");
        contentTypes.SelectWidget("File");
        contentTypes.ClickSaveBtn();
        overlay.SwitchToActiveFrame();
        contentTypes.ClickSaveBtn();
        overlay.SwitchToActiveFrame();
        contentParent.VerifyMessageStatus("Updated field " + newFileFieldName + " field settings.");
        
        Reporter.log("STEP 4");
        Edit edit = new Edit(webDriver, applib);
        edit.VerifyFileDirectoryValue("[current-date:custom:Y]/[current-date:custom:m]");
        
        Reporter.log("STEP 5");
        contentTypes.ClickSaveBtn();
        overlay.SwitchToActiveFrame();
        String newImageFieldName = random.GetCharacterString(15);
        contentTypes.EnterAddNewField(newImageFieldName);
        contentTypes.SelectFieldType("Image");
        contentTypes.SelectWidget("Image");
        contentTypes.ClickSaveBtn();
        overlay.SwitchToActiveFrame();
        contentTypes.ClickSaveBtn();
        overlay.SwitchToActiveFrame();
        
        Reporter.log("STEP 6");
        contentParent.VerifyMessageStatus("Updated field " + newImageFieldName + " field settings.");
        edit.VerifyFileDirectoryValue("[current-date:custom:Y]/[current-date:custom:m]");
        
        Reporter.log("STEP 7");
        contentTypes.ClickSaveBtn();
        overlay.SwitchToActiveFrame();
        contentParent.VerifyMessageStatus("Saved " + newImageFieldName + " configuration.");
        contentTypes.ClickSaveBtn();
        contentParent.VerifyMessageStatus("Your settings have been saved.");
        overlay.ClickCloseOverlayLnk();
        
        Reporter.log("STEP 8 - N/A");
        
        Reporter.log("STEP 9");
        taxonomy.NavigateSite("Content>>Add content>>" + contentTypeName);
        overlay.SwitchToActiveFrame();
        BasicInformation basicInformation = new BasicInformation(webDriver);
        String contentTitle = random.GetCharacterString(15);
        basicInformation.EnterTitle(contentTitle);
        basicInformation.EnterSynopsis();
        overlay.SwitchToActiveFrame();
        SelectFile selectFile = new SelectFile(webDriver);
        selectFile.EnterCustomFieldFilePath(newImageFieldName, config.getConfigValueFilePath("PathToMediaContent") + "HanSolo1.jpg");
		selectFile.ClickCustomFieldUploadBtn(newImageFieldName);
    	selectFile.WaitForFileUploaded("HanSolo1.jpg");
    	contentParent.ClickSaveBtn();
        overlay.switchToDefaultContent(true);
        contentParent.VerifyMessageStatus(contentTypeName + " " + contentTitle + " has been created.");
        WorkBench workBench = new WorkBench(webDriver);
        Date currentMonth = Calendar.getInstance().getTime();
    	SimpleDateFormat currentMonthFormat = new SimpleDateFormat("yyyy/MM");
    	currentMonthFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    	workBench.VerifyFileImagePresent(currentMonthFormat.format(currentMonth) + "/HanSolo", "1");
    	
    	Reporter.log("STEP 10-11");
    	taxonomy.NavigateSite("Structure>>Content types>>Character Profile>>Manage fields>>Cover Photo");
    	overlay.SwitchToActiveFrame();
    	edit.VerifyFileDirectoryValue("[current-date:custom:Y]/[current-date:custom:m]");
    	
    	Reporter.log("STEP 12 - TODO");
    	
    }
}
