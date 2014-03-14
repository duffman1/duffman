package com.nbcuni.test.publisher.tests.Advertising.AdFolders;

import java.util.Arrays;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.Delete;
import com.nbcuni.test.publisher.pageobjects.Content.Revert;
import com.nbcuni.test.publisher.pageobjects.Structure.AddNewSideFile;
import com.nbcuni.test.publisher.pageobjects.Structure.ImportSideFile;
import com.nbcuni.test.publisher.pageobjects.Structure.PublisherAdsSideFiles;

public class AdFoldersRichMediaAds extends ParentTest {
	
    /*************************************************************************************
     * TEST CASE 
     * Steps - https://rally1.rallydev.com/#/14663927728/detail/testcase/17540213680 
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "smoke"})
    public void AdFoldersRichMediaAds_Test() throws Exception{
        
    	//Step 1
        UserLogin userLogin = applib.openApplication();
        userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
        //Setup
        Modules modules = new Modules(webDriver, applib);
        modules.VerifyModuleEnabled("Pub Ads");
        
        //Step 2
        taxonomy.NavigateSite("Structure>>Publisher Ads Side Files");
        overlay.SwitchToActiveFrame();
        
        //Step 3
        PublisherAdsSideFiles publisherAdsSideFiles = new PublisherAdsSideFiles(webDriver, applib);
        publisherAdsSideFiles.ClickAddLnk();
        overlay.SwitchToActiveFrame();
        
        //Step 4
        String sideFileName = random.GetCharacterString(15);
        AddNewSideFile addNewSideFile = new AddNewSideFile(webDriver, applib);
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
        overlay.SwitchToActiveFrame();
        contentParent.VerifyMessageStatus(sideFileName + " has been created.");
        
        //Step 9
        publisherAdsSideFiles.ClickAdSideFileLnk(sideFilePath);
        Assert.assertEquals(webDriver.getCurrentUrl(), applib.getApplicationURL() + "/" + sideFilePath + "#overlay-context=");
        webDriver.navigate().back();
        overlay.SwitchToActiveFrame();
        
        //Step 10
        publisherAdsSideFiles.ClickAdSideFileEditLnk("Atlas");
        overlay.SwitchToActiveFrame();
        
        //Step 11
        addNewSideFile.ClickSaveBtn();
        overlay.SwitchToActiveFrame();
        contentParent.VerifyMessageStatus("atlas has been updated");
        publisherAdsSideFiles.VerifyAdSideFileStorageType("Atlas", "Overridden");
        
        //Step 12
        publisherAdsSideFiles.ClickAdSideFileEditLnk("Atlas");
        overlay.SwitchToActiveFrame();
        addNewSideFile.ClickRevertBtn();
        
        //Step 13
        Revert revert = new Revert(webDriver);
        revert.ClickRevertBtn();
        overlay.SwitchToActiveFrame();
        contentParent.VerifyMessageStatus("The item has been reverted.");
        publisherAdsSideFiles.VerifyAdSideFileStorageType("Atlas", "Default");
        
        //Step 14 - TODO automate as time allows
        
        //Step 15
        publisherAdsSideFiles.ClickAdSideFileExpandEditLnk("Atlas");
        publisherAdsSideFiles.ClickAdSideFileCloneLnk("Atlas");
        overlay.SwitchToActiveFrame();
        
        //Step 16
        String clonedSideFileName = random.GetCharacterString(15);
        addNewSideFile.EnterName(clonedSideFileName);
        addNewSideFile.ClickSaveBtn();
        contentParent.VerifyMessageStatus("clone_of_atlas has been created.");
        publisherAdsSideFiles.VerifyAdSideFileStorageType(clonedSideFileName, "Normal");
        
        //Step 17
        publisherAdsSideFiles.ClickAdSideFileEditLnk("Atlas");
        overlay.SwitchToActiveFrame();
        addNewSideFile.ClickExportTab();
        overlay.SwitchToActiveFrame();
        String exportTxt = addNewSideFile.CopyExportTxt();
        overlay.ClickCloseOverlayLnk();
        
        //Step 18
        taxonomy.NavigateSite("Structure>>Publisher Ads Side Files>>Import");
        overlay.SwitchToActiveFrame();
        
        //Step 19
        ImportSideFile importSideFile = new ImportSideFile(webDriver);
        importSideFile.EnterSideFileCode(exportTxt);
        importSideFile.UncheckAllowImportOverwriteRecordCbx();
        importSideFile.ClickContinueBtn();
        overlay.SwitchToActiveFrame();
        contentParent.VerifyMessageWarning("You have unsaved changes. These changes will not be made permanent until you click Save.");
        
        //Step 20
        String importSideFileName = random.GetCharacterString(15);
        addNewSideFile.EnterName(importSideFileName);
        String importedSideFilePath = random.GetCharacterString(10) + "/" + random.GetCharacterString(10) + ".html";
        addNewSideFile.EnterPath(importedSideFilePath);
        addNewSideFile.ClickUpdateBtn();
        overlay.SwitchToActiveFrame();
        contentParent.VerifyMessageWarning("You have unsaved changes. These changes will not be made permanent until you click Save.");
        
        //Step 21
        addNewSideFile.ClickSaveBtn();
        overlay.SwitchToActiveFrame();
        addNewSideFile.EnterMachineName(importSideFileName.toLowerCase());
        addNewSideFile.ClickUpdateBtn();
        overlay.SwitchToActiveFrame();
        contentParent.VerifyMessageWarning("You have unsaved changes. These changes will not be made permanent until you click Save.");
        addNewSideFile.ClickSaveBtn();
        overlay.SwitchToActiveFrame();
        contentParent.VerifyMessageStatus(importSideFileName.toLowerCase() + " has been created.");
        publisherAdsSideFiles.VerifyAdSideFileStorageType(importSideFileName, "Normal");
        
        //Step 22
        List<String> newlyCreatedSideFiles = Arrays.asList(sideFileName, clonedSideFileName, importSideFileName);
        for (String sideFile : newlyCreatedSideFiles) {
        	publisherAdsSideFiles.ClickAdSideFileExpandEditLnk(sideFile);
            publisherAdsSideFiles.ClickAdSideFileDeleteLnk(sideFile);
            overlay.SwitchToActiveFrame();
            Delete delete = new Delete(webDriver);
            delete.ClickDeleteBtn();
            overlay.SwitchToActiveFrame();
            contentParent.VerifyMessageStatus("The item has been deleted.");
            publisherAdsSideFiles.VerifyAdSideFileNotPresent(sideFile);
        }
        
    }
}
