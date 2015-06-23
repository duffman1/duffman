package com.nbcuni.test.publisher.tests.S3;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.nbcuni.test.publisher.bo.SimpleCustomContent;
import com.nbcuni.test.publisher.pageobjects.Configuration.ConfigPreferences;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.Structure.ContentType.MediaGallery.ManageFields;
import com.nbcuni.test.publisher.pageobjects.Structure.ContentTypes;
import com.nbcuni.test.publisher.pageobjects.Structure.ManageFields.EditCustomCT;
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
    private SimpleCustomContent content;

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

    @Test
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

        new ManageFields(webDriver).
                attachImage(content.getImage()).
                checkDestinationS3().
                save().
                checkS3boxes().
                checkSchemas().
                checkDestinationS3().
                checkRequiredField().
                checkAllowedFileTypes().
                save();
        new EditCustomCT(webDriver).selectImageForBundle(content.getField());
    }

    @Test(dependsOnMethods = "imageConfiguration_TC8595")
    public void getBucketFiles(){
        AWSCredentials credentials = new BasicAWSCredentials(apiKey, apiSecret);
        AmazonS3 s3client = new AmazonS3Client(credentials);
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest().withBucketName(apiBucket);
        ObjectListing objectListing = s3client.listObjects(listObjectsRequest);
        for(S3ObjectSummary objectSummary: objectListing.getObjectSummaries()) {
            System.out.println(objectSummary.getKey());
        }
    }
}
