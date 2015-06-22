package com.nbcuni.test.publisher.pageobjects.Structure.ManageFields;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by kiryl_zayets on 6/22/15.
 */

@FindBy(css = "ul.vertical-tabs-list")
public class VerticalTabs extends HtmlElement {

    @FindBy(xpath = "//strong[contains(., 'Representative image')]")
    private HtmlElement repImage;

    public void selectRepresentativeTab(){
        repImage.click();
    }


}
