package com.nbcuni.test.publisher.pageobjects.Structure.ContentType.MediaGallery;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.CheckBox;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by kiryl_zayets on 6/18/15.
 */

@Name("Browser plugins")
@FindBy(id = "edit-instance-widget-settings-browser-plugins")
public class MediaBrowserPlugins extends HtmlElement {

    @FindBy(id="edit-instance-widget-settings-browser-plugins-upload")
    CheckBox upload;

    @FindBy(id="edit-instance-widget-settings-browser-plugins-publisher-media-browser-media-browser-1")
    CheckBox viewLibrary;

    @FindBy(id="edit-instance-widget-settings-browser-plugins-media-internet")
    CheckBox web;


    public void checkUpload() {
        upload.select();
    }

    public void checkViewLibrary(){
        viewLibrary.select();
    }

    public void checkWeb(){
        web.select();
    }

}
