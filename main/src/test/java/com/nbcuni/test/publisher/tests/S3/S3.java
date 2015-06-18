package com.nbcuni.test.publisher.tests.S3;

import com.nbcuni.test.publisher.pageobjects.Configuration.ConfigPreferences;
import com.nbcuni.test.publisher.pageobjects.Modules;
import org.springframework.beans.factory.annotation.Value;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

/**
 * Holder for S3 test cases.
 */
public class S3 extends BaseTest {

    @Value("${api.key}")
    private String apiKey;

    @Value("${api.secret}")
    private String apiSecret;

    @Value("${api.bucket}")
    private String apiBucket;

    @Test
    public void basicConfiguration_TC8099() throws Exception {
        initialPage.
                navigate(siteMap.getModulesUrl(), Modules.class).
                EnableModule("AmazonS3").
                navigate(siteMap.getConfigUrl(), ConfigPreferences.class).
                goToAmazonSettings().
                setRequiredFields(apiKey, apiSecret, apiBucket);
    }

    @AfterClass
    public void tearDown() {
        webDriver.quit();
    }
}
