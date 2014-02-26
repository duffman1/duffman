package com.nbcuni.test.publisher.tests.Setup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class TestCleanup extends ParentTest{
	
    /*************************************************************************************
     * Test executes some common teardown logic after the full suite execution
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "smoke", "mpx"})
    public void TestSetup_Test() throws Exception{
         
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
            
            //Step 2
            Modules modules = new Modules(webDriver, applib);
            if (applib.getApplicationURL().contains("demo.publisher7.com")) {
            	modules.VerifyModuleEnabled("Sticky Edit Actions");
            }
            
            //Step 3 (DE3921)
            webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            try {
            	List<String> eachURL = new ArrayList<String>();
            	String allURLs = null;
            	for (WebElement el : webDriver.findElements(By.xpath("//a[text()='MPX Video for Account \"DB TV\" (2312945284)']"))) {
            		allURLs = allURLs + el.getAttribute("href");
            		eachURL.add(el.getAttribute("href"));
            	}
            	allURLs = allURLs.replaceAll(applib.getApplicationURL() + "/admin/structure/file-types/manage/", "");
            	String[] index = allURLs.split("mpx_video_");
            	ArrayList<Integer> allIndexInts = new ArrayList<Integer>();
            	allIndexInts.removeAll(Collections.singleton("empty"));
            	for (String s : index) {
            		try {
            			allIndexInts.add(Integer.parseInt(s));
            		}
            		catch (NumberFormatException e) {}
            	}
            	Integer maxScore = Collections.max(allIndexInts);
    		
            	for (String url : eachURL) {
    			
            		if (!url.contains(maxScore.toString())) {
            			webDriver.navigate().to(url);
            			webDriver.findElement(By.id("edit-delete")).click();
            			webDriver.findElement(By.id("edit-submit")).click();
            		}
            	}
            }
            catch (Exception e) {}
            webDriver.manage().timeouts().implicitlyWait(applib.getImplicitWaitTime(), TimeUnit.SECONDS);
            
            //Step 6 - if Cindia's demo set set timezone to NY
            if (applib.getApplicationURL().contains("demo.publisher7.com")) {
            	webDriver.navigate().to(applib.getApplicationURL() + "/admin/config/regional/settings");
            	new Select(webDriver.findElement(By.id("edit-date-default-timezone"))).selectByValue("America/New_York");
            	webDriver.findElement(By.id("edit-submit")).click();
            }
            
            //Step 7
            taxonomy.NavigateSite("Home>>Flush all caches");
            
    }
}
