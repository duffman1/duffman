package com.nbcuni.test.publisher.pageobjects.Content;


import com.nbcuni.test.publisher.Components.BasicInfo;
import com.nbcuni.test.publisher.bo.MediaGallery;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.pageobjects.Page;

/**
 * Created by kiryl_zayets on 6/21/15.
 */
public class MediaGalleryPage extends Page {

    public MediaGalleryPage(Driver webDriver) {
        super(webDriver);
        basicInformation.webDriver = webDriver;
    }

    BasicInfo basicInformation;

    public void fillBasicInfo(MediaGallery mediaGallery){
        basicInformation.setTitle(mediaGallery.getTitle());
        basicInformation.setShortDescription(mediaGallery.getShortDescription());
        basicInformation.setBody(mediaGallery.getBody());
        basicInformation.selectCover(mediaGallery.getCoverItem());
        basicInformation.selectMediaItems(mediaGallery.getMediaItems());
    }

}
