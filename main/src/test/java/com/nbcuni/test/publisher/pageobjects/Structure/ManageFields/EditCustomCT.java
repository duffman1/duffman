package com.nbcuni.test.publisher.pageobjects.Structure.ManageFields;

import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.pageobjects.Page;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.Link;
import ru.yandex.qatools.htmlelements.element.Select;

/**
 * Created by kiryl_zayets on 6/22/15.
 */
public class EditCustomCT extends Page {
    public EditCustomCT(Driver webDriver) {
        super(webDriver);
    }


    VerticalTabs verticalTabs;


    @FindBy(css = "select[id^='edit-representative-image-node']")
    private Select firstBundle;


    @FindBy(css = "input#edit-submit")
    private Button saveContentType;


     @FindBy(css = "div#tab-bar a[href*='manage']")
     private Link editContentType;

    public void selectImageForBundle(String value) {
        editContentType.click();
        verticalTabs.selectRepresentativeTab();
        firstBundle.selectByValue("field_" + value.replace('.','_'));
        saveContentType.click();
    }

}
