package com.nbcuni.test.publisher.tests.S3;

import com.nbcuni.test.publisher.SiteMap;
import com.nbcuni.test.publisher.bo.User;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Driver.DriverSetup;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;

/**
 * Created by kiryl_zayets on 6/17/15.
 */

@ContextConfiguration(locations = {"classpath:entry.xml"})
public class BaseTest extends AbstractTestNGSpringContextTests {

    UserLogin initialPage;
    Driver webDriver;

    @Autowired
    protected User admin;

    @Autowired
    protected SiteMap siteMap;

    @BeforeMethod
    public void init() throws Exception {
        DriverSetup driverSetup = new DriverSetup();
        webDriver = driverSetup.WebDriverSetup(true);
        webDriver.get(siteMap.getBaseUrl());
        initialPage = new UserLogin(webDriver);
        initialPage.Login(admin.getUser(), admin.getPassword());
    }
}
