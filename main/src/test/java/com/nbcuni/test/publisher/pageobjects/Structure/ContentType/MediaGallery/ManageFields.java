package com.nbcuni.test.publisher.pageobjects.Structure.ContentType.MediaGallery;

import com.nbcuni.test.publisher.common.Driver.component.annotations.Page;
import com.nbcuni.test.publisher.common.Driver.configuration.SeleniumContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.CheckBox;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

import javax.annotation.PostConstruct;
import java.io.File;

/**
 * Created by kiryl_zayets on 6/18/15.
 */

@Configuration
@Scope("prototype")
@Page
public class ManageFields {

    private MediaBrowserPlugins mediaBrowserPlugins;
    private AllowedURISchemas allowedURISchemas;
    private AllowedFileType allowedFileType;


    @FindBy(id = "edit-submit")
    private HtmlElement saveSettings;

    @FindBy(id = "edit-field-settings-uri-scheme-s3")
    private CheckBox amazons3;

    @FindBy(id = "edit-instance-required")
    private CheckBox requiredFields;

    @FindBy(id = "edit-field-settings-uri-scheme-s3")
    private CheckBox uploadDestination;

    @FindBy(id = "edit-field-settings-amazons3-bucket")
    private TextInput bucketName;

    @FindBy(css = "input[type='file']")
    private TextInput file;

    @FindBy(id = "edit-upload-upload-button")
    private Button upload;

    @FindBy(id = "edit-upload-remove-button")
    private HtmlElement remove;

    @FindBy(id = "edit-next")
    private Button next;

    @FindBy(css = "iframe.media-modal-frame")
    private HtmlElement frame;

    @Autowired
    SeleniumContext context;
    WebDriver webDriver;

    public ManageFields() {
    }

    ;

    @PostConstruct
    public void init() {
        webDriver = context.webDriver();

    }


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

    public ManageFields checkDestinationS3(String bucketName) {
        amazons3.select();
        this.bucketName.sendKeys(bucketName);
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

    public ManageFields attachImage(String path) {
        File file1 = new File(this.getClass().getClassLoader().getResource(path).getPath());
        if (file1.exists()) {
            file.sendKeys(file1.getAbsolutePath());
            upload.click();
        }
        new WebDriverWait(webDriver, 8).until(ExpectedConditions.visibilityOf(remove));

        return this;
    }

    public ManageFields attachImageFrame(String path) {
        webDriver.switchTo().frame(frame);
        attachImage(path);
        return this;
    }

    public ManageFields save() {
        new WebDriverWait(webDriver, 8).until(ExpectedConditions.visibilityOf(saveSettings));
        saveSettings.click();
        return this;
    }

    public ManageFields next() {
        next.click();
        return this;
    }
}
