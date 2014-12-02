package com.nbcuni.test.publisher.tests.Advertising.AdFolders;

import java.util.Arrays;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.Delete;
import com.nbcuni.test.publisher.pageobjects.Content.Revert;
import com.nbcuni.test.publisher.pageobjects.Structure.AddNewSideFile;
import com.nbcuni.test.publisher.pageobjects.Structure.ImportSideFile;
import com.nbcuni.test.publisher.pageobjects.Structure.PublisherAdsSideFiles;

public class AdFoldersRichMediaAds extends ParentTest {
	
    /*************************************************************************************
     * TEST CASE - TC1160
     * Steps - https://rally1.rallydev.com/#/14663927728/detail/testcase/17540213680 
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void AdFoldersRichMediaAds_TC1160() throws Exception{
        
    	//Step 1
        UserLogin userLogin = applib.openApplication();
        userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        
        //Setup
        Modules modules = new Modules(webDriver);
        modules.VerifyModuleEnabled("Pub Ads");
        
        //Setup
        String defaultAddFile = "AdCentric";
        	
        //Step 2
        navigation.Structure("Publisher Ads Side Files");
        
        //Step 3
        PublisherAdsSideFiles publisherAdsSideFiles = new PublisherAdsSideFiles(webDriver);
        publisherAdsSideFiles.DeleteAllUnwantedSideFiles();
        publisherAdsSideFiles.ClickAddLnk();
        
        //Step 4
        String sideFileName = random.GetCharacterString(15);
        AddNewSideFile addNewSideFile = new AddNewSideFile(webDriver);
        addNewSideFile.EnterName(sideFileName);
        
        //Step 5
        String sideFilePath = random.GetCharacterString(10) + "/" + random.GetCharacterString(10) + ".html";
        addNewSideFile.EnterPath(sideFilePath);
        
        //Step 6
        addNewSideFile.SelectResponseType("text/html");
        
        //Step 7
        addNewSideFile.EnterContent("<html><body bgcolor=white><script language=javascript>function pq(q) {if(q.length > 1) this.q = q.substring(1, q.length);else this.q = null;this.keyValuePairs = new Array();if(q) {for(var i=0; i < this.q.split(\"&\").length; i++) {this.keyValuePairs[i] = this.q.split(\"&\")[i];}}this.getValue = function(s) {for(var j=0; j < this.keyValuePairs.length; j++) {if(this.keyValuePairs[j].split(\"=\")[0] == s)return this.keyValuePairs[j].split(\"=\")[1];}return -1;}}function qs(key){var page = new pq(document.location.search);return unescape(page.getValue(key));}var _c = qs('c');var r_number = Math.random();document.writeln('<SCR'+'IPT language=\'JavaScript\' S'+'RC=\"http://media.adcentriconline.com/adcentric/tag/'+_c+'?ADCadcifr=1&number='+r_number+'\"></SCR'+'IPT>');</script></body></html>");
        
        //Step 8
        addNewSideFile.ClickSaveBtn();
        contentParent.VerifyMessageStatus(sideFileName + " has been created.");
        
        //Step 9
        publisherAdsSideFiles.ClickAdSideFileLnk(sideFilePath);
        Assert.assertEquals(webDriver.getCurrentUrl(), config.getConfigValueString("AppURL") + "/" + sideFilePath);
        webDriver.navigate().back();
        
        //Step 10
        publisherAdsSideFiles.ClickAdSideFileEditLnk(defaultAddFile);
        
        //Step 11
        addNewSideFile.ClickSaveBtn();
        contentParent.VerifyMessageStatus(defaultAddFile.toLowerCase() + " has been updated");
        publisherAdsSideFiles.VerifyAdSideFileStorageType(defaultAddFile, "Overridden");
        
        //Step 12
        publisherAdsSideFiles.ClickAdSideFileEditLnk(defaultAddFile);
        addNewSideFile.ClickRevertBtn();
        
        //Step 13
        Revert revert = new Revert(webDriver);
        revert.ClickRevertBtn();
        contentParent.VerifyMessageStatus("The item has been reverted.");
        publisherAdsSideFiles.VerifyAdSideFileStorageType(defaultAddFile, "Default");
        
        //Step 14 - TODO automate as time allows
        
        //Step 15
        publisherAdsSideFiles.ClickAdSideFileExpandEditLnk(defaultAddFile);
        publisherAdsSideFiles.ClickAdSideFileCloneLnk(defaultAddFile);
        
        //Step 16
        String clonedSideFileName = random.GetCharacterString(15);
        addNewSideFile.EnterName(clonedSideFileName);
        addNewSideFile.ClickSaveBtn();
        contentParent.VerifyMessageStatus("clone_of_" + defaultAddFile.toLowerCase() + " has been created.");
        publisherAdsSideFiles.VerifyAdSideFileStorageType(clonedSideFileName, "Normal");
        
        //Step 17
        publisherAdsSideFiles.ClickAdSideFileEditLnk(defaultAddFile);
        addNewSideFile.ClickExportTab();
        String exportTxt = addNewSideFile.CopyExportTxt();
        
        //Step 18
        navigation.Structure("Publisher Ads Side Files");
        publisherAdsSideFiles.ClickImportLnk();
        
        //Step 19
        ImportSideFile importSideFile = new ImportSideFile(webDriver);
        importSideFile.EnterSideFileCode(exportTxt);
        importSideFile.UncheckAllowImportOverwriteRecordCbx();
        importSideFile.ClickContinueBtn();
        contentParent.VerifyMessageWarning("You have unsaved changes. These changes will not be made permanent until you click Save.");
        
        //Step 20
        String importSideFileName = random.GetCharacterString(15);
        addNewSideFile.EnterName(importSideFileName);
        String importedSideFilePath = random.GetCharacterString(10) + "/" + random.GetCharacterString(10) + ".html";
        addNewSideFile.EnterPath(importedSideFilePath);
        addNewSideFile.ClickUpdateBtn();
        contentParent.VerifyMessageWarning("You have unsaved changes. These changes will not be made permanent until you click Save.");
        
        //Step 21
        addNewSideFile.ClickSaveBtn();
        addNewSideFile.EnterMachineName(importSideFileName.toLowerCase());
        addNewSideFile.ClickUpdateBtn();
        contentParent.VerifyMessageWarning("You have unsaved changes. These changes will not be made permanent until you click Save.");
        addNewSideFile.ClickSaveBtn();
        contentParent.VerifyMessageStatus(importSideFileName.toLowerCase() + " has been created.");
        publisherAdsSideFiles.VerifyAdSideFileStorageType(importSideFileName, "Normal");
        
        //Step 22
        List<String> newlyCreatedSideFiles = Arrays.asList(sideFileName, clonedSideFileName, importSideFileName);
        for (String sideFile : newlyCreatedSideFiles) {
        	publisherAdsSideFiles.ClickAdSideFileExpandEditLnk(sideFile);
            publisherAdsSideFiles.ClickAdSideFileDeleteLnk(sideFile);
            Delete delete = new Delete(webDriver);
            delete.ClickDeleteBtn();
            contentParent.VerifyMessageStatus("The item has been deleted.");
            publisherAdsSideFiles.VerifyAdSideFileNotPresent(sideFile);
        }
        
    }
}
