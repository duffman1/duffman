package com.nbcuni.test.publisher.pageobjects;

import com.nbcuni.test.publisher.SiteMap;
import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.configuration.SeleniumContext;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import com.nbcuni.test.publisher.pageobjects.ErrorChecking.ErrorChecking;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;


/**
 * Created by kiryl_zayets on 6/17/15.
 */


public class Page {

    protected WebDriver webDriver;
    protected ErrorChecking errorChecking;
    protected Config config;
    protected WaitFor waitFor;
    protected Interact interact;
    protected Integer timeout;
    protected WebDriverWait wait;
    protected Actions actions;

    @Autowired
    protected SiteMap siteMap;

    @Autowired
    SeleniumContext context;


    @PostConstruct
    public void init() {
        webDriver = context.webDriver();
        config = new Config();
        errorChecking = new ErrorChecking(webDriver);
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webDriver, timeout);
        interact = new Interact(webDriver, timeout);
        wait = new WebDriverWait(webDriver, timeout);
        actions = new Actions(webDriver);
    }


    //    @Autowired
    public Page() {

//        PageFactory.initElements(new HtmlElementDecorator(webWebDriver), this);
    }

    public org.openqa.selenium.WebDriver getWebDriver() {
        return webDriver;
    }


    public <T> T navigate(String url, Class<T> clazz) {
        T instance = null;
        webDriver.get(url);
        try {
            instance = clazz.getDeclaredConstructor(WebDriver.class).newInstance(webDriver);
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
