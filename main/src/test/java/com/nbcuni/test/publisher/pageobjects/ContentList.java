package com.nbcuni.test.publisher.pageobjects;


import com.nbcuni.test.publisher.common.Driver.component.annotations.*;
import com.nbcuni.test.publisher.common.Driver.configuration.SeleniumContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

/**
 * Created by kiryl_zayets on 6/24/15.
 */

@Configuration
@Scope("prototype")
@com.nbcuni.test.publisher.common.Driver.component.annotations.Page
public class ContentList extends Page {


    public ContentList() {

    } public ContentList(WebDriver webDriver) {

    }

    @Autowired
    SeleniumContext context;


    @PostConstruct
    public void init() {
        webDriver = context.webDriver();

    }

    public void openCreatedContentPattern(String title) {
        webDriver.findElement(By.cssSelector(String.format("a[href*='%s']:not([class]", title.replace('.', '-')))).click();
    }
}
