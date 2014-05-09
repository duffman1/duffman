package com.nbcuni.test.publisher.tests.Metrics.SiteCatalyst;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.SiteCatalyst;
import com.nbcuni.test.publisher.pageobjects.Content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.Content.SiteCatalystVariables;
import com.nbcuni.test.publisher.pageobjects.Content.WorkBench;

public class ImplementSiteCatalyst extends ParentTest {
	
    /*************************************************************************************
     * TEST CASE - TC1061
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/17441703257
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void ImplementSiteCatalyst_TC1061() throws Exception {
         
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
            
        	//Step 2
        	taxonomy.NavigateSite("Configuration>>System>>SiteCatalyst");
        	overlay.SwitchToActiveFrame();
            
        	//Step 3
        	SiteCatalyst siteCatalyst = new SiteCatalyst(webDriver, applib);
        	siteCatalyst.ClickCustomVariablesLnk();
        	
        	//Step 4
        	siteCatalyst.ClickOverridesLnk();
        	siteCatalyst.ClickMediaGalleryCbx();
        	siteCatalyst.ClickPostCbx();
        	siteCatalyst.ClickSaveConfigurationBtn();
        	ContentParent contentParent = new ContentParent(webDriver, applib);
        	contentParent.VerifyMessageStatus("The configuration options have been saved.");
        	
        	//Step 5
        	overlay.ClickCloseOverlayLnk();
        	taxonomy.NavigateSite("Content>>Add content>>Media Gallery");
        	overlay.SwitchToActiveFrame();
        	BasicInformation basicInformation = new BasicInformation(webDriver);
        	String mediaGalleryTitle = "MediaGallery" + random.GetCharacterString(10);
        	basicInformation.EnterTitle(mediaGalleryTitle);
        	SiteCatalystVariables siteCatalystVariables = new SiteCatalystVariables(webDriver);
        	siteCatalystVariables.ClickSiteCatalystVariablesTab();
        	
        	//Step 6 NOTE - clicking add another variable step not needed as there is always a blank option with a new piece of content
        	String sPageNameVariable = random.GetCharacterString(15);
        	siteCatalystVariables.EnterVariableValue("s.pageName", sPageNameVariable);
        	String newVariableName = "s." + random.GetCharacterString(15);
        	String newVariableValue = random.GetCharacterString(15);
        	siteCatalystVariables.EnterBlankVariableName(newVariableName);
        	siteCatalystVariables.EnterBlankVariableValue(newVariableValue);
        	contentParent.ClickSaveBtn();
        	overlay.switchToDefaultContent();
        	contentParent.VerifyMessageStatus("Media Gallery " + mediaGalleryTitle + " has been created.");
        	
        	//Step 7
        	String pageSource = webDriver.getPageSource();
        	if (!pageSource.contains(sPageNameVariable)) {
        		Assert.fail("Page source does not contain site catalyst variable '" + sPageNameVariable + "'.");
        	}
        	if (!pageSource.contains(newVariableName) || !pageSource.contains(newVariableValue)) {
        		Assert.fail("Page source does not contain site catalyst name/variable '" + newVariableName + "'.");
        	}
        	
        	//Step 8
        	WorkBench workBench = new WorkBench(webDriver, applib);
        	workBench.ClickWorkBenchTab("Edit Draft");
        	overlay.SwitchToActiveFrame();
        	siteCatalystVariables.ClickSiteCatalystVariablesTab();
        	String updatedVariableValue = random.GetCharacterString(15);
        	siteCatalystVariables.EnterVariableValue(newVariableName, updatedVariableValue);
        	String newVariableName2 = "s." + random.GetCharacterString(15);
        	String newVariableValue2 = random.GetCharacterString(15);
        	siteCatalystVariables.EnterBlankVariableName(newVariableName2);
        	siteCatalystVariables.EnterBlankVariableValue(newVariableValue2);
        	contentParent.ClickSaveBtn();
        	overlay.switchToDefaultContent();
        	contentParent.VerifyMessageStatus("Media Gallery " + mediaGalleryTitle + " has been updated.");
        	String pageSource2 = webDriver.getPageSource();
        	if (!pageSource2.contains(updatedVariableValue)) {
        		Assert.fail("Page source does not contain site catalyst variable '" + updatedVariableValue + "'.");
        	}
        	if (!pageSource2.contains(newVariableName2) || !pageSource2.contains(newVariableValue2)) {
        		Assert.fail("Page source does not contain site catalyst name/variable '" + newVariableName2 + "'.");
        	}
        	
        	//Step 9 - NA
    }
}
