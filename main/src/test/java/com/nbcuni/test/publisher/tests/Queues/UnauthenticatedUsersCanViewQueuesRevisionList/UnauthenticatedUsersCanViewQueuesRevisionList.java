package com.nbcuni.test.publisher.tests.Queues.UnauthenticatedUsersCanViewQueuesRevisionList;

import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.AccessDenied;

public class UnauthenticatedUsersCanViewQueuesRevisionList extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Invoke a new browser, and access the user login page of the Publisher 7 QA instance.  Note --> Do not login at all<br>
     * Step 2 - Access the following link:  http://<Replace with appropriate environment name>.publisher.nbcuni.com/#overlay=admin/content/queues/manage/all/revisions-state-flow-states  For Example: http://qa5dev.publisher.nbcuni.com/#overlay=admin/content/queues/manage/all/revisions-state-flow-states<br>
     * Step 3 - Access the following link:  http://<Replace with the appropriate environment name>/admin/content/queues/  For Example: http://qa3stg.publisher.nbcuni.com/admin/content/queues/
     * Step 4 - Access the following link:  http://<Replace with appropriate environment name>.publisher.nbcuni.com/admin/content/queues/manage/all/revisions-state-flow-states  For Example: http://qa3stg.publisher.nbcuni.com/admin/content/queues/manage/all/revisions-state-flow-states<br>
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void UnauthenticatedUsersCanViewQueuesRevisionList_Test() throws Exception{
    	
        //Step 1
        applib.openApplication();
        
        //Step 2
        webDriver.navigate().to(config.getConfigValueString("AppURL") + "/#overlay=admin/content/queues/manage/all/revisions-state-flow-states");
        overlay.SwitchToActiveFrame();
        AccessDenied accessDenied = new AccessDenied(webDriver);
        accessDenied.VerifyAccessDeniedTxt();
        
        //Step 3
        webDriver.navigate().to(config.getConfigValueString("AppURL") + "/admin/content/queues/");
        accessDenied.VerifyAccessDeniedTxt();
        
        //Step 4
        webDriver.navigate().to(config.getConfigValueString("AppURL") + "/admin/content/queues/manage/all/revisions-state-flow-states");
        accessDenied.VerifyAccessDeniedTxt();
        
        
    }
}
