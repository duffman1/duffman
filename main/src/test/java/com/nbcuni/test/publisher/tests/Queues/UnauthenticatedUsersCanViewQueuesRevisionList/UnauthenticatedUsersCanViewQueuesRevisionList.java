package com.nbcuni.test.publisher.tests.Queues.UnauthenticatedUsersCanViewQueuesRevisionList;


import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Random;
import com.nbcuni.test.publisher.pageobjects.AccessDenied;
import com.nbcuni.test.publisher.pageobjects.Logout;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.Overlay;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.content.CastCrew;
import com.nbcuni.test.publisher.pageobjects.content.CharactersInformation;
import com.nbcuni.test.publisher.pageobjects.content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.content.ContentTypes;
import com.nbcuni.test.publisher.pageobjects.content.PersonsInformation;
import com.nbcuni.test.publisher.pageobjects.content.PublishingOptions;
import com.nbcuni.test.publisher.pageobjects.content.RevisionState;
import com.nbcuni.test.publisher.pageobjects.content.SelectFile;
import com.nbcuni.test.publisher.pageobjects.errorchecking.ErrorChecking;
import com.nbcuni.test.publisher.pageobjects.queues.DeleteQueue;
import com.nbcuni.test.publisher.pageobjects.queues.Queues;
import com.nbcuni.test.publisher.pageobjects.taxonomy.Taxonomy;
import com.nbcuni.test.webdriver.CustomWebDriver;
import com.nbcuni.test.webdriver.WebDriverClientExecution;


public class UnauthenticatedUsersCanViewQueuesRevisionList extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Invoke a new browser, and access the user login page of the Publisher 7 QA instance.  Note --> Do not login at all<br>
     * Step 2 - Access the following link:  http://<Replace with appropriate environment name>.publisher.nbcuni.com/#overlay=admin/content/queues/manage/all/revisions-state-flow-states  For Example: http://qa5dev.publisher.nbcuni.com/#overlay=admin/content/queues/manage/all/revisions-state-flow-states<br>
     * Step 3 - Access the following link:  http://<Replace with the appropriate environment name>/admin/content/queues/  For Example: http://qa3stg.publisher.nbcuni.com/admin/content/queues/
     * Step 4 - Access the following link:  http://<Replace with appropriate environment name>.publisher.nbcuni.com/admin/content/queues/manage/all/revisions-state-flow-states  For Example: http://qa3stg.publisher.nbcuni.com/admin/content/queues/manage/all/revisions-state-flow-states<br>
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(groups = {"full"})
    public void UnauthenticatedUsersCanViewQueuesRevisionList() throws Exception{
    	
        //Step 1
        applib.openApplication();
        
        //Step 2
        webDriver.navigate().to(applib.getApplicationURL() + "/#overlay=admin/content/queues/manage/all/revisions-state-flow-states");
        Overlay overlay = new Overlay(webDriver);
        overlay.SwitchToAccessDeniedFrm();
        AccessDenied accessDenied = new AccessDenied(webDriver);
        accessDenied.VerifyAccessDeniedTxt();
        
        //Step 3
        webDriver.navigate().to(applib.getApplicationURL() + "/admin/content/queues/");
        accessDenied.VerifyAccessDeniedTxt();
        
        //Step 4
        webDriver.navigate().to(applib.getApplicationURL() + "/admin/content/queues/manage/all/revisions-state-flow-states");
        accessDenied.VerifyAccessDeniedTxt();
        
        
    }
}
