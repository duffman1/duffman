package com.nbcuni.test.publisher.pageobjects.Configuration;

import com.nbcuni.test.publisher.common.Driver.component.annotations.Page;
import com.nbcuni.test.publisher.common.Driver.configuration.SeleniumContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

/**
 * Created by kiryl_zayets on 6/17/15.
 */
@Configuration
@Scope("prototype")
@Page
public class ConfigPreferences {

    public ConfigPreferences(WebDriver webDriver) {

    }
    public ConfigPreferences() {

    }

    @Autowired
    SeleniumContext context;


    @FindBy(css = "div.body a[href$='amazons3']")
    private WebElement amazon;


    public Amazons3 goToAmazonSettings() {
        amazon.click();
        return new Amazons3();
    }
}
