package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentTypesEntities.CustomContentType;


import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nbcuni.test.publisher.ContentTypes;
import com.nbcuni.test.publisher.AppLib;
import com.nbcuni.test.publisher.Logout;
import com.nbcuni.test.publisher.Overlay;
import com.nbcuni.test.publisher.Taxonomy;
import com.nbcuni.test.publisher.UserLogin;
import com.nbcuni.test.publisher.common.Random;
import com.nbcuni.test.webdriver.CustomWebDriver;
import com.nbcuni.test.webdriver.WebDriverClientExecution;


public class AddingNewCustomContentType {
	
	private CustomWebDriver webDriver;
    private AppLib applib;

    /**
     * Instantiate the TestNG Before Class Method.
     * 
     * @param sEnv - environment
     * @throws Exception - error
     */
    @BeforeMethod(alwaysRun = true)
    @Parameters("Environment")
    public void startSelenium(@Optional("PROD") String sEnv) {
        try {
            webDriver = WebDriverClientExecution.getInstance().getDriver();
            applib = new AppLib(webDriver);
            applib.setEnvironmentInfo(sEnv);
        } catch (Exception e) {
            applib.fail(e.toString());
        }

    }

    /**
     * Instantiate the TestNG After Class Method.
     * 
     * @throws Exception - error
     */
    @AfterMethod(alwaysRun = true)
    public void stopSelenium() {
        try {
            webDriver.quit();
        } catch (Exception e) {
            applib.fail(e.toString());
        }

    }

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
    public void AddingNewCustomContentType() throws Exception{
        
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
            userLogin.Login("admin@publisher.nbcuni.com", "pa55word");
            
            //Step 2
            Taxonomy taxonomy = new Taxonomy(webDriver);
            taxonomy.MouseOverTier1StructureLnk();
            taxonomy.MouseOverTier1StructureTier2ContentTypeLnk();
            taxonomy.ClickTier1StructureTier2ContentTypeTier3AddContentTypeLnk();
            
            //Step 3
            ContentTypes contentTypes = new ContentTypes(webDriver);
            contentTypes.SwitchToAddContentTypeFrm();
            Random random = new Random();
            String contentTypeName = random.GetCharacterString(10);
            contentTypes.EnterName(contentTypeName);
            
            //Step 4
            contentTypes.ClickSaveBtn();
            contentTypes.VerifyContentTypeSaved(contentTypeName);
            Overlay overlay = new Overlay(webDriver);
            overlay.ClickCloseOverlayLnk();
            
            //Step 5
            applib.switchToDefaultContent();
            taxonomy.MouseOverTier1StructureLnk();
            taxonomy.MouseOverTier1StructureTier2ContentTypeLnk();
            taxonomy.MouseOverTier1StructureTier2ContentTypeTier3ContentTypeLnk(contentTypeName);
            taxonomy.ClickTier1StructureTier2ContentTypeTier3ContentTypeTier4ManageFieldsLnk(contentTypeName);
            
            //Step 6
            contentTypes.SwitchToNewContentTypeFrm(contentTypeName);
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
            applib.switchToDefaultContent();
            taxonomy.MouseOverTier1ContentLnk();
            taxonomy.MouseOverTier1ContentTier2AddContentLnk();
            taxonomy.ClickTier1ContentTier2AddContentTier3ContentTypeLnk(contentTypeName);
            contentTypes.SwitchToCreateContentFrm(contentTypeName);
            contentTypes.VerifyFieldSaveBtnPresent(newFieldName);
            
        
    }
}
