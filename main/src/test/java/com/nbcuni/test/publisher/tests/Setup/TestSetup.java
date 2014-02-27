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

public class TestSetup extends ParentTest{
	
    /*************************************************************************************
     * Test executes some common setup logic prior to full suite execution
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "smoke", "mpx"})
    public void TestSetup_Test() throws Exception{
         
        	//login
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
            
            //disable sticky edit actions module
            taxonomy.NavigateSite("Modules");
        	overlay.SwitchToFrame("Modules");
        	Modules modules = new Modules(webDriver, applib);
        	modules.EnterFilterName("Sticky Edit Actions");
        	modules.DisableModule("Sticky Edit Actions");
            
        	//disable devel module
        	modules.EnterFilterName("Devel");
            modules.DisableModule("Devel");
            
            //disable database logging module
            modules.EnterFilterName("Database logging");
            modules.DisableModule("Database logging");
            overlay.ClickCloseOverlayLnk();
            overlay.switchToDefaultContent();
            
            //if Cindia's demo site, set set timezone to UTC
            if (applib.getApplicationURL().contains("demo.publisher7.com")) {
            	webDriver.navigate().to(applib.getApplicationURL() + "/admin/config/regional/settings");
            	new Select(webDriver.findElement(By.id("edit-date-default-timezone"))).selectByValue("UTC");
            	webDriver.findElement(By.id("edit-submit")).click();
            }
            
          //delete any old mpx account file types (DE3921)
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
            
            //flush all caches
            taxonomy.NavigateSite("Home>>Flush all caches");
            
    }
}
