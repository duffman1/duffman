package com.nbcuni.test.publisher.pageobjects.Structure.ContentType.MediaGallery;

import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.pageobjects.Page;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.CheckBox;

/**
 * Created by kiryl_zayets on 6/18/15.
 */
public class ManageFields extends Page {

    public ManageFields(Driver webDriver) {
        super(webDriver);
    }

    private MediaBrowserPlugins mediaBrowserPlugins;
    private AllowedURISchemas allowedURISchemas;

    @FindBy(id="edit-submit")
    private Button saveSettings;

    @FindBy(id="edit-field-settings-uri-scheme-s3")
    private CheckBox amazons3;

    public void checkS3boxes(){
        mediaBrowserPlugins.checkUpload();
        mediaBrowserPlugins.checkViewLibrary();
        mediaBrowserPlugins.checkWeb();
    }

    public void checkSchemas(){
        allowedURISchemas.checkPubFiles();
        allowedURISchemas.checkAmazons3();
    }

    public void checkDestinationS3(){
        amazons3.select();
    }

    public void save(){
        saveSettings.click();
    }

}
