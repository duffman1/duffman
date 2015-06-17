package com.nbcuni.test.publisher.tests.S3;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Driver.DriverSetup;
import com.nbcuni.test.publisher.pageobjects.Configuration.Amazons3;
import com.nbcuni.test.publisher.pageobjects.Configuration.ConfigPreferences;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by kiryl_zayets on 6/17/15.
 */
public class S3 {
    Driver webDriver;
    Config config;
    AppLib appLib;

    @BeforeTest
    public void init() throws Exception {
        DriverSetup driverSetup = new DriverSetup();
        config = new Config();
        webDriver = driverSetup.WebDriverSetup(true);
        appLib = new AppLib(webDriver);
        UserLogin userLogin = appLib.openApplication();
        userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
    }

    @Test
    public void basicConfiguration_TC8099() throws Exception {
        appLib.openSitePage("/admin/modules");
        Modules modules = new Modules(webDriver);
        modules.EnableModule("AmazonS3");
        appLib.openSitePage("/admin/config");
        ConfigPreferences configPreferences = new ConfigPreferences(webDriver);
        Amazons3 amazons3 = configPreferences.goToAmazonSettings();
        amazons3.setRequiredFields("AKIAI2KTRQDN3BSRML5Q", "iEjFwkOOdUYsxTV3Wq45zMKsLorYpuT9aroIWPVd", "imgs.publisher7.com");
        Assert.assertTrue(amazons3.getStatus().contains("The configuration options have been saved."));
    }

    @AfterClass
    public void tearDown(){
        webDriver.quit();
    }
}
