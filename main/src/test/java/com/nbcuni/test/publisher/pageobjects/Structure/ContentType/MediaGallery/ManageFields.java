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
    private AllowedFileType allowedFileType;


    @FindBy(id = "edit-submit")
    private Button saveSettings;

    @FindBy(id = "edit-field-settings-uri-scheme-s3")
    private CheckBox amazons3;

    @FindBy(id = "edit-instance-required")
    private CheckBox requiredFields;

    @FindBy(id = "edit-field-settings-uri-scheme-s3")
    private CheckBox uploadDestination;


    public ManageFields checkS3boxes() {
        mediaBrowserPlugins.checkUpload();
        mediaBrowserPlugins.checkViewLibrary();
        mediaBrowserPlugins.checkWeb();
        return this;
    }

    public ManageFields checkSchemas() {
        allowedURISchemas.checkPubFiles();
        allowedURISchemas.checkAmazons3();
        return this;
    }

    public ManageFields checkDestinationS3() {
        amazons3.select();
        return this;
    }

    public ManageFields checkRequiredField() {
        requiredFields.select();
        return this;
    }

    public ManageFields checkAllowedFileTypes() {
        allowedFileType.selectImage();
        return this;
    }

    public ManageFields save() {
        saveSettings.click();
        return this;
    }


}
