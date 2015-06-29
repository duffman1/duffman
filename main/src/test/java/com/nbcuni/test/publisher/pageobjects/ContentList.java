package com.nbcuni.test.publisher.pageobjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by kiryl_zayets on 6/24/15.
 */
public class ContentList extends Page {
    public ContentList(WebDriver webWebWebDriver) {
        super(webWebWebDriver);
    }

    public void openCreatedContentPattern(String title) {
        webWebDriver.findElement(By.cssSelector(String.format("a[href*='%s']:not([class]", title.replace('.', '-')))).click();
    }
}
