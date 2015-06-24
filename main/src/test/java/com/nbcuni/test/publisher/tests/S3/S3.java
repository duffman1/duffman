package com.nbcuni.test.publisher.tests.S3;

import com.nbcuni.test.publisher.bo.SimpleCustomContent;
import com.nbcuni.test.publisher.common.Util.S3Actions;
import com.nbcuni.test.publisher.pageobjects.Configuration.ConfigPreferences;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.Structure.ContentType.MediaGallery.ManageFields;
import com.nbcuni.test.publisher.pageobjects.Structure.ContentTypes;
import com.nbcuni.test.publisher.pageobjects.Structure.ManageFields.EditCustomCT;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.testng.Assert;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import java.util.ArrayList;

/**
 * Holder for S3 test cases.
 */

@Test(groups = {"S3"})
public class S3 extends BaseTest {

    @Value("${api.key}")
    private String apiKey;

    @Value("${api.secret}")
    private String apiSecret;

    @Value("${api.bucket}")
    private String apiBucket;

    @Autowired
    private SimpleCustomContent content;

    @Autowired
    private String code;

    @Autowired
    private S3Actions s3Actions;


    String key;

    @BeforeGroups(groups = {"S3"})
    public void clearBucket() {
        key = content.getImage().split("/")[1].split("\\.")[0];
        s3Actions.deleteKeysByPattern(apiBucket, key);
    }

    @Test
    public void basicConfiguration_TC8099() throws Exception {
        initialPage.
                navigate(siteMap.getModulesUrl(), Modules.class).
                EnableModule("AmazonS3").
                EnableModule("Devel").
                navigate(siteMap.getConfigUrl(), ConfigPreferences.class).
                goToAmazonSettings().
                setRequiredFields(apiKey, apiSecret, apiBucket);
    }

    @Test(dependsOnMethods = "basicConfiguration_TC8099")
    public void imageConfiguration_TC8595() throws Exception {
        menu.Structure("Content types");
        new ContentTypes(webDriver).
                addContentType().
                EnterName(content.getContentName()).
                ClickSaveAddFieldsBtn().
                EnterAddNewField(content.getField()).
                SelectFieldType(content.getFieldType()).
                SelectWidget(content.getWidget()).
                ClickSaveBtn();

        ManageFields manageFields = new ManageFields(webDriver).
                save().
                checkS3boxes().
                checkSchemas().
                checkDestinationS3(apiBucket).
                checkRequiredField().
                checkAllowedFileTypes().
                save();
        new EditCustomCT(webDriver).selectImageForBundle(content.getField());

        webDriver.get(siteMap.getAddContent());


        String loc = String.format("a[href*='%s']:not([class]", code.replace('.', '-'));

        webDriver.findElement(By.cssSelector(loc)).click();
        webDriver.findElement(By.id("edit-title")).sendKeys(content.getContentName());
        webDriver.findElement(By.cssSelector("a.button.browse")).click();

        manageFields.attachImageFrame(content.getImage());
        manageFields.next().next().save().save();

    }

    @Test(dependsOnMethods = "imageConfiguration_TC8595")
    public void getBucketFiles() {
        ArrayList<String> uploadedImages = (ArrayList<String>) s3Actions.findKeys(apiBucket, key);
        Assert.assertEquals(uploadedImages.size(), 3);
    }
}
