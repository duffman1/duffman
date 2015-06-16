package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.Mapper;

import java.util.Arrays;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.RestWSSchemaMapping;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class Phase1ContentTypeMapper extends ParentTest {
	
	Boolean testSuccessful;
	
    /*************************************************************************************
     * TEST CASE - TC5260
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/23092901752
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full" })
    public void Phase1ContentTypeMapper_TC5260() throws Exception{
         
    		testSuccessful = false;
    		
        	Reporter.log("STEP 1");
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
            
            Reporter.log("STEP 2");
            navigation.Modules();
            Modules modules = new Modules(webDriver);
            for (String module : Arrays.asList("Pub Schema Example", "RestWS Schema", "RestWS Schema UI")) {
            	modules.EnterFilterName(module);
            	modules.EnableModule(module);
            }
            
            Reporter.log("STEP 3");
            navigation.Configuration("RestWS Schema mapping");
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
            
            navigation.Modules();
            for (String module : Arrays.asList("Pub Schema Example", "RestWS Schema UI")) {
            	modules.EnterFilterName(module);
            	modules.DisableModule(module);
            }
            
            testSuccessful = true;
            
    }
    
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"}, dependsOnMethods = {"Phase1ContentTypeMapper_TC5260"}, alwaysRun=true)
	public void Cleanup() throws Exception {
    	if (testSuccessful == false) {
    		UserLogin userLogin = applib.openApplication();
    		userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
    		navigation.Modules();
    		Modules modules = new Modules(webDriver);
    		for (String module : Arrays.asList("Pub Schema Example", "RestWS Schema UI")) {
    			modules.EnterFilterName(module);
    			modules.DisableModule(module);
    		}
    	}
		
	}
}
