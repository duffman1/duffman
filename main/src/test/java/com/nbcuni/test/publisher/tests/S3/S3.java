package com.nbcuni.test.publisher.tests.S3;

import com.nbcuni.test.publisher.bo.Metadata;
import com.nbcuni.test.publisher.bo.SimpleCustomContent;
import com.nbcuni.test.publisher.common.Util.S3Actions;
import com.nbcuni.test.publisher.common.Util.Str;
import com.nbcuni.test.publisher.pageobjects.Configuration.ConfigPreferences;
import com.nbcuni.test.publisher.pageobjects.ContentList;
import com.nbcuni.test.publisher.pageobjects.EmberNav;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.Structure.ContentType.MediaGallery.ManageFields;
import com.nbcuni.test.publisher.pageobjects.Structure.ContentTypes;
import com.nbcuni.test.publisher.pageobjects.Structure.ManageFields.EditCustomCT;
import com.nbcuni.test.publisher.pageobjects.Upload;
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

    @Value("${api.key}")
    private String apiKey;

    @Value("${api.secret}")
    private String apiSecret;

    @Value("${api.bucket}")
    private String apiBucket;

    @Autowired @Qualifier("ct_without_metadata") private SimpleCustomContent content;

    @Autowired @Qualifier("ct_metadata") private SimpleCustomContent contentWithMetaData;

    @Autowired private String code;

    @Autowired private S3Actions s3Actions;

    @Autowired private Metadata metaTags;

    String key;

    ArrayList<String> uploadedImagesBefore;
    ArrayList<String> uploadedImagesAfter;

    @BeforeGroups(groups = {"S3"})
    public void clearBucket() {
        key = Str.getImageNameFromPath(content.getImage());
        uploadedImagesBefore = (ArrayList<String>) s3Actions.findKeys(apiBucket, key);
        System.out.println(webDriver);
    }

    @Test
    public void basicConfiguration_TC8099() throws Exception {
        userLogin.
                navigate(siteMap.getModulesUrl(), Modules.class).
                EnableModule("AmazonS3").
                EnableModule("Devel").
                navigate(siteMap.getConfigUrl(), ConfigPreferences.class).
                goToAmazonSettings().
                setRequiredFields(apiKey, apiSecret, apiBucket);

        System.out.println(webDriver);

    }
    @Test
    public void basicConfiguration() throws Exception {
        userLogin.
                navigate(siteMap.getModulesUrl(), Modules.class).
                EnableModule("AmazonS3").
                EnableModule("Devel").
                navigate(siteMap.getConfigUrl(), ConfigPreferences.class).
                goToAmazonSettings().
                setRequiredFields(apiKey, apiSecret, apiBucket);
        System.out.println(webDriver);

    }

    @Test(dependsOnMethods = "basicConfiguration_TC8099")
    public void imageConfiguration_TC8595() throws Exception {

        new EmberNav(webDriver).Structure("Content types");

        ContentTypes contentTypes = new ContentTypes(webDriver).
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
        ContentList contentList  = manageFields.navigate(siteMap.getAddContent(), ContentList.class);
        contentList.openCreatedContentPattern(code);
        contentTypes.setTitle(content.getContentName());
        contentTypes.browseFile();
        manageFields.attachImageFrame(content.getImage());
        manageFields.next().next().save();
        contentTypes.isRendered();
        manageFields.save();
        System.out.println(webDriver);
    }

    @Test(dependsOnMethods = "imageConfiguration_TC8595")
    public void checkMetaDataMapping(){
        ContentTypes contentTypes = new ContentTypes(webDriver);
        ManageFields manageFields = new ManageFields(webDriver);

        webDriver.get(siteMap.getAddContent());
        ContentList contentList  = manageFields.navigate(siteMap.getAddContent(), ContentList.class);
        contentList.openCreatedContentPattern(code);
        contentTypes.setTitle(contentWithMetaData.getContentName());
        contentTypes.browseFile();
        manageFields.attachImageFrame(contentWithMetaData.getImage());
        manageFields.next().next();
        Assert.assertEquals(metaTags, new Upload(webDriver).getMetadata());
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
