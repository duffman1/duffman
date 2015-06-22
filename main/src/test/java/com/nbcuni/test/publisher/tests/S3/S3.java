package com.nbcuni.test.publisher.tests.S3;

import com.nbcuni.test.publisher.bo.MediaGallery;
import com.nbcuni.test.publisher.bo.SimpleCustomContent;
import com.nbcuni.test.publisher.pageobjects.Configuration.ConfigPreferences;
import com.nbcuni.test.publisher.pageobjects.Content.MediaGalleryPage;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.Structure.ContentType.MediaGallery.ManageFields;
import com.nbcuni.test.publisher.pageobjects.Structure.ContentTypes;
import com.nbcuni.test.publisher.pageobjects.Structure.ManageFields.EditCustomCT;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    private SimpleCustomContent content;


    //    @Test
    public void basicConfiguration_TC8099() throws Exception {
        initialPage.
                navigate(siteMap.getModulesUrl(), Modules.class).
                EnableModule("AmazonS3").
                navigate(siteMap.getConfigUrl(), ConfigPreferences.class).
                goToAmazonSettings().
                setRequiredFields(apiKey, apiSecret, apiBucket);
    }

    @Test
    public void imageConfiguration_TC8595() throws Exception {
        menu.Structure("Content types");
        webDriver.findElement(By.cssSelector("div#content a[href$='add']")).click();

        new ContentTypes(webDriver)
                .EnterName(content.getContentName()).
                ClickSaveAddFieldsBtn().
                EnterAddNewField(content.getField()).
                SelectFieldType(content.getFieldType()).
                SelectWidget(content.getWidget()).
                ClickSaveBtn();


        webDriver.findElement(By.id("edit-field-settings-uri-scheme-s3")).click();
        webDriver.findElement(By.id("edit-submit")).click();

        new ManageFields(webDriver).
                checkS3boxes().
                checkSchemas().
                checkDestinationS3().
                checkRequiredField().
                checkAllowedFileTypes().
                save();

        webDriver.findElement(By.cssSelector("div#tab-bar a[href*='manage']")).click();
        EditCustomCT editCustomCT = new EditCustomCT(webDriver);
        editCustomCT.selectImageForBundle(content.getField());

    }


    //    @Test
    public void createMediaGallery_TC333() throws Exception {
        menu.AddContent("Media Gallery");
        MediaGalleryPage mediaGal = new MediaGalleryPage(webDriver);
        mediaGal.fillBasicInfo(mediaGallery);
    }


}
