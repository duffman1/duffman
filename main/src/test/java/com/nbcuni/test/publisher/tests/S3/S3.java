package com.nbcuni.test.publisher.tests.S3;

import com.nbcuni.test.publisher.bo.Metadata;
import com.nbcuni.test.publisher.bo.SimpleCustomContent;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Util.S3Actions;
import com.nbcuni.test.publisher.common.Util.Str;
import com.nbcuni.test.publisher.pageobjects.Configuration.Amazons3;
import com.nbcuni.test.publisher.pageobjects.Configuration.ConfigPreferences;
import com.nbcuni.test.publisher.pageobjects.ContentList;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.Structure.ContentType.MediaGallery.ManageFields;
import com.nbcuni.test.publisher.pageobjects.Structure.ContentTypes;
import com.nbcuni.test.publisher.pageobjects.Structure.ManageFields.EditCustomCT;
import com.nbcuni.test.publisher.pageobjects.Upload;
import org.openqa.selenium.remote.server.handler.interactions.touch.Up;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.testng.Assert;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import java.util.ArrayList;

/**
 * Holder for S3 test cases. Cleanup S3 bucket. Creates custom content type with image field.
 * Attaches images. Verify that image is uploaded to S3 cloud and have thumbnail.
 */

@Test(groups = {"S3"})
public class S3 extends BaseTest {

    @Value("${api.key}")  String apiKey;
    @Value("${api.secret}")  String apiSecret;
    @Value("${api.bucket}")  String apiBucket;

    @Autowired @Qualifier("ct_without_metadata") SimpleCustomContent content;
    @Autowired @Qualifier("ct_metadata") SimpleCustomContent contentWithMetaData;

    @Autowired String code;
    @Autowired S3Actions s3Actions;
    @Autowired Metadata metaTags;
    @Autowired ContentTypes contentTypes;
    @Autowired ManageFields manageFields;
    @Autowired EditCustomCT editCustomCT;
    @Autowired ContentList contentList;
    @Autowired Modules modules;
    @Autowired ConfigPreferences preferences;
    @Autowired Amazons3 amazons3;
    @Autowired Upload upload;

    @Autowired AppLib appLib;

    String key;
    ArrayList<String> uploadedImagesBefore;
    ArrayList<String> uploadedImagesAfter;

    @BeforeGroups(groups = {"S3"})
    public void clearBucket() {
        key = Str.getImageNameFromPath(content.getImage());
        uploadedImagesBefore = (ArrayList<String>) s3Actions.findKeys(apiBucket, key);
    }

    @Test
    public void basicConfiguration_TC8099() throws Exception {
        appLib. navigate(siteMap.getModulesUrl(), Modules.class);
                modules.EnableModule("AmazonS3").
                EnableModule("Devel");
        appLib.navigate(siteMap.getConfigUrl(), ConfigPreferences.class);
                preferences.goToAmazonSettings();
                amazons3.setRequiredFields(apiKey, apiSecret, apiBucket);
    }

    @Test
    public void basicConfiguration() throws Exception {
        appLib.navigate(siteMap.getModulesUrl(), Modules.class);
                modules.EnableModule("AmazonS3").
                EnableModule("Devel");
        appLib.navigate(siteMap.getConfigUrl(), ConfigPreferences.class);
                preferences.goToAmazonSettings();
                amazons3.setRequiredFields(apiKey, apiSecret, apiBucket);
    }

    @Test(dependsOnMethods = "basicConfiguration_TC8099")
    public void imageConfiguration_TC8595() throws Exception {

        menu.Structure("Content types");

        contentTypes.
                addContentType().
                EnterName(content.getContentName()).
                ClickSaveAddFieldsBtn().
                EnterAddNewField(content.getField()).
                SelectFieldType(content.getFieldType()).
                SelectWidget(content.getWidget()).
                ClickSaveBtn();

        manageFields.
                save().
                checkS3boxes().
                checkSchemas().
                checkDestinationS3(apiBucket).
                checkRequiredField().
                checkAllowedFileTypes().
                save();
       editCustomCT.selectImageForBundle(content.getField());

        webDriver.get(siteMap.getAddContent());
        appLib.navigate(siteMap.getAddContent(), ContentList.class);
        contentList.openCreatedContentPattern(code);
        contentTypes.setTitle(content.getContentName());
        contentTypes.browseFile();
        manageFields.attachImageFrame(content.getImage());
        manageFields.next().next().save();
        contentTypes.isRendered();
        manageFields.save();
    }

    @Test(dependsOnMethods = "imageConfiguration_TC8595")
    public void checkMetaDataMapping() {
        webDriver.get(siteMap.getAddContent());
        appLib.navigate(siteMap.getAddContent(), ContentList.class);
        contentList.openCreatedContentPattern(code);
        contentTypes.setTitle(contentWithMetaData.getContentName());
        contentTypes.browseFile();
        manageFields.attachImageFrame(contentWithMetaData.getImage());
        manageFields.next().next();
        Assert.assertEquals(metaTags, upload.getMetadata());
    }

    @Test(dependsOnMethods = "imageConfiguration_TC8595")
    public void getBucketFiles() {
        uploadedImagesAfter = (ArrayList<String>) s3Actions.findKeys(apiBucket, key);
        uploadedImagesAfter.removeAll(uploadedImagesBefore);
        Assert.assertEquals(uploadedImagesAfter.size(), 2);
        Assert.assertTrue(uploadedImagesAfter.get(0).contains(key));
        Assert.assertTrue(uploadedImagesAfter.get(1).contains(key));
        Assert.assertTrue(uploadedImagesAfter.get(1).contains("thumbnail"));
    }
}
