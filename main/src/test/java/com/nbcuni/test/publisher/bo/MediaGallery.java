package com.nbcuni.test.publisher.bo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kiryl_zayets on 6/21/15.
 */
public class MediaGallery {
    private String title;
    private String shortDescription;
    private String body;
    private List<String> mediaItems;
    private String coverTitle;

    public MediaGallery(String title, String shortDescription, String body, List<String> mediaItems, String coverTitle) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.body = body;
        this.mediaItems = mediaItems;
        this.coverTitle = coverTitle;
    }

    public List<String> getMediaItems() {
        return mediaItems;
    }

    public void setMediaItems(ArrayList<String> mediaItems) {
        this.mediaItems = mediaItems;
    }

    public String getCoverItem() {
        return coverTitle;
    }

    public void setCoverItem(String coverItem) {
        this.coverTitle = coverItem;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
