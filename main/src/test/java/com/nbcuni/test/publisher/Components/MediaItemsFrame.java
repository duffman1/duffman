package com.nbcuni.test.publisher.Components;

import com.nbcuni.test.publisher.common.Driver.Driver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.io.File;
import java.util.List;

/**
 * Created by kiryl_zayets on 6/21/15.
 */

@FindBy(css = "iframe.media-modal-frame")
public class MediaItemsFrame extends HtmlElement {

    @FindBy(css = "div.plupload.html5")
    HtmlElement parentOfFile;

    @FindBy(xpath = "//input[@type='file']")
    TextInput fileToAttach;


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
