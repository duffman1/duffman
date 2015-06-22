package com.nbcuni.test.publisher.pageobjects.Structure.ContentType.MediaGallery;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.CheckBox;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by kiryl_zayets on 6/18/15.
 */

@Name("Allowed URI Schemas")
@FindBy(id = "edit-instance-widget-settings-allowed-schemes")
public class AllowedURISchemas extends HtmlElement {

    @FindBy(id = "edit-instance-widget-settings-allowed-schemes-s3")
    CheckBox amazons3;

    @FindBy(id = "edit-instance-widget-settings-allowed-schemes-public")
    CheckBox publicFiles;


    public void checkAmazons3() {
        amazons3.select();
    }

    public void checkPubFiles() {
        publicFiles.select();
    }
}
