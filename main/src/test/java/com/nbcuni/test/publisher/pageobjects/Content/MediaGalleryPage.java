package com.nbcuni.test.publisher.pageobjects.Content;


import com.nbcuni.test.publisher.Components.BasicInfo;
import com.nbcuni.test.publisher.Components.MediaItemsFrame;
import com.nbcuni.test.publisher.bo.MediaGallery;
import com.nbcuni.test.publisher.pageobjects.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by kiryl_zayets on 6/21/15.
 */
public class MediaGalleryPage extends Page {

    BasicInfo basicInformation;
    MediaItemsFrame mediaItemsFrame;
    public MediaGalleryPage(WebDriver webWebWebDriver) {
        super(webWebWebDriver);
        basicInformation.webWebWebDriver = webWebWebDriver;
        mediaItemsFrame.webWebWebDriver = webWebWebDriver;
    }

    public void fillBasicInfo(MediaGallery mediaGallery) {
        basicInformation.setTitle(mediaGallery.getTitle());
        basicInformation.setShortDescription(mediaGallery.getShortDescription());
        basicInformation.setBody(mediaGallery.getBody());
        webWebDriver.findElement(By.id("edit-field-cover-item-und-0-browse-button")).click();
//        webDriver.switchTo().frame(webDriver.findElement(By.cssSelector("iframe.media-modal-frame")));

        mediaItemsFrame.selectCover(mediaGallery.getCoverItem());
        mediaItemsFrame.selectMediaItems(mediaGallery.getMediaItems());

//        basicInformation.selectCover(mediaGallery.getCoverItem());
//        basicInformation.selectMediaItems(mediaGallery.getMediaItems());
    }

}
