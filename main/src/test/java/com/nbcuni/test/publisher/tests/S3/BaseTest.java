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

    @Autowired protected WebDriver webDriver;

    @Autowired protected User admin;

    @Autowired protected SiteMap siteMap;

    @Autowired protected UserLogin userLogin;

    @Autowired protected EmberNav menu;

    @BeforeClass
    public void init() throws Exception {
        userLogin.
                navigate().
                Login(admin.getUser(), admin.getPassword());
    }

    @AfterClass
    public void tearDown() {
        webDriver.quit();
    }
}
