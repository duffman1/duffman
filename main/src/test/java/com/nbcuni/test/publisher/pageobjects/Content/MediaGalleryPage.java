package com.nbcuni.test.publisher.pageobjects.Content;


import com.nbcuni.test.publisher.Components.BasicInfo;
import com.nbcuni.test.publisher.Components.MediaItemsFrame;
import com.nbcuni.test.publisher.bo.MediaGallery;
import com.nbcuni.test.publisher.common.Driver.component.annotations.Page;
import com.nbcuni.test.publisher.common.Driver.configuration.SeleniumContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

/**
 * Created by kiryl_zayets on 6/21/15.
 */
@Configuration
@Scope("prototype")
@Page
public class MediaGalleryPage {

    WebDriver webWebDriver;
    BasicInfo basicInformation;
    MediaItemsFrame mediaItemsFrame;

    public MediaGalleryPage(WebDriver webWebWebDriver) {
        basicInformation.webWebWebDriver = webWebWebDriver;
        mediaItemsFrame.webWebWebDriver = webWebWebDriver;
    }


    @Autowired
    SeleniumContext context;

    public MediaGalleryPage() {};

    @PostConstruct
    public void init() {
        webWebDriver = context.webDriver();
    }

    public void fillBasicInfo(MediaGallery mediaGallery) {
        basicInformation.setTitle(mediaGallery.getTitle());
        basicInformation.setShortDescription(mediaGallery.getShortDescription());
        basicInformation.setBody(mediaGallery.getBody());
        webWebDriver.findElement(By.id("edit-field-cover-item-und-0-browse-button")).click();

        mediaItemsFrame.selectCover(mediaGallery.getCoverItem());
        mediaItemsFrame.selectMediaItems(mediaGallery.getMediaItems());
    }

}
