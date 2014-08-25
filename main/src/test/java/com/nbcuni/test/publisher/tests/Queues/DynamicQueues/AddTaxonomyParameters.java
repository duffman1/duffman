package com.nbcuni.test.publisher.tests.Queues.DynamicQueues;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.CreateDefaultContent;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues.AddDynamicQueueType;

import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class AddTaxonomyParameters extends ParentTest{
	
	/*************************************************************************************
     * TEST CASE - TC4420
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/21008432775
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void AddTaxonomyParameters_TC4420() throws Exception{
    	
    	webDriver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
    	
        Reporter.log("STEP 1");
        UserLogin userLogin = applib.openApplication();
        userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
        Reporter.log("SETUP");
        Modules modules = new Modules(webDriver, applib);
        modules.VerifyModuleEnabled("Dynamic Queue");
        CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver, applib);
        String tvShowName1 = createDefaultContent.TVShow("Published");
        
        Reporter.log("STEP 2");
        taxonomy.NavigateSite("Structure>>Dynamic Queue types>>Add dynamic queue type");
        overlay.SwitchToActiveFrame();
        String dynamicQueueTypeName = random.GetCharacterString(15);
        AddDynamicQueueType addDynamicQueueType = new AddDynamicQueueType(webDriver);
        addDynamicQueueType.EnterName(dynamicQueueTypeName);
        addDynamicQueueType.SelectEntityType();
        addDynamicQueueType.SelectCacheLifetime("1 min");
        addDynamicQueueType.EnableContentTypes(Arrays.asList("Movie", "Person"));
        addDynamicQueueType.ClickTaxonomyFilterLnk();
        addDynamicQueueType.EnableTaxonomyFilters(Arrays.asList("field_movie_primary_genre", 
        		"field_movie_secondary_genre", "field_additional_genres", "field_primary_genre", 
        		" field_movie_rating", "field_movie_type"));
        addDynamicQueueType.ClickSaveBtn();
        overlay.SwitchToActiveFrame();
        contentParent.VerifyPageContentPresent(Arrays.asList(dynamicQueueTypeName));
        overlay.ClickCloseOverlayLnk();
        
        Reporter.log("STEP 3");
        taxonomy.NavigateSite("Content>>Dynamic Queues>>Add " + dynamicQueueTypeName);
        overlay.SwitchToActiveFrame();
        
        
        
    }
    
}
