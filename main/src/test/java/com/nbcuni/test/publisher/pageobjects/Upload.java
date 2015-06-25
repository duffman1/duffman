package com.nbcuni.test.publisher.pageobjects;

import com.nbcuni.test.publisher.bo.Metadata;
import com.nbcuni.test.publisher.common.Driver.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by kiryl_zayets on 6/25/15.
 */
public class Upload extends Page {

    public Upload(Driver webDriver) {
        super(webDriver);

    }

    @FindBy(id = "mediaBrowser")
    private WebElement frame;

    @FindBy(id = "edit-field-file-image-title-text-und-0-value")
    private WebElement title;

    @FindBy(id = "edit-field-file-image-alt-text-und-0-value")
    private WebElement alt;

    @FindBy(id = "edit-field-source-und-0-value")
    private WebElement source;

    @FindBy(id = "edit-field-credit-und-0-value")
    private WebElement credit;

    @FindBy(id = "edit-field-copyright-und-0-value")
    private WebElement copyright;

    @FindBy(id = "edit-field-keywords-und-0-value")
    private WebElement keywords;

    @FindBy(id = "edit-field-media-tags-und")
    private WebElement mediaItems;

    @FindBy(id = "edit-submit")
    private WebElement save;


    public Metadata getMetadata() {
//        webDriver.switchTo().frame(frame);
        return new Metadata(title.getText(),
                alt.getText(),
                source.getText(),
                credit.getText(),
                copyright.getText(),
                keywords.getText(),
                mediaItems.getText());
    }

}
