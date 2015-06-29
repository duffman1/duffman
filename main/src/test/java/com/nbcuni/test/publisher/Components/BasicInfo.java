package com.nbcuni.test.publisher.Components;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.io.File;
import java.util.List;

/**
 * Created by kiryl_zayets on 6/21/15.
 */

@FindBy(id = "edit-group_required_information")
public class BasicInfo extends HtmlElement {
    public WebDriver webWebWebDriver;

    @FindBy(xpath = "//input[contains(@id, 'edit-title')]")
    TextInput title;

    @FindBy(id = "edit-field-summary-und-0-value")
    TextInput shortDescription;

    @FindBy(id = "tinymce")
    TextInput body;

    @FindBy(id = "edit-field-cover-item-und-0-browse-button")
    Button selectCover;

    @FindBy(xpath = "//a[@id='edit-field-media-items-und-0-browse-button']")
    Button selectMediaItems;

    @FindBy(id = "edit-body-und-0-value_ifr")
    HtmlElement bodyFrame;

    public void setTitle(String title) {
        this.title.sendKeys(title);
    }

    public void setShortDescription(String description) {
        this.shortDescription.sendKeys(description);
    }

    public void setBody(String body) {
        webWebWebDriver.switchTo().frame(bodyFrame);
        webWebWebDriver.findElement(By.id("tinymce")).sendKeys(body);
        webWebWebDriver.switchTo().defaultContent();
    }


    public void selectCover(String coverImage) {
        if (coverImage == null) return;
        selectCover.click();

    }

    public void selectMediaItems(List<String> items) {
        selectMediaItems.click();
        webWebWebDriver.switchTo().frame(webWebWebDriver.findElement(By.cssSelector("iframe.media-modal-frame")));

        ((JavascriptExecutor) webWebWebDriver).executeScript("arguments[0].style.overflow='visible';",
                webWebWebDriver.findElement(By.cssSelector("div.plupload.html5")));

        this.getClass().getClassLoader().getResource("images/brad_pitt.jpg");
        for (String item : items) {
            File file = new File(this.getClass().getClassLoader().getResource(item).getPath());
            if (file.exists()) {
                webWebWebDriver.findElement(By.xpath("//input[@type='file']")).
                        sendKeys(file.getAbsolutePath());
            }
        }
        webWebWebDriver.findElement(By.xpath("//a[@class='plupload_button plupload_start']")).click();
        webWebWebDriver.findElement(By.id("edit-next")).click();
        webWebWebDriver.switchTo().defaultContent();
    }
}
