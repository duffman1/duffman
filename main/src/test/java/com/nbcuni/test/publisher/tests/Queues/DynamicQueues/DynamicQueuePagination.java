package com.nbcuni.test.publisher.tests.Queues.DynamicQueues;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.ContentPagination;
import com.nbcuni.test.publisher.pageobjects.Content.CreateDefaultContent;
import com.nbcuni.test.publisher.pageobjects.Content.SearchFor;
import com.nbcuni.test.publisher.pageobjects.ErrorChecking.ErrorChecking;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues.AddDynamicQueue;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues.AddDynamicQueueType;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues.DynamicQueues;
import org.testng.Reporter;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DynamicQueuePagination extends ParentTest {
	
	/*************************************************************************************
	 * * publisher.nbcuni.com Driver Library. Copyright
	 * TEST CASE - TC4791
	 * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/21982955417  
 	* @author Vineela Juturu
 	* @version 1.1 Date: October 5, 2014
 	*************************************************************************************/
	@Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
	public void DynamicQueuePagination_TC4791() throws Exception{

		Reporter.log("STEP 1");
		UserLogin userLogin = applib.openApplication();
        userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
        Reporter.log("SETUP");
        taxonomy.NavigateSite("Content");
        overlay.SwitchToActiveFrame();
        Boolean contentPresent = false;
        SearchFor searchFor = new SearchFor(webDriver, applib);
        if (searchFor.GetSearchResultSize() > 20) {
        	contentPresent = true;
        }
        overlay.ClickCloseOverlayLnk();
        
        CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver, applib);
        List<String> postTitles = new ArrayList<String>();
        if (!contentPresent) {
        	for (int i=0; i < 7; i++) {
        		postTitles.add(createDefaultContent.Post("Published"));
        	}
        }
        
        Modules modules = new Modules(webDriver, applib);
        modules.VerifyModuleEnabled("Dynamic Queue");
        
        taxonomy.NavigateSite("Structure>>Dynamic Queue types>>Add dynamic queue type");
        overlay.SwitchToActiveFrame();
        
        String dynamicQueueTypeName = random.GetCharacterString(15);
        AddDynamicQueueType addDynamicQueueType = new AddDynamicQueueType(webDriver);
        addDynamicQueueType.EnterName(dynamicQueueTypeName);
        addDynamicQueueType.SelectCacheLifetime("1 min");
        addDynamicQueueType.SelectEntityType();
        addDynamicQueueType.ClickSaveBtn();
        overlay.SwitchToActiveFrame();
        contentParent.VerifyPageContentPresent(Arrays.asList(dynamicQueueTypeName));
        overlay.ClickCloseOverlayLnk();
       
        taxonomy.NavigateSite("Content>>Dynamic Queues>>Add " + dynamicQueueTypeName);
        overlay.SwitchToActiveFrame();
       
        String dynamicQueueTitle = random.GetCharacterString(15);
        AddDynamicQueue addDynamicQueue = new AddDynamicQueue(webDriver);
        addDynamicQueue.EnterTitle(dynamicQueueTitle);
        addDynamicQueue.CheckTargetBundle_Cbx("Post");
        addDynamicQueue.ClickSortByNewestRdb();
        
        Reporter.log("STEP 2");
        addDynamicQueue.EnterItemsPerPage(5);
        addDynamicQueue.EnterTotalItemsLimit(10);
        addDynamicQueue.ClickSaveDynamicQueueBtn();
        overlay.switchToDefaultContent(true);  
        
        Reporter.log("STEP 3");
        taxonomy.NavigateSite("Content>>Dynamic Queues");
        overlay.SwitchToActiveFrame();
        DynamicQueues dynamicQueues = new DynamicQueues(webDriver, applib);
        String dynamicQueueNodeID = dynamicQueues.GetDynamicQueueNodeNumber(dynamicQueueTitle);
        overlay.ClickCloseOverlayLnk();
        String parentWindow = webDriver.getWindowHandle();
        applib.openNewWindow();
        applib.switchToNewWindow(parentWindow);
        applib.openSitePage("/dynamic-queue/" + dynamicQueueNodeID);
        
        Reporter.log("STEP 4");
        ContentPagination contentPagination = new ContentPagination(webDriver);
        ErrorChecking errorChecking = new ErrorChecking(webDriver, applib);
        contentPagination.VerifyPageCtrPresent();
        dynamicQueues.VerifyVisibleLnkCount(5);
        contentPagination.ClickNextPageLnk();
        errorChecking.VerifyNoMessageErrorsPresent();
        dynamicQueues.VerifyVisibleLnkCount(5);
        
	}
}

