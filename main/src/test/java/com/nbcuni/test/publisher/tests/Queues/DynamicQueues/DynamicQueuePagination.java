package com.nbcuni.test.publisher.tests.Queues.DynamicQueues;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.CreateDefaultContent;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues.AddDynamicQueue;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues.AddDynamicQueueType;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues.DynamicQueues;

import org.testng.Reporter;
import org.testng.annotations.Test;

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
@Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
public void CreateConfigureDynamicQueue_TC4197() throws Exception{
//Test Data. please note that this test case is not meant for 'Total Limit' = 0.
int itemsPerPage = 2;
int totalLimit = 6; 
String testContent[] = new String[totalLimit]; 
webDriver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
   
        Reporter.log("STEP 1");
        UserLogin userLogin = applib.openApplication();
        userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
        Reporter.log("SETUP");
        CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver, applib);
        
        // create testContent ArrayList of Posts
        
        for (int i=0; i < testContent.length; i++){
    
        testContent[i] = createDefaultContent.Post("Published");
       
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
        contentParent.VerifyPageCtrElementPresent();
        contentParent.VerifyCorrectNumberOfPagesDisplayed(itemsPerPage, totalLimit);
        
        
        // Verify content on first page
        int i = testContent.length; 
        
        contentParent.VerifyPageContentPresent(Arrays.asList(testContent[i-1], testContent[i-2]));
        while (contentParent.isNextDynamicQueuePageExists()) {
        i=i-2;
       
        Reporter.log("since next page exists, click on 'next >' link.");
        contentParent.ClickNextPageLink(); 
        Reporter.log("Verify page containing expected content.");
            contentParent.VerifyPageContentPresent(Arrays.asList(testContent[i-1], testContent[i-2]));       
       
        }
        
}

}

