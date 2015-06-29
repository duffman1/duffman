package com.nbcuni.test.publisher.pageobjects;

import com.nbcuni.test.publisher.SiteMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

import java.lang.reflect.InvocationTargetException;


/**
 * Created by kiryl_zayets on 6/17/15.
 */
public class Page {
    protected org.openqa.selenium.WebDriver webWebDriver;

    @Autowired
    protected SiteMap siteMap;

    @Autowired
    public Page(WebDriver webWebDriver) {
        this.webWebDriver = webWebDriver;

        PageFactory.initElements(new HtmlElementDecorator(webWebDriver), this);
    }

    public org.openqa.selenium.WebDriver getWebDriver() {
        return webWebDriver;
    }



    public <T extends Page> T navigate(String url, Class<T> clazz) {
        T instance = null;
        webWebDriver.get(url);
        try {
            instance = clazz.getDeclaredConstructor(WebDriver.class).newInstance(webWebDriver);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return instance;
    }

    ;

    public void waitVisibilityOfElement(HtmlElement element) {
        new WebDriverWait(webWebDriver, 8).until(ExpectedConditions.visibilityOf(element));
    }

    public <T extends WebElement> void waitVisibilityOfElement(int seconds, T element) {
        new WebDriverWait(webWebDriver, seconds).until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementIsInvisible(int timeOutSec, By by) {
        new WebDriverWait(webWebDriver, timeOutSec).until(ExpectedConditions
                .invisibilityOfElementLocated(by));
    }


    public UserLogin navigateLoginPage() {
        webWebDriver.get(siteMap.getBaseUrl());
        return new UserLogin(webWebDriver);
    }

}
