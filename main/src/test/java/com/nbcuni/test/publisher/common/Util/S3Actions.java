package com.nbcuni.test.publisher.common.Util;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kiryl_zayets on 6/23/15.
 */
public class S3Actions {

    @Autowired
    AmazonS3 s3client;

    @Autowired
    ListObjectsRequest listObjectsRequest;

    public S3Actions(){};

    public void deleteKeysByPattern(String bucket, String pattern) {
        ArrayList<String> keys = (ArrayList<String>) findKeys(bucket, pattern);
        for (String key : keys) {
            s3client.deleteObject(new DeleteObjectRequest(bucket, key));
            System.out.println(key);
        }
    }

    public List<String> findKeys(String bucket, String pattern) {
        ArrayList<String> keys = new ArrayList<>();
        ObjectListing objectListing = s3client.listObjects(listObjectsRequest.withBucketName(bucket));
        for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
            String key = objectSummary.getKey();
            if (key.contains(pattern)) {
                keys.add(key);
                System.out.println(key);
            }
        }
        return keys;
    }

}
