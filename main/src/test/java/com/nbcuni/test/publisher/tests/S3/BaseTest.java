package com.nbcuni.test.publisher.tests.S3;

import com.amazonaws.services.dynamodbv2.document.Page;
import com.nbcuni.test.publisher.SiteMap;
import com.nbcuni.test.publisher.bo.User;
import com.nbcuni.test.publisher.common.Driver.listeners.SeleniumTestListener;
import com.nbcuni.test.publisher.pageobjects.EmberNav;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.testng.annotations.BeforeClass;

/**
 * Created by kiryl_zayets on 6/17/15.
 */

@TestExecutionListeners(SeleniumTestListener.class)
@ContextConfiguration(locations = {"classpath:entry.xml"})
public class BaseTest extends AbstractCustomContext {

    @Autowired WebDriver webDriver;
    @Autowired User admin;
    @Autowired SiteMap siteMap;
    @Autowired UserLogin userLogin;
    @Autowired EmberNav menu;


    @BeforeClass
    public void init() throws Exception {
        userLogin.
                navigate(siteMap.getBaseUrl()).
                Login(admin.getUser(), admin.getPassword());
    }
}
