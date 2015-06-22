package com.nbcuni.test.publisher.tests.S3;

import com.nbcuni.test.publisher.bo.MediaGallery;
import com.nbcuni.test.publisher.pageobjects.Configuration.ConfigPreferences;
import com.nbcuni.test.publisher.pageobjects.Content.MediaGalleryPage;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.Structure.ContentType.MediaGallery.ManageFields;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    private MediaGallery mediaGallery;


    //    @Test
    public void basicConfiguration_TC8099() throws Exception {
        initialPage.
                navigate(siteMap.getModulesUrl(), Modules.class).
                EnableModule("AmazonS3").
                navigate(siteMap.getConfigUrl(), ConfigPreferences.class).
                goToAmazonSettings().
                setRequiredFields(apiKey, apiSecret, apiBucket);
    }

    //    @Test
    public void imageConfiguration_TC8595() throws Exception {
        menu.Structure("Content types");
        webDriver.findElement(By.cssSelector("a[href*='media-gallery/fields']")).click();
        webDriver.findElement(By.id("edit-fields-field-cover-item-edit")).click();
        ManageFields manageFields = new ManageFields(webDriver);
        manageFields.checkS3boxes();
        manageFields.checkSchemas();
        manageFields.checkDestinationS3();
        manageFields.save();
    }


    @Test
    public void createMediaGallery_TC333() throws Exception {
        menu.AddContent("Media Gallery");
        MediaGalleryPage mediaGal = new MediaGalleryPage(webDriver);
        mediaGal.fillBasicInfo(mediaGallery);
    }


    @AfterClass
    public void tearDown() {
        webDriver.quit();
    }
}
