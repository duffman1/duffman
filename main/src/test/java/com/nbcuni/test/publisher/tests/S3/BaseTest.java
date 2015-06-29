package com.nbcuni.test.publisher.tests.S3;

import com.nbcuni.test.publisher.SiteMap;
import com.nbcuni.test.publisher.bo.User;
import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Random;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.EmberNav;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

/**
 * Created by kiryl_zayets on 6/17/15.
 */

@ContextConfiguration(locations = {"classpath:entry.xml"})
public class BaseTest extends AbstractCustomContext {

    UserLogin initialPage;
//    Driver webDriver;
    EmberNav menu;
    protected ContentParent contentParent;
    protected Config config = new Config();
    protected Random random;

    @Autowired
    WebDriver webDriver;

    @Autowired
    protected User admin;

    @Autowired
    protected SiteMap siteMap;

    @BeforeClass
    public void init() throws Exception {
//        DriverSetup driverSetup = new DriverSetup();
//        webDriver = driverSetup.WebDriverSetup(true);
        webDriver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        webDriver.get(siteMap.getBaseUrl());
        initialPage = new UserLogin(webDriver);
        initialPage.Login(admin.getUser(), admin.getPassword());
        menu = new EmberNav(webDriver);
        contentParent = new ContentParent(webDriver);
        random = new Random();
    }



    @AfterClass
    public void tearDown() {
        webDriver.quit();
    }
}
