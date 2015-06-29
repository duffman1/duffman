package com.nbcuni.test.publisher.pageobjects.Configuration;

import com.nbcuni.test.publisher.pageobjects.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by kiryl_zayets on 6/17/15.
 */
public class ConfigPreferences extends Page {

    public ConfigPreferences(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(css = "div.body a[href$='amazons3']")
    private WebElement amazon;


    public Amazons3 goToAmazonSettings() {
        amazon.click();
        return new Amazons3(this.webWebDriver);
    }
}
