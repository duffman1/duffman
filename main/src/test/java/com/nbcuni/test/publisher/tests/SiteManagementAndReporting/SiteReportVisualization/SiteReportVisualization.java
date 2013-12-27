package com.nbcuni.test.publisher.tests.SiteManagementAndReporting.SiteReportVisualization;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
import com.nbcuni.test.publisher.pageobjects.queues.QueuesRevisionList;
import com.nbcuni.test.publisher.pageobjects.taxonomy.Taxonomy;
import com.nbcuni.test.webdriver.CustomWebDriver;
import com.nbcuni.test.webdriver.WebDriverClientExecution;


public class SiteReportVisualization extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Log in to the Publisher test site as Drupal User 1 (commonly user admin [admin@publisher.nbcuni.com], password pa55word)<br> 
     * Step 1a - Enable "Pub Report Server" module<br>
     * Step 2 - In the main menu, navigate to <SiteTop>admin/site-reports, where <SiteTop> is the URL of the site top/homepage<br>
,Step 3,Verify that the report includes  Site name Publisher_profile Admin theme Has module Time sent  filters associated with an Apply button.,The report includes  Site name Publisher_profile Admin theme Has module Time sent  filters associated with an Apply button.
,Step 4,Verify that the report list includes the columns  SITE NAME DRUPAL VERSION PUBLISHER VERSION PUBLISHER PROFILE ADMIN THEME DEFAULT THEME MODULES COUNT SHOW MODULES TIME SENT DETAILS  columns in that order from left to right, and that the SHOW MODULES column includes a checkbox (defaulted to unchecked) for each list entry, and that the  DETAILS column for each list entry includes a More hyperlink.,The report list includes the columns  SITE NAME DRUPAL VERSION PUBLISHER VERSION PUBLISHER PROFILE ADMIN THEME DEFAULT THEME MODULES COUNT SHOW MODULES TIME SENT DETAILS  columns in that order from left to right, and the SHOW MODULES column includes a checkbox (defaulted to unchecked) for each list entry, and the  DETAILS column for each list entry includes a More hyperlink.
,Step 5,By selecting entries in the Site name list and clicking Apply in turn, confirm that the list can be filtered to show:  a  A single site entry b  All sites by selecting the - Any - entry.,Using the Site name filter, the list can be filtered to show:  a  A single site entry b  All sites by selecting the - Any - entry. 
,Step 6,By selecting entries in the Publisher_profile list and clicking Apply in turn, confirm that the list can be filtered to show:  a  Entries for sites that use particular profiles in the list. b  All sites by selecting the - Any - entry.,The list can be filtered to show:  a  Entries for sites that use particular profiles in the list. b  All sites by selecting the - Any - entry. 
,Step 7,By typing a value in the Admin theme box and clicking Apply, confirm that the list can be filtered to show:  a  All sites by clearing the Admin theme box and clicking Apply.  b  Entries only for sites that use a particular admin theme present in the unfiltered list.,The list can be filtered to show:  a  All sites by clearing the Admin theme box and clicking Apply.  b  Entries only for sites that use a particular admin theme present in the unfiltered list
,Step 8,By selecting entries in the Time sent list and clicking Apply in turn, confirm that the list can be filtered to show:  a  All sites by selecting the - Any - entry.  b  Entries for fewer sites by selecting Most recent in the list.,The list can be filtered to show:  a  All sites by selecting the - Any - entry.  b  Entries for fewer sites by selecting Most recent in the list.
,Step 9,Filter the list by Time sent = Most recent, and then:  a  Check and uncheck the Show modules checkbox for each entry in the list.  b  As you display the module lists, note for later use one or more module names.,a  Checking and unchecking the Show modules checkbox for each list entry expands and shrinks that entry to display and hide a list of modules used in the site.  b  One or more module names are noted for later use.
,Step 10,By typing a value (examples: pub_post, views_ui, admin_menu, workbench) in the Has module box and clicking Apply, confirm that the list can be filtered to show:  a  All sites by clearing the Has module box and clicking Apply.  b  Entries only for sites that use a particular module.,The list can be filtered to show:  a  All sites by clearing the Has module box and clicking Apply.  b  Entries only for sites that use a particular module.
,Step 11,Filter the list by Time sent = Most recent, and then in turn, until you have examined each list entry:  a  Check the More checkbox for each entry in the list.  b  Use the browser Back button to return to the site listing page. ,a  Checking the More checkbox for a list entry displays a detail page for that site.  b  The browser Back button restores display of the Site reports list.   
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(groups = {"full", "smoke"})
    public void SiteReportVisualization() throws Exception{
    	
    	//Step 1
    	UserLogin userLogin = applib.openApplication();
        userLogin.Login("admin@publisher.nbcuni.com", "pa55word");
        
        //Step 1a
        Taxonomy taxonomy = new Taxonomy(webDriver);
        taxonomy.ClickTier1ModulesLnk();
        Overlay overlay = new Overlay(webDriver);
        overlay.SwitchToModulesFrm();
        Modules modules = new Modules(webDriver);
        modules.EnterFilterName("Pub Report Server");
        modules.EnableModule("Pub Report Server");
        overlay.ClickCloseOverlayLnk();
        overlay.switchToDefaultContent();
        
        //Step 2
        taxonomy.ClickTier1ReportsTier2PublisherReportsLnk();
        overlay.SwitchToSiteReportsFrm();
        
        //Step 3
        Assert.fail("Test steps indicate that I should be able to filter with results on columns but no action taken results in data present. Emailing pete/sruthi.");
        Assert.fail("Test under construction");
    }
}
