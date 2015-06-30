package com.nbcuni.test.publisher.tests.S3;

import com.nbcuni.test.publisher.common.Driver.bpp.SeleniumContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

/**
 * Created by kiryl_zayets on 6/28/15.
 */
@ContextConfiguration(locations = {"classpath:entry.xml"})
public class AbstractCustomContext extends AbstractTestNGSpringContextTests {

    public static String browser;

    @Parameters("browser")
    @BeforeTest
    public void getXMLParams(String browser, ITestContext iTestContext){
        this.browser = browser;

    }

    public String getBrowser() {
        return browser;
    }

}
