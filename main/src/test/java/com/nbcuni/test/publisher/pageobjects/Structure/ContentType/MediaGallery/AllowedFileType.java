package com.nbcuni.test.publisher.pageobjects.Structure.ContentType.MediaGallery;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.CheckBox;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by kiryl_zayets on 6/22/15.
 */


@FindBy(id="edit-instance-widget-settings-allowed-types")
public class AllowedFileType extends HtmlElement {


    @FindBy(id="edit-instance-widget-settings-allowed-types-image")
    private CheckBox image;


    public void selectImage(){
        image.select();
    }
}
