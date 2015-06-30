package com.nbcuni.test.publisher.tests.S3;

import com.nbcuni.test.publisher.SiteMap;
import com.nbcuni.test.publisher.bo.User;
import com.nbcuni.test.publisher.common.Driver.bpp.SeleniumContext;
import com.nbcuni.test.publisher.common.Driver.bpp.SeleniumTestListener;
import com.nbcuni.test.publisher.pageobjects.EmberNav;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

/**
 * Created by kiryl_zayets on 6/17/15.
 */

@TestExecutionListeners(SeleniumTestListener.class)
public class BaseTest extends AbstractCustomContext {

    @Autowired protected WebDriver webDriver;
    @Autowired protected User admin;
    @Autowired protected SiteMap siteMap;

    @Autowired protected UserLogin userLogin;

//    @Autowired protected EmberNav menu;


    @BeforeClass
    public void init() throws Exception {
        userLogin.
                navigate(siteMap.getBaseUrl()).
                Login(admin.getUser(), admin.getPassword());
    }
}
