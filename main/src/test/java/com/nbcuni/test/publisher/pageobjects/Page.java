package com.nbcuni.test.publisher.pageobjects;

import com.nbcuni.test.publisher.common.Driver.Driver;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;


/**
 * Created by kiryl_zayets on 6/17/15.
 */
public class Page {
    protected Driver webDriver;

    public Page(Driver webDriver){
        this.webDriver = webDriver;

        PageFactory.initElements(new HtmlElementDecorator(webDriver), this);
    }

}
