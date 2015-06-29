package com.nbcuni.test.publisher.tests.Security;

import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.AccessDenied;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class FileViewDeniedToUnauthenticatedUsers extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Access the page <SiteBaseURL>admin/content/file, where <SiteBaseURL> is the site base URL, as in http://qa1dev.publisher.nbcuni.com/<br>
     * Step 2 - Log in to drupal with user 1 credentials<br>
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void FileViewDeniedToUnauthenticatedUsers_Test() throws Exception{
    	
    	//Step 1
        applib.openSitePage("/admin/content/file");
        AccessDenied accessDenied = new AccessDenied(webWebWebDriver);
        accessDenied.VerifyAccessDeniedTxt();
        
        //Step 2
        UserLogin userLogin = new UserLogin(webWebWebDriver);
        userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        new WebDriverWait(webWebWebDriver, 10).until(ExpectedConditions.titleContains("Content"));
        
    }
}
