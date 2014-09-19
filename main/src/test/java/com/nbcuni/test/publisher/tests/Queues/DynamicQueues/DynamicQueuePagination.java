package com.nbcuni.test.publisher.tests.Queues.DynamicQueues;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.ContentPagination;
import com.nbcuni.test.publisher.pageobjects.Content.CreateDefaultContent;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues.AddDynamicQueue;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues.AddDynamicQueueType;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues.DynamicQueues;

import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;


public class DynamicQueuePagination extends ParentTest{
/*************************************************************************************
* * publisher.nbcuni.com Driver Library. Copyright
     * TEST CASE - TC4791
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/21982955417  
     * @author Vineela Juturu
     * @version 1.0 Date: September 3, 2014
     *************************************************************************************/

	// Test will be Data Driven with following sets. 
	// Except TotalLimit = 0, This Test case will work for remaining all scenarios. 
	@DataProvider(name = "dqpaginationTestdata")
		public Object[][] provideData() {
 
			return new Object[][] { 
					{2, 6, 6}
			};
		
		}
	 
	@Test(dataProvider = "dqpaginationTestdata", retryAnalyzer = RerunOnFailure.class, groups = {"full"})
	public void DynamicQueuePagination_TC4791(int itemsPerPage, int totalLimit, int createContentTotal) throws Exception{

		String testContent[] = new String[createContentTotal]; 
		webDriver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
   
        Reporter.log("STEP 1");
        UserLogin userLogin = applib.openApplication();
        userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
        Reporter.log("SETUP");
        CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver, applib);
        
        // create testContent ArrayList of Posts
        for (int i=0; i < testContent.length; i++)
        	testContent[i] = createDefaultContent.Post("Published");
        
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
        
        Reporter.log("STEP 2 - SET ITEMS PER PAGE and TOTAL LIMIT");
        addDynamicQueue.EnterItemsPerPage(itemsPerPage);
        addDynamicQueue.EnterTotalItemsLimit(totalLimit);
        addDynamicQueue.ClickSaveDynamicQueueBtn();
        overlay.switchToDefaultContent(true);  
        
        Reporter.log("STEP 3 - Verification of display of pages");
        taxonomy.NavigateSite("Content>>Dynamic Queues");
        overlay.SwitchToActiveFrame();
        DynamicQueues dynamicQueues = new DynamicQueues(webDriver, applib);
        String dynamicQueueNodeID = dynamicQueues.GetDynamicQueueNodeNumber(dynamicQueueTitle);
        overlay.ClickCloseOverlayLnk();
        String parentWindow = webDriver.getWindowHandle();
        applib.openNewWindow();
        applib.switchToNewWindow(parentWindow);
        applib.openSitePage("/dynamic-queue/" + dynamicQueueNodeID);
        
        Reporter.log("STEP 4 - Verification of content on each page");
        ContentPagination contentPagination = new ContentPagination(webDriver);
        if (contentPagination.getExpectedNumberOfPages(itemsPerPage, totalLimit) == 1){
        	for(int index = (createContentTotal-1), loopvar = 0; loopvar <= (totalLimit-1); loopvar++)
        		 contentParent.VerifyPageContentPresent(Arrays.asList(testContent[index--])); 
   		 	Reporter.log("Single page displayed and Verified all content on Page");
        } else 
        {
        	int pageNum = 1;
        	int index = createContentTotal-1;
        	int currentPageItems = 0;
        	int loopVar;
        	
	        contentPagination.VerifyPageCtrElementPresent();
	        contentPagination.VerifyCorrectNumberOfPagesDisplayed(itemsPerPage, totalLimit);
        	
        	do{
        		// Click on NextPage link starting from Second page
        		if( pageNum != 1) {contentPagination.ClickNextPageLink();}
        		
	        	if((pageNum * itemsPerPage) <= totalLimit)
	        		currentPageItems = itemsPerPage;
	        	else
	        		currentPageItems = totalLimit - (pageNum-1) * itemsPerPage;
	        	
	        	for(loopVar = 0; loopVar <= (currentPageItems-1); loopVar++)
	        		contentParent.VerifyPageContentPresent(Arrays.asList(testContent[index--])); 
	        	
        		Reporter.log("On pagenum:'"+ pageNum + "' displayed content Verified");
		        	pageNum++;
	        	}while(contentPagination.isNextPageExists());
	    	}
	}
}

