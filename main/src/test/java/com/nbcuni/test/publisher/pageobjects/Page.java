package com.nbcuni.test.publisher.pageobjects;

import com.nbcuni.test.publisher.SiteMap;
import com.nbcuni.test.publisher.common.Driver.Driver;
import org.openqa.selenium.By;
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
    protected Driver webDriver;

    @Autowired
    protected SiteMap siteMap;

    public Page(Driver webDriver) {
        this.webDriver = webDriver;

        PageFactory.initElements(new HtmlElementDecorator(webDriver), this);
    }

    public Driver getWebDriver() {
        return webDriver;
    }

    public <T extends Page> T navigate(String url, Class<T> clazz) {
        T instance = null;
        webDriver.get(url);
        try {
            instance = clazz.getDeclaredConstructor(Driver.class).newInstance(webDriver);
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
        new WebDriverWait(webDriver, 8).until(ExpectedConditions.visibilityOf(element));
    }

    public <T extends WebElement> void waitVisibilityOfElement(int seconds, T element) {
        new WebDriverWait(webDriver, seconds).until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementIsInvisible(int timeOutSec, By by) {
        new WebDriverWait(webDriver, timeOutSec).until(ExpectedConditions
                .invisibilityOfElementLocated(by));
    }


    public UserLogin navigateLoginPage() {
        webDriver.get(siteMap.getBaseUrl());
        return new UserLogin(webDriver);
    }

}
