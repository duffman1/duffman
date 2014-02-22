package com.nbcuni.test.publisher.tests.Metrics.AddAbilityOverrideSiteCatalystVariablesPerNodeBasis;

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

public class AddAbilityOverrideSiteCatalystVariablesPerNodeBasis extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE
     * Step 1 - Login to P7 using valid credentials (user1) ,Login Successful 
     * Step 2 - Go to Configuration -> System -> Site Catalyst ,Site Catalyst overlay appears 
     * Step 3 - Scroll down and Click on Custom Variable ,Custom variable expands 
	 * Step 4 - 1.Click on overrides. 2. All the content types are seen. 3. Put a check on media gallery, post. 4. Click on Save Configuration ,Content types are selected and configuration is done successfully. 
     * Step 5 - Navigate to Content -> Media Gallery ->Set required fields-> On vertical tab Click on "Sitecatalyst Variables" options.                                                                                               Note: "Site Catalyst Variable" tab is seen on those content types which are configured in step 2 above ,Default variable overlay appears with varaible fields "Name" and "value"  field. 
     * Step 6 - 1. For Variable "s.pageName" set any value eg. "sitecatalyst" 2.Click on Add variable- Under Name column set "s.prop10" - Set Value column with token from "Browse available token link" eg. [node:content-type] 3. Save the content node. ,Selected token value should come up in the field box which was clicked. 
     * Step 7 - Go to Home page -> Flush all cache-> Right click and View Pagesource.  ,On the page source token value given under the variable in prior step should be displayed.  s.pageName="sitecatalyst"; s.channel="testing-sitecatalyst"; s.linkInternalFilters="javascript:,this-site.com"; s.prop1="authenticated user, administrator"; s.prop2="testing-sitecatalyst"; s.prop3="Media Gallery"; s.prop4="testing sitecatalyst"; s.prop6="http://qa4dev.publisher.nbcuni.com/testing-sitecatalyst"; s.contextData["tve_domain"]=""; s.contextData["tve_date"]="02-14-2014"; s.contextData["tve_day"]="Friday"; s.contextData["tve_hour"]="03"; s.contextData["tve_minute"]="03:38"; s.prop8="Operations"; s.prop9="NBCUOTS"; s.prop10="Media Gallery";              
     * Step 8 - 1. Go to edit page of content node created in step 6 2. Under SiteCatalyst variable option update/change the value field populated in step6. 3. Add new variable. 4. Save the content node. 5. Go to Home page -> Flush cache 6. View PageSource-> Verify that changes made in variable values, newly added variable/value are seen. ,Changes are updated as expected 
     * Step 9 - Logout from P7 ,Logout successful 
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void AddAbilityOverrideSiteCatalystVariablesPerNodeBasis_Test() throws Exception {
         
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
