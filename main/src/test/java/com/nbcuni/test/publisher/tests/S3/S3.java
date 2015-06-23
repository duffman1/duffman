package com.nbcuni.test.publisher.tests.S3;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.nbcuni.test.publisher.bo.SimpleCustomContent;
import com.nbcuni.test.publisher.pageobjects.Configuration.ConfigPreferences;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.Structure.ContentType.MediaGallery.ManageFields;
import com.nbcuni.test.publisher.pageobjects.Structure.ContentTypes;
import com.nbcuni.test.publisher.pageobjects.Structure.ManageFields.EditCustomCT;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

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

    AmazonS3 s3client;
    ObjectListing objectListing;
    ListObjectsRequest listObjectsRequest;


    @BeforeGroups(groups = {"S3"})
    public void clearBucket() {
        AWSCredentials credentials = new BasicAWSCredentials(apiKey, apiSecret);
        s3client = new AmazonS3Client(credentials);
       listObjectsRequest = new ListObjectsRequest().withBucketName(apiBucket);

        objectListing = s3client.listObjects(listObjectsRequest);
        for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
            if (objectSummary.getKey().contains(content.getImage().split("/")[1].split("\\.")[0])) {
                System.out.println(objectSummary.getKey());
                s3client.deleteObject(new DeleteObjectRequest(apiBucket, objectSummary.getKey()));
            }
        }
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
        objectListing = s3client.listObjects(listObjectsRequest);
        for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
            if (objectSummary.getKey().contains(content.getImage().split("/")[1].split("\\.")[0])) {
                s3client.deleteObject(new DeleteObjectRequest(apiBucket, objectSummary.getKey()));
                System.out.println(objectSummary.getKey());
            }
        }
    }
}
