package com.nbcuni.test.publisher.common.Driver.listeners;

import org.openqa.selenium.WebDriver;
import org.springframework.context.support.SimpleThreadScope;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

/**
 * Created by kiryl_zayets on 6/29/15.
 */
public class SeleniumTestListener extends AbstractTestExecutionListener {

    @Override
    public void beforeTestClass(TestContext testContext) {
        SimpleThreadScope simpleThreadScope = (SimpleThreadScope) testContext.getApplicationContext().getBean("simpleThreadScope");
        simpleThreadScope.remove("webDriver");

    }

    @Override
    public void afterTestClass(TestContext testContext) {
        testContext.getApplicationContext().getBean(WebDriver.class).quit();
    }

}
