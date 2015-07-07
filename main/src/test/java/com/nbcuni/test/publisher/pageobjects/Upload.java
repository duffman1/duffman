package com.nbcuni.test.publisher.pageobjects;

import com.nbcuni.test.publisher.bo.Metadata;
import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.component.annotations.*;
import com.nbcuni.test.publisher.common.Driver.configuration.SeleniumContext;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

/**
 * Created by kiryl_zayets on 6/25/15.
 */
@Configuration
@Scope("prototype")
@com.nbcuni.test.publisher.common.Driver.component.annotations.Page
public class Upload extends Page {

    public Upload() {
    }

     @Autowired
     SeleniumContext context;


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
