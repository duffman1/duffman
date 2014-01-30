package com.nbcuni.test.publisher.tests.Security;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.AccessDenied;
import com.nbcuni.test.publisher.pageobjects.UserLogin;

public class FileViewDeniedToUnauthenticatedUsers extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Access the page <SiteBaseURL>admin/content/file, where <SiteBaseURL> is the site base URL, as in http://qa1dev.publisher.nbcuni.com/<br>
     * Step 2 - Log in to drupal with user 1 credentials<br>
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(groups = {"full"})
    public void FileViewDeniedToUnauthenticatedUsers_Test() throws Exception{
    	
    	//Step 1
        applib.openApplication();
        webDriver.navigate().to(applib.getApplicationURL() + "/admin/content/file");
        AccessDenied accessDenied = new AccessDenied(webDriver);
        accessDenied.VerifyAccessDeniedTxt();
        
        //Step 2
        UserLogin userLogin = new UserLogin(webDriver);
        PageFactory.initElements(webDriver, userLogin);
        userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Username());
        new WebDriverWait(webDriver, 10).until(ExpectedConditions.titleContains("Content"));
        
    }
}
