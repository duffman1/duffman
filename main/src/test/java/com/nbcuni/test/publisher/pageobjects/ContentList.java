package com.nbcuni.test.publisher.pageobjects;

import com.nbcuni.test.publisher.common.Driver.Driver;
import org.openqa.selenium.By;

/**
 * Created by kiryl_zayets on 6/24/15.
 */
public class ContentList extends Page {
    public ContentList(Driver webDriver) {
        super(webDriver);
    }

    public void openCreatedContentPattern(String title) {
        webDriver.findElement(By.cssSelector(String.format("a[href*='%s']:not([class]", title.replace('.', '-')))).click();
    }
}
