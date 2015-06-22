package com.nbcuni.test.publisher.Components;

import com.nbcuni.test.publisher.common.Driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.io.File;
import java.util.List;

/**
 * Created by kiryl_zayets on 6/21/15.
 */

@FindBy(css = "div#media-browser-tabset")
public class MediaItemsFrame extends HtmlElement {
    public Driver webDriver;

    @FindBy(css = "div.plupload.html5")
    HtmlElement parentOfFile;

    @FindBy(xpath = "//input[@type='file']")
    TextInput fileToAttach;

    @FindBy(id = "edit-field-cover-item-und-0-browse-button")
    Button selectCover;

//    @FindBy(css = "iframe.media-modal-frame")
//    HtmlElement frame;


    public void selectCover(String coverImage) {
//        if (coverImage == null) return;
//
//        selectCover.click();
    }

    public void selectMediaItems(List<String> items) {
//        selectMediaItems.click();

//        webDriver.switchTo().frame(frame);

        ((JavascriptExecutor) webDriver).executeScript("arguments[0].style.overflow='visible';",
                parentOfFile);

        this.getClass().getClassLoader().getResource("images/brad_pitt.jpg");
        for (String item : items) {
            File file = new File(this.getClass().getClassLoader().getResource(item).getPath());
            if (file.exists()) {
                webDriver.findElement(By.xpath("//input[@type='file']")).
                        sendKeys(file.getAbsolutePath());
            }
        }
        webDriver.findElement(By.xpath("//a[@class='plupload_button plupload_start']")).click();
        webDriver.findElement(By.id("edit-next")).click();
        webDriver.switchTo().defaultContent();
    }


    public void makeFileVisible(Driver webDriver) {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].style.overflow='visible';",
                parentOfFile);
    }

    public void attachFiles(List<String> files) {
        for (String item : files) {
            File file = new File(item);
            if (file.exists()) {
                fileToAttach.sendKeys(file.getAbsolutePath());
            }
        }
    }
}
