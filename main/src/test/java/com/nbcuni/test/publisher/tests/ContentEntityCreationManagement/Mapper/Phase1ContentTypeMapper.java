package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.Mapper;

import java.util.Arrays;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Blocks;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.RestWSSchemaMapping;
import com.nbcuni.test.publisher.pageobjects.TVEModule.jQueryUpdate;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class Phase1ContentTypeMapper extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE - TC5260
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/23092901752
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full" })
    public void Phase1ContentTypeMapper_TC5260() throws Exception{
         
        	Reporter.log("STEP 1");
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
            
            Reporter.log("STEP 2");
            taxonomy.NavigateSite("Modules");
            overlay.SwitchToActiveFrame();
            Modules modules = new Modules(webDriver, applib);
            for (String module : Arrays.asList("Pub Schema Example", "RestWS Schema", "RestWS Schema UI")) {
            	modules.EnterFilterName(module);
            	modules.EnableModule(module);
            }
            overlay.ClickCloseOverlayLnk();
            
            Reporter.log("STEP 3");
            taxonomy.NavigateSite("Configuration>>Web services>>RestWS Schema mapping");
            overlay.SwitchToActiveFrame();
            RestWSSchemaMapping restWSSchemaMapping = new RestWSSchemaMapping(webDriver);
            restWSSchemaMapping.SelectShowSeason("- Please select -");
            restWSSchemaMapping.SelectSeasonsEpisodes("- Please select -");
            restWSSchemaMapping.SelectEpisodesSeason("- Please select -");
            restWSSchemaMapping.SelectBlogsShow("- Please select -");
            restWSSchemaMapping.SelectGallerysShow("- Please select -");
            restWSSchemaMapping.ClickSaveConfigurationBtn();
            contentParent.VerifyMessageStatus("The configuration options have been saved.");
            contentParent.VerifyPageContentPresent(Arrays.asList("The following resources are not mapped.", 
            		"show:seasons", "season:episodes", "episode:season", "blog:show", "gallery:show"));
            
            
    }
    
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"}, dependsOnMethods = {"Phase1ContentTypeMapper_TC5260"}, alwaysRun=true)
	public void Cleanup() throws Exception {
		UserLogin userLogin = applib.openApplication();
		userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
		taxonomy.NavigateSite("Modules");
        overlay.SwitchToActiveFrame();
        Modules modules = new Modules(webDriver, applib);
        for (String module : Arrays.asList("Pub Schema Example", "RestWS Schema UI")) {
        	modules.EnterFilterName(module);
        	modules.DisableModule(module);
        }
		
	}
}
